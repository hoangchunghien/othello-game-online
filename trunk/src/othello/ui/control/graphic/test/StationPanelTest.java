package othello.ui.control.graphic.test;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import othello.ui.control.graphic.StationPanel;

/**
 *
 * @author Hien Hoang
 */
public class StationPanelTest {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new GridLayout(0,1));
        
        testFrame.pack();
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        StationPanel stationPanel = new StationPanel();
        stationPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        testFrame.add(stationPanel);
    }
}
