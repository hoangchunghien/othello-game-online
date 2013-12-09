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
import othello.command.ChatCmd;
import othello.command.GetBoardsCmd;
import othello.command.IChatCmdExec;
import othello.command.IGetBoardsCmdExec;
import othello.command.IJoinCmdExec;
import othello.command.IListCmdExec;
import othello.command.ILoginCmdExec;
import othello.command.IMoveCmdExec;
import othello.command.IQuitCmdExec;
import othello.command.IRedoCmdExec;
import othello.command.IResignCmdExec;
import othello.command.IUndoCmdExec;
import othello.command.ListCmd;
import othello.command.response.IResponse;
import othello.command.JoinCmd;
import othello.command.LoginCmd;
import othello.command.response.ResponseFactory;
import othello.common.AbstractPlayer;
import othello.common.Position;

import othello.configuration.Configuration;


/**
 *
 * @author Hien Hoang
 * @version Dec 2, 2013
 */
public class Client implements IMoveCmdExec, ILoginCmdExec, IUndoCmdExec, IRedoCmdExec,
        IResignCmdExec, IQuitCmdExec, IJoinCmdExec, IListCmdExec, IGetBoardsCmdExec, IChatCmdExec {
    
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
        
        LoginCmd login = new LoginCmd(null, username, password);
        System.out.println("Invoking command: " + login.serializeJSON());
        writer.println(login.serializeJSON());   
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
        
        JoinCmd join = new JoinCmd(null, locationId);   
        System.out.println("Sending command: " + join.serializeJSON());
        writer.println(join.serializeJSON());
    }

    @Override
    public void listLocations() {
        
        ListCmd list = new ListCmd(null, ListCmd.LOCATION);
        System.out.println("Sending command: " + list.serializeJSON());
        writer.println(list.serializeJSON());
    }

    @Override
    public void listPlayers() {
        
        ListCmd list = new ListCmd(null, ListCmd.PLAYER);
        System.out.println("Sending command: " + list.serializeJSON());
        writer.println(list.serializeJSON());
    }

    @Override
    public void listRooms() {
        
        ListCmd list = new ListCmd(null, ListCmd.ROOM);
        System.out.println("Sending command: " + list.serializeJSON());
        writer.println(list.serializeJSON());
    }


    @Override
    public void getBoards(String roomId) {
        GetBoardsCmd getBoards = new GetBoardsCmd(null, roomId);
        System.out.println("Sending command: " + getBoards.serializeJSON());
        writer.println(getBoards.serializeJSON());
    }

    @Override
    public void chat(String msg) {
        ChatCmd chat = new ChatCmd(this, msg);
        System.out.println("Sending command: " + chat.serializeJSON());
        writer.println(chat.serializeJSON());
    }

    @Override
    public void makeMove(Position position, AbstractPlayer caller) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
