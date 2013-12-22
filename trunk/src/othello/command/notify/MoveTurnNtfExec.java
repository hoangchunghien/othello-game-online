package othello.command.notify;

import java.util.List;

import othello.common.Piece;
import othello.common.Position;

/**
 *
 * @author Hien Hoang
 * @since Dec 11, 2013
 * @version Dec 11, 2013
 * Description
 * . TODO
 */
public interface MoveTurnNtfExec {
    public void fireMoveTurnNotify(Piece piece, List<Position> validMoves);
}
