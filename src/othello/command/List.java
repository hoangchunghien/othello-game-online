package othello.command;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 */
public class List implements ICommand {
    
    public final static String NAME = "list";
    public final static String LOCATION = "locations";
    public final static String PLAYER = "players";

    private IListExec listExecutor;
    private String opt;
    
    public List(IListExec listExecutor, String opt) {
        
        this.listExecutor = listExecutor;
        this.opt = opt;
    }
    
    @Override
    public void execute() {
        if (opt.equalsIgnoreCase(LOCATION)) {
            
            listExecutor.listLocations();
        }
        
        if (opt.equalsIgnoreCase(PLAYER)) {
            
            listExecutor.ListPlayers();
        }
    }

    @Override
    public JSONObject serializeJSON() {

        JSONObject jObj = new JSONObject();
        jObj.put("command", NAME);
        jObj.put("option", opt);
        
        return jObj;
    }

    @Override
    public void deserializeJSON(JSONObject jObj) {
        
        this.opt = jObj.getString("option");
    }
    
}
