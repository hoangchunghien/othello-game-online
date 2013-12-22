package othello.command;

import org.json.JSONObject;

import othello.common.AbstractPlayer;

public class ReadyCmd implements Commandable{
	
	public static final String NAME = "ready";
	
	private ReadyCmdExecutable executor;
	private AbstractPlayer player;

	public ReadyCmd(ReadyCmdExecutable executor) {
		this.executor = executor;
	}
	
	public ReadyCmd(ReadyCmdExecutable executor, AbstractPlayer player) {
		this(executor);
		this.player = player;
	}
	
	public void setPlayer(AbstractPlayer player) {
		this.player = player;
	}
	
	@Override
	public void execute() {
		executor.setReady(player);
	}

	@Override
	public JSONObject serializeJSON() {
		JSONObject json = new JSONObject();
		 json.put("cmdType", "command");
	     json.put("command", NAME);
	     
		return json;
	}

	@Override
	public void deserializeJSON(JSONObject jObj) {
		
	}

}
