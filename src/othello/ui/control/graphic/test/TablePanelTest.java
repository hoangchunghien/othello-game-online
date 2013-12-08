package othello.ui.control.graphic.test;

import javax.swing.JFrame;
import othello.models.Board;
import othello.ui.control.graphic.TablePanel;

/**
 *
 * @author Hien Hoang
 */
public class TablePanelTest {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame("Table Board Panel");
        TablePanel tablePanel = new TablePanel(new Board());
        testFrame.add(tablePanel);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(300, 200);
        testFrame.setVisible(true);
    }
}
