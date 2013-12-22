package othello.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import othello.server.configuration.Configuration;
import othello.server.configuration.PortCfg;
import othello.server.location.Board;
import othello.server.location.Room;
import othello.server.location.Station;

/**
 * OTHELLO SERVER
 * @author Hien Hoang
 * @version Dec 4, 2013
 * Description 
 * . TODO
 */
public class Othello {
	
    static HashMap<String, Player> playingTicket = new HashMap<>();
    static Configuration cfg = Configuration.getInstance();
    
    public static void main(String[] args) throws Exception {
        
    	// Start [selection] port
        System.out.print("Starting port [selection] -> " + cfg.ports.getPort(PortCfg.NAME_SELECTION).number + " ...");
        ServerSocket listenerSelection = new ServerSocket(
                cfg.ports.getPort(PortCfg.NAME_SELECTION).number);
        SelectionListener selectionListener = new SelectionListener(listenerSelection, playingTicket);
        selectionListener.start();
        System.out.println("[OK]");
        
        // Start [playing] port
        System.out.print("Starting port [playing] -> " + cfg.ports.getPort(PortCfg.NAME_PLAYING).number + " ...");
        ServerSocket listenerPlaying = new ServerSocket(
                cfg.ports.getPort(PortCfg.NAME_PLAYING).number);
        PlayingListener playingListener = new PlayingListener(listenerPlaying, playingTicket);
        playingListener.start();
        System.out.println("[OK]" );
        
        System.out.println("Othello server started");
        
    }
       
}

class SelectionListener extends Thread {
	
	ServerSocket listener;
	HashMap<String, Player> playingTicket;
	
	public SelectionListener(ServerSocket listener, HashMap<String, Player> playingTicket) {
		
		this.listener = listener;
		this.playingTicket = playingTicket;
	}
	public void run() {
		while (true) {
			try {
				Socket connection = listener.accept();
				System.out.println("Selecter accepted");
				Selecter selecter = new Selecter(connection);
				selecter.startListenFromClient();
			}
			catch (IOException ie) {
                ie.printStackTrace();
            }
		}
	}
}

class PlayingListener extends Thread {
	
	ServerSocket listener;
	HashMap<String, Player> playingTicket;
	
	public PlayingListener(ServerSocket listener, HashMap<String, Player> playingTicket) {
		
		this.listener = listener;
		this.playingTicket = playingTicket;
	}
	
	public void run() {
		while (true) {
			try {
				Socket connection = listener.accept();
				System.out.println("Checking player, waiting for ticket");
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String ticket = reader.readLine();
				System.out.println("Got a ticket: " + ticket);
				Player player = playingTicket.get(ticket);
				if (player != null) {
					player.setConnection(connection);
					player.startListenFromClient();
				}
				else {
					connection.close();
				}
			}
			catch (IOException ie) {
                ie.printStackTrace();
            }
		}
	}
}