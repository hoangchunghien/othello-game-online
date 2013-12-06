package othello.command.notify;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class DrawNtf implements INotification {
    
    public static final String NAME = "draw";
    
    private IDrawNtfExec drawNotifyExecutor;
    
    public DrawNtf(IDrawNtfExec drawNotifyExecutor) {
        this.drawNotifyExecutor = drawNotifyExecutor;
    }

    @Override
    public void execute() {
        drawNotifyExecutor.makeDrawing();
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
