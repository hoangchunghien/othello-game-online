package othello.auth;

import java.net.Socket;
import java.util.Dictionary;
import othello.models.Player;

/**
 *
 * @author Hien Hoang
 */
public class LoggedInPlayersManager {
    
    private Dictionary<Socket, Player> loggedInPlayers;
    
    private static LoggedInPlayersManager singletonObject;
    
    public static LoggedInPlayersManager getInstance() {
        if (singletonObject == null) {
            singletonObject = new LoggedInPlayersManager();
        }
        return singletonObject;
    }
    
    public Player getPlayer(Socket connection) {
        
        return loggedInPlayers.get(connection);
    }
}
