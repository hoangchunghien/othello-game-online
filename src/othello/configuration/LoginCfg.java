package othello.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Login")
public class LoginCfg {
	
    @XmlAttribute(name = "remember")
    public boolean remember;
    
    @XmlAttribute(name = "username")
    public String username;
    
    @XmlAttribute(name = "password")
    public String password;
}

