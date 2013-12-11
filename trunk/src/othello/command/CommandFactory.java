package othello.command;

import org.json.JSONObject;
import othello.client.OnlineGameMonitor;
import othello.client.ClientGameMonitor;
import othello.common.AbstractPlayer;
import othello.common.Position;
import othello.configuration.Configuration;

/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class CommandFactory {
    
    public static ICommand getCommand(String str) {
        String element[] = str.split(" ");
        switch (element[0].toLowerCase()) {
            case MoveCmd.NAME:
            {
                if (element.length >= 3) {
                    IMoveCmdExec moveExecutor;
                    if (Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("offline")) {
                        moveExecutor = ClientGameMonitor.getInstance();
                    }
                    else {
                        moveExecutor = OnlineGameMonitor.getInstance();
                    }
                    Position p = new Position(Integer.parseInt(element[1]),
                            Integer.parseInt(element[2]));
//                    return new MoveCmd(moveExecutor, p);
                }
                else 
                    return null;
            }
            case UndoCmd.NAME: 
            {
                IUndoCmdExec undoExecutor;
                if (Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("offline")) {
                        undoExecutor = ClientGameMonitor.getInstance();
                }
                else {
                    undoExecutor = OnlineGameMonitor.getInstance();
                }
                return new UndoCmd(undoExecutor);
                
            }
            case RedoCmd.NAME:
            {
                IRedoCmdExec redoExecutor;
                if (Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("offline")) {
                    redoExecutor = ClientGameMonitor.getInstance();
                }
                else {
                    redoExecutor = OnlineGameMonitor.getInstance();
                }
                return new RedoCmd(redoExecutor);
            }
            case LoginCmd.NAME:
            {
                if (element.length >= 3 && Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("online")) {
                    return new LoginCmd(OnlineGameMonitor.getInstance(), element[1], element[2]);
                }
                else {
                    return null;
                }
            
            }
            case JoinCmd.NAME:
            {
                if (element.length >= 2 && Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("online")) {
                    return new JoinCmd(OnlineGameMonitor.getInstance(), element[1]);
                }
            }
            case DrawCmd.NAME:
            {
                
            }
            case ResignCmd.NAME:
            {
                
            }
            case ListCmd.NAME:
            {
                if (element.length >= 2 && Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("online")) {
                    return new ListCmd(OnlineGameMonitor.getInstance(), element[1]);
                }
            }
            case QuitCmd.NAME:
            {
                
            }
            case ChatCmd.NAME:
                if (element.length >= 2 && Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("online")) {
                    return new ChatCmd(OnlineGameMonitor.getInstance(), element[1]);
                }
            default:
                return null;
        }
    }
    
    public static ICommand getCmdGetBoards(String roomId) {
        if (Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("online")) {
            return new GetBoardsCmd(OnlineGameMonitor.getInstance(), roomId);
        }
        return null;
    }
    
    public static ICommand getServerCommand(IExec executor, JSONObject jObj) {
        switch(jObj.getString("command")) {
            case JoinCmd.NAME:
                JoinCmd join = new JoinCmd((IJoinCmdExec)executor, null);
                join.deserializeJSON(jObj);
                return join;
            case MoveCmd.NAME:
                MoveCmd move = new MoveCmd((IMoveCmdExec)executor, null, Position.UNDEFINED);
                move.deserializeJSON(jObj);
                return move;
            case ListCmd.NAME:
                ListCmd list = new ListCmd((IListCmdExec)executor, null);
                list.deserializeJSON(jObj);
                return list;
            case LoginCmd.NAME:
                LoginCmd login = new LoginCmd((ILoginCmdExec)executor, null, null);
                login.deserializeJSON(jObj);
                return login;
            case DrawCmd.NAME:
                DrawCmd draw = new DrawCmd((IDrawExec)executor, null);
                draw.deserializeJSON(jObj);
                return draw;
            case UndoCmd.NAME:
                UndoCmd undo = new UndoCmd((IUndoCmdExec)executor);
                undo.deserializeJSON(jObj);
                return undo;
            case RedoCmd.NAME:
                RedoCmd redo = new RedoCmd((IRedoCmdExec)executor);
                redo.deserializeJSON(jObj);
                return redo;
            case ResignCmd.NAME:
                ResignCmd resign = new ResignCmd((IResignCmdExec)executor);
                resign.deserializeJSON(jObj);
                return resign;
            case QuitCmd.NAME:
                QuitCmd quit = new QuitCmd((IQuitCmdExec)executor);
                quit.deserializeJSON(jObj);
                return quit;
            case GetBoardsCmd.NAME:
                GetBoardsCmd getBoards = new GetBoardsCmd((IGetBoardsCmdExec)executor, null);
                getBoards.deserializeJSON(jObj);
                return getBoards;
            case ChatCmd.NAME:
                ChatCmd chat = new ChatCmd((IChatCmdExec)executor, null);
                chat.deserializeJSON(jObj);
                return chat;
            case JoinPlayerCmd.NAME:
                JoinPlayerCmd joinPlayer = new JoinPlayerCmd((IJoinPlayerCmdExec)executor, (AbstractPlayer)executor);
                joinPlayer.deserializeJSON(jObj);
                return joinPlayer;
            default:
                return null;
        }
    }
}
