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
import othello.command.notify.IStateChangedNtfExec;
import othello.command.notify.PassNtf;
import othello.command.notify.StateChangedNtf;
import othello.command.response.IMoveResExec;
import othello.command.response.MoveRes;
import othello.common.AbstractPlayer;
import othello.common.Board;
import othello.common.Piece;
import othello.configuration.Configuration;
import othello.ui.UIFactory;
import othello.ui.control.IControl;

/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class GameMonitor implements IMoveCmdExec, IUndoCmdExec, IRedoCmdExec {
    
    private GameState state; // The current game state
    private int turn;
      
    // Store the game record
    List<Position> gameRecords;
    Stack undoState;
    Stack redoState;
    Dictionary<Position, GameState> recordStates;
    
    public GameMonitor() {
        
        state = new GameState();
        turn = Configuration.getInstance().players.getFirstPlayerIndex();
        gameRecords = new ArrayList<Position>();
        undoState = new Stack();
        redoState = new Stack();
        recordStates = new Hashtable<>();
    }
    
    public GameState getGameStateCopy() {
        
        return this.state.clone();
    }
    
    public boolean isGameOver() {
        
        return state.getBoard().isGameOver();
    }
    
    public void start() {
        
        System.out.println("Loading UI Control...");
        IControl ControlUI = UIFactory.getControlUI();
        ControlUI.renderGameState(state);
        
        System.out.println("Allowing make move...");
        state.getCurrentPlayer().fireMoveTurn(state);
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
    public void makeMove(Position p, AbstractPlayer caller) {
        this.redoState.removeAllElements();

        Piece currentPiece = state.getCurrentPlayer().getPiece();
        Board currentBoard = state.getBoard();

        if (!state.getBoard().isValidMove(currentPiece, p)) {

            MoveRes moveResponse = new MoveRes((IMoveResExec)caller, 
                    MoveRes.REJECTED, "INVALID MOVE!!! TRY OTHER.", p);
            moveResponse.execute();
            
            // Let player re get move
            state.getCurrentPlayer().fireMoveTurn(state);
        }
        else {

            addRecord(p, state);

            // First make move
            currentBoard.placePiece(currentPiece, p);

            // Then change turn and calculate score
            turn = 1 - turn;
            state.setCurrentPlayer(state.getPlayers()[turn]);
            state.calculateScore();        
            
            MoveRes moveResponse = new MoveRes((IMoveResExec)caller, MoveRes.ACCEPTED, "OK", p);
            moveResponse.execute();
            
            // Notify state changed to all player
            for (int i = 0; i < 2; i++) {
                StateChangedNtf stateChangedNotify =
                        new StateChangedNtf((IStateChangedNtfExec)state.getPlayers()[i], state);
                stateChangedNotify.execute();
            }
            // After made a move, check the board again for gameover or next player 
            // has any valid move ...
            if (currentBoard.hasAnyValidMove(state.getCurrentPlayer().getPiece())) {

                state.getCurrentPlayer().fireMoveTurn(state);
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
                    state.getCurrentPlayer().fireMoveTurn(state);

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
        IControl controlUI = UIFactory.getControlUI();

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
        IControl controlUI = UIFactory.getControlUI();
        
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

    
}
