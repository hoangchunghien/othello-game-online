package othello.command.notify;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 * @since Dec 11, 2013
 * @version Dec 11, 2013
 * Description 
 * . TODO
 */
public class MoveTurnNtf implements INotification {

    public static final String NAME = "move turn";
    
    private MoveTurnNtfExec executor;
    
    public MoveTurnNtf(MoveTurnNtfExec executor) {
        this.executor = executor;
    }

    @Override
    public void execute() {
        
        executor.fireMoveTurnNotify();
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
