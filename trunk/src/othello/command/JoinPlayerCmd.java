package othello.command;

import org.json.JSONObject;
import othello.common.AbstractPlayer;

/**
 *
 * @author Hien Hoang
 */
public class JoinPlayerCmd implements Commandable {

    public static final String NAME = "join player";
    private JoinPlayerCmdExecutable executor;
    private String boardId;
    
    public JoinPlayerCmd(JoinPlayerCmdExecutable executor) {
        this.executor = executor;
    }
    
    public void setBoardId(String boardId) {
    	this.boardId = boardId;
    }
    
    @Override
    public void execute() {
        executor.joinPlayer(boardId);
    }

    @Override
    public JSONObject serializeJSON() {
        
        JSONObject json = new JSONObject();
        json.put("cmdType", "command");
        json.put("command", NAME);
        json.put("boardId", boardId);
        
        
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject jObj) {
        boardId = jObj.getString("boardId");
    }

}
