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
import othello.command.response.ListLocationsRes;
import othello.command.response.ListPlayersRes;
import othello.models.Location;
import othello.models.Player;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 * Description 
 * . The Station location is the biggest game station on the server, so it only
 * . have one object on the server. It mean this singleton object. The station,
 * . it will have a list of Room, a list of Player's Connection that haven't
 * . join the room or the game board
 */
public class Station implements ILocation {
    
    private String name;
    private String id;
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Socket> getConnections() {
        return connections;
    }

    public void setConnections(List<Socket> connections) {
        this.connections = connections;
    }
    private List<Socket> connections;
    
    public Station() {
        
        rooms = new ArrayList<Room>();
        connections = new ArrayList<Socket>();
    }
    
    private static Station singletonObject;
    
    public static Station getInstance() {
        
        if (singletonObject == null) {
            singletonObject = new Station();
        }
        return singletonObject;
    }
    

    @Override
    public void join(Socket connectionSoc) {
        this.connections.add(connectionSoc);
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
        java.util.List<Location> locations = new ArrayList<>();
        for (Room room : rooms) {
            Location lo = new Location();
            lo.id = room.getId();
            lo.name = room.getName();
            lo.numUsers = 0;
            locations.add(lo);
        }
        ListLocationsRes list = new ListLocationsRes(null, 
                ListLocationsRes.ACCEPTED, "OK", locations);
        sendTo(connectionSoc, list.serializeJSON());
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
    public void listPlayers(Socket connectionSoc) {
        java.util.List<Player> players = new ArrayList<>();
        System.out.println("Connection: " + connectionSoc + " in station");
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

    @Override
    public boolean isBoard() {
        return false;
    }

    @Override
    public ILocation getLocationById(String id) {
        for (ILocation room : rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }
}
