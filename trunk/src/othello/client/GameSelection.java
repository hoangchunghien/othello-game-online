package othello.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import org.json.JSONObject;

import othello.command.FetchBoardListCmd;
import othello.command.FetchBoardListCmdExecutable;
import othello.command.FetchRoomListCmd;
import othello.command.FetchRoomListCmdExecutable;
import othello.command.JoinPlayerCmd;
import othello.command.JoinPlayerCmdExecutable;
import othello.command.notify.INotification;
import othello.command.notify.NotifyFactory;
import othello.command.response.FetchBoardListResExecutable;
import othello.command.response.FetchRoomListResExecutable;
import othello.command.response.IResponse;
import othello.command.response.JoinPlayerResExecutable;
import othello.command.response.ResponseFactory;
import othello.common.AbstractPlayer;
import othello.configuration.Configuration;
import othello.game.NotificationBoard;
import othello.models.Board;
import othello.models.Location;

public class GameSelection implements FetchBoardListCmdExecutable, FetchRoomListCmdExecutable, FetchRoomListResExecutable,
	FetchBoardListResExecutable, JoinPlayerCmdExecutable, JoinPlayerResExecutable {
	
    private String serverAddress;
    private int serverPort;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    
    protected Configuration cfg = Configuration.getInstance();
    protected NotificationBoard nb = NotificationBoard.getInstance();
    
    private static GameSelection instance;
    public static GameSelection getInstance() {
    	if (instance == null) {
    		instance = new GameSelection();
    	}
    	return instance;
    }
    
    public GameSelection() {
    	initialize();
    	startListenFromServer();
    }
    
    private void initialize() {
    	try {
	    	serverAddress = Configuration.getInstance().getSelectedServer().address;
	        serverPort = Configuration.getInstance().getSelectedServer().selectionPort;
	        socket = new Socket(serverAddress, serverPort);
	        writer = new PrintWriter(socket.getOutputStream(), true);
	        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	}
    	catch (UnknownHostException ue) {
    		
    		ue.printStackTrace();    		
    	} 
    	catch (IOException e) {

			e.printStackTrace();
		}
    }
    
    private void startListenFromServer() {
    	
    	SelectingListener listener = new SelectingListener(reader);
    	listener.start();
    }

	@Override
	public void fetchRoomList(String stationId) {
		FetchRoomListCmd command = new FetchRoomListCmd(null, stationId);
        System.out.println("Sending command: " + command.serializeJSON());
        writer.println(command.serializeJSON());
	}

	@Override
	public void fetchBoardList(String roomId) {
		
		FetchBoardListCmd command = new FetchBoardListCmd(null, roomId);
        System.out.println("Sending command: " + command.serializeJSON());
        writer.println(command.serializeJSON());
	}

	@Override
	public void loadRoomList(List<Location> rooms) {
		nb.fireChangeNotification(NotificationBoard.NF_ROOM_LIST_CHANGED, rooms);
	}

	@Override
	public void loadBoardList(List<Board> boards) {
		nb.fireChangeNotification(NotificationBoard.NF_BOARD_LIST_CHANGED, boards);
	}

	@Override
	public void joinPlayer(String boardId) {
		JoinPlayerCmd joinPlayerCmd = new JoinPlayerCmd(null);
        System.out.println("Sending command: " + joinPlayerCmd.serializeJSON());
        writer.println(joinPlayerCmd.serializeJSON());
	}

	@Override
	public void joinAccepted(String playerTicket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinRejected(String message) {
		// TODO Auto-generated method stub
		
	}
}

class SelectingListener extends Thread {
	private BufferedReader reader;
	
    public SelectingListener(BufferedReader reader) {
        this.reader = reader;
    }
    
    @Override
    public void run() {
        JSONObject receivedJSON;
        System.out.println("Client listening...");
        while (true) {
            try {
                System.out.println("Reading..");
                receivedJSON = new JSONObject(reader.readLine());
                System.out.println("Received: " + receivedJSON);
                if (receivedJSON.getString("cmdType").equalsIgnoreCase("response")) {
                    System.out.println("Received Response message");
                    IResponse responseCmd = ResponseFactory.getResponse(receivedJSON);
                    if (responseCmd == null) {
                        System.out.println("Executor not found...");
                    }
                    else {
                        System.out.println("Found executor");
                        ResponseExecuting exe = new ResponseExecuting(responseCmd);
                        exe.start();
                    }
                }
                else if (receivedJSON.getString("cmdType").equalsIgnoreCase("notify")) {
                    System.out.println("Received Notify message");
                    INotification notifyCmd = NotifyFactory.getNotifyCommand(receivedJSON);
                    if (notifyCmd == null) {
                        System.out.println("Notify '" + receivedJSON.getString("command") +
                                "' unsupported");
                    }
                    else {
                        NotifyExecuting exe = new NotifyExecuting(notifyCmd);
                        exe.start();
                    }
                }
                
            } 
            catch (IOException ex) {

                ex.printStackTrace();
            }
        }
    }
}