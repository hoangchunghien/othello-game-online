package othello.server.location;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import othello.command.response.FetchBoardListRes;
import othello.command.response.FetchRoomListRes;
import othello.common.Piece;
import othello.models.Location;

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
		
		for (int i = 0; i < 10; i++ ) {
			Room room = new Room();
			room.setId("r" + i);
			room.setName("room " + i);
			List<Board> _boards = new ArrayList<Board>();
			room.setBoards(boards);
			for (int j = 0; j < 10; j++) {
				Board board = new Board();
              	board.setId("b" + i + j);
              	board.setName("Board " + 1);
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
        for (int i = 0; i < 100; i++) {
            othello.models.Board board = new othello.models.Board("board " + i);
            othello.models.Player p1 = new othello.models.Player();
            p1.setUsername("user" + i);
            p1.setType(1);
            p1.setScore(i+100);
            othello.models.Player p2 = new othello.models.Player();
            p2.setUsername("opp" + i);
            p2.setType(2);
            p2.setScore(i + 200);
            board.setPlayer(Piece.BLACK, p1);
            board.setPlayer(Piece.WHITE, p2);
            boards.add(board);
        }
        FetchBoardListRes boardsRes = new FetchBoardListRes(null, boards);
        sendTo(connection, boardsRes.serializeJSON());
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
