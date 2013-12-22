package othello.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Online")
public class OnlineCfg {
	
	@XmlElement(name = "Connection")
	public ConnectionCfg connection;
	
	@XmlElement(name = "Login")
	public LoginCfg login;
}
