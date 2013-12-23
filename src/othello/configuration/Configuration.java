package othello.configuration;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

import othello.OthelloPlay;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Othello")
public class Configuration {
	static File file = new File(Configuration.class.getResource("config.xml").getPath());
    public static final String CONFIG_FILEPATH = file.getAbsolutePath();
    
    private static Configuration SingletonObject = null;
    
    @XmlElement(name = "Players", required = true)
    public PlayersCfg players;
    
    @XmlElement(name = "Timer", required = true)
    public TimerCfg timer;
    
    @XmlElement(name = "Playing", required = true)
    public PlayingCfg playing;
    
    @XmlElement(name = "Board", required = true)
    public BoardCfg board;
    
    @XmlElement(name = "Engines", required = true)
    public EnginesCfg engines;
    
    @XmlElement(name = "Levels", required = true)
    public LevelsCfg levels;
    
    @XmlElement(name = "UserInterfaces", required = true)
    public UserInterfacesCfg userInterfaces;
    
    @XmlElement(name = "Sounds", required = true)
    public SoundsCfg sounds;
    
    @XmlElement(name = "Online", required = true)
    public OnlineCfg online;
    
    @XmlElement(name = "Servers", required = true)
    public ServersCfg servers;
    
    
    public LevelCfg getSelectedLevel() {
        for (LevelCfg e : levels.items) {
            if (e.selected) {
                return e;
            }
        }
        return null;
    }
    
    public void setSelectedLevel(String name) {
    	 for (LevelCfg e : levels.items) {
             if (e.name.equalsIgnoreCase(name)) {
                 e.selected = true;
             }
             else {
            	 e.selected = false;
             }
         }
    }
    
    public ServerCfg getSelectedServer() {
        for (ServerCfg s : servers.servers) {
            if (s.selected) {
                return s;
            }
        }
        return null;
    }
    
    public TypeCfg getPlayingType() {
        for (TypeCfg t : playing.types) {
            if (t.selected) {
                return t;
            }
        }
        return null;
    }
    
    public UICfg getSelectedControlUI() {
        for (UICfg u : userInterfaces.controlUI.UIs) {
            if (u.selected) {
                return u;
            }
        }
        return null;
    }
    
    public UICfg getSelectedConfigurationUI() {
        for (UICfg u : userInterfaces.configurationUI.UIs) {
            if (u.selected) {
                return u;
            }
        }
        return null;
    }
    
    public Configuration() {
        players = new PlayersCfg();
        board = new BoardCfg();
    }
    
    public static Configuration getInstance() {
        if (SingletonObject == null) {
            SingletonObject = Configuration.deserialize(CONFIG_FILEPATH);
        }
        return SingletonObject;
    }
    
    public void serialize(String fileName) {
        try {
            JAXBContext jc = JAXBContext.newInstance(this.getClass().getPackage().getName());
            Marshaller m = jc.createMarshaller();
            m.marshal( this, new FileOutputStream(fileName) );
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Configuration deserialize(String fileName) {
        try {
            JAXBContext jc = JAXBContext.newInstance((new Configuration()).getClass().getPackage().getName());
            Unmarshaller um = jc.createUnmarshaller();
            return (Configuration) um.unmarshal(new File(fileName));
        } 
        catch (Exception e) {
        	
        	e.printStackTrace();
            return null;
        }
    }
}

