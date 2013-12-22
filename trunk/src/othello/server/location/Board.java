package othello.server.location;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import othello.Othello;
import othello.command.response.JoinPlayerResExecutable;
import othello.common.AbstractPlayer;
import othello.common.Piece;
import othello.common.Position;
import othello.game.GameMonitor;
import othello.game.GameTimer;
import othello.game.NotificationBoard;
import othello.server.Player;

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
    private NotificationBoard nb;
    private GameTimer timer;
    private List<Player> players;
    
    public Board() {
        
    	nb = new NotificationBoard();
    	timer = new GameTimer(nb);
        this.gameMonitor = new GameMonitor(nb, timer);
        this.connections = new ArrayList<Socket>();
        this.playerConnections = new ArrayList<Socket>();
        this.viewerConnections = new ArrayList<Socket>();
        playerPiece = new Hashtable<Socket, Piece>();
        pieceSelected = new Hashtable<Piece, Boolean>();
        supportedPiece = new ArrayList<Piece>();
        supportedPiece.add(Piece.BLACK);
        supportedPiece.add(Piece.WHITE);
        players = new ArrayList<>();
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

//    @Override
//    public void joinPlayer(AbstractPlayer player) {
//        
//        String msg;
//        JoinPlayerResExecutable joinPlayerResExec = (JoinPlayerResExecutable)player;
//        if (gameMonitor.getState().getPlayers()[0] == null) {
//
//            player.setPiece(Piece.BLACK);
//            gameMonitor.addPlayer(player);
//            gameMonitor.getState().setCurrentPlayer(player);
//            msg = "Joined";
//            joinPlayerResExec.joinAccepted(player);
//        } 
//        else if (gameMonitor.getState().getPlayers()[1] == null) {
//
//            player.setPiece(Piece.WHITE);
//            gameMonitor.addPlayer(player);
//            msg = "Joined";
//            joinPlayerResExec.joinAccepted(player);
//        }
//        else {
//            msg = "Can't join, table full!!!";
//            
//            joinPlayerResExec.joinRejected(msg);
//        }
//
//    }
    
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
    	System.out.println("Request board make move !!!");
        this.gameMonitor.makeMove(position, player);
    }

    @Override
    public ILocation getLocationById(String id) {
        return null;
    }

    @Override
    public void setReady(AbstractPlayer player) {
        gameMonitor.setReady(player);
        this.startGame();
    }

	@Override
	public void joinPlayer(AbstractPlayer player) {
		gameMonitor.addPlayer(player);
		
		// generate unique id for ticket
		String uuid = UUID.randomUUID().toString(); 
		othello.server.Othello.getPlayingTicket().put(uuid, player);
		othello.server.Othello.getTicketPlayer().put(player, uuid);
		
		players.add((Player)player);
	}
	
	public void startGame() {
		boolean isAllPlayerReady = true;
		for (Player player : players) {
			if (player.getConnection() == null) {
				isAllPlayerReady = false;
				break;
			}
		}
		if (isAllPlayerReady == true) {
			gameMonitor.start();
		}
	}
}
