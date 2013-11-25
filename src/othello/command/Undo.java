/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.command;

import othello.game.GameMonitor;

/**
 *
 * @author Hien
 */
public class Undo implements ICommand {

    GameMonitor monitor;
    
    public Undo(GameMonitor monitor) {
        
        this.monitor = monitor;
    }
    
    @Override
    public void execute() {
        
        monitor.undoGame();
    }
    
}
