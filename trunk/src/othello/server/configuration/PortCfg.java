package othello.server.configuration;

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
@XmlType(name = "Port")
public class PortCfg {
	public static final String NAME_SELECTION = "selection";
	public static final String NAME_PLAYING = "playing";
	
	@XmlAttribute(name = "name")
    public String name;
	
    @XmlAttribute(name = "number")
    public int number;
}