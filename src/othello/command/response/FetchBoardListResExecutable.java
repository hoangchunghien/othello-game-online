package othello.command.response;

import java.util.List;
import othello.models.Board;

/**
 *
 * @author Hien Hoang
 */
public interface FetchBoardListResExecutable {
    
    public void loadBoardList(List<Board> boards);
}
