package othello.command.response;

import java.util.List;
import othello.models.Location;

/**
 *
 * @author Hien Hoang
 */
public interface IListRoomsResExec {

    public void loadRoomsList(List<Location> rooms);
}
