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
    @XmlElement(name = "Player")
    public List<PlayerCfg> players;
    
    public PlayersCfg() {
        playFirstId = 1;
        players = new ArrayList<PlayerCfg>();
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
}
