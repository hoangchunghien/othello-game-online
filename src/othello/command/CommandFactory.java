package othello.command;

import org.json.JSONObject;
import othello.client.Client;
import othello.common.Position;
import othello.configuration.Configuration;
import othello.game.GameMonitor;

/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class CommandFactory {
    
    public static ICommand getCommand(String str) {
        String element[] = str.split(" ");
        switch (element[0].toLowerCase()) {
            case Move.NAME:
            {
                if (element.length >= 3) {
                    IMoveExec moveExecutor;
                    if (Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("offline")) {
                        moveExecutor = GameMonitor.getInstance();
                    }
                    else {
                        moveExecutor = Client.getInstance();
                    }
                    Position p = new Position(Integer.parseInt(element[1]),
                            Integer.parseInt(element[2]));
                    return new Move(moveExecutor, p);
                }
                else 
                    return null;
            }
            case Undo.NAME: 
            {
                IUndoExec undoExecutor;
                if (Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("offline")) {
                        undoExecutor = GameMonitor.getInstance();
                }
                else {
                    undoExecutor = Client.getInstance();
                }
                return new Undo(undoExecutor);
                
            }
            case Redo.NAME:
            {
                IRedoExec redoExecutor;
                if (Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("offline")) {
                    redoExecutor = GameMonitor.getInstance();
                }
                else {
                    redoExecutor = Client.getInstance();
                }
                return new Redo(redoExecutor);
            }
            case Login.NAME:
            {
                if (element.length >= 3 && Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("online")) {
                    return new Login(Client.getInstance(), element[1], element[2]);
                }
                else {
                    return null;
                }
            
            }
            case Join.NAME:
            {
                if (element.length >= 2 && Configuration.getInstance().getPlayingType()
                            .name.equalsIgnoreCase("online")) {
                    return new Join(Client.getInstance(), element[1]);
                }
            }
            case Draw.NAME:
            {
                
            }
            case Resign.NAME:
            {
                
            }
            case List.NAME:
            {
                
            }
            case Quit.NAME:
            {
                
            }
            default:
                return null;
        }
    }
    
    public static ICommand getServerCommand(IExec executor, JSONObject jObj) {
        switch(jObj.getString("command")) {
            case Join.NAME:
                Join join = new Join((IJoinExec)executor, null);
                join.deserializeJSON(jObj);
                return join;
            case Move.NAME:
                Move move = new Move((IMoveExec)executor, Position.UNDEFINED);
                move.deserializeJSON(jObj);
                return move;
            case List.NAME:
                List list = new List((IListExec)executor, null);
                list.deserializeJSON(jObj);
                return list;
            case Login.NAME:
                Login login = new Login((ILoginExec)executor, null, null);
                login.deserializeJSON(jObj);
                return login;
            case Draw.NAME:
                Draw draw = new Draw((IDrawExec)executor);
                draw.deserializeJSON(jObj);
                return draw;
            case Undo.NAME:
                Undo undo = new Undo((IUndoExec)executor);
                undo.deserializeJSON(jObj);
                return undo;
            case Redo.NAME:
                Redo redo = new Redo((IRedoExec)executor);
                redo.deserializeJSON(jObj);
                return redo;
            case Resign.NAME:
                Resign resign = new Resign((IResignExec)executor);
                resign.deserializeJSON(jObj);
                return resign;
            case Quit.NAME:
                Quit quit = new Quit((IQuitExec)executor);
                quit.deserializeJSON(jObj);
                return quit;
            default:
                return null;
        }
    }
}
