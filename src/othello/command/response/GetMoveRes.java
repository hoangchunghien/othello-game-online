package othello.command.response;

import org.json.JSONObject;
import othello.common.Position;

/**
 *
 * @author Hien Hoang
 * @version Dec 8, 2013
 * Description
 * . For client use only
 */
public class GetMoveRes implements IResponse {

    private IGetMoveResExec executor;
    private Position position;
    
    public GetMoveRes(IGetMoveResExec executor, Position position) {
        
        this.executor = executor;
        this.position = position;
    }
    
    @Override
    public JSONObject serializeJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute() {
        executor.makeMoving(position);
    }

}
