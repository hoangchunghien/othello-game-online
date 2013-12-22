package othello.command;

import org.json.JSONObject;
import othello.common.AbstractPlayer;
import othello.common.Position;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class MoveCmd implements Commandable {

    public final static String NAME = "move";
    
    IMoveCmdExec moveExecutor;
    AbstractPlayer caller;
    Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
       
    public MoveCmd(IMoveCmdExec moveExecutor) {
        
        this.moveExecutor = moveExecutor;
        
    }
    
    public MoveCmd(IMoveCmdExec moveExecutor, AbstractPlayer caller,  Position position) {
        
        this.moveExecutor = moveExecutor;
        this.position = position;
        this.caller = caller;
    }
    
    @Override
    public void execute() {
        
        moveExecutor.makeMove(position, caller);
        
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
