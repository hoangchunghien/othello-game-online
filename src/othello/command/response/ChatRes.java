package othello.command.response;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class ChatRes implements IResponse {
    
    public final static String NAME = "chat";
    public final static String ACCEPTED = "accepted";
    public final static String REJECTED = "rejected";
    
    private String message;
    private String status;
    private IChatResExec executor;
    
    public ChatRes(IChatResExec executor, String status, String message) {
        this.executor = executor;
        this.status = status;
        this.message = message;
    }
    
    @Override
    public void execute() {
        
        executor.loadMessage(message);
    }

    @Override
    public JSONObject serializeJSON() {
        JSONObject json = new JSONObject();
        json.put("cmdType", "response");
        json.put("command", NAME);
        json.put("status", status);
        json.put("message", message);
        
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        this.status = json.getString("status");
        this.message = json.getString("message");
    }
        
}
