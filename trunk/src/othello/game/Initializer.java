package othello.game;

import othello.ui.UIFactory;

/**
 *
 * @author Hien Hoang
 */
public class Initializer {
    public static void initialize() {
        // Init the notification board first
        NotificationBoard.getInstance();
        
        // Init the UI
        UIFactory.getControlUI().show();
    }
}
