package othello.command.response;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import othello.models.Player;

/**
 *
 * @author Hien Hoang
 */
public class ListPlayers implements IResponse{
    
    public static final String NAME = "list players";
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";
    
    private java.util.List<othello.models.Player> players;
    private String status;
    private String message;

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    private IListPlayersResExec listPlayersResponseExecutor;
    
    public ListPlayers(IListPlayersResExec executor, String status, String message) {
        this.listPlayersResponseExecutor = executor;
        this.status = status;
        this.message = message;
    }
    
    public ListPlayers(IListPlayersResExec executor, String status, String message, List<Player> players) {
        this(executor, status, message);
        this.players = players;
    }
    
    @Override
    public JSONObject serializeJSON(){
        
        JSONArray jArr = new JSONArray();
        for (Player player : players) {
            jArr.put(player.serializeJSON());
        }
        
        JSONObject jObj = new JSONObject();
        jObj.put("cmdType", "response");
        jObj.put("command", NAME);
        jObj.put("status", status);
        jObj.put("message", message);
        jObj.put("players", jArr);
        
        return jObj;
    }
    
    @Override
    public void deserializeJSON(JSONObject jObj) {
        
        this.status = jObj.getString("status");
        this.message = jObj.getString("message");
        players = new ArrayList<>();
        JSONArray jArr = jObj.getJSONArray("players");
        for (int i = 0; i < jArr.length(); i++) {
            JSONObject playerJSON = jArr.getJSONObject(i);
            Player player = new Player();
            player.deserializeJSON(playerJSON);
            this.players.add(player);
        }
    }

    @Override
    public void execute() {
        System.out.println("Executing list players command...");
        listPlayersResponseExecutor.loadPlayersList(players);
    }
    
}
