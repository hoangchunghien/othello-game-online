package othello.configuration.server;

import java.io.File;
import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "OthelloServer")
public class Configuration {
    public final static String CONFIG_FILEPATH = "src/othello/configuration/server/config.xml";
    
    private static Configuration SingletonObject = null;
    
    @XmlElement(name = "Ports", required = true)
    public PortsCfg ports;
    
    @XmlElement(name = "Board", required = true)
    public BoardCfg board;
    
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
            return new Configuration();
        }
    }
}

