package othello.command.response;

import othello.common.AbstractPlayer;

/**
 *
 * @author Hien Hoang
 */
public interface JoinPlayerResExecutable {
    public void joinAccepted(String playerTicket);
    public void joinRejected(String message);
}
