package othello.command.response;

import othello.ui.UIFactory;
import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 * Description
 * . TODO
 */
public class Join implements IResponse {

    public static final String NAME = "join";
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";
    
    private String status;
    private String message;
    private String locationId;
    
    private IJoinResExec joinResponseExecutor;
    
    
    public Join(IJoinResExec joinResponseExecutor,  String status, 
            String message, String locationId) {
        
        this.joinResponseExecutor = joinResponseExecutor;
        this.status = status;
        this.message = message;
        this.locationId = locationId;
    }
    

    @Override
    public void execute() {
        if (status.equalsIgnoreCase(ACCEPTED)) {
            // Join successful
            joinResponseExecutor.makeJoining();
        }
        else {
            // Join failure
        }
    }

    @Override
    public JSONObject serializeJSON() {
        
        JSONObject jObj = new JSONObject();
        jObj.put("cmdType", "response");
        jObj.put("command", NAME);
        jObj.put("status", status);
        jObj.put("locationId", locationId);
        jObj.put("message", message);
        
        return jObj;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        
        this.status = json.getString("status");
        this.message = json.getString("message");
        this.locationId = json.getString("locationId");
    }
    
}
