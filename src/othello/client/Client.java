package othello.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import othello.command.Chat;
import othello.command.GetBoards;
import othello.command.IChatCmdExec;
import othello.command.IGetBoardsExec;
import othello.command.IJoinExec;
import othello.command.IListExec;
import othello.command.ILoginExec;
import othello.command.IMoveExec;
import othello.command.IQuitExec;
import othello.command.IRedoExec;
import othello.command.IResignExec;
import othello.command.IUndoExec;
import othello.command.List;
import othello.command.response.IResponse;
import othello.command.Join;
import othello.command.Login;
import othello.command.response.ResponseFactory;
import othello.common.Position;

import othello.configuration.Configuration;


/**
 *
 * @author Hien Hoang
 * @version Dec 2, 2013
 */
public class Client implements IMoveExec, ILoginExec, IUndoExec, IRedoExec,
        IResignExec, IQuitExec, IJoinExec, IListExec, IGetBoardsExec, IChatCmdExec {
    
    private String serverAddress;
    private int serverPort;
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private PrintWriter writer;
    private BufferedReader reader;
    
    // Certificate to check user is login success or not
    // In server, this certificate string are bind with this socket
    private String loginCertificate;
    
    // User profile, the information about the user like username, 
    // full name, level, type, etc...
    private Profile profile;
    
    
    static Client singletonObject;
    
    public static Client getInstance() {
        
        if (singletonObject == null) {
            singletonObject = new Client();
        }
        return singletonObject;
    }
    
    public Client() {
        
        try {
            
            loginCertificate = null;
            serverAddress = Configuration.getInstance().getSelectedServer().address;
            serverPort = Configuration.getInstance().getSelectedServer().port;
            profile = new Profile();
            socket = new Socket(serverAddress, serverPort);
            output = socket.getOutputStream();
            input = socket.getInputStream();
            writer = new PrintWriter(output, true);
            reader = new BufferedReader(new InputStreamReader(input));
            listenResponseFromServer();
            
        } catch(UnknownHostException ue) {
            // Notify can't connect to server
            System.out.print(ue);
            
        } catch (IOException ie) {
            System.out.print(ie);
        }
    }
    
    public void setLoginCertificate(String loginCertificate) {
        
        this.loginCertificate = loginCertificate;
    }
    
    private void listenResponseFromServer() {
        
        Listener listener = new Listener(reader);
        listener.start();
    }
    
    @Override
    public void doLogin(String username, String password) {
        
        Login login = new Login(null, username, password);
        System.out.println("Invoking command: " + login.serializeJSON());
        writer.println(login.serializeJSON());   
    }
    
    @Override
    public void makeMove(Position position){
        
        String command = "move " + position.getX() + " " + position.getY();
        System.out.println("Invoking command: " + command);
        writer.println(command);
    }

    @Override
    public void makeUndo() {
        
        String command = "undo ";
        System.out.println("Invoking command: " + command);
        writer.println(command);
    }

    @Override
    public void makeRedo() {
    }

    @Override
    public void makeResign() {
        
    }

    @Override
    public void quit() {

    }

    @Override
    public void join(String locationId) {
        
        Join join = new Join(null, locationId);   
        System.out.println("Sending command: " + join.serializeJSON());
        writer.println(join.serializeJSON());
    }

    @Override
    public void listLocations() {
        
        List list = new List(null, List.LOCATION);
        System.out.println("Sending command: " + list.serializeJSON());
        writer.println(list.serializeJSON());
    }

    @Override
    public void listPlayers() {
        
        List list = new List(null, List.PLAYER);
        System.out.println("Sending command: " + list.serializeJSON());
        writer.println(list.serializeJSON());
    }

    @Override
    public void listRooms() {
        
        List list = new List(null, List.ROOM);
        System.out.println("Sending command: " + list.serializeJSON());
        writer.println(list.serializeJSON());
    }


    @Override
    public void getBoards(String roomId) {
        GetBoards getBoards = new GetBoards(null, roomId);
        System.out.println("Sending command: " + getBoards.serializeJSON());
        writer.println(getBoards.serializeJSON());
    }

    @Override
    public void chat(String msg) {
        Chat chat = new Chat(this, msg);
        System.out.println("Sending command: " + chat.serializeJSON());
        writer.println(chat.serializeJSON());
    }
    
}
class Listener extends Thread {
    
    private BufferedReader reader;
    public Listener(BufferedReader reader) {
        this.reader = reader;
    }
    
    @Override
    public void run() {
        JSONObject response;
        System.out.println("Client listening...");
        while (true) {
            try {
                System.out.println("Reading..");
                response = new JSONObject(reader.readLine());
                System.out.println("Received: " + response);
                if (response.getString("cmdType").equalsIgnoreCase("response")) {
                    System.out.println("Finding executor...");
                    IResponse responseCmd = ResponseFactory.getResponse(response);
                    if (responseCmd == null) {
                        System.out.println("Executor not found...");
                    }
                    else {
                        System.out.println("Found executor");
                        ResponseExecuting exe = new ResponseExecuting(responseCmd);
                        exe.start();
                    }
                }
            } catch (IOException ex) {

                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

class ResponseExecuting extends Thread {
    
    private IResponse response;
    
    public ResponseExecuting(IResponse response) {
        this.response = response;
    }
    
    @Override
    public void run() {
        System.out.println("Executing response...");
        this.response.execute();
    }
}
