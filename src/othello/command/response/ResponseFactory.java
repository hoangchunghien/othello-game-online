package othello.command.response;

import org.json.JSONObject;
import othello.command.GetBoardsCmd;
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
            case JoinRes.NAME :
                JoinRes joinCmd = new JoinRes(null, null, null, null);
                joinCmd.deserializeJSON(jObj);
                return joinCmd;
                
            case LoginRes.NAME:
                LoginRes loginCmd = 
                        new LoginRes(ResponseExecutorManager.getLoginResponseExecutor(), 
                            null, null);
                loginCmd.deserializeJSON(jObj);
                return loginCmd;
                
            case MoveRes.NAME:
                MoveRes moveCmd = 
                        new MoveRes(ResponseExecutorManager.getMoveResponseExecutor(),
                            null, null, Position.UNDEFINED);
                moveCmd.deserializeJSON(jObj);
                return moveCmd;
                
            case ListLocationsRes.NAME:
                ListLocationsRes listCmd = 
                        new ListLocationsRes(ResponseExecutorManager.getListLocationsResponseExecutor(),
                            null, null, null);
                listCmd.deserializeJSON(jObj);
                return listCmd;
                
            case ListPlayersRes.NAME:
                ListPlayersRes listPlayerCmd = new ListPlayersRes(
                        PlayerListPanel.getInstance(), null, null);
                listPlayerCmd.deserializeJSON(jObj);
                return listPlayerCmd;
                
            case ListRoomsRes.NAME:
                ListRoomsRes listRoomsCmd = new ListRoomsRes(ResponseExecutorManager.getListRoomsResponseExecutor(),
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
                
            case JoinPlayerRes.NAME:
                JoinPlayerRes joinPlayerRes = new JoinPlayerRes(ResponseExecutorManager.getJoinPlayerResponseExecutor(),
                        null, null, null);
                joinPlayerRes.deserializeJSON(jObj);
                return joinPlayerRes;
                
            default:
                return null;
        }

    }
}
