package othello.command;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class GetBoards implements ICommand {
    public static final String NAME = "getboards";

    private String roomId;
    private IGetBoardsExec executor;
    
    public GetBoards(IGetBoardsExec executor, String roomId) {
        this.executor = executor;
        this.roomId = roomId;
    }
    
    @Override
    public void execute() {
        executor.getBoards(roomId);
    }

    @Override
    public JSONObject serializeJSON() {
        JSONObject json = new JSONObject();
        json.put("cmdType", "command");
        json.put("command", NAME);
        json.put("roomId", roomId);
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject jObj) {
        this.roomId = jObj.getString("roomId");
    }

}
