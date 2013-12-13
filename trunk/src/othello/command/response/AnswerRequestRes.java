package othello.command.response;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class AnswerRequestRes implements IResponse{

    public static final String NAME = "answer request";
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";
    
    private AnswerRequestResExec executor;
    private int reqType;
    private String status;
    private String message;
    
    public AnswerRequestRes(AnswerRequestResExec executor, int reqType, String status, String message) {
        this.executor = executor;
        this.reqType = reqType;
        this.status = status;
        this.message = message;
    }
    
    @Override
    public JSONObject serializeJSON() {
        JSONObject json = new JSONObject();
        json.put("cmdType", "response");
        json.put("command", NAME);
        json.put("reqType", reqType);
        json.put("status", status);
        json.put("message", message);
        
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        this.status = json.getString("status");
        this.message = json.getString("message");
        this.reqType = json.getInt("reqType");
    }

    @Override
    public void execute() {
//        if (status == ACCEPTED) {
//            executor.requestAccepted(reqType);
//        }
//        else {
//            executor.requestRejected(reqType);
//        }
    }

}
