package othello.command.notify;

import othello.common.Position;

/**
 *
 * @author Hien Hoang
 */
public interface IMoveNtfExec {
    
    public void notifyMoving(Position p);
}
