package othello.ui.control.graphic.test;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import othello.ui.control.graphic.ThinkingTimerPanel;
import othello.ui.control.graphic.feature.AbstractFeaturePanel;
import othello.ui.control.graphic.feature.UndoPanel;

/**
 *
 * @author Hien Hoang
 */
public class ThinkingPanelTest {
    public static void main(String args[]) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ThinkingTimerPanel timerPanel = new ThinkingTimerPanel();
        testFrame.add(timerPanel);
        testFrame.setVisible(true);
    }
}
