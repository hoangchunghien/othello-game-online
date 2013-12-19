package othello.configuration;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Playing")
public class PlayingCfg {
    
    @XmlElement(name = "Type")
    public List<TypeCfg> types;
    
    public void setSelectedType(String type) {
    	for (TypeCfg item : types) {
    		if (item.name.equalsIgnoreCase(type)) {
    			item.selected = true;
    		}
    		else {
    			item.selected = false;
    		}
    	}
    }
    
}
