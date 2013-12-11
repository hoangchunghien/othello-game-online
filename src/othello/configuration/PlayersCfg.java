package othello.configuration;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Players")
public class PlayersCfg {
    @XmlAttribute(name = "playFirstId")
    protected int playFirstId;
    
    @XmlAttribute(name = "playOnlineId")
    public int playOnlineId;
    
    @XmlAttribute(name = "autoViewer")
    public boolean autoViewer;
    
    @XmlElement(name = "Player")
    public List<PlayerCfg> players;
    
    public PlayersCfg() {
        playFirstId = 1;
        playOnlineId = 1;
        players = new ArrayList<PlayerCfg>();
    }
    
    public void setPlayOnlineId(int value) {
        if (value <= 1) {
            this.playOnlineId = 1;
        }
        else {
            this.playOnlineId = 2;
        }
    }
    
    public int getPlayerOnlineIndex() {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).id == playOnlineId)
                return i;
        }
        return 0;
    }
    
    public void setFirstPlayerId(int value) {
        if (value <= 1) {
            this.playFirstId = 1;
        }
        else {
            this.playFirstId = 2;
        }
    }
    
    public int getFirstPlayerIndex() {
        if (playFirstId <= 1) {
            return 0;
        }
        else {
            return 1;
        }
    }
    
    public boolean hasHumanPlayer() {
        for (PlayerCfg player : players) {
            if (player.type.equalsIgnoreCase(PlayerCfg.TYPE_HUMAN)) 
                return true;
        }
        return false;
    }
}
