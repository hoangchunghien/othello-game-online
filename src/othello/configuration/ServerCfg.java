package othello.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Server")
public class ServerCfg {
    @XmlAttribute(name = "address")
    public String address;
    
    @XmlAttribute(name = "selectionPort")
    public int selectionPort;
    
    @XmlAttribute(name = "playingPort")
    public int playingPort;
    
    @XmlAttribute(name = "selected")
    public boolean selected;
}
