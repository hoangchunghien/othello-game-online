package othello.command;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 */
public class Undo implements ICommand {
    
    public final static String NAME = "undo";

    IUndoExec undoExecutor;
    
    public Undo(IUndoExec undoExecutor) {
        
        this.undoExecutor = undoExecutor;
    }
    
    @Override
    public void execute() {
        
        undoExecutor.makeUndo();
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
