package othello.server.location;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import othello.command.response.FetchBoardListRes;
import othello.command.response.FetchRoomListRes;
import othello.command.response.JoinPlayerRes;
import othello.models.Location;
import othello.server.Othello;
import othello.server.Player;

public class LocationManager {
	
	private List<Room> rooms;
	private List<Board> boards;
	
	private static LocationManager instance;
	public static LocationManager getInstance() {
		if (instance == null) {
			instance = new LocationManager();
		}
		return instance;
	}
	
	public LocationManager() {
		
		initialize();
	}
	
	private void initialize() {
		rooms = new ArrayList<>();
		boards = new ArrayList<>();
		
		for (int i = 0; i < 50; i++ ) {
			Room room = new Room();
			room.setId("r" + i);
			room.setName("room " + i);
			List<Board> _boards = new ArrayList<>();
			room.setBoards(_boards);
			for (int j = 0; j < 50; j++) {
				Board board = new Board();
              	board.setId("b" + i + j);
              	board.setName("Board " + i + j);
              	_boards.add(board);
              	boards.add(board);
			}
			rooms.add(room);
 		}
	}
	
	public void fetchRoomList(Socket connection) {
		java.util.List<Location> locations = new ArrayList<>();
        for (Room room : rooms) {
            Location lo = new Location();
            lo.id = room.getId();
            lo.name = room.getName();
            lo.numUsers = 0;
            locations.add(lo);
        }
        FetchRoomListRes list = new FetchRoomListRes(null, FetchRoomListRes.ACCEPTED, "OK", locations);
        sendTo(connection, list.serializeJSON());
	}
	
	public void fetchBoardList(Socket connection, String roomId) {
        List<othello.models.Board> boards = new ArrayList<>();
        for (Room room : rooms) {
        	if (room.getId().equals(roomId)) {
        		System.out.println("Room id: " + roomId);
        		System.out.println("Board nums: " + room.getBoards().size());
        		for (Board b : room.getBoards()) {
        			othello.models.Board mB = new othello.models.Board(b.getName());
        			mB.setId(b.getId());
        			boards.add(mB);
        		}
        		break;
        	}
        }
        FetchBoardListRes boardsRes = new FetchBoardListRes(null, boards);
        sendTo(connection, boardsRes.serializeJSON());
	}
	
	public void joinPlayer(Socket connection, String boardId) {
		for (Board board : boards)
		{
			if (board.getId().equals(boardId))
			{
				Player player = new Player(null);
				board.joinPlayer(player);
				player.setLocation(board);
				player.setBoard(board);
				
				String ticket = Othello.getTicketPlayer().get(player);
				
				JoinPlayerRes res = new JoinPlayerRes(null);
				res.setPlayerTicket(ticket);
				res.setMessage("");
				res.setStatus(JoinPlayerRes.ACCEPTED);
				
				sendTo(connection, res.serializeJSON());
			}
		}
	}
	
	private void sendTo(Socket soc, JSONObject jObj){
        try {
            
            PrintWriter writer = new PrintWriter(soc.getOutputStream(), true);
            writer.println(jObj);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
