package othello.engine;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import othello.common.Board;
import othello.common.Piece;
import othello.common.Position;
import othello.game.GameMonitor;

/**
 *
 * @author Hien Hoang
 * @version Nov 8, 2013
 */
public class Random extends AbstractEngine {

    public Random(Board board) {
        super(board);
    }
    
    @Override
    public Position getMove(Piece p) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Random.class.getName()).log(Level.SEVERE, null, ex);
        }
        Position pos;   
        List<Position> positionList = this.board.getValidMoveList(p);
        pos = positionList.get(0);
        
        return pos;
    }
    
}
