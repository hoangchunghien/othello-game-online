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
@XmlType(name = "UI")
public class UICfg {
    public final static String UI_GRAPHIC = "graphic";
    public final static String UI_CONSOLE = "console";
    
    @XmlAttribute(name = "name")
    public String name;
    
    @XmlAttribute(name = "selected")
    public boolean selected;
}
