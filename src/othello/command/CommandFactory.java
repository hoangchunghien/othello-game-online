package othello.command;

import othello.common.Position;
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
            case "move":
                if (element.length >= 3) {
                    return new Move(GameMonitor.getInstance(), 
                                    new Position(Integer.parseInt(element[1]), Integer.parseInt(element[2])));
                }
                else 
                    return null;
            case "undo":
                return new Undo(GameMonitor.getInstance());
            case "redo":
                return new Redo(GameMonitor.getInstance());
            default:
                return null;
        }
    }
}
