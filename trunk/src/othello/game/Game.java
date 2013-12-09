/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.game;

import othello.client.ClientGameMonitor;
import othello.ui.UIFactory;

/**
 *
 * @author Hien Hoang
 * @version Oct 22, 2013
 *
 */
public class Game {

    GameMonitor monitor;

    public Game() {

        monitor = ClientGameMonitor.getInstance();
    }

    public void play() {

        System.out.println("Starting the game...");
        monitor.start();
        
        System.out.println("Showing the UI...");
        UIFactory.getControlUI().show();
    }
    
}
