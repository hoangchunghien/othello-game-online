package othello.configuration;

import javax.xml.bind.annotation.*;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Engine")
public class EngineCfg {
	
    @XmlAttribute(name = "name")
    public String name;

}
