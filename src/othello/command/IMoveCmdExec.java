/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.command;

import othello.common.Position;

/**
 *
 * @author Hien
 */
public interface IMoveCmdExec {
    
    public void makeMove(Position position, othello.common.AbstractPlayer caller);
}
