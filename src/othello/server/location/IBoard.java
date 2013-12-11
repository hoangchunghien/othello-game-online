package othello.server.location;

import java.net.Socket;
import othello.common.AbstractPlayer;
import othello.common.Position;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 * Description
 * . TODO
 */
public interface IBoard {
    
    public void joinPlayer(AbstractPlayer player);
    public void joinViewer(Socket connectionSoc);
    public void disjoinPlayer(AbstractPlayer player);
    public void disjoinViewer(Socket connectionSoc);
    public boolean isPlayer(Socket connectionSoc);
    public boolean isViewer(Socket connectionSoc);
    public void makeMove(othello.common.AbstractPlayer player, Position position);
    public void setReady(othello.common.AbstractPlayer player);
}
