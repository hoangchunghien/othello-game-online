package othello.ui.control.graphic.test;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import othello.ui.control.graphic.GameFeaturePanel;

/**
 *
 * @author Hien Hoang
 */
public class GameFeaturePanelTest {
    
    public static void main(String args[]) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GameFeaturePanel featurePanel = new GameFeaturePanel();
        testFrame.add(featurePanel);
        testFrame.setVisible(true);
    }
}
