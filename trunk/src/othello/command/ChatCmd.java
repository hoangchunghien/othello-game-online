package othello.command;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class ChatCmd implements Commandable {

    public static final String NAME = "chat";
    
    private String message;
    private IChatCmdExec executor;
    
    public ChatCmd(IChatCmdExec executor, String message) {
        this.executor = executor;
        this.message = message;
    }
    
    @Override
    public void execute() {
        executor.chat(message);
    }

    @Override
    public JSONObject serializeJSON() {
        
        JSONObject json = new JSONObject();
        json.put("cmdType", "command");
        json.put("command", NAME);
        json.put("message", message);
        
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject jObj) {
        
        this.message = jObj.getString("message");
    }

}
