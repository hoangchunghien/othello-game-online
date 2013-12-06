package othello.command.notify;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class ChatNtf implements INotification {
    
    public final static String NAME = "chat";
    
    private IChatNtfExec chatNotifyExecutor;
    private String username;
    private String message;
    
    public ChatNtf(IChatNtfExec executor, String username, String message) {
        this.chatNotifyExecutor = executor;
        this.username = username;
        this.message = message;
    }

    @Override
    public void execute() {
        chatNotifyExecutor.displayChatMessage(username, message);
    }

    @Override
    public JSONObject serializeJSON() {
        JSONObject json = new JSONObject();
        json.put("cmdType", "notify");
        json.put("command", NAME);
        json.put("username", username);
        json.put("message", message);
        
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        this.username = json.getString("username");
        this.message = json.getString("message");
    }
    
    
}
