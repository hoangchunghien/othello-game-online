package othello.command;

import org.json.JSONObject;
import othello.common.AbstractPlayer;

/**
 *
 * @author Hien Hoang
 */
public class JoinPlayerCmd implements Commandable {

    public static final String NAME = "join player";
    private IJoinPlayerCmdExec executor;
    private AbstractPlayer player;
    
    public JoinPlayerCmd(IJoinPlayerCmdExec executor, AbstractPlayer player) {
        this.executor = executor;
        this.player = player;
    }
    
    @Override
    public void execute() {
        executor.joinPlayer(player);
    }

    @Override
    public JSONObject serializeJSON() {
        
        JSONObject json = new JSONObject();
        json.put("cmdType", "command");
        json.put("command", NAME);
        
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject jObj) {
        
    }

}
