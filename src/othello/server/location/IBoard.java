package othello.server.location;

import java.net.Socket;
import othello.common.Position;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 * Description
 * . TODO
 */
public interface IBoard {
    
    public void joinPlayer(Socket connectionSoc);
    public void joinViewer(Socket connectionSoc);
    public void disjoinPlayer(Socket connectionSoc);
    public void disjoinViewer(Socket connectionSoc);
    public boolean isPlayer(Socket connectionSoc);
    public boolean isViewer(Socket connectionSoc);
    public void makeMove(Socket connectionSoc, Position position);
}
