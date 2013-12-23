package othello.command.notify;

import othello.client.OnlineGameMonitor;
import othello.configuration.Configuration;
import othello.configuration.TypeCfg;
import othello.configuration.UICfg;
import othello.ui.control.graphic.ChatPanel;
import othello.ui.control.graphic.LoginFrame;

/**
 *
 * @author Hien Hoang
 */
public class ClientNotifyExecutorManager {
    
    private static Configuration cfg = Configuration.getInstance();
    
    public static IChatNtfExec getChatNotifyExecutor() {
        
        // Notify to graphic chat panel if client using UI Graphic
        if (cfg.getSelectedControlUI().name.equals(UICfg.UI_GRAPHIC)) {
            return ChatPanel.getInstance();
        }
        
        if (cfg.getSelectedControlUI().name.equals(UICfg.UI_CONSOLE)) {
            // Return the the chat console
        }
        
        return null;
    }
    
    public static IMoveNtfExec getMoveNotifyExecutor() {
        
        return null;
    }
    
    public static PassNtfExecutable getPassNotifyExecutor() {
    	
    	if (cfg.getPlayingType().name.equalsIgnoreCase(TypeCfg.TYPE_ONLINE)) {
    		return OnlineGameMonitor.getInstance();
    	}
    	return null;
    }
}
