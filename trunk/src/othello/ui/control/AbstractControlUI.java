package othello.ui.control;

import othello.game.GameState;
import othello.game.GameStateChangedListener;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public abstract class AbstractControlUI implements GameStateChangedListener {
    public abstract void show();
    
    // Render the game state (The state change while a new move occur)
    public abstract void renderGameState(GameState gameState);
    
    // Notify a message in ui
    public abstract void notifyMessage(String msg);
    
    // Lock the game board while computer or online player are thinking
    public abstract void allowMakeMove();
}
