package othello.command.response;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import othello.models.Board;

/**
 *
 * @author Hien Hoang
 */
public class GetBoardsRes implements IResponse{

   public final static String NAME = "list boards";
    
    private IGetBoardsResExec executor;
    private List<Board> boards;
    
    public GetBoardsRes(IGetBoardsResExec executor, List<Board> boards) {
        this.executor = executor;
        this.boards = boards;
    }
    
    @Override
    public JSONObject serializeJSON() {
        
        JSONArray jarr = new JSONArray();
        for (Board b : boards) {
            jarr.put(b.serializeJSON());
        }
        
        JSONObject json = new JSONObject();
        json.put("cmdType", "response");
        json.put("command", NAME);
        json.put("boards", jarr);
        
        return json;
    }

    @Override
    public void deserializeJSON(JSONObject json) {
        
        boards = new ArrayList<>();
        JSONArray jarr = json.getJSONArray("boards");
        for (int i = 0; i < jarr.length(); i++) {
            JSONObject jobj = jarr.getJSONObject(i);
            Board b = new Board();
            b.deserializeJSON(jobj);
            boards.add(b);
        }
    }

    @Override
    public void execute() {
    
        executor.loadBoards(boards);
    }

}
