package othello.server.location;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import othello.auth.LoggedInPlayersManager;
import othello.auth.LoginManager;
import othello.command.response.ListPlayersRes;
import othello.models.Player;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 * Description 
 * . TODO
 */
public class Room implements ILocation {

    private String name;
    private String id;
    private List<Board> boards;

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public List<Socket> getConnections() {
        return connections;
    }

    public void setConnections(List<Socket> connections) {
        this.connections = connections;
    }
    private List<Socket> connections;
    
    public Room() {
        
        this.boards = new ArrayList<Board>();
        this.connections = new ArrayList<Socket>();
    }
    
    @Override
    public void join(Socket connectionSoc) {
        this.connections.add(connectionSoc);
    }

    @Override
    public void disjoin(Socket connectionSoc) {
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
        java.util.List<Player> players = new ArrayList<>();
        for (Socket soc : connections) {
            Player player = new Player();
            if (LoginManager.getInstance().isLoggedIn(soc)) {
                player = LoggedInPlayersManager.getInstance().getPlayer(soc);
            }
            else {
                player.setType(Player.TYPE_GUEST);
                player.setScore(0);
                player.setUsername("guest");
            }
            players.add(player);
        }
        ListPlayersRes listPlayersResponse = new ListPlayersRes(null, 
                ListPlayersRes.ACCEPTED, "OK", players);
        sendTo(connectionSoc, listPlayersResponse.serializeJSON());
        
    }
    
    private void sendTo(Socket soc, JSONObject jObj){
        try {
            
            PrintWriter writer = new PrintWriter(soc.getOutputStream(), true);
            writer.println(jObj);
        } catch (IOException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean isBoard() {
        return false;
    }

    @Override
    public ILocation getLocationById(String id) {
        for (ILocation board : boards) {
            
            if (board.getId().equals(id)) {
                return board;
            }
        }
        return null;
    }
    
}
