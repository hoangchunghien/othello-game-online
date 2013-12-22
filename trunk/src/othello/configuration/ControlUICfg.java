package othello.configuration;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Control")
public class ControlUICfg {
	
	@XmlAttribute(name = "showValidMove")
    public boolean showValidMove;
	
    @XmlElement(name = "UI")
    public List<UICfg> UIs;
}