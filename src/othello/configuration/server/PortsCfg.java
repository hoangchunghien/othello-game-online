package othello.configuration.server;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ports")
public class PortsCfg {
	
    @XmlElement(name = "Port")
    public List<PortCfg> ports;
    
    public PortCfg getPort(String name) {
    	for (PortCfg port : ports) {
    		if (port.name.equalsIgnoreCase(name)) {
    			return port;
    		}
    	}
    	return null;
    }
}
