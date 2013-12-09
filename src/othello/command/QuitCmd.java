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
public class QuitCmd implements ICommand {
    
    public static final String NAME = "quit";
    
    private IQuitCmdExec quitExecutor;
    
    public QuitCmd(IQuitCmdExec quitExecutor) {
        
        this.quitExecutor = quitExecutor;
    }

    @Override
    public void execute() {
        quitExecutor.quit();
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
