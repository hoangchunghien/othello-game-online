package othello.command;

import org.json.JSONObject;
import othello.common.AbstractPlayer;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 */
public class UndoCmd implements ICommand {
    
    public final static String NAME = "undo";

    IUndoCmdExec undoExecutor;
    AbstractPlayer caller;
    
    public UndoCmd(IUndoCmdExec undoExecutor, AbstractPlayer caller) {
        
        this.undoExecutor = undoExecutor;
        this.caller = caller;
    }
    
    @Override
    public void execute() {
        
        undoExecutor.makeUndo(caller);
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
