package othello.command;

import org.json.JSONObject;
import othello.common.AbstractPlayer;

/**
 *
 * @author Hien Hoang
 * @version Dec 8, 2013
 * Description
 * . This command is client only
 * . Use for invoke the executor to get a move
 */
public class GetMoveCmd implements ICommand {

    private IGetMoveCmdExec executor;
    private AbstractPlayer caller;
    
    public GetMoveCmd(IGetMoveCmdExec executor, AbstractPlayer caller) {
        this.executor = executor;
        this.caller = caller;
    }
    @Override
    public void execute() {
        if (caller != null) {
            executor.getMoveFor(caller);
        }
    }

    @Override
    public JSONObject serializeJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deserializeJSON(JSONObject jObj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
