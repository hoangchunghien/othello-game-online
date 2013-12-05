package othello.models;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class Location {
    
    public String name;
    public String id;
    public int numUsers;
    
    public JSONObject serializeJSON() {
        
        JSONObject jObj = new JSONObject();
        jObj.put("id", id);
        jObj.put("name", name);
        jObj.put("numUsers", numUsers);
        
        return jObj;
    }
    
    public void deserializeJSON(JSONObject jObj) {
        
        this.id = jObj.getString("id");
        this.name = jObj.getString("name");
        this.numUsers = jObj.getInt("numUsers");
    }
}
