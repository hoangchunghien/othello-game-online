package othello.command.notify;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class GameOverNtf implements INotification {
    public final static String NAME = "gameover";
    
    IGameOverNtfExec gameOverNotifyExecutor;
    
    public GameOverNtf(IGameOverNtfExec executor) {
        this.gameOverNotifyExecutor = executor;
    }

    @Override
    public void execute() {
        this.gameOverNotifyExecutor.makeOverGame();
    }

    @Override
    public JSONObject serializeJSON() {
        
        JSONObject json = new JSONObject();
        json.put("cmdType", "notify");
        json.put("command", NAME);
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        
    }
    
}
