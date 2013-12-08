package othello.ui.control.graphic.test;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import othello.common.Piece;
import othello.models.Player;
import othello.models.Board;
import othello.ui.control.graphic.TableListPanel;

/**
 *
 * @author Hien Hoang
 */
public class TableListPanelTest {
    public static void main(String[] args) {
        
        TableListPanel tableListPanel = new TableListPanel();
        JFrame testFrame = new JFrame();
        List<Board> boards = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Board board = new Board("board " + i);
            Player p1 = new Player();
            p1.setUsername("user" + i);
            p1.setType(1);
            p1.setScore(i+100);
            Player p2 = new Player();
            p2.setUsername("opp" + i);
            p2.setType(2);
            p2.setScore(i + 200);
            board.setPlayer(Piece.BLACK, p1);
            board.setPlayer(Piece.WHITE, p2);
            boards.add(board);
        }
        tableListPanel.loadBoards(boards);
        testFrame.add(new JScrollPane(tableListPanel));
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(1000, 500);
        testFrame.setVisible(true);
    }
}
