package othello.ui.control.graphic.feature.test;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import othello.ui.control.graphic.feature.UndoImagePanel;

/**
 *
 * @author Hien Hoang
 */
public class UndoImagePanelTest {
    public static void main(String args[]) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UndoImagePanel undoImagePanel = new UndoImagePanel();
        testFrame.add(undoImagePanel);
    }
}
