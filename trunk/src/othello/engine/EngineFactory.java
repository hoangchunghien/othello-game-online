package othello.engine;

import othello.common.Board;
import othello.configuration.Configuration;

/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class EngineFactory {
    
    private static AEngine singletonObject;
    
    public static AEngine getEngine() {
        
        if (singletonObject == null) {
            switch (Configuration.getInstance().getSelectedEngine().name.toLowerCase()) {
                case "random":
                    singletonObject = new Random(new Board());
                    break;
                default:
                    singletonObject = new Random(new Board());
                    break;
            }
        }
        
        return singletonObject;
        
    }
    
}
