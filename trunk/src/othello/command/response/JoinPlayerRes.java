package othello.command.response;

import org.json.JSONObject;
import othello.client.HumanPlayer;
import othello.common.AbstractPlayer;
import othello.common.Piece;

/**
 *
 * @author Hien Hoang
 */
public class JoinPlayerRes implements IResponse {

        
    public final static String NAME = "join player";
    public final static String ACCEPTED = "accepted";
    public final static String REJECTED = "rejected";
    
    private String message;
    private String status;
    private IJoinPlayerResExec executor;
    private AbstractPlayer player;
    
    public JoinPlayerRes(IJoinPlayerResExec executor, String status, String message, AbstractPlayer player) {
        
        this.executor = executor;
        this.message = message;
        this.status = status;
        this.player = player;
    }
    @Override
    public JSONObject serializeJSON() {
        
        JSONObject json = new JSONObject();
        json.put("cmdType", "response");
        json.put("command", NAME);
        json.put("message", message);
        json.put("status", status);
        json.put("player", player.serializeJson());
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        this.player = new HumanPlayer(Piece.EMPTY);
        this.message = json.getString("message");
        this.status = json.getString("status");
        this.player.deserializeJson(json.getJSONObject("player"));
    }

    @Override
    public void execute() {
        
        if (status.equalsIgnoreCase(ACCEPTED)) {
            executor.joinAccepted(player);
        }
        else {
            executor.joinRejected(message);
        }
    }

}
