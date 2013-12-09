package othello.command.response;

import othello.common.Position;

/**
 *
 * @author Hien Hoang
 */
public interface IMoveResExec {
    
    public void processMoveAccepted();
    public void processMoveRejected(String msg);
}
