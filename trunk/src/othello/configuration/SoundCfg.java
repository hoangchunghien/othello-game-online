package othello.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sound")
public class SoundCfg {
	public static String MOVE = "move";
	public static String PASS = "pass";
	public static String GAMEOVER = "gameover";
	
    @XmlAttribute(name = "name")
    public String name;
    
    @XmlAttribute(name = "file")
    public String file;

    @XmlAttribute(name = "play")
    public boolean isPlay;

}