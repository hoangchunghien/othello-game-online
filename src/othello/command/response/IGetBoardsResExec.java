package othello.command.response;

import java.util.List;
import othello.models.Board;

/**
 *
 * @author Hien Hoang
 */
public interface IGetBoardsResExec {
    
    public void loadBoards(List<Board> boards);
}
