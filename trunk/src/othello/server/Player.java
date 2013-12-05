package othello.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import othello.command.CommandFactory;
import othello.command.ICommand;
import othello.command.IDrawExec;
import othello.command.IExec;
import othello.command.IJoinExec;
import othello.command.IListExec;
import othello.command.ILoginExec;
import othello.command.IMoveExec;
import othello.command.IQuitExec;
import othello.command.IRedoExec;
import othello.command.IResignExec;
import othello.command.IUndoExec;
import othello.command.Join;
import othello.server.location.IBoard;
import othello.server.location.ILocation;
import othello.server.location.Station;
import othello.common.Piece;
import othello.common.Position;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 * Description 
 * . Player object that will hold and listening on the client connection, and
 * . it also communicate with other object in the server like Station, Room, 
 * . Board,...
 */
public class Player extends Thread implements IExec, IJoinExec, IDrawExec, IListExec, 
       ILoginExec, IMoveExec, IQuitExec, IRedoExec, IResignExec, IUndoExec {
    
    private Socket connection;
    private BufferedReader reader;
    private PrintWriter writer;
    private ILocation location;
    private Piece piece;
    private int score = 0;
    private String playerName = "undefined";
    
    public Socket getConnection() {
        return connection;
    }
    
     public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
    
    public void setPlayerName(String name) {
        
        this.playerName = name;
    }
    
    public String getPlayerName() {
     
        return this.playerName;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
        
        this.connection = connection;
        
        // By default, join to the station first
        this.location = Station.getInstance();
        this.location.join(connection);
        
        this.board = null;
        initialize();
    }
    
    public Player(Socket connection, ILocation location) {
        
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
    
    
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("waiting command...");
                JSONObject command = new JSONObject(reader.readLine());
                System.out.println("Received command: " + command);
                ICommand cmd = CommandFactory.getServerCommand(this, command);
                if (cmd != null) {
                    
                    cmd.execute();
                }
            }
        } catch (IOException ex) {
                
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            othello.command.response.Join joinRes = 
                    new othello.command.response.Join(null, Join.ACCEPTED, msg, locationId);
            getWriter().println(joinRes.serializeJSON());
        }
        else {
            
            String msg = "Can't Join";
            othello.command.response.Join joinRes = 
                    new othello.command.response.Join(null, Join.REJECTED, msg, locationId);
            getWriter().println(joinRes.serializeJSON());
        }
    }

    @Override
    public void dealDraw() {
    }

    @Override
    public void listLocations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ListPlayers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doLogin(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeMove(Position position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
