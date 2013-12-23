package othello.command.notify;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 * @version Dec 7, 2013
 * Description
 * . TODO
 */
public class PassNtf implements INotification {

    public final static String NAME = "pass";
    
    private PassNtfExecutable passNotifyExecutor;
    
    public PassNtf(PassNtfExecutable passNotifyExecutor) {
        
        this.passNotifyExecutor = passNotifyExecutor;
    }
    
    @Override
    public void execute() {
        this.passNotifyExecutor.makePassing();
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
