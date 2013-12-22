/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.game;

import othello.client.ClientGameMonitor;
import othello.client.OnlineGameMonitor;
import othello.configuration.Configuration;
import othello.configuration.TypeCfg;
import othello.ui.UIFactory;

/**
 *
 * @author Hien Hoang
 * @version Oct 22, 2013
 *
 */
public class Game {

    GameMonitor monitor;
    OnlineGameMonitor onlineMonitor;
    Configuration cfg = Configuration.getInstance();

    public Game(){
        
        UIFactory.getControlUI();
        
        if (cfg.getPlayingType().name.equalsIgnoreCase(TypeCfg.TYPE_ONLINE)) {
            onlineMonitor = OnlineGameMonitor.getInstance();
        } 
        else {
            monitor = ClientGameMonitor.getInstance();
        }
        
    }

    public void play() {

        System.out.println("Starting the game...");
        if (cfg.getPlayingType().name.equalsIgnoreCase(TypeCfg.TYPE_OFFLINE)) {
            monitor.start();
        }
        else {
        	
        }
        
    }
    
}
