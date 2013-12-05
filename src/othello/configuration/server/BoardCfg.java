package othello.configuration.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Board")
public class BoardCfg {
    @XmlAttribute(name = "numPlayer")
    public int numPlayer;
    
    @XmlAttribute(name = "numViewer")
    public int numViewer;
}
