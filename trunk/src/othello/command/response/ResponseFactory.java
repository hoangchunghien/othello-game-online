package othello.command.response;

import org.json.JSONObject;
import othello.command.GetBoards;
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
                
            case ListRooms.NAME:
                ListRooms listRoomsCmd = new ListRooms(ResponseExecutorManager.getListRoomsResponseExecutor(),
                            null, null);
                listRoomsCmd.deserializeJSON(jObj);
                return listRoomsCmd;
                
            case GetBoardsRes.NAME:
                GetBoardsRes getBoardsRes = new GetBoardsRes(ResponseExecutorManager.getBoardsResponseExecutor(),
                            null);
                getBoardsRes.deserializeJSON(jObj);
                return getBoardsRes;
            case ChatRes.NAME:
                ChatRes chatRes = new ChatRes(ResponseExecutorManager.getChatResponseExecutor(), null, null);
                chatRes.deserializeJSON(jObj);
                return chatRes;
            default:
                return null;
        }

    }
}
