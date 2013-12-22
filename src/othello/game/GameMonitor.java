package othello.game;

import othello.common.Position;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

import othello.command.AnswerRequestCmd;
import othello.command.IMoveCmdExec;
import othello.command.IRedoCmdExec;
import othello.command.IUndoCmdExec;
import othello.command.notify.GameOverNtf;
import othello.command.notify.IGameOverNtfExec;
import othello.command.notify.IPassNtfExec;
import othello.command.notify.PassNtf;
import othello.command.response.AnswerRequestResExec;
import othello.command.response.IMoveResExec;
import othello.command.response.MoveRes;
import othello.command.response.UndoRes;
import othello.common.AbstractPlayer;
import othello.common.Board;
import othello.common.Piece;
import othello.ui.UIFactory;
import othello.ui.control.AbstractControlUI;

/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class GameMonitor implements Notifiable, IMoveCmdExec, IUndoCmdExec, IRedoCmdExec, AnswerRequestResExec {
    
    public static final Integer WAIT_RES_UNDO = 1;
    public static final Integer WAIT_RES_REDO = 2;
    public static final Integer WAIT_RES_DRAW = 3;
    
    protected GameTimer gameTimer = new GameTimer();
    protected GameState state; // The current game state
    protected int turn;
    protected Dictionary<AbstractPlayer, Boolean> isReady;
    protected List<AbstractPlayer> viewers;
    
    // Store the game record
    protected List<Position> gameRecords;
    protected Stack undoState;
    protected Stack redoState;
    protected Dictionary<Position, GameState> recordStates;
    protected HashMap<Integer, Queue> waitingResList;
    protected NotificationBoard nb = NotificationBoard.getInstance();
    protected boolean isGameReady = false;
    protected boolean isTerminated = false;
    
    public GameMonitor() {
        
    	this.initialize();
        nb.subscribe(this, NotificationBoard.NF_TIMEOUT);
    }
    
    public GameMonitor(NotificationBoard nb, GameTimer timer) {
    	this.nb = nb;
    	this.gameTimer = timer;
    	this.initialize();
    	nb.subscribe(this, NotificationBoard.NF_TIMEOUT);
    }
    
    protected void initialize() {
    	
        isReady = new Hashtable<>();
        viewers = new ArrayList<>();
        state = new GameState();
        gameRecords = new ArrayList<Position>();
        undoState = new Stack();
        redoState = new Stack();
        recordStates = new Hashtable<>();
        waitingResList = new HashMap<>();
    }
    
    public GameState getState() {
        return this.state;
    }
    
    public GameState getGameStateCopy() {
        
        return this.state.clone();
    }
    
    public boolean isGameOver() {
        
        return state.getBoard().isGameOver();
    }
    
    public void start() {
        
    	nb.fireChangeNotification(NotificationBoard.NF_GAME_STARTING, null);
    	
        if (!isGameReady) {
            System.out.println("Waiting other player to ready...");
        }
        else {
            System.out.println("All player is ready to play, game started.");
            System.out.println("Turn: " + state.getCurrentPlayer().getName());
            nb.fireChangeNotification(NotificationBoard.NF_GAMESTATE_CHANGED, state);
            nb.fireChangeNotification(NotificationBoard.NF_MOVE_TURN, state.getCurrentPlayer());
        }
    }
    
    public void addPlayer(AbstractPlayer player) {
        
        if (state.players[0] == null) {
            
            state.players[0] = player;
            System.out.println(player.getName() + " joined first player.");
            isReady.put(player, Boolean.FALSE);
            gameTimer.registerTimer(player);
            state.setCurrentPlayer(player);
            player.setPiece(Piece.BLACK);
        } 
        else if (state.players[1] == null) {
            
            state.players[1] = player;
            System.out.println(player.getName() + " joined second player.");
            isReady.put(player, Boolean.FALSE);
            gameTimer.registerTimer(player);
            player.setPiece(Piece.WHITE);
        }
        else {

            viewers.add(player);
            System.out.println("Player list already full, joined viewer list");
        }
        
        nb.subscribe(player, NotificationBoard.NF_GAMESTATE_CHANGED);
        nb.subscribe(player, NotificationBoard.NF_MOVE_TURN);
    }
    
    public void setReady(AbstractPlayer player) {
        
        System.out.println("Player " + player + " ready.");
        this.isReady.put(player, Boolean.TRUE);
        
        if (isReady.size() >= 2 && isReady.get(state.players[0]) != Boolean.FALSE && 
                isReady.get(state.players[1]) != Boolean.FALSE) {
            this.isGameReady = true;
        }
    }
    
    public void restart() {
        
        this.start();
    }
       
    private void addRecord(Position p, GameState state) {
        
        undoState.push(state.clone());
        gameRecords.add(p);
        recordStates.put(p, state.clone());
        
        // Allow undo
        nb.fireChangeNotification(NotificationBoard.NF_UNDOABLE, Boolean.TRUE);
    }
     
    @Override
    public void makeMove(Position position, AbstractPlayer caller) {
        
    	System.out.println("Request monitor make move !!!");
    	
        if (isTerminated) {
            //return;
        }
        
        
        Piece currentPiece = state.getCurrentPlayer().getPiece();
        Board currentBoard = state.getBoard();

        if (!state.getBoard().isValidMove(currentPiece, position)) {

            MoveRes moveResponse = new MoveRes((IMoveResExec)caller, 
                    MoveRes.REJECTED, "INVALID MOVE!!! TRY OTHER.", position);
            moveResponse.execute();
            
            // Let player re get move
            nb.fireChangeNotification(NotificationBoard.NF_MOVE_TURN, state.getCurrentPlayer());
        }
        else {

            addRecord(position, state);

            // First make move
            currentBoard.placePiece(currentPiece, position);

            // Then change turn and calculate score
            turn = 1 - turn;
            state.setCurrentPlayer(state.getPlayers()[turn]);
            state.calculateScore();        
            
            // Unallow redo
            this.redoState.removeAllElements();
            nb.fireChangeNotification(NotificationBoard.NF_REDOABLE, Boolean.FALSE);
        
            // Notify state changed
            nb.fireChangeNotification(NotificationBoard.NF_GAMESTATE_CHANGED, state);
            
            MoveRes moveResponse = new MoveRes((IMoveResExec)caller, MoveRes.ACCEPTED, "OK", position);
            moveResponse.execute();
            
            // After made a move, check the board again for gameover or next player 
            // has any valid move ...
            if (currentBoard.hasAnyValidMove(state.getCurrentPlayer().getPiece())) {

                nb.fireChangeNotification(NotificationBoard.NF_MOVE_TURN, state.getCurrentPlayer());
            }
            else if (currentBoard.isGameOver()) {

                // Notify game over to all players
                for (int i = 0; i < 2; i++) {
                    GameOverNtf gameOverNotify = 
                            new GameOverNtf((IGameOverNtfExec)state.getPlayers()[i]);
                    gameOverNotify.execute();
                 }
            }
            else { 
                //Next player has no valid move change turn again if next player
                //has any valid move. Otherwise, terminate the game

                turn = 1 - turn;
                state.setCurrentPlayer(state.getPlayers()[turn]);

                if (state.getBoard().hasAnyValidMove(state.getCurrentPlayer().getPiece())) {

                    // Notify to the player that pass the current turn
                    PassNtf passNotify = new PassNtf((IPassNtfExec)state.getPlayers()[1-turn]);
                    passNotify.execute();

                    // Tell the current player to make move
                    nb.fireChangeNotification(NotificationBoard.NF_MOVE_TURN, state.getCurrentPlayer());

                }
                else {

                    nb.fireChangeNotification(NotificationBoard.NF_GAMEOVER, this);
                }

            }

        }
        
    }
    

    @Override
    public void makeUndo(AbstractPlayer caller) {
        
        if (waitingResList.get(WAIT_RES_UNDO) == null) {
            waitingResList.put(WAIT_RES_UNDO, new ConcurrentLinkedQueue());
        }   
        Queue waitingQueue = waitingResList.get(WAIT_RES_UNDO);
        waitingQueue.add(caller);
        
    }

    @Override
    public void makeRedo() {
        AbstractControlUI controlUI = UIFactory.getControlUI();
        
        if (!redoState.isEmpty()) {
            
//            do {
//                this.undoState.push(this.state.clone());  // Make a copy of this state and store at undo
//                this.state = (GameState) redoState.pop();  // Set this state to redo
//                this.turn = (this.state.getCurrentPlayer() == this.state.getPlayers()[0])?0:1;
//            } while (this.state.getCurrentPlayer().isComputerPlayer() && !redoState.isEmpty());
            
            controlUI.notifyMessage("Redo OK");
        }
        else {
            
            controlUI.notifyMessage("Can't Redo");
        }
        this.start();
    }
    

    @Override
    public void requestAccepted(int reqType, AbstractPlayer respondent) {
        
        Queue waiting = waitingResList.get(reqType);
        if (waiting == null) {
            return;
        }
        else {
            AbstractPlayer caller = (AbstractPlayer)waiting.peek();
            if (isPlayer(respondent) && respondent != caller) {
                
            }
            
        }
    }

    @Override
    public void requestRejected(int reqType, AbstractPlayer respondent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected boolean isPlayer(AbstractPlayer player) {
        for (AbstractPlayer p : this.getState().players) {
            if (player == p) {
                return true;
            }
        }
        return false;
    } 

    @Override
    public void receiveChangeNotification(int category, Object detail) {
        
        if (category == NotificationBoard.NF_TIMEOUT) {
            AbstractPlayer player = (AbstractPlayer)detail;
            
            terminateGame();
        }
    }
    
    protected void terminateGame() {
        
        isTerminated = true;
        nb.fireChangeNotification(NotificationBoard.NF_GAMEOVER, null);
    }
}
