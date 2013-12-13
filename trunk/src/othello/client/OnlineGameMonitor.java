package othello.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import othello.command.ChatCmd;
import othello.command.GetBoardsCmd;
import othello.command.IChatCmdExec;
import othello.command.IGetBoardsCmdExec;
import othello.command.IJoinCmdExec;
import othello.command.IJoinPlayerCmdExec;
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
import othello.command.JoinPlayerCmd;
import othello.command.LoginCmd;
import othello.command.MoveCmd;
import othello.command.notify.GameStateNtfExec;
import othello.command.notify.IGameOverNtfExec;
import othello.command.notify.INotification;
import othello.command.notify.IPassNtfExec;
import othello.command.notify.MoveTurnNtfExec;
import othello.command.notify.NotifyFactory;
import othello.command.response.AnswerRequestResExec;
import othello.command.response.IJoinPlayerResExec;
import othello.command.response.IMoveResExec;
import othello.command.response.MoveRes;
import othello.command.response.ResponseFactory;
import othello.common.AbstractPlayer;
import othello.common.Piece;
import othello.common.Position;

import othello.configuration.Configuration;
import othello.configuration.PlayerCfg;
import othello.game.GameState;
import othello.game.GameStateChangedListener;
import othello.game.NotificationBoard;


/**
 *
 * @author Hien Hoang
 * @version Dec 2, 2013
 */
public class OnlineGameMonitor implements IMoveCmdExec, ILoginCmdExec, IUndoCmdExec, IRedoCmdExec,
        IResignCmdExec, IQuitCmdExec, IJoinCmdExec, IListCmdExec, IGetBoardsCmdExec, IChatCmdExec,
        IMoveResExec, IJoinPlayerCmdExec, IJoinPlayerResExec, MoveTurnNtfExec, 
        GameStateNtfExec, IPassNtfExec, IGameOverNtfExec, AnswerRequestResExec {
    
    private String serverAddress;
    private int serverPort;
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private PrintWriter writer;
    private BufferedReader reader;
    
    protected Configuration cfg = Configuration.getInstance();
    protected AbstractPlayer onlinePlayer;
    protected List<GameStateChangedListener> stateChangedListeners;
    protected NotificationBoard nb = NotificationBoard.getInstance();
    
    // User profile, the information about the user like username, 
    // full name, level, type, etc...
    
    
    static OnlineGameMonitor singletonObject;
    
    public static OnlineGameMonitor getInstance() {
        
        if (singletonObject == null) {
            singletonObject = new OnlineGameMonitor();
        }
        return singletonObject;
    }
    
    public OnlineGameMonitor() {
        initialize();
        listenResponseFromServer();
    }
    
    private void initialize() {
        try {
            stateChangedListeners = new ArrayList<>();
            serverAddress = Configuration.getInstance().getSelectedServer().address;
            serverPort = Configuration.getInstance().getSelectedServer().port;
            socket = new Socket(serverAddress, serverPort);
            output = socket.getOutputStream();
            input = socket.getInputStream();
            writer = new PrintWriter(output, true);
            reader = new BufferedReader(new InputStreamReader(input));
            
            if (cfg.players.autoViewer) {
                if (cfg.players.players.get(cfg.players.getPlayerOnlineIndex())
                        .getType().equalsIgnoreCase(PlayerCfg.TYPE_COMPUTER)) {
                    AbstractPlayer viewers = new HumanPlayer(Piece.UNDEFINED);
                    nb.subscribe(viewers, NotificationBoard.NF_GAMESTATE_CHANGED);
                }
            }
        } catch(UnknownHostException ue) {
            // Notify can't connect to server
            System.out.print(ue);    
        } catch (IOException ie) {
            System.out.print(ie);
        }
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
    public void makeUndo(AbstractPlayer caller) {
        
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
    public void makeMove(Position position, othello.common.AbstractPlayer caller) {
        
        MoveCmd moveCommand = new MoveCmd(null, null, position);
        System.out.println("Sending command: " + moveCommand.serializeJSON());
        writer.println(moveCommand.serializeJSON());
    }

    @Override
    public void processMoveAccepted(Position position) {
        
        onlinePlayer.processMoveAccepted(position);
    }

    @Override
    public void processMoveRejected(String msg) {
        
        MoveRes moveRes = new MoveRes(onlinePlayer, MoveRes.REJECTED, msg, Position.UNDEFINED);
        moveRes.execute();
    }


    @Override
    public void joinPlayer(AbstractPlayer player) {
        JoinPlayerCmd joinPlayerCmd = new JoinPlayerCmd(null, player);
        System.out.println("Sending command: " + joinPlayerCmd.serializeJSON());
        writer.println(joinPlayerCmd.serializeJSON());
    }

    @Override
    public void joinAccepted(AbstractPlayer player) {
        
        System.out.println("Accepted join player command");
        onlinePlayer = PlayerFactory.getPlayer(cfg.players.players
                .get(cfg.players.getPlayerOnlineIndex()).getType());
        onlinePlayer.setName(player.getName());
        onlinePlayer.setPiece(player.getPiece());
        onlinePlayer.setScore(player.getScore());
        
        nb.subscribe(onlinePlayer, NotificationBoard.NF_GAMESTATE_CHANGED);
    }

    @Override
    public void joinRejected(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fireMoveTurnNotify() {
        
        nb.fireChangeNotification(NotificationBoard.NF_MOVE_TURN, onlinePlayer);
    }

    @Override
    public void fireStateChangedNotify(GameState state) {
        
        nb.fireChangeNotification(NotificationBoard.NF_GAMESTATE_CHANGED, state);
    }

    @Override
    public void makePassing() {
        onlinePlayer.makePassing();
    }

    @Override
    public void makeOverGame() {
        onlinePlayer.makeOverGame();
    }

    @Override
    public void requestAccepted(int reqType, AbstractPlayer respondent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void requestRejected(int reqType, AbstractPlayer respondent) {
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
                
            } catch (IOException ex) {

                Logger.getLogger(OnlineGameMonitor.class.getName()).log(Level.SEVERE, null, ex);
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

class NotifyExecuting extends Thread {
    private INotification notify;
    
    public NotifyExecuting(INotification notify) {
        this.notify = notify;
    }
    @Override
    public void run() {
        System.out.println("Executing notify...");
        this.notify.execute();
    }
}
