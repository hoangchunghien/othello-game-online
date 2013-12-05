package othello.command;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 * 
 */
public class Join implements ICommand {
    
    public final static String NAME = "join";
    public final static String ACCEPTED = "accepted";
    public final static String REJECTED = "rejected";
    
    private IJoinExec joinExecutor;
    private String locationId;
    
    public Join(IJoinExec joinExecutor, String locationId) {
        
        this.joinExecutor = joinExecutor;
        this.locationId = locationId;
    }

    @Override
    public void execute() {
        joinExecutor.join(locationId);
    }
    
    @Override
    public JSONObject serializeJSON() {
        
        JSONObject jObj = new JSONObject();
        jObj.put("command", NAME);
        jObj.put("locationId", this.locationId);
        
        return jObj;
    }
    
    @Override
    public void deserializeJSON(JSONObject jObj) {
        
        this.locationId = jObj.getString("locationId");
    }
    
}
