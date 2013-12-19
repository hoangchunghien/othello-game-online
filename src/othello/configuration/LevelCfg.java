package othello.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Level")
public class LevelCfg {
	
	public static final String EASY = "easy";
	public static final String NORMAL = "normal";
	public static final String HARD = "hard";
	public static final String EXPERT = "expert";
	
    @XmlAttribute(name = "name")
    public String name;
    
    @XmlAttribute(name = "depth")
    public int depth;
    
    @XmlAttribute(name = "selected")
    public boolean selected;
}