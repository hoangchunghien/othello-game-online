package othello.configuration;

import javax.xml.bind.annotation.*;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserInterfaces")
public class UserInterfacesCfg {
    @XmlElement(name = "Configuration")
    public ConfigurationUICfg configurationUI;
    
    @XmlElement(name = "Control")
    public ControlUICfg controlUI;
}