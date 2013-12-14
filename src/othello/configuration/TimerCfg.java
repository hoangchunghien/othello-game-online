package othello.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Hien Hoang
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Timer")
public class TimerCfg {
    
    @XmlAttribute(name = "secsPerMove")
    public int secsPerMove;
    
    @XmlAttribute(name = "secsPerGame")
    public int secsPerGame;
}