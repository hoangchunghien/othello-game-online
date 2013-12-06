package othello.models;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class Player {
    
    public final static int TYPE_GUEST = 0;
    public final static int TYPE_NORMAL = 1;
    public final static int TYPE_GOLD = 2;
    public final static int TYPE_DIAMON = 3;
    
    private int type;
    private String username;
    private int score;
    
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public JSONObject serializeJSON() {
        
        JSONObject jObj = new JSONObject();
        jObj.put("type", type);
        jObj.put("username", username);
        jObj.put("score",score);
        
        return jObj;
    }
    
    public void deserializeJSON(JSONObject jObj) {
        
        this.type = jObj.getInt("type");
        this.username = jObj.getString("username");
        this.score = jObj.getInt("score");
    }
    
    @Override
    public String toString() {
        return username;
    }
    
}
