package othello.ui.control.graphic.test;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import othello.client.HumanPlayer;
import othello.common.Piece;
import othello.ui.control.graphic.PlayerInfoPanel;

/**
 *
 * @author Hien Hoang
 */
public class PlayerInfoPanelTest {
    public static void main(String args[]) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PlayerInfoPanel infoPanel = new PlayerInfoPanel(new HumanPlayer(Piece.BLACK, "hienhoang"));
        testFrame.add(infoPanel);
        testFrame.setVisible(true);
    }
}
