package othello.command.response;

import othello.common.Position;

/**
 *
 * @author Hien Hoang
 */
public interface IMoveResExec {
    
    public void processMoveAccepted(Position postion);
    public void processMoveRejected(String msg);
}
