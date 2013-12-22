package othello.command;

import org.json.JSONObject;

public class FetchRoomListCmd implements Commandable{
	public static final String NAME = "fetch_room_list";
	
	FetchRoomListCmdExecutable executor;
	String stationId;
	
	public FetchRoomListCmd(FetchRoomListCmdExecutable executor, String stationId) {
		this.stationId = stationId;
		this.executor = executor;
	}
	
	@Override
    public void execute() {
        executor.fetchRoomList(stationId);
    }

    @Override
    public JSONObject serializeJSON() {
    	JSONObject json = new JSONObject();
        json.put("cmdType", "command");
        json.put("command", NAME);
        json.put("stationId", stationId);
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject jObj) {
        this.stationId = jObj.getString("stationId");
    }
}
