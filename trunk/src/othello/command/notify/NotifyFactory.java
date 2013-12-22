package othello.command.notify;

import javax.swing.JOptionPane;

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
            	// JOptionPane.showMessageDialog(null, "Got executor for move turn");
                MoveTurnNtf moveTurnNtf = new MoveTurnNtf(OnlineGameMonitor.getInstance());
                moveTurnNtf.deserializeJSON(json);
                return moveTurnNtf;
                
            case GameStateNtf.NAME:
            	// JOptionPane.showMessageDialog(null, "Got state changed message");
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
