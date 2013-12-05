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
import javax.swing.SwingUtilities;
import org.json.JSONObject;
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
import othello.command.response.ResponseFactory;
import othello.common.Position;

import othello.configuration.Configuration;

/**
 *
 * @author Hien Hoang
 * @version Dec 2, 2013
 */
public class Client implements IMoveExec, ILoginExec, IUndoExec, IRedoExec,
        IResignExec, IQuitExec, IJoinExec, IListExec {
    
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
        
        SwingUtilities.invokeLater(new Runnable() {
            
            JSONObject response;
            
            @Override
            public void run() {
                System.out.println("Client listening...");
                while (true) {
                    try {
                        System.out.print("Reading..");
                        response = new JSONObject(reader.readLine());
                        System.out.print("Response: " + response);
                        IResponse responseCmd = ResponseFactory.getResponse(response);
                        responseCmd.execute();
                    } catch (IOException ex) {
                        
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    @Override
    public void doLogin(String username, String password) {
        
        String command = "login " + username + " " + password;
        System.out.println("Invoking command: " + command);
        writer.println(command);   
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
        
        String command = List.NAME + " " + List.LOCATION;
        System.out.println("Invoking command: " + command);
        writer.println(command);
    }

    @Override
    public void ListPlayers() {
        
        String command = List.NAME + " " + List.PLAYER;
        System.out.println("Invoking command: " + command);
        writer.println(command);
    }
    
}
