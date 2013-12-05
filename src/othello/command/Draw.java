package othello.command;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class Draw implements ICommand {
    
    public static final String NAME = "draw";

    private IDrawExec drawExecutor;
    
    public Draw(IDrawExec drawExecutor) {
        
        this.drawExecutor = drawExecutor;
    }
    
    @Override
    public void execute() {
        drawExecutor.dealDraw();
    }
    
    @Override
    public JSONObject serializeJSON() {
        
        JSONObject jObj = new JSONObject();
        jObj.put("command", NAME);
        
        return jObj;
    }
    
    @Override
    public void deserializeJSON(JSONObject jObj) {
        
    }
    
}
