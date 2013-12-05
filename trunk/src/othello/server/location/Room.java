package othello.server.location;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
        for (ILocation board : boards) {
            
            if (board.getId().equals(id)) {
                return board;
            }
        }
        return null;
    }
    
}
