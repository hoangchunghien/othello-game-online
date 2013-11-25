package othello.command;

import othello.game.GameMonitor;

/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class Redo implements ICommand {

    GameMonitor monitor;
    
    public Redo(GameMonitor monitor) {
        
        this.monitor = monitor;
    }
    
    @Override
    public void execute() {
        
        monitor.redoGame();
    }
    
}
