package othello.command.notify;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class MakeDrawNtf implements INotification {
    
    public final static String NAME = "make draw";
    
    

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JSONObject serializeJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
