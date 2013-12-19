package othello.configuration;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Hien Hoang
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Timer")
public class TimerCfg {
	public static final String TYPE_GAME = "game";
	public static final String TYPE_MOVE = "move";
    
    @XmlElement(name = "MoveTimer", required = true)
    public MoveTimerCfg moveTimer;
    
    @XmlElement(name = "GameTimer", required = true)
    public GameTimerCfg gameTimer;
    
    public TimeCfg getMoveTimer(String name) {
    	for (TimeCfg item : moveTimer.times) {
    		if (item.name.equalsIgnoreCase(name)) {
    			return item;
    		}
    	}
    	return null;
    }
    
    public TimeCfg getSelectedMoveTimer() {
    	for (TimeCfg item : moveTimer.times) {
    		if (item.selected) {
    			return item;
    		}
    	}
    	return null;
    }
    
    public void setSelectedMoveTimer(String name) {
    	for (TimeCfg item : moveTimer.times) {
    		if (item.name.equalsIgnoreCase(name)) {
    			item.selected = true;
    		}
    		else {
    			item.selected = false;
    		}
    	}
    }
    
    public TimeCfg getGameTimer(String name) {
    	for (TimeCfg item : gameTimer.times) {
    		if (item.name.equalsIgnoreCase(name)) {
    			return item;
    		}
    	}
    	return null;
    }
    
    public TimeCfg getSelectedGameTimer() {
    	for (TimeCfg item : gameTimer.times) {
    		if (item.selected) {
    			return item;
    		}
    	}
    	return null;
    }
    
    public void setSelectedGameTimer(String name) {
    	for (TimeCfg item : gameTimer.times) {
    		if (item.name.equalsIgnoreCase(name)) {
    			item.selected = true;
    		}
    		else {
    			item.selected = false;
    		}
    	}
    }
}