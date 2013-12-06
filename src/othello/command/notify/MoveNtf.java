package othello.command.notify;

import org.json.JSONObject;
import othello.common.Position;

/**
 *
 * @author Hien Hoang
 */
public class MoveNtf implements INotification {

    public static final String NAME = "move";
    
    private IMoveNtfExec moveNtfExec;
    private Position position;
    
    public MoveNtf(IMoveNtfExec moveNtfExec, Position position) {
        this.moveNtfExec = moveNtfExec;
        this.position = position;
    }
    
    @Override
    public void execute() {
        moveNtfExec.makeMoving(position);
    }

    @Override
    public JSONObject serializeJSON() {
        JSONObject json = new JSONObject();
        json.put("cmdType", "notify");
        json.put("command", NAME);
        json.put("position", position.serializeJSON());
        
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        this.position = Position.UNDEFINED;
        this.position.deserializeJSON(json.getJSONObject("position"));
    }
    
    
}
