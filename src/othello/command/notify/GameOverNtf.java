package othello.command.notify;

import org.json.JSONObject;

/**
 *
 * @author Hien Hoang
 */
public class GameOverNtf implements INotification {
    public final static String NAME = "gameover";
    
    IGameOverNtfExec gameOverNotifyExecutor;
    
    public GameOverNtf(IGameOverNtfExec executor) {
        this.gameOverNotifyExecutor = executor;
    }

    @Override
    public void execute() {
        this.gameOverNotifyExecutor.makeOverGame();
    }

    @Override
    public JSONObject serializeJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
