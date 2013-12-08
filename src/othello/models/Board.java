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
        json.put("black", seats.get(Piece.BLACK).serializeJSON());
        json.put("white", seats.get(Piece.WHITE).serializeJSON());
        return json;
    }
    
    public void deserializeJSON(JSONObject json) {
        
        this.name = json.getString("name");
        Player black = new Player();
        black.deserializeJSON(json.getJSONObject("black"));
        Player white = new Player();
        white.deserializeJSON(json.getJSONObject("white"));
        seats.put(Piece.BLACK, black);
        seats.put(Piece.WHITE, white);
    }
}
