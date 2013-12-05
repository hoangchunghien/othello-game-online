/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.command.response;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 * Description 
 * . The response command to client like this
 * . Success: login accepted <message>
 * . Failure: login rejected <message>
 */
public class Login implements IResponse {
    
    public static final String NAME = "login";
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";

    ILoginResExec loginResponseExecutor;
    String status;
    String message;
    
    public Login(ILoginResExec loginResExec, String status, String message) {
        
        this.loginResponseExecutor = loginResExec;
        this.status = status;
        this.message = message;
    }
    
    @Override
    public void execute() {
        
        if (status.equalsIgnoreCase(ACCEPTED)) {
            
            // Notify to user the successful of login
            loginResponseExecutor.makeLogin();
        } else {
            // Notify to user the failure, wrong username or wrong password
            // ... Notify code here
        }
    }


    @Override
    public JSONObject serializeJSON() {
        
        String result = "{'command':" + NAME + ",'status':" + status + 
                ",'message':" + message + "}";
        JSONObject jObj = new JSONObject(result);
        return jObj;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        
        this.status = json.getString("status");
        this.message = json.getString("message");
    }
    
}
