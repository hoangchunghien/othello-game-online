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
import othello.command.IChatCmdExec;
import othello.command.ICommand;
import othello.command.IDrawExec;
import othello.command.IExec;
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
import othello.command.JoinCmd;
import othello.command.notify.GameOverNtf;
import othello.command.notify.GameStateNtf;
import othello.command.notify.IGameOverNtfExec;
import othello.command.notify.MoveTurnNtf;
import othello.command.notify.PassNtf;
import othello.command.response.ChatRes;
import othello.command.response.GetBoardsRes;
import othello.command.response.IJoinPlayerResExec;
import othello.command.response.JoinPlayerRes;
import othello.command.response.ListRoomsRes;
import othello.command.response.MoveRes;
import othello.common.AbstractPlayer;
import othello.server.location.IBoard;
import othello.server.location.ILocation;
import othello.server.location.Station;
import othello.common.Piece;
import othello.common.Position;
import othello.game.GameState;
import othello.models.Board;
import othello.models.Location;


/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 * Description 
 * . Player object that will hold and listening on the client connection, and
 * . it also communicate with other object in the server like Station, Room, 
 * . Board,...
 */
public class Player extends AbstractPlayer implements IExec, IJoinCmdExec, IDrawExec, IListCmdExec, 
       ILoginCmdExec, IMoveCmdExec, IQuitCmdExec, IRedoCmdExec, IResignCmdExec, IUndoCmdExec, IGetBoardsCmdExec, 
       IChatCmdExec, IJoinPlayerResExec, IJoinPlayerCmdExec {
    
    private Socket connection;
    private BufferedReader reader;
    private PrintWriter writer;
    private ILocation location;
    
    public Socket getConnection() {
        return connection;
    }
    
     public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
   
   
    public ILocation getLocation() {
        return location;
    }

    public void setLocation(ILocation location) {
        this.location = location;
    }

    public IBoard getBoard() {
        return board;
    }

    public void setBoard(IBoard board) {
        this.board = board;
    }
    private IBoard board;
    
    public Player(Socket connection) {
        super(Piece.UNDEFINED);
        this.connection = connection;
        
        // By default, join to the station first
        this.location = Station.getInstance();
        this.location.join(connection);
        
        this.board = null;
        initialize();
    }
    
    public Player(Socket connection, ILocation location) {
        super(Piece.UNDEFINED);
        this.connection = connection;
        this.location = location;
        if (this.location.isBoard()) {
            this.board = (IBoard)location;
        } else {
            this.board = null;
        }
        initialize();
    }
    
    private void initialize() {
        try {
            
            writer = new PrintWriter(connection.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException ex) {
            
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void startListenFromClient() {
        
        Listener listener = new Listener(reader, this);
        listener.start();
    }

    @Override
    public void join(String locationId) {

        ILocation _location = getLocation().getLocationById(locationId);
        if (_location != null) {
            _location.join(getConnection());
            setLocation(_location);
            if (_location.isBoard()) {
                setBoard((IBoard)_location);
            }
            String msg = "Joined";
            othello.command.response.JoinRes joinRes = 
                    new othello.command.response.JoinRes(null, JoinCmd.ACCEPTED, msg, locationId);
            getWriter().println(joinRes.serializeJSON());
        }
        else {
            
            String msg = "Can't Join";
            othello.command.response.JoinRes joinRes = 
                    new othello.command.response.JoinRes(null, JoinCmd.REJECTED, msg, locationId);
            getWriter().println(joinRes.serializeJSON());
        }
    }

    @Override
    public void dealDraw() {
    }

    @Override
    public void listLocations() {
        getLocation().listLocations(connection);
    }

    @Override
    public void listPlayers() {
        getLocation().listPlayers(connection);
    }

    @Override
    public void doLogin(String username, String password) {
        
    }

    @Override
    public void makeMove(Position position, othello.common.AbstractPlayer caller) {
        
        if (location.isBoard()) {
            IBoard board = (IBoard)location;
            board.makeMove(this, position);
        }
    }

    @Override
    public void quit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeRedo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeResign() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeUndo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rejectDraw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listRooms() {
        List<Location> rooms = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Location room = new Location();
            room.id = "" + i;
            room.name = "Room " + i;
            room.numUsers = i;
            rooms.add(room);
        }
        ListRoomsRes listRoomsRes = new ListRoomsRes(null, ListRoomsRes.ACCEPTED , rooms);
        getWriter().println(listRoomsRes.serializeJSON());
    }

    @Override
    public void getBoards(String roomId) {
        List<Board> boards = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Board board = new Board("board " + i);
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
        GetBoardsRes boardsRes = new GetBoardsRes(null, boards);
        getWriter().println(boardsRes.serializeJSON());
    }

    @Override
    public void chat(String msg) {
        ChatRes chatRes = new ChatRes(null, ChatRes.ACCEPTED, msg);
        getWriter().println(chatRes.serializeJSON());
    }

    @Override
    public AbstractPlayer clone() {
        AbstractPlayer dup = new Player(this.connection);
        dup.setName(this.getName());
        dup.setPiece(this.getPiece());
        dup.setScore(this.getScore());
        return dup;
    }

    @Override
    public void fireMoveTurn() {
        MoveTurnNtf moveTurnNtf = new MoveTurnNtf(null);
        getWriter().println(moveTurnNtf.serializeJSON());
    }

    @Override
    public void makeMoving(Position p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processMoveAccepted(Position postion) {
        MoveRes moveRes = new MoveRes(null, MoveRes.ACCEPTED, "OK", postion);
        getWriter().println(moveRes.serializeJSON());
    }

    @Override
    public void processMoveRejected(String msg) {
        MoveRes moveRes = new MoveRes(null, MoveRes.REJECTED, "Invalid move!!!", Position.UNDEFINED);
        getWriter().println(moveRes.serializeJSON());
    }

    @Override
    public void joinAccepted(AbstractPlayer player) {
        // Return join player response to client
        JoinPlayerRes joinPlayerRes = new JoinPlayerRes(null, JoinPlayerRes.ACCEPTED, "OK", player);
        this.getWriter().println(joinPlayerRes.serializeJSON());
        
        if (location.isBoard()) {
            IBoard board = (IBoard)location;
            board.setReady(player);
        }
    }

    @Override
    public void joinRejected(String message) {
        // Return join player response failure to client
        JoinPlayerRes joinPlayerRes = new JoinPlayerRes(null, JoinPlayerRes.REJECTED, "Can't join", null);
        this.getWriter().println(joinPlayerRes.serializeJSON());
    }

    @Override
    public void joinPlayer(AbstractPlayer player) {
        if (location.isBoard()) {
            IBoard board = (IBoard)location;
            board.joinPlayer(player);
        }
    }

    @Override
    public void fireStateChanged(GameState newState) {
        GameStateNtf gameStateNotify = new GameStateNtf(null, newState);
        getWriter().println(gameStateNotify.serializeJSON());
    }

    @Override
    public void makePassing() {
        PassNtf passNtf = new PassNtf(null);
        getWriter().println(passNtf.serializeJSON());
    }

    @Override
    public void makeOverGame() {
        GameOverNtf gameOverNtf = new GameOverNtf(null);
        getWriter().println(gameOverNtf.serializeJSON());
    }
    
}
class Listener extends Thread {
    
    private BufferedReader reader;
    private Player player;
    
    public Listener(BufferedReader reader, Player player) {
        this.reader = reader;
        this.player = player;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("waiting command...");
                JSONObject command = new JSONObject(reader.readLine());
                System.out.println("Received command: " + command);
                ICommand cmd = CommandFactory.getServerCommand(player, command);
                if (cmd != null) {
                    
                    cmd.execute();
                }
            }
        } catch (IOException ex) {
                
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
