package othello.command.response;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import othello.models.Location;

/**
 *
 * @author Hien Hoang
 */
public class ListLocations implements IResponse {
    
    public static final String NAME = "list locations";
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";
    
    private java.util.List<othello.models.Location> locations;
    private IListLocationsResExec listLocationsResExec;
    private String status;
    private String message;

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
    
    public ListLocations(IListLocationsResExec executor, String status, String message) {
        
        this.listLocationsResExec = executor;
        this.status = status;
        this.message = message;
    }
    
    public ListLocations(IListLocationsResExec executor, 
            String status, String message, List<othello.models.Location> locations) {
        this(executor, status, message);
        this.locations = locations;
    }

    @Override
    public JSONObject serializeJSON() {
        
        JSONArray jArr = new JSONArray();
        for (othello.models.Location lo : locations) {
            jArr.put(lo.serializeJSON());
        }
        
        JSONObject jObj = new JSONObject();
        jObj.put("cmdType", "response");
        jObj.put("command", NAME);
        jObj.put("status", status);
        jObj.put("message", message);
        jObj.put("locations", jArr);
        
        return jObj;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        this.status = json.getString("status");
        this.message = json.getString("message");
        locations = new ArrayList<>();
        JSONArray jArr = json.getJSONArray("locations");
        for (int i = 0; i < jArr.length(); i++) {
            JSONObject jobj = jArr.getJSONObject(i);
            Location lo = new Location();
            lo.deserializeJSON(jobj);
            locations.add(lo);
        }
    }

    @Override
    public void execute() {
        
    }
    
    
}
