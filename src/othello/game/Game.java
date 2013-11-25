/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.game;

/**
 *
 * @author Hien Hoang
 * @version Oct 22, 2013
 *
 */
public class Game {

    GameMonitor monitor;

    public Game() {

        monitor = GameMonitor.getInstance();
    }

    public void play() {

        monitor.start();
    }
    
}
