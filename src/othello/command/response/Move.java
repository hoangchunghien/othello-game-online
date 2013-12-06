package othello.command.response;

import org.json.JSONObject;
import othello.common.Position;
import othello.game.GameMonitor;
import othello.ui.UIFactory;

/**
 *
 * @author Hien Hoang
 */
public class Move implements IResponse {
    
    public static final String NAME = "move";
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";

    String status;
    Position position;
    String message;
    
    IMoveResExec moveResponseExecutor;
    
    public Move(IMoveResExec executor, String status, String message, Position position) {
        
        this.moveResponseExecutor = executor;
        this.status = status;
        this.message = message;
        this.position = position;
    }
    
    @Override
    public void execute() {
        
        if (status.equalsIgnoreCase(ACCEPTED)) {
            moveResponseExecutor.makeMoving(position);
        } 
        else {
            // Notify to user unvalid move
            String msg = "Invalid move!!!";
            UIFactory.getControlUI().notifyMessage(msg);
        }
    }


    @Override
    public JSONObject serializeJSON() {
        
        JSONObject jObj = new JSONObject();
        jObj.put("cmdType", "response");
        jObj.put("command", NAME);
        jObj.put("status", status);
        jObj.put("message", message);
        jObj.put("position", position.serializeJSON());
        return jObj;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        
        this.status = json.getString("status");
        this.message = json.getString("message");
        this.position.deserializeJSON(json.getJSONObject("position"));
    }
    
}
