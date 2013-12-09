package othello.client;

import othello.common.AbstractPlayer;
import othello.common.Piece;
import othello.common.Position;
import othello.game.GameState;

/**
 *
 * @author Hien Hoang
 */
public class OnlinePlayer extends AbstractPlayer {

    public OnlinePlayer(Piece piece) {
        super(piece);
    }
    @Override
    public AbstractPlayer clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fireMoveTurn(GameState currentStateClone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeMoving(Position p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
