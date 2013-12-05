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
public interface IMoveExec {
    
    public void makeMove(Position position);
}
