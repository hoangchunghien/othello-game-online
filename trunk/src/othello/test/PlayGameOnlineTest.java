package othello.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import othello.server.Player;
import othello.server.configuration.Configuration;
import othello.server.location.Board;

/**
 *
 * @author Hien Hoang
 */
public class PlayGameOnlineTest {
    
    public static void main(String args[]){
        Process process;
        try {
            process = Runtime.getRuntime().exec("java ");
            printLines(" stdout:", process.getInputStream());
            printLines(" stderr:", process.getErrorStream());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


      }

      private static void printLines(String name, InputStream ins) throws Exception {
          String line = null;
          BufferedReader in = new BufferedReader(new InputStreamReader(ins));
          while ((line = in.readLine()) != null) {
              System.out.println(name + " " + line);
          }
        }
}

//class OneBoardServerTest {
//    
//    public static void main(String args[]) throws Exception {
//        System.out.println("Othello server starting..." + Configuration.getInstance().port);
//        System.out.println("Running port: " + Configuration.getInstance().port.number);
//        ServerSocket listener = new ServerSocket(
//                Configuration.getInstance().port.number);
//        System.out.println("Othello server running");
//
//        Board board = new Board();
//        board.setId("board" +1);
//        board.setName("Board " + 1);
//        
//        while (true) {
//            
//            try {
//                Socket connection = listener.accept();
//                System.out.println("Player accepted");
//                Player player = new Player(connection);
//                player.setLocation(board);
//                player.getLocation().join(connection);
//                player.startListenFromClient();
//            }
//            catch (IOException ie) {
//                ie.printStackTrace();
//            }
//        }
//    }
//}
