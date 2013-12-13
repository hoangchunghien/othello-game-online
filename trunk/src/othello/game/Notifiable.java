package othello.game;

/**
 *
 * @author Hien Hoang
 */
public interface Notifiable {
    public void receiveChangeNotification(int category, Object detail);
}
