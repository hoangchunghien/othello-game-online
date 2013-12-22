/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.server.configuration;

import javax.xml.bind.annotation.XmlRegistry;
/**
 *
 * @author Hien
 */
@XmlRegistry
public class ObjectFactory {
    
    public ObjectFactory() {
        
    }
    
    public Configuration createConfiguration() {
        return new Configuration();
    }
    
    
}
