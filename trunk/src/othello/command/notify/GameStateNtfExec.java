package othello.command.notify;

import othello.game.GameState;

/**
 *
 * @author Hien Hoang
 */
public interface GameStateNtfExec {
    
    public void fireStateChangedNotify(GameState state);
}
