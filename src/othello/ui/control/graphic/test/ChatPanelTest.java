package othello.ui.control.graphic.test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import othello.ui.control.graphic.ChatPanel;

/**
 *
 * @author Hien Hoang
 */
public class ChatPanelTest {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChatPanel chatPanel = ChatPanel.getInstance();
        chatPanel.loadMessage("message");
        testFrame.add(chatPanel);
        
    }
}
