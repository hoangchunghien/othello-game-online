package othello.command;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 * Description
 * . TODO
 */
public class Login implements ICommand{
    
    public final static String NAME = "login";
    
    private ILoginExec loginExecutor;

    private String username;
    private String password;
    
    public Login(ILoginExec loginExecutor, String username, String password) {
        
        this.loginExecutor = loginExecutor;
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        
        return this.username;
    }
    
    public void setUsername(String username) {
        
        this.username = username;
    }
    
    public String getPassword() {
        
        return this.password;
    }
    
    public void setPassword(String password) {
        
        this.password = password;
    }
    
    @Override
    public void execute() {
        
        loginExecutor.doLogin(username, password);
    }

    @Override
    public JSONObject serializeJSON() {
        
        JSONObject jObj = new JSONObject();
        jObj.put("command", NAME);
        jObj.put("username", username);
        jObj.put("password", password);
        
        return jObj;
    }

    @Override
    public void deserializeJSON(JSONObject jObj) {
        
        this.username = jObj.getString("username");
        this.password = jObj.getString("password");
    }
    
}
