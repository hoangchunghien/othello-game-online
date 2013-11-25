package othello.ui.control;

import othello.game.GameState;
import othello.common.Position;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public interface IControl {
    public void show();
    
    // Render the game state (The state change while a new move occur)
    public void renderGameState(GameState gameState);
    
    // Notify a message in ui
    public void notifyMessage(String msg);
    
    // Lock the game board while computer or online player are thinking
    public void allowMakeMove();
}
