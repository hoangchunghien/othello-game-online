package othello.command;

import org.json.JSONObject;

import othello.client.GameSelection;
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
    
    public static Commandable getCommand(String str, AbstractPlayer caller) {
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
                return new UndoCmd(undoExecutor, caller);
                
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
    
    public static Commandable getFetchBoardListCmd(String roomId) {
        if (Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("online")) {
            return new FetchBoardListCmd(GameSelection.getInstance(), roomId);
        }
        return null;
    }
    
    public static Commandable getFetchRoomListCmd(String stationId) {
    	if (Configuration.getInstance().getPlayingType()
                .name.equalsIgnoreCase("online")) {
    		return new FetchRoomListCmd(GameSelection.getInstance(), stationId);
		}
		return null;
    }
    
    public static Commandable getJoinPlayerCmd(String boardId) {
    	if (Configuration.getInstance().getPlayingType()
                .name.equalsIgnoreCase("online")) {
    		JoinPlayerCmd command = new JoinPlayerCmd(GameSelection.getInstance());
    		command.setBoardId(boardId);
    		return command;
    	}
    	return null;
    }
    
    public static Commandable getServerCommand(Executable executor,AbstractPlayer caller, JSONObject jObj) {
        switch(jObj.getString("command")) {
            case JoinCmd.NAME:
                JoinCmd join = new JoinCmd((IJoinCmdExec)executor, null);
                join.deserializeJSON(jObj);
                return join;
            case MoveCmd.NAME:
                MoveCmd move = new MoveCmd((IMoveCmdExec)executor, null, Position.UNDEFINED);
                move.deserializeJSON(jObj);
                return move;
            case LoginCmd.NAME:
                LoginCmd login = new LoginCmd((ILoginCmdExec)executor, null, null);
                login.deserializeJSON(jObj);
                return login;
            case DrawCmd.NAME:
                DrawCmd draw = new DrawCmd((IDrawExec)executor, null);
                draw.deserializeJSON(jObj);
                return draw;
            case UndoCmd.NAME:
                UndoCmd undo = new UndoCmd((IUndoCmdExec)executor, caller);
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
            case FetchBoardListCmd.NAME:
                FetchBoardListCmd getBoards = new FetchBoardListCmd((FetchBoardListCmdExecutable)executor, null);
                getBoards.deserializeJSON(jObj);
                return getBoards;
               
            case FetchRoomListCmd.NAME:
            	FetchRoomListCmd fetchRooms = new FetchRoomListCmd((FetchRoomListCmdExecutable)executor, null);
            	fetchRooms.deserializeJSON(jObj);
            	return fetchRooms;
            	
            case ChatCmd.NAME:
                ChatCmd chat = new ChatCmd((IChatCmdExec)executor, null);
                chat.deserializeJSON(jObj);
                return chat;
            case JoinPlayerCmd.NAME:
                JoinPlayerCmd joinPlayer = new JoinPlayerCmd((JoinPlayerCmdExecutable)executor);
                joinPlayer.deserializeJSON(jObj);
                return joinPlayer;
            default:
                return null;
        }
    }
}
