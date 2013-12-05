package othello.command.response;

import othello.models.Location;

/**
 *
 * @author Hien Hoang
 */
public interface IListResExec {
    
    public void displayLocations(java.util.List<Location> locations);
    public void displayPlayers(java.util.List<String> players);
}
