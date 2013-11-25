/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.engine;

import othello.common.*;
import othello.game.GameMonitor;
import othello.game.GameState;
/**
 *
 * @author Hien
 */
public abstract class AEngine implements Runnable {
    protected int depth = 1;
    protected Board board;
    
    public AEngine(Board board) {
        this.board = board;
    }
    
    public int getDepth() {
        return this.depth;
    }
    
    public void setDepth(int value) {
        this.depth = value;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }
    
    public Board getBoard() {
        return this.board;
    }
    
    public void allowGenerateMove(GameState state){
        
        this.board = state.getBoard();
        GameMonitor.getInstance().makeMove(getMove(state.getCurrentPlayer().getPiece()));
    }
    
    public abstract Position getMove(Piece p);

    
}
