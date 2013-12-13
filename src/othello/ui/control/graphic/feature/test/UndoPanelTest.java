package othello.ui.control.graphic.feature.test;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import othello.ui.control.graphic.feature.AbstractFeaturePanel;
import othello.ui.control.graphic.feature.DrawPanel;
import othello.ui.control.graphic.feature.RedoPanel;
import othello.ui.control.graphic.feature.ResignPanel;
import othello.ui.control.graphic.feature.UndoPanel;

/**
 *
 * @author Hien Hoang
 */
public class UndoPanelTest {
    public static void main(String args[]) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AbstractFeaturePanel featurePanel = new UndoPanel();
        featurePanel.activeService(true);
        testFrame.add(featurePanel);
        testFrame.setVisible(true);
    }
}
