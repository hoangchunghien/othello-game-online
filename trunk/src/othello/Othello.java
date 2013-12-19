package othello;

import othello.client.OnlineGameMonitor;
import othello.command.JoinPlayerCmd;
import othello.configuration.Configuration;
import othello.configuration.TypeCfg;
import othello.game.Game;
import othello.game.Initializer;
import othello.ui.MainFrame;

/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class Othello {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
//        if (Configuration.getInstance().getPlayingType().name.equalsIgnoreCase(TypeCfg.TYPE_ONLINE)) {
//            JoinPlayerCmd joinPlayer = new JoinPlayerCmd(OnlineGameMonitor.getInstance(), null);
//            joinPlayer.execute();
//        }
//        Game game = new Game();     
//        game.play();
    	MainFrame mainFrame = new MainFrame();
    }
    
}
