package othello.command.response;

import org.json.JSONObject;
import othello.common.Position;
import othello.ui.control.graphic.PlayerListPanel;

/**
 *
 * @author Hien Hoang
 * @version Dec 5, 2013
 */
public class ResponseFactory {
    
    public static IResponse getResponse(JSONObject jObj) {
        
        switch (jObj.getString("command")) {
            case Join.NAME :
                Join joinCmd = new Join(null, null, null, null);
                joinCmd.deserializeJSON(jObj);
                return joinCmd;
                
            case Login.NAME:
                Login loginCmd = 
                        new Login(ResponseExecutorManager.getLoginResponseExecutor(), 
                            null, null);
                loginCmd.deserializeJSON(jObj);
                return loginCmd;
                
            case Move.NAME:
                Move moveCmd = 
                        new Move(ResponseExecutorManager.getMoveResponseExecutor(),
                            null, null, Position.UNDEFINED);
                moveCmd.deserializeJSON(jObj);
                return moveCmd;
                
            case ListLocations.NAME:
                ListLocations listCmd = 
                        new ListLocations(ResponseExecutorManager.getListLocationsResponseExecutor(),
                            null, null, null);
                listCmd.deserializeJSON(jObj);
                return listCmd;
                
            case ListPlayers.NAME:
                ListPlayers listPlayerCmd = new ListPlayers(
                        PlayerListPanel.getInstance(), null, null);
                listPlayerCmd.deserializeJSON(jObj);
                return listPlayerCmd;
                
            default:
                return null;
        }

    }
}
