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
public class Quit implements ICommand {
    
    public static final String NAME = "quit";
    
    private IQuitExec quitExecutor;
    
    public Quit(IQuitExec quitExecutor) {
        
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
