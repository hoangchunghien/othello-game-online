package othello.game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Hien Hoang
 */
public class NotificationBoard {

    public static final Integer NF_UNDOABLE = 0;
    public static final Integer NF_REDOABLE = 1;
    public static final Integer NF_DRAWABLE = 2;
    public static final Integer NF_RESIGNABLE = 3;
    public static final Integer NF_MOVEABLE = 4;
    public static final Integer NF_GAMESTATE_CHANGED = 5;
    public static final Integer NF_UNDOCALLED = 6;
    public static final Integer NF_TIMEOUT = 7;
    public static final Integer NF_TIMEMOVE_CHANGED = 8;
    public static final Integer NF_TIMEGAME_CHANGED = 9;
    public static final Integer NF_TIMER_PAUSED = 10;
    public static final Integer NF_TIMER_RESUME = 11;
    public static final Integer NF_MOVE_TURN = 12;
    public static final Integer NF_TIMER_START = 13;
    public static final Integer NF_GAMEOVER = 14;
    public static final Integer NF_UI_NEXT = 15;
    public static final Integer NF_UI_BACK = 16;
    public static final Integer NF_GAME_EXITED = 17;
    public static final Integer NF_GAME_STARTING = 18;
    public static final Integer NF_START_GAME = 19;
    
    private HashMap<Integer, ArrayList<Notifiable>> clientMap;
    
    public NotificationBoard() {
        clientMap = new HashMap<>();
    }
    
    private static NotificationBoard singletonObject;
    private static final Object lock = new Object();
    
    public static NotificationBoard getInstance() {
        synchronized(lock) {
            if (singletonObject == null) {
                singletonObject = new NotificationBoard();
            }
            return singletonObject;
        }
    }
    
    public void subscribe(Notifiable client, Integer category){
        synchronized(lock) {
            if (clientMap.get(category) == null) {
                clientMap.put(category, new ArrayList<Notifiable>());
            }
            ArrayList<Notifiable> notifiableList = clientMap.get(category);
            if (notifiableList.contains(client)) {
                // System.out.println("Notifiable list already contain this object");
                return;
            }
            else {
                // System.out.println("Subscribed: " + client);
                notifiableList.add(client);
            }
        }
    }
    
    public void unsubscribe(Notifiable client, Integer category) {
        if (clientMap.get(category) == null) {
            return;
        }
        else {
            ArrayList<Notifiable> notifiableList = clientMap.get(category);
            notifiableList.remove(client);
        }
    }
    
    public void fireChangeNotification(Integer category, Object detail) {
        synchronized(lock) {
            if (clientMap.get(category) == null) {

                return;
            }
            else {

                ArrayList<Notifiable> notifiableList = clientMap.get(category);
//                System.out.println("Category: " + category);
//                System.out.println("List: " + notifiableList);
//                System.out.println("ListLength: " + notifiableList.size());
//                System.out.println("Detail: " + detail);
                for (Notifiable notify : notifiableList) {
                    notify.receiveChangeNotification(category, detail);
                }
            }
        }
    }
    
}
