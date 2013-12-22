package othello.command;

import othello.client.GameSelection;
import othello.client.OnlineGameMonitor;
import othello.client.ClientGameMonitor;
import othello.client.ComputerPlayer;
import othello.client.HumanPlayer;
import othello.common.AbstractPlayer;
import othello.configuration.Configuration;
import othello.ui.control.graphic.BoardPanel;

/**
 *
 * @author Hien Hoang
 */
public class ClientCommandExecutorManager {
    
    private static Configuration cfg = Configuration.getInstance();
    
    public static IChatCmdExec getChatCommandExecutor() {
        if (cfg.getPlayingType().name.equalsIgnoreCase("online")) {
            return OnlineGameMonitor.getInstance();
        }
        return null;
    }
    
    public static FetchBoardListCmdExecutable getGetBoardsCommandExecutor() {
        if (cfg.getPlayingType().name.equalsIgnoreCase("online")) {
            return GameSelection.getInstance();
        }
        return null;
    }
    
    public static IJoinCmdExec getJoinCommandExecutor() {
        if (cfg.getPlayingType().name.equalsIgnoreCase("online")) {
            return OnlineGameMonitor.getInstance();
        }
        return null;   
    }
    
    public static ILoginCmdExec getLoginCommandExecutor() {
        if (cfg.getPlayingType().name.equalsIgnoreCase("online")) {
            return OnlineGameMonitor.getInstance();
        }
        return null;   
    }
    
    public static IMoveCmdExec getMoveCommandExecutor() {
        if (cfg.getPlayingType().name.equalsIgnoreCase("online")) {
            return OnlineGameMonitor.getInstance();
        }
        else {
            return ClientGameMonitor.getInstance();
        } 
    }
    
    public static IQuitCmdExec getQuitCommandExecutor() {
        if (cfg.getPlayingType().name.equalsIgnoreCase("online")) {
            return OnlineGameMonitor.getInstance();
        }
        return null;   
    }
    
    public static IRedoCmdExec getRedoCommandExecutor() {
        if (cfg.getPlayingType().name.equalsIgnoreCase("online")) {
            return OnlineGameMonitor.getInstance();
        }
        return null;   
    }
    
    public static IUndoCmdExec getUndoCommandExecutor() {
        if (cfg.getPlayingType().name.equalsIgnoreCase("online")) {
            return OnlineGameMonitor.getInstance();
        } 
        else {
            return ClientGameMonitor.getInstance();
        } 
    }
    
    public static IResignCmdExec getResignCommandExecutor() {
        if (cfg.getPlayingType().name.equalsIgnoreCase("online")) {
            return OnlineGameMonitor.getInstance();
        }
        return null;
    }
    
    public static IGetMoveCmdExec getGetMoveCommandExecutor(AbstractPlayer player) {
        
        if (player.getClass().equals(HumanPlayer.class)) {
            return BoardPanel.getInstance();
        }
        if (player.getClass().equals(ComputerPlayer.class)) {
            return (IGetMoveCmdExec)player;
        }
        return null;
    }
    
}
