package othello.models;

import java.util.Dictionary;
import java.util.Hashtable;
import org.json.JSONObject;
import othello.common.Piece;

/**
 *
 * @author Hien Hoang
 */
public class Board {
    
    String name;
    Dictionary<Piece, Player> seats;

    public Board() {
        seats = new Hashtable<>();

    }
    
    public String getName() {
        return this.name;
    }
    
    public void setPlayer(Piece piece, Player player) {
        seats.put(piece, player);
    }
    
    public Dictionary<Piece, Player> getSeats() {
        return seats;
    }
    
    public Board(String name) {
        this();
        this.name = name;   
    }
    
    public JSONObject serializeJSON() {
        
        JSONObject json = new JSONObject();
        json.put("name", name);        
        
        if (seats.get(Piece.BLACK) != null)
        	json.put("black", seats.get(Piece.BLACK).serializeJSON());
        else 
        	json.put("black", "undefined");
        
        if (seats.get(Piece.WHITE) != null)
        	json.put("white", seats.get(Piece.WHITE).serializeJSON());
        else 
        	json.put("white", "undefined");
        
        return json;
    }
    
    public void deserializeJSON(JSONObject json) {
        
        this.name = json.getString("name");
        Player black = new Player();
        
        if (!json.getString("black").equalsIgnoreCase("undefined"))
        	black.deserializeJSON(json.getJSONObject("black"));
        
        Player white = new Player();
        if (!json.getString("white").equalsIgnoreCase("undefined"))
        	white.deserializeJSON(json.getJSONObject("white"));
        
        seats.put(Piece.BLACK, black);
        seats.put(Piece.WHITE, white);
    }
}
