package othello.command.notify;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public interface INotification {
    
    public void execute();
    public JSONObject serializeJSON();
    public void deserializeJSON(JSONObject json);
}
