package othello.game;

import java.util.HashMap;
import othello.common.AbstractPlayer;

/**
 *
 * @author Hien Hoang
 * @since Dec 13, 2013
 * @version Dec 13, 2013
 * Description
 * . For play game time
 */
public class GameTimer {
    
    protected HashMap<AbstractPlayer, Integer> timePerMove;
    protected HashMap<AbstractPlayer, Integer> timePerGame;
    protected NotificationBoard nb = NotificationBoard.getInstance();
    
    
    protected void registerTimer(AbstractPlayer player, Integer seconds) {
        
    }
    
    protected void pauseTimer(AbstractPlayer player) {
        
    }
    
    protected void resumeTimer(AbstractPlayer player) {
        
    }
    
    protected void renewTimePerMove(AbstractPlayer player) {
        
    }
    
    public Integer getResidualTimePerGame(AbstractPlayer player) {
        if (timePerGame.get(player) != null) {
            return timePerGame.get(player);
        }
        return null;
    } 
    
    public Integer getResidualTimePerMove(AbstractPlayer player) {
        if (timePerMove.get(player) != null) {
            return timePerMove.get(player);
        }
        return null;
    }
}
