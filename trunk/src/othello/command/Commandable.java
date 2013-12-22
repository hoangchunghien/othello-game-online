package othello.command;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public interface Commandable {
    public void execute();
    public JSONObject serializeJSON();
    public void deserializeJSON(JSONObject jObj);
}
