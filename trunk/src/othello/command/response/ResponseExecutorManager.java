/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.command.response;

import othello.configuration.Configuration;
import othello.configuration.UICfg;
import othello.game.GameMonitor;
import othello.ui.control.graphic.ChatPanel;
import othello.ui.control.graphic.LoginFrame;
import othello.ui.control.graphic.PlayerListPanel;
import othello.ui.control.graphic.RoomListPanel;
import othello.ui.control.graphic.TableListPanel;

/**
 *
 * @author Hien Hoang
 */
public class ResponseExecutorManager {
    
    private static Configuration config = Configuration.getInstance();
    
    public static ILoginResExec getLoginResponseExecutor() {
        
        if (config.getSelectedControlUI().name.equals(UICfg.UI_GRAPHIC)) {
            return LoginFrame.getInstance();
        }
        return null;
    }
    
    public static IListPlayersResExec getListPlayersResponseExecutor() {
        
        if (config.getSelectedControlUI().name.equals(UICfg.UI_GRAPHIC)) {
            return PlayerListPanel.getInstance();
        }
        return null;
    }
    
    public static IJoinResExec getJoinResponseExecutor() {
        
        return null;
    }
    
    public static IMoveResExec getMoveResponseExecutor() {
        
        return GameMonitor.getInstance();
    }
    
    public static IListLocationsResExec getListLocationsResponseExecutor() {
        
        return null;
    }
    
    public static IGetBoardsResExec getBoardsResponseExecutor() {
        
        if (config.getSelectedControlUI().name.equalsIgnoreCase(UICfg.UI_GRAPHIC)) {
            System.out.println("Get boards executor found");
            return TableListPanel.getInstance();
        }
        return null;
    }
    
    public static IListRoomsResExec getListRoomsResponseExecutor() {
        if (config.getSelectedControlUI().name.equalsIgnoreCase(UICfg.UI_GRAPHIC)) {
            return RoomListPanel.getInstance();
        }
        System.out.println("Executor not found...");
        return null;
    }
    
    public static IChatResExec getChatResponseExecutor() {
        if (config.getSelectedControlUI().name.equalsIgnoreCase(UICfg.UI_GRAPHIC)) {
            return ChatPanel.getInstance();
        }
        System.out.println("Executor not found...");
        return null;
    }
    
}
