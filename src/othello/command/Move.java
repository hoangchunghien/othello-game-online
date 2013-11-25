package othello.command;

import othello.game.GameMonitor;
import othello.common.Position;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class Move implements ICommand {

    GameMonitor monitor;
    Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
       
    public Move(GameMonitor monitor) {
        
        this.monitor = monitor;
        
    }
    
    public Move(GameMonitor monitor, Position position) {
        
        this.monitor = monitor;
        this.position = position;
    }
    
    @Override
    public void execute() {
        
        monitor.makeMove(position);
        
    }
    
}
