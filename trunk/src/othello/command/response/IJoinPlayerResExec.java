package othello.command.response;

import othello.common.AbstractPlayer;

/**
 *
 * @author Hien Hoang
 */
public interface IJoinPlayerResExec {
    public void joinAccepted(AbstractPlayer player);
    public void joinRejected(String message);
}
