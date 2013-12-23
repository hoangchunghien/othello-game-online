package othello.engine;

import othello.common.Board;
import othello.configuration.Configuration;

/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class EngineFactory {
    
	static Configuration cfg = Configuration.getInstance();
    public static AbstractEngine getEngine(String name) {
        
        switch (name.toLowerCase()) {
            case Random.NAME:
                return new Random(new Board());
            case AlphaBeta.NAME:
            	return new AlphaBeta(new Board(), cfg.getSelectedLevel());
            default:
                return new Random(new Board());

        }
        
    }
    
}
