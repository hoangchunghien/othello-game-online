package othello.auth;

import java.net.Socket;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 * @author Hien Hoang
 */
public class LoginManager {
    
    private Dictionary<Socket, Boolean> loggedInConnections;
    
    private static LoginManager singletonObject;
    
    public static LoginManager getInstance() {
        if (singletonObject == null) {
            singletonObject = new LoginManager();
        }
        return singletonObject;
    }
    
    public LoginManager() {
        
        loggedInConnections = new Hashtable<>();
    }
    
    public boolean isLoggedIn(Socket connection) {
        Boolean loggedIn = loggedInConnections.get(connection);
        if (loggedIn != null && loggedIn == Boolean.TRUE) {
            return true;
        }
        return false;
    }
    
    public void login(Socket connection, String username, String password) {
        
        LoginAuthenticator authenticator = new LoginAuthenticator();
        if (authenticator.doLogin(connection, username, password)) {
            loggedInConnections.put(connection, Boolean.TRUE);
        }
    }
}
