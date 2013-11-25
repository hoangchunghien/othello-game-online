package othello.game;

import othello.common.Position;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;
import othello.common.Board;
import othello.common.Piece;
import othello.configuration.Configuration;
import othello.engine.EngineFactory;
import othello.ui.UIFactory;
import othello.ui.control.IControl;

/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class GameMonitor {
    
    private GameState state; // The current game state
    private int turn;
    
    private Thread engine;
    
    static GameMonitor singletonObject;   
    
    public static GameMonitor getInstance() {
        if (singletonObject == null) {
            singletonObject = new GameMonitor();
        }
        return singletonObject;
    }
      
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
        engine = new Thread(EngineFactory.getEngine());
        UIFactory.getControlUI().show();
    }
    
    public GameState getGameStateCopy() {
        
        return this.state.clone();
    }
    
    public boolean isComputerTurn() {
        
        return state.getPlayers()[turn].isComputerPlayer();
    }
    
    public boolean isGameOver() {
        
        return state.getBoard().isGameOver();
    }
    
    public void start() {
        
        IControl ControlUI = UIFactory.getControlUI();
        ControlUI.renderGameState(state);
        
        // Start the engine
        engine.start();
        // Allow user or computer to play
        if (state.getCurrentPlayer().isComputerPlayer()) {
            
            ControlUI.notifyMessage("Computer thinking...");
            //EngineFactory.getEngine().allowGenerateMove(state);
            //engine.start();
        }
        else {
            
            ControlUI.notifyMessage("Your turn");
            ControlUI.allowMakeMove();
        }
    }
    
    public void restart() {
        singletonObject = new GameMonitor();
        this.start();
    }
       
    private void addRecord(Position p, GameState state) {
        
        undoState.push(state.clone());
        gameRecords.add(p);
        recordStates.put(p, state.clone());
    }
     
    public void makeMove(Position p) {
        
        this.redoState.removeAllElements();
        
        Piece currentPiece = state.getCurrentPlayer().getPiece();
        Board currentBoard = state.getBoard();
        othello.ui.control.IControl controlUI = UIFactory.getControlUI();
        
        if (!state.getBoard().isValidMove(currentPiece, p)) {
            
            controlUI.notifyMessage("INVALID MOVE!!! TRY OTHER.");
            controlUI.renderGameState(state);
            controlUI.allowMakeMove();
        }
        else {

            //
            // Make move
            //
            
            // Later add a record
            addRecord(p, state);
            
            // First make move
            currentBoard.placePiece(currentPiece, p);
            
            // Then change turn and calculate score
            turn = 1 - turn;
            state.setCurrentPlayer(state.getPlayers()[turn]);
            state.calculateScore();        
            
            // After made a move, check the board again for gameover or next player 
            // has any valid move ...
            if (currentBoard.hasAnyValidMove(state.getCurrentPlayer().getPiece())) {
                
                controlUI.renderGameState(state);
                // If Next player isn't computer, then allow ui to get move from user,
                // otherwise let engine generate a move;
                if (!state.getPlayers()[turn].isComputerPlayer()) {
                    
                    controlUI.allowMakeMove();
                }
                else {
                    
                    // Notify user computer is making a move
                    controlUI.notifyMessage("Computer thinking...");
                    
                    // Let allow engine generate a move
                    //EngineFactory.getEngine().allowGenerateMove(state);  
                    //engine.start();
                    
                }
                
            }
            else if (currentBoard.isGameOver()) {
                
                controlUI.renderGameState(state);
                controlUI.notifyMessage("No more move. Game over!");
            }
            else { 
                //Next player has no valid move change turn again if next player
                //has any valid move. Otherwise, terminate the game
                
                turn = 1 - turn;
                state.setCurrentPlayer(state.getPlayers()[turn]);
                
                if (state.getBoard().hasAnyValidMove(state.getCurrentPlayer().getPiece())) {
                  
                    controlUI.renderGameState(state);
                    controlUI.notifyMessage(state.getPlayers()[1-turn].getName() + " has no valid move. Passed!");

                    if (state.getCurrentPlayer().isComputerPlayer()) {

                        //EngineFactory.getEngine().allowGenerateMove(state);
                    } 
                    else {

                        controlUI.allowMakeMove();
                    }
                
                }
                else {
                    
                    controlUI.renderGameState(state);
                    controlUI.notifyMessage("No more move, game over!");
                }
                
            }
            
        }
        
    }
    
    public void undoGame() {
        
        IControl controlUI = UIFactory.getControlUI();
        
        if (!undoState.isEmpty()) {
            
            // Turn the undo to the last user move if play with computer
            do {
                this.redoState.push(this.state.clone());   // Copy this state to redo
                this.state = (GameState) this.undoState.pop();  // Set this state to undo state
                this.turn = (this.state.getCurrentPlayer() == this.state.getPlayers()[0])?0:1;
            } while (!undoState.isEmpty() && this.state.getCurrentPlayer().isComputerPlayer());
            
            controlUI.notifyMessage("Undo OK");
        }
        else {
            
            controlUI.notifyMessage("Can't Undo");
        }
        this.start();
        
    }
    
    public void redoGame() {
        
        IControl controlUI = UIFactory.getControlUI();
        
        if (!redoState.isEmpty()) {
            
            do {
                this.undoState.push(this.state.clone());  // Make a copy of this state and store at undo
                this.state = (GameState) redoState.pop();  // Set this state to redo
                this.turn = (this.state.getCurrentPlayer() == this.state.getPlayers()[0])?0:1;
            } while (this.state.getCurrentPlayer().isComputerPlayer() && !redoState.isEmpty());
            
            controlUI.notifyMessage("Redo OK");
        }
        else {
            
            controlUI.notifyMessage("Can't Redo");
        }
        this.start();
        
    }
    
}
