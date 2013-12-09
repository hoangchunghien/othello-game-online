package othello.command.notify;

import othello.game.GameState;

/**
 *
 * @author Hien Hoang
 */
public interface IStateChangedNtfExec {
    
    public void fireStateChangedNotification(GameState state);
}
