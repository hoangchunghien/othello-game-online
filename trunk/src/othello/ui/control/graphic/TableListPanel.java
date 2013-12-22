package othello.ui.control.graphic;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import othello.command.response.FetchBoardListResExecutable;
import othello.models.Board;

/**
 *
 * @author Hien Hoang
 */
public class TableListPanel extends JPanel implements FetchBoardListResExecutable {

    List<TablePanel> tableList;
    
    private static TableListPanel singletonObject;
    
    public static TableListPanel getInstance() {
        if (singletonObject == null) {
            singletonObject = new TableListPanel();
        }
        return singletonObject;
    }
    
    public TableListPanel() {
        super();
        tableList = new ArrayList<>();
        this.setLayout(new GridLayout(0, 3));
        this.initialize();
        
    }
    
    private void initialize(){
        
    }
    
    @Override
    public void loadBoardList(List<Board> boards) {
        this.tableList.clear();
        this.removeAll();
        System.out.println("Loading boards...");
        for (Board board : boards) {
            TablePanel tablePanel = new TablePanel(board);
            System.out.println("Adding board: " + board.getName());
            this.add(tablePanel);
            this.tableList.add(tablePanel);
            this.updateUI();
        }
        
    }
    
}
