package othello.game;

import othello.common.Position;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;
import othello.command.IMoveCmdExec;
import othello.command.IRedoCmdExec;
import othello.command.IUndoCmdExec;
import othello.command.notify.GameOverNtf;
import othello.command.notify.IGameOverNtfExec;
import othello.command.notify.IPassNtfExec;
import othello.command.notify.PassNtf;
import othello.command.response.IMoveResExec;
import othello.command.response.MoveRes;
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
public class GameMonitor implements IMoveCmdExec, IUndoCmdExec, IRedoCmdExec {
    
    protected GameState state; // The current game state
    protected int turn;
    protected List<GameStateChangedListener> stateChangedListeners;
    protected Dictionary<AbstractPlayer, Boolean> isReady;
    protected List<AbstractPlayer> viewers;
    // Store the game record
    List<Position> gameRecords;
    Stack undoState;
    Stack redoState;
    Dictionary<Position, GameState> recordStates;
    
    public GameMonitor() {
        
        stateChangedListeners = new ArrayList<>();
        isReady = new Hashtable<>();
        viewers = new ArrayList<>();
        state = new GameState();
        gameRecords = new ArrayList<Position>();
        undoState = new Stack();
        redoState = new Stack();
        recordStates = new Hashtable<>();
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
        
        if (isReady.get(state.players[0]) == Boolean.FALSE || 
                isReady.get(state.players[1]) == Boolean.FALSE) {
            System.out.println("Waiting other player to ready...");
        }
        else {
            System.out.println("All player is ready to play, game started.");
            notifyStateChanged();
            state.getCurrentPlayer().fireMoveTurn();
        }
    }
    
    public void addPlayer(AbstractPlayer player) {
        
        if (state.players[0] == null) {
            
            state.players[0] = player;
            System.out.println(player.getName() + " joined first player.");
            isReady.put(player, Boolean.FALSE);
        } 
        else if (state.players[1] == null) {
            
            state.players[1] = player;
            System.out.println(player.getName() + " joined second player.");
            isReady.put(player, Boolean.FALSE);
        }
        else {

            viewers.add(player);
            System.out.println("Player list already full, joined viewer list");
        }
        this.addGameStateChangedListener(player);
    }
    
    public void setReady(AbstractPlayer player) {
        
        System.out.println("Player " + player.getName() + " ready.");
        this.isReady.put(player, Boolean.TRUE);
        
        if (isReady.size() >= 2 && isReady.get(state.players[0]) != Boolean.FALSE && 
                isReady.get(state.players[1]) != Boolean.FALSE) {
            this.start();
        }
    }
    
    public void restart() {
        
        this.start();
    }
       
    private void addRecord(Position p, GameState state) {
        
        undoState.push(state.clone());
        gameRecords.add(p);
        recordStates.put(p, state.clone());
    }
     
    @Override
    public void makeMove(Position position, AbstractPlayer caller) {
        this.redoState.removeAllElements();

        Piece currentPiece = state.getCurrentPlayer().getPiece();
        Board currentBoard = state.getBoard();

        if (!state.getBoard().isValidMove(currentPiece, position)) {

            MoveRes moveResponse = new MoveRes((IMoveResExec)caller, 
                    MoveRes.REJECTED, "INVALID MOVE!!! TRY OTHER.", position);
            moveResponse.execute();
            
            // Let player re get move
            state.getCurrentPlayer().fireMoveTurn();
        }
        else {

            addRecord(position, state);

            // First make move
            currentBoard.placePiece(currentPiece, position);

            // Then change turn and calculate score
            turn = 1 - turn;
            state.setCurrentPlayer(state.getPlayers()[turn]);
            state.calculateScore();        
            
            // Notify state changed
            notifyStateChanged();
            
            MoveRes moveResponse = new MoveRes((IMoveResExec)caller, MoveRes.ACCEPTED, "OK", position);
            moveResponse.execute();
            
            // After made a move, check the board again for gameover or next player 
            // has any valid move ...
            if (currentBoard.hasAnyValidMove(state.getCurrentPlayer().getPiece())) {

                state.getCurrentPlayer().fireMoveTurn();
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
                    state.getCurrentPlayer().fireMoveTurn();

                }
                else {

                    // Notify game over to all players
                    for (int i = 0; i < 2; i++) {
                        GameOverNtf gameOverNotify = 
                                new GameOverNtf((IGameOverNtfExec)state.getPlayers()[i]);
                        gameOverNotify.execute();
                     }
                }

            }

        }
        
    }
    

    @Override
    public void makeUndo() {
        AbstractControlUI controlUI = UIFactory.getControlUI();

        if (!undoState.isEmpty()) {

            // Turn the undo to the last user move if play with computer
//            do {
//                this.redoState.push(this.state.clone());   // Copy this state to redo
//                this.state = (GameState) this.undoState.pop();  // Set this state to undo state
//                this.turn = (this.state.getCurrentPlayer() == this.state.getPlayers()[0])?0:1;
//            } while (!undoState.isEmpty() && this.state.getCurrentPlayer().isComputerPlayer());

            controlUI.notifyMessage("Undo OK");
        }
        else {

            controlUI.notifyMessage("Can't Undo");
        }
        this.start();
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
    
    public void addGameStateChangedListener(GameStateChangedListener listener) {
        
        this.stateChangedListeners.add(listener);
    }
    
    public void removeGameStateChangedListener(GameStateChangedListener listener) {
        
        this.stateChangedListeners.remove(listener);
    }

    private void notifyStateChanged() {
        for (GameStateChangedListener listener : stateChangedListeners) {
            
            listener.fireStateChanged(state);
        }
    }
    
}
