package othello.game;

/**
 *
 * @author Hien Hoang
 */
public interface GameStateChangedListener {

    public void fireStateChanged(GameState newState);
}
