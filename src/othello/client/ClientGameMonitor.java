package othello.client;

import othello.game.GameMonitor;

/**
 *
 * @author Hien Hoang
 */
public class ClientGameMonitor extends GameMonitor {

    private static ClientGameMonitor singletonObject;
    
    public static ClientGameMonitor getInstance() {
        if (singletonObject == null) {
            singletonObject = new ClientGameMonitor();
        }
        return singletonObject;
    }
    
}
