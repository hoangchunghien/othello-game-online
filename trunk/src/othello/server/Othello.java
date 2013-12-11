package othello.server;

import java.io.IOException;
import othello.configuration.server.Configuration;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
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
    
    public static void main(String[] args) throws Exception {
        
        System.out.println("Othello server starting..." + Configuration.getInstance().port);
        System.out.println("Running port: " + Configuration.getInstance().port.number);
        ServerSocket listener = new ServerSocket(
                Configuration.getInstance().port.number);
        System.out.println("Othello server running");
        Station station = new Station();
//        List<Room> rooms = new ArrayList<Room>();
//        station.setRooms(rooms);
//        for (int i = 0; i < 10; i++) {
//            Room room = new Room();
//            room.setName("room " + i);
//            room.setId("room" + i);
//            List<Board> boards = new ArrayList<Board>();
//            room.setBoards(boards);
//            for (int j = 0; j < 10; j++) {
                Board board = new Board();
                board.setId("board" +1);
                board.setName("Board " + 1);
//                boards.add(board);
//            }
//            rooms.add(room);
//        }
        
        while (true) {
            
            try {
                Socket connection = listener.accept();
                System.out.println("Player accepted");
                Player player = new Player(connection);
                player.setLocation(board);
                player.getLocation().join(connection);
                player.startListenFromClient();
            }
            catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }
}
