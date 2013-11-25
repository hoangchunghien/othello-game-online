package othello.configuration;

import java.util.List;
import javax.xml.bind.annotation.*;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Engines")
public class EnginesCfg {
    @XmlElement(name = "Engine")
    public List<EngineCfg> engines;
}
