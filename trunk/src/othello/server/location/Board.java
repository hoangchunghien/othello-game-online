package othello.server.location;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import othello.command.response.IJoinPlayerResExec;
import othello.common.AbstractPlayer;
import othello.common.Piece;
import othello.common.Position;
import othello.game.GameMonitor;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 */
public class Board implements ILocation, IBoard {

    private String name;
    private String id;
    private List<Socket> connections;
    private List<Socket> playerConnections;
    private List<Socket> viewerConnections;
    private Dictionary<Socket, Piece> playerPiece;
    private Dictionary<Piece, Boolean> pieceSelected;
    private List<Piece> supportedPiece;
    private GameMonitor gameMonitor;
    
    public Board() {
        
        this.gameMonitor = new GameMonitor();
        this.connections = new ArrayList<Socket>();
        this.playerConnections = new ArrayList<Socket>();
        this.viewerConnections = new ArrayList<Socket>();
        playerPiece = new Hashtable<Socket, Piece>();
        pieceSelected = new Hashtable<Piece, Boolean>();
        supportedPiece = new ArrayList<Piece>();
        supportedPiece.add(Piece.BLACK);
        supportedPiece.add(Piece.WHITE);
    }
    
    @Override
    public void join(Socket connectionSoc) {
        joinViewer(connectionSoc);
    }

    @Override
    public void disjoin(Socket connectionSoc) {
        this.connections.remove(connectionSoc);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setId(String Id) {
        this.id = Id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void listLocations(Socket connectionSoc) {
    }

    @Override
    public void listPlayers(Socket connectionSoc) {
    }

    @Override
    public void joinPlayer(AbstractPlayer player) {
        
        String msg;
        IJoinPlayerResExec joinPlayerResExec = (IJoinPlayerResExec)player;
        if (gameMonitor.getState().getPlayers()[0] == null) {

            player.setPiece(Piece.BLACK);
            gameMonitor.addPlayer(player);
            gameMonitor.getState().setCurrentPlayer(player);
            msg = "Joined";
            joinPlayerResExec.joinAccepted(player);
        } 
        else if (gameMonitor.getState().getPlayers()[1] == null) {

            player.setPiece(Piece.WHITE);
            gameMonitor.addPlayer(player);
            msg = "Joined";
            joinPlayerResExec.joinAccepted(player);
        }
        else {
            msg = "Can't join, table full!!!";
            
            joinPlayerResExec.joinRejected(msg);
        }

    }
    
    private void sendTo(Socket soc, JSONObject jObj){
        try {
            
            PrintWriter writer = new PrintWriter(soc.getOutputStream(), true);
            writer.println(jObj);
        } catch (IOException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void registerPiece(Socket connectionSoc, Piece p) {
        
        pieceSelected.put(p, Boolean.TRUE);
        playerPiece.put(connectionSoc, p);
    }

    @Override
    public void joinViewer(Socket connectionSoc) {
        this.connections.add(connectionSoc);
        this.viewerConnections.add(connectionSoc);
    }

    @Override
    public void disjoinPlayer(AbstractPlayer player) {
        
    }

    @Override
    public void disjoinViewer(Socket connectionSoc) {
        this.connections.remove(connectionSoc);
        this.viewerConnections.remove(connectionSoc);
    }

    @Override
    public boolean isBoard() {
        return true;
    }

    @Override
    public boolean isPlayer(Socket connectionSoc) {
        if (playerConnections.contains(connectionSoc)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isViewer(Socket connectionSoc) {
        if (viewerConnections.contains(connectionSoc)) {
            return true;
        }
        return false;
    }

    @Override
    public void makeMove(othello.common.AbstractPlayer player, Position position) {
        
        this.gameMonitor.makeMove(position, player);
    }

    @Override
    public ILocation getLocationById(String id) {
        return null;
    }

    @Override
    public void setReady(AbstractPlayer player) {
        gameMonitor.setReady(player);
    }
    
}