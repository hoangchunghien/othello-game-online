package othello.client;

import othello.common.AbstractPlayer;
import othello.common.Piece;
import othello.configuration.PlayerCfg;

/**
 *
 * @author Hien Hoang
 */
public class PlayerFactory {
    
    public static AbstractPlayer getPlayer(String type){
        switch (type) {
        case PlayerCfg.TYPE_COMPUTER: 
            return new ComputerPlayer(Piece.UNDEFINED);
        case PlayerCfg.TYPE_HUMAN:
            return new HumanPlayer(Piece.UNDEFINED);
        default:
            return null;
        }
    }
}
