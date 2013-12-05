package othello.server.location;

import java.net.Socket;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 */
public interface ILocation {
    
    public ILocation getLocationById(String id);
    /*
     * Set name of this locaiton
     */
    public void setName(String name);
    
    /*
     * Allow get name of this location
     */
    public String getName();
    
    /*
     * Set id for this location
     */
    public void setId(String Id);
    
    /*
     * The id of this location
     */
    public String getId();
    
    /*
     * Allow player to join to this location
     */
    public void join(Socket connectionSoc);
    
    /*
     * Allow player to move out of this location
     */
    public void disjoin(Socket connectionSoc);
    
    
    /**
     * Must have to list (show) the location that player can join in
     */
    public void listLocations();
    
    /**
     * Must have to list (show) all the player in this location that, player
     * can invite to play.
     */
    public void listPlayers();
    
    /*
     * Return true, if this is the board too
     */
    public boolean isBoard();
}
