/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.engine;

import othello.common.*;
/**
 *
 * @author Hien Hoang
 */
public abstract class AbstractEngine {
    protected int depth = 1;
    protected Board board;
    
    public AbstractEngine(Board board) {
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
    
    
    public abstract Position getMove(Piece p);

    
}
