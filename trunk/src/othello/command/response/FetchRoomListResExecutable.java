package othello.command.response;

import java.util.List;
import othello.models.Location;

/**
 *
 * @author Hien Hoang
 */
public interface FetchRoomListResExecutable {

    public void loadRoomList(List<Location> rooms);
}
