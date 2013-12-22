package othello.command.notify;

import org.json.JSONObject;

import othello.common.Piece;

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
    
    public MoveTurnNtf(MoveTurnNtfExec executor) {
        this.executor = executor;
    }
    
    public void setPiece(Piece piece) {
    	this.piece = piece;
    }

    @Override
    public void execute() {
        
        executor.fireMoveTurnNotify(piece);
    }

    @Override
    public JSONObject serializeJSON() {
        JSONObject json = new JSONObject();
        json.put("cmdType", "notify");
        json.put("command", NAME);
        json.put("piece", piece.toString());
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        piece = Piece.toPiece(json.getString("piece"));
    }
    
    
}
