package othello.command.notify;

import org.json.JSONObject;
import othello.game.GameState;

/**
 *
 * @author Hien Hoang
 * @version Dec 9, 2013
 * Description
 */
public class StateChangedNtf implements INotification {

    private IStateChangedNtfExec executor;
    private GameState state;
    
    public StateChangedNtf(IStateChangedNtfExec executor, GameState state) {
        this.executor = executor;
        this.state = state;
    }
    
    @Override
    public void execute() {
        executor.fireStateChangedNotification(state);
    }

    @Override
    public JSONObject serializeJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
