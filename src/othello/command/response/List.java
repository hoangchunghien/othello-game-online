package othello.command.response;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import othello.models.Location;

/**
 *
 * @author Hien Hoang
 * @version Dec 5, 2013
 */
public class List implements IResponse {
    
    public static final String NAME = "list";
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";
    public static final String LOCATION = "location";
    public static final String PLAYER = "player";
    
    private java.util.List<othello.models.Location> locations;
    private java.util.List<String> players;
    private String status;
    private String message;
    private String type;
    
    private IListResExec listResponseExecutor;
    
    public List(IListResExec listResponseExecutor, String status, String message, 
            String type) {
        locations = new ArrayList<>();
        players = new ArrayList<>();
        this.status = status;
        this.message = message;
        this.type = type;
        this.listResponseExecutor = listResponseExecutor;
    }

    @Override
    public JSONObject serializeJSON() {
        
        JSONObject jObj = new JSONObject();
        jObj.put("command", NAME);
        jObj.put("status", status);
        jObj.put("message", message);
        jObj.put("type", type);
        JSONArray jArr = new JSONArray();
        if (type.equalsIgnoreCase(LOCATION)) {
            for (othello.models.Location lo : locations) {
                jArr.put(lo.serializeJSON());
            }
            jObj.put("locations", jArr);
        } 
        else {
            for (String u : players) {
                jArr.put(u);
            }
            jObj.put("players", jArr);
        }
        
        return jObj;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        
        this.status = json.getString("status");
        this.message = json.getString("message");
        this.type = json.getString("type");
        if (type.equalsIgnoreCase(LOCATION)) {
            locations = new ArrayList<>();
            JSONArray jArr = json.getJSONArray("locations");
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject jobj = jArr.getJSONObject(i);
                Location lo = new Location();
                lo.deserializeJSON(jobj);
                locations.add(lo);
            }
        }
        else {
            players = new ArrayList<>();
            JSONArray jArr = json.getJSONArray("players");
            for (int i = 0; i < jArr.length(); i++) {
                
                players.add(jArr.getString(i));
            }
        }
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
