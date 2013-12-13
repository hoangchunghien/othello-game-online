package othello.command.response;

import othello.common.AbstractPlayer;

/**
 *
 * @author Hien Hoang
 */
public interface AnswerRequestResExec {
    public void requestAccepted(int reqType, AbstractPlayer caller);
    public void requestRejected(int reqType, AbstractPlayer caller);
}
