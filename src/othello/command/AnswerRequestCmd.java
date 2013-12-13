package othello.command;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class AnswerRequestCmd implements ICommand {

    public static final String NAME = "answer request";
    public static final int REQ_UNDO = 1;
    public static final int REQ_REDO = 2;
    public static final int REQ_DRAW = 3;
    
    private AnswerRequestCmdExec respondent;
    int reqType;
    
    public AnswerRequestCmd(AnswerRequestCmdExec respondent, int reqType) {
        this.reqType = reqType;
        this.respondent = respondent;
    }
    
    @Override
    public void execute() {
        respondent.answerRequest(reqType);
    }

    @Override
    public JSONObject serializeJSON() {
        
        JSONObject json = new JSONObject();
        json.put("cmdType", "command");
        json.put("command", NAME);
        json.put("reqType", reqType);
        
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject jObj) {
        this.reqType = jObj.getInt("reqType");
    }

}
