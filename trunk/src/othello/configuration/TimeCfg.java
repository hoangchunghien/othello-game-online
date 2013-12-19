package othello.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Time")
public class TimeCfg {
	
    @XmlAttribute(name = "name")
    public String name;
    
    @XmlAttribute(name = "value")
    public int value;
    
    @XmlAttribute(name = "selected")
    public boolean selected;
    
    public String toString() {
    	int mins = value / 60;
    	int secs = value % 60;
    	String result = "";
    	if (mins > 0) {
    		result += mins + " mins ";
    	}
    	if (secs > 0) {
    		result += secs + " secs";
    	}
    	return result;
    }
}
