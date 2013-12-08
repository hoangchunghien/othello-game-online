package othello.ui.control.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 *
 * @author Hien Hoang
 * @version Dec 8, 2013
 * Description 
 * . TODO
 */
public class StationPanel extends JPanel {
    
    private JSplitPane splitPane;
    private RoomListPanel roomListPanel;
    private TableListPanel tableListPanel;
    private ChatPanel chatPanel;
    
    public StationPanel() {
        roomListPanel = RoomListPanel.getInstance();
        tableListPanel = TableListPanel.getInstance();
        chatPanel = ChatPanel.getInstance();
        chatPanel.setPreferredSize(new Dimension(260, 300));
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, roomListPanel, new JScrollPane(tableListPanel));
        splitPane.setBorder(BorderFactory.createLineBorder(Color.red));
        this.setLayout(new BorderLayout());
        this.add(splitPane, BorderLayout.CENTER);
        this.add(chatPanel, BorderLayout.EAST);
        this.setSize(800, 1000);
    }
    
}
