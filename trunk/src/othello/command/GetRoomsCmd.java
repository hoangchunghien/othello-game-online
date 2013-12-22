package othello.command;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class GetRoomsCmd implements Commandable {
    
    public static final String NAME = "getrooms";
    
    

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONObject serializeJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deserializeJSON(JSONObject jObj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
