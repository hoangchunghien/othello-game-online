/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.command;

import org.json.JSONObject;

/**
 *
 * @author Hien
 */
public class ResignCmd implements Commandable {
    
    public final static String NAME = "resign";
    
    private IResignCmdExec resignExecutor;
    
    public ResignCmd(IResignCmdExec resignExecutor) {
        this.resignExecutor = resignExecutor;
    }

    @Override
    public void execute() {
        resignExecutor.makeResign();
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
