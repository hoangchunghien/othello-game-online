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
@XmlType(name = "Board")
public class BoardCfg {
    @XmlAttribute(name = "width")
    public int width;
    @XmlAttribute(name = "height")
    public int height;
    
    public BoardCfg() {
        this.width = 8;
        this.height = 8;
    }
}
