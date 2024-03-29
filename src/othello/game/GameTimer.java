package othello.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.TimerTask;
import javax.swing.Timer;
import othello.common.AbstractPlayer;
import othello.configuration.Configuration;


/**
 *
 * @author Hien Hoang
 * @since Dec 13, 2013
 * @version Dec 13, 2013
 * Description
 * . For play game time
 */
public class GameTimer extends TimerTask implements Notifiable {
    
    protected HashMap<AbstractPlayer, Integer> timePerMove;
    protected HashMap<AbstractPlayer, Integer> timePerGame;
    protected NotificationBoard nb = NotificationBoard.getInstance();
    Configuration cfg = Configuration.getInstance();
    
    ActionListener taskPerformer = new ActionListener() {
        
      public void actionPerformed(ActionEvent evt) {
         handleTimer(onServicePlayer);
      }
    };
    Timer timer = new Timer(1000, taskPerformer);
    
    protected AbstractPlayer onServicePlayer;
    protected int maxTimePerMove;
    protected int maxTimePerGame;
    
    public GameTimer() {
    	initialize();
        nb.subscribe(this, NotificationBoard.NF_MOVE_TURN);
        nb.subscribe(this, NotificationBoard.NF_GAMEOVER);
    }
    
    public GameTimer(NotificationBoard nb) {
    	this.nb = nb;
    	initialize();
        nb.subscribe(this, NotificationBoard.NF_MOVE_TURN);
        nb.subscribe(this, NotificationBoard.NF_GAMEOVER);
    }
    
    public void initialize() {
    	timePerGame = new HashMap<>();
        timePerMove = new HashMap<>();
        maxTimePerGame = cfg.timer.getSelectedGameTimer().value;
        maxTimePerMove = cfg.timer.getSelectedMoveTimer().value;
    }

    public int getMaxTimePerMove() {
        return maxTimePerMove;
    }

    public void setMaxTimePerMove(int maxTimePerMove) {
        this.maxTimePerMove = maxTimePerMove;
        AbstractPlayer[] players = timePerMove.keySet().toArray(new AbstractPlayer[timePerMove.size()]);
        for (AbstractPlayer player : players) {
        	timePerMove.put(player, maxTimePerMove);
        }
    }

    public int getMaxTimePerGame() {
        return maxTimePerGame;
    }

    public void setMaxTimePerGame(int maxTimePerGame) {
        this.maxTimePerGame = maxTimePerGame;
        AbstractPlayer[] players = timePerGame.keySet().toArray(new AbstractPlayer[timePerGame.size()]);
        for (AbstractPlayer player : players) {
        	timePerGame.put(player, maxTimePerGame);
        }
    }
    
    
    public void registerTimer(AbstractPlayer player) {
        if (!timePerMove.containsKey(player)) {
            timePerMove.put(player, maxTimePerMove);
        }
        if (!timePerGame.containsKey(player)) {
            timePerGame.put(player, maxTimePerGame);
        }
    }
    
    protected void renewTimePerMove(AbstractPlayer player) {
        
        if (timePerGame.get(player) > maxTimePerMove) {
            
            timePerMove.put(player, maxTimePerMove);
        }
        else {
            
            timePerMove.put(player, timePerGame.get(player));
        }
    }
    
    public Integer getResidualTimePerGame(AbstractPlayer player) {
        if (timePerGame.get(player) != null) {
            return timePerGame.get(player);
        }
        return null;
    } 
    
    public Integer getResidualTimePerMove(AbstractPlayer player) {
        if (timePerMove.get(player) != null) {
            return timePerMove.get(player);
        }
        return null;
    }
    
    protected void handleTimer(AbstractPlayer player) {
        
        int secsPerMove = timePerMove.get(player) - 1;
        int secsPerGame = timePerGame.get(player) - 1;
        if (secsPerMove > 0 && secsPerGame > 0) {
            timePerMove.put(player, secsPerMove);
            timePerGame.put(player, secsPerGame);           
        }
        else {
            nb.fireChangeNotification(NotificationBoard.NF_TIMEOUT, player);
            timer.stop();
        }
        
        nb.fireChangeNotification(NotificationBoard.NF_TIMEMOVE_CHANGED, secsPerMove);
        nb.fireChangeNotification(NotificationBoard.NF_TIMEGAME_CHANGED, secsPerGame);
    }

    @Override
    public void receiveChangeNotification(int category, Object detail) {
        
        if (category == NotificationBoard.NF_MOVE_TURN) {
            AbstractPlayer player = (AbstractPlayer)detail;
            this.onServicePlayer = player;
            timer.stop();
            this.renewTimePerMove(player);
            timer.restart();
        }
        
        if (category == NotificationBoard.NF_GAMEOVER) {
            timer.stop();
        }
        
        if (category == NotificationBoard.NF_GAME_EXITED){
        	timer.stop();
        }
        
        if (category == NotificationBoard.NF_GAME_STARTING) {
        	
        }
    }

    @Override
    public void run() {
        handleTimer(onServicePlayer);
    }
}
