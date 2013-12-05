package othello.server.location;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
    public void listLocations() {
    }

    @Override
    public void listPlayers() {
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
