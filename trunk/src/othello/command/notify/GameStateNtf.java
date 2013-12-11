package othello.command.notify;

import org.json.JSONObject;
import othello.game.GameState;

/**
 *
 * @author Hien Hoang
 * @version Dec 11, 2013
 * Description
 * . TODO
 */
public class GameStateNtf implements INotification{

    public static final String NAME = "game state";
    
    private GameStateNtfExec executor;
    private GameState state;
    
    public GameStateNtf(GameStateNtfExec executor, GameState state) {
        this.executor = executor;
        this.state = state;
    }
    
    @Override
    public void execute() {
        this.executor.fireStateChangedNotify(state);
    }

    @Override
    public JSONObject serializeJSON() {
        JSONObject json = new JSONObject();
        json.put("cmdType", "notify");
        json.put("command", NAME);
        json.put("state", state.serializeJson());
        
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        
        this.state = new GameState();
        this.state.deserializeJson(json.getJSONObject("state"));
    }

}
