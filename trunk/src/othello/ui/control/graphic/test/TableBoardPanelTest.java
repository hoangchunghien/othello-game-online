package othello.ui.control.graphic.test;

import javax.swing.JFrame;
import othello.ui.control.graphic.TableBoardPanel;

/**
 *
 * @author Hien Hoang
 */
public class TableBoardPanelTest {
    
    public static void main(String [] args) {
        JFrame testFrame = new JFrame("Table Board Panel");
        TableBoardPanel boardPanel = new TableBoardPanel();
        testFrame.add(boardPanel);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(300, 200);
        testFrame.setVisible(true);
    }
}
