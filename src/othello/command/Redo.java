package othello.command;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class Redo implements ICommand {
    
    public final static String NAME = "redo";

    IRedoExec redoExecutor;
    
    public Redo(IRedoExec redoExecutor) {
        
        this.redoExecutor = redoExecutor;
    }
    
    @Override
    public void execute() {
        
        redoExecutor.makeRedo();
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
