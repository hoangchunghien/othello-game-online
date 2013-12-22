package othello.command.notify;

import othello.common.Piece;

/**
 *
 * @author Hien Hoang
 * @since Dec 11, 2013
 * @version Dec 11, 2013
 * Description
 * . TODO
 */
public interface MoveTurnNtfExec {
    public void fireMoveTurnNotify(Piece piece);
}
