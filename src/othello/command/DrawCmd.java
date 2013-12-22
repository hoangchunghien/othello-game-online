package othello.command;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class DrawCmd implements Commandable {
    
    public static final String NAME = "draw";
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";

    private IDrawExec drawExecutor;
    private String status;
    
    public DrawCmd(IDrawExec drawExecutor, String status) {
        
        this.drawExecutor = drawExecutor;
        this.status = status;
    }
    
    @Override
    public void execute() {
        if (status.equalsIgnoreCase(ACCEPTED)) {
            drawExecutor.dealDraw();
        }
        else {
            drawExecutor.rejectDraw();
        }
    }
    
    @Override
    public JSONObject serializeJSON() {
        
        JSONObject jObj = new JSONObject();
        jObj.put("command", NAME);
        jObj.put("status", status);
        
        return jObj;
    }
    
    @Override
    public void deserializeJSON(JSONObject jObj) {
        this.status = jObj.getString("status");
    }
    
}
