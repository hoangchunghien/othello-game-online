package othello.configuration;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sounds")
public class SoundsCfg {
	
	@XmlAttribute(name = "playSound")
    public boolean playSound;
	
    @XmlElement(name = "Sound")
    public List<SoundCfg> sounds;
    
    public SoundCfg getMoveSound() {
    	for (SoundCfg sound : sounds) {
    		if (sound.name.equalsIgnoreCase(SoundCfg.MOVE)){
    			return sound;
    		}
    	}
    	return null;
    }
    
    public SoundCfg getPassSound() {
    	for (SoundCfg sound : sounds) {
    		if (sound.name.equalsIgnoreCase(SoundCfg.PASS)){
    			return sound;
    		}
    	}
    	return null;
    }
    
    public SoundCfg getGameOverSound() {
    	for (SoundCfg sound : sounds) {
    		if (sound.name.equalsIgnoreCase(SoundCfg.GAMEOVER)){
    			return sound;
    		}
    	}
    	return null;
    }
}