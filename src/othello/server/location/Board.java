package othello.server.location;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import othello.command.response.JoinRes;
import othello.common.Piece;
import othello.common.Position;

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
    
    public Board() {
        
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
    public void joinPlayer(Socket connectionSoc) {
        boolean joined = false;

        for (Piece p : supportedPiece) {
            if (pieceSelected.get(p) == null || 
                    pieceSelected.get(p) == Boolean.FALSE) {

                joined = true;
                registerPiece(connectionSoc, p);
                this.connections.add(connectionSoc);
                this.playerConnections.add(connectionSoc);
                break;
            }
        }

        String msg;
        if (!joined) {
            msg = "Can't join!!!";

        }
        else {
            msg = "Joined";
        }

        othello.command.response.JoinRes joinRes = 
                new JoinRes(null, joined?JoinRes.ACCEPTED:JoinRes.REJECTED, msg, id);

        sendTo(connectionSoc, joinRes.serializeJSON());
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
    public void disjoinPlayer(Socket connectionSoc) {
        this.connections.remove(connectionSoc);
        this.playerConnections.remove(connectionSoc);
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
    public void makeMove(Socket connectionSoc, Position position) {
        
    }

    @Override
    public ILocation getLocationById(String id) {
        return null;
    }
    
}
