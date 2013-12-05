package othello.command.response;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 */
public interface IResponse {

    public JSONObject serializeJSON();
    public void deserializeJSON(JSONObject json);
    public void execute();
}
