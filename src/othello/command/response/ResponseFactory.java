package othello.command.response;

import org.json.JSONObject;
import othello.common.Position;

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
                Login loginCmd = new Login(null, null, null);
                loginCmd.deserializeJSON(jObj);
                return loginCmd;
            case Move.NAME:
                Move moveCmd = new Move(null, null, Position.UNDEFINED);
                moveCmd.deserializeJSON(jObj);
                return moveCmd;
            case List.NAME:
                List listCmd = new List(null, null, null, null);
                listCmd.deserializeJSON(jObj);
                return listCmd;
            default:
                return null;
        }

    }
}
