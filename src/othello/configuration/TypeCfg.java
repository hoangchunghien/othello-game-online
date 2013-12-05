package othello.configuration;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
/**
 *
 * @author Hien Hoang
 * @version Dec 2, 2013
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Type")
public class TypeCfg {
    
    @XmlAttribute(name = "name")
    public String name;
    
    @XmlAttribute(name = "selected")
    public boolean selected;
}
