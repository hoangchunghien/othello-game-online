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
    
    private String message = "";
    private String status = REJECTED;
    private String playerTicket = "";
    private JoinPlayerResExecutable executor;
    
    public JoinPlayerRes(JoinPlayerResExecutable executor) {
        
        this.executor = executor;
    }
    
    public void setPlayerTicket(String ticket) {
    	this.playerTicket = ticket;
    }
    
    public void setStatus(String status) {
    	this.status = status;
    }
    
    public void setMessage(String message) {
    	this.message = message;
    }
    
    @Override
    public JSONObject serializeJSON() {
        
        JSONObject json = new JSONObject();
        json.put("cmdType", "response");
        json.put("command", NAME);
        json.put("message", message);
        json.put("status", status);
        json.put("ticket", playerTicket);
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        this.message = json.getString("message");
        this.status = json.getString("status");
        this.playerTicket = json.getString("ticket");
    }

    @Override
    public void execute() {
        
        if (status.equalsIgnoreCase(ACCEPTED)) {
            executor.joinAccepted(playerTicket);
        }
        else {
            executor.joinRejected(message);
        }
    }

}
