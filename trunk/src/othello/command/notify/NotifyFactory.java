package othello.command.notify;

import org.json.JSONObject;
import othello.client.OnlineGameMonitor;

/**
 *
 * @author Hien Hoang
 */
public class NotifyFactory {
    public static INotification getNotifyCommand(JSONObject json) {
        
        switch (json.getString("command")) {
            case MoveTurnNtf.NAME:
                System.out.println("Got executor for move turn");
                MoveTurnNtf moveTurnNtf = new MoveTurnNtf(OnlineGameMonitor.getInstance());
                moveTurnNtf.deserializeJSON(json);
                return moveTurnNtf;
                
            case GameStateNtf.NAME:
                GameStateNtf gameStateNtf = new GameStateNtf(OnlineGameMonitor.getInstance(), null);
                gameStateNtf.deserializeJSON(json);
                return gameStateNtf;
                
            case GameOverNtf.NAME:
                GameOverNtf gameOverNtf = new GameOverNtf(OnlineGameMonitor.getInstance());
                gameOverNtf.deserializeJSON(json);
                return gameOverNtf;
                
            default:
                return null;
        }
    }
}
