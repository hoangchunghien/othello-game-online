package othello.ui;

import othello.configuration.Configuration;
import othello.ui.configuration.ConsoleCfg;
import othello.ui.configuration.IConfiguration;
import othello.ui.control.IControl;
import othello.ui.control.ConsoleCtrl;
import othello.ui.control.GraphicCtrl;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class UIFactory {
    
    static IControl singletonControlUI;
    static IConfiguration singletonConfigrationUI;
    
    public static IControl getControlUI() {
        
        if (singletonControlUI == null) {
            switch (Configuration.getInstance().getSelectedControlUI().name.toLowerCase()) {
                case "console":
                    singletonControlUI = new ConsoleCtrl();
                    break;
                case "graphic":
                    singletonControlUI = new GraphicCtrl();
                    break;
                default:
                    singletonControlUI = new ConsoleCtrl();
                    break;
            }
        }
        return singletonControlUI;
    }
    
    public static IConfiguration getConfigurationUI() {
        
        if (singletonConfigrationUI == null) {
            
            switch (Configuration.getInstance().getSelectedConfigurationUI().name.toLowerCase()) {
                case "console":
                    singletonConfigrationUI = new ConsoleCfg();
                    break;
                case "graphic":
                    singletonConfigrationUI = new ConsoleCfg();
                    break;
                default:
                    singletonConfigrationUI = new ConsoleCfg();
                    break;
            }
        }
        return singletonConfigrationUI;
    }
    
}
