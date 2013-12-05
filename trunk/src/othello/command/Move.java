package othello.command;

import org.json.JSONObject;
import othello.common.Position;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class Move implements ICommand {

    public final static String NAME = "move";
    
    IMoveExec moveExecutor;
    Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
       
    public Move(IMoveExec moveExecutor) {
        
        this.moveExecutor = moveExecutor;
        
    }
    
    public Move(IMoveExec moveExecutor, Position position) {
        
        this.moveExecutor = moveExecutor;
        this.position = position;
    }
    
    @Override
    public void execute() {
        
        moveExecutor.makeMove(position);
        
    }

    @Override
    public JSONObject serializeJSON() {
        
        JSONObject jObj = new JSONObject();
        jObj.put("command", NAME);
        jObj.put("position", position.serializeJSON());
        
        return jObj;
    }

    @Override
    public void deserializeJSON(JSONObject jObj) {
        
        this.position.deserializeJSON(jObj.getJSONObject("position"));
    }
    
}
