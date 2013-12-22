package othello.command.notify;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import othello.common.Piece;
import othello.common.Position;
import othello.models.Player;

/**
 *
 * @author Hien Hoang
 * @since Dec 11, 2013
 * @version Dec 11, 2013
 * Description 
 * . TODO
 */
public class MoveTurnNtf implements INotification {

    public static final String NAME = "move turn";
    
    private MoveTurnNtfExec executor;
    private Piece piece;
    private List<Position> validMoves;
    
    public MoveTurnNtf(MoveTurnNtfExec executor) {
        this.executor = executor;
    }
    
    public void setPiece(Piece piece) {
    	this.piece = piece;
    }
    
    public void setValidMoves(List<Position> validMoves) {
    	this.validMoves = validMoves;
    }

    @Override
    public void execute() {
        
        executor.fireMoveTurnNotify(piece, validMoves);
    }

    @Override
    public JSONObject serializeJSON() {
    	JSONArray jarr = new JSONArray();
    	for (Position p : validMoves) {
    		jarr.put(p.serializeJSON());
    	}
    	
        JSONObject json = new JSONObject();
        json.put("cmdType", "notify");
        json.put("command", NAME);
        json.put("piece", piece.toString());
        json.put("validMoves", jarr);
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        piece = Piece.toPiece(json.getString("piece"));
        validMoves = new ArrayList<>();
        JSONArray jarr = json.getJSONArray("validMoves");
        for (int i = 0; i < jarr.length(); i++) {
            JSONObject playerJSON = jarr.getJSONObject(i);
           	Position p = new Position(-1, -1);
            p.deserializeJSON(playerJSON);
            this.validMoves.add(p);
        }
    }
    
    
}
