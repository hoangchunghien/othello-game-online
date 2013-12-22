package othello.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import othello.command.CommandFactory;
import othello.command.Commandable;
import othello.command.Executable;
import othello.command.FetchBoardListCmdExecutable;
import othello.command.FetchRoomListCmdExecutable;
import othello.server.location.LocationManager;

public class Selecter implements FetchRoomListCmdExecutable, FetchBoardListCmdExecutable, Executable {
	
	private Socket connection;
	private BufferedReader reader;
    private PrintWriter writer;
    
    private LocationManager locationManager = LocationManager.getInstance();
    
	public Selecter(Socket connection) {
		this.connection = connection;
		initialize();
	}
	
	private void initialize() {
		try {
            
            writer = new PrintWriter(connection.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } 
		catch (IOException ex) {
        	ex.printStackTrace();
        }
	}

	@Override
	public void fetchRoomList(String stationId) {
        locationManager.fetchRoomList(connection);
	}

	@Override
	public void fetchBoardList(String roomId) {
		locationManager.fetchBoardList(connection, roomId);
	}
	
    public void startListenFromClient() {
        if (connection != null) {
	        CommandListener listener = new CommandListener(this);
	        listener.start();
        }
    }
    
    class CommandListener extends Thread {
        Executable executor;
        
        public CommandListener(Executable executor) {
        	this.executor = executor;
        }
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("waiting command...");
                    JSONObject command = new JSONObject(reader.readLine());
                    System.out.println("Received command: " + command);
                    Commandable cmd = CommandFactory.getServerCommand(executor, null, command);
                    if (cmd != null) {
                        
                        cmd.execute();
                    }
                }
            } catch (IOException ex) {
                    
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
