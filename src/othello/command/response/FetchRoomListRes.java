package othello.command.response;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import othello.models.Location;

/**
 *
 * @author Hien Hoang
 */
public class FetchRoomListRes implements IResponse {

    public static final String NAME = "fetch_room_list";
    public static final String ACCEPTED = "accepted";
    public static final String REJECTED = "rejected";
    
    private String status;
    private String message;
    private List<Location> rooms;
    private FetchRoomListResExecutable executor;
    
    public FetchRoomListRes(FetchRoomListResExecutable executor, String status, String message,List<Location> rooms) {
        
        this.executor = executor;
        this.status = status;
        this.message = message;
        this.rooms = rooms;
    }
    
    @Override
    public JSONObject serializeJSON() {
        
        JSONArray jArr = new JSONArray();
        for (Location room : rooms) {
            jArr.put(room.serializeJSON());
        }
        
        JSONObject json = new JSONObject();
        json.put("cmdType", "response");
        json.put("command", NAME);
        json.put("status", status);
        json.put("rooms", jArr);
        
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        
        this.status = json.getString("status");
        this.rooms = new ArrayList<>();
        JSONArray jArr = json.getJSONArray("rooms");
        for (int i = 0; i < jArr.length(); i++) {
            JSONObject jobj = jArr.getJSONObject(i);
            Location lo = new Location();
            lo.deserializeJSON(jobj);
            rooms.add(lo);
        }
    }

    @Override
    public void execute() {
        executor.loadRoomList(rooms);
    }

}
