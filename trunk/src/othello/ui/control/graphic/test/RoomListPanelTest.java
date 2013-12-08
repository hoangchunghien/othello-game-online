package othello.ui.control.graphic.test;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import othello.models.Location;
import othello.ui.control.graphic.RoomListPanel;

/**
 *
 * @author Hien Hoang
 */
public class RoomListPanelTest {
    public static void main(String args[]) {
        JFrame testFrame = new JFrame();
        testFrame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        JLabel lbTestLabel = new JLabel();
        testFrame.add(lbTestLabel, c);
        
        List<Location> rooms = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Location room = new Location();
            room.id = "" + i;
            room.name = "Room " + i;
            room.numUsers = i;
            rooms.add(room);
        }
        RoomListPanel roomsPanel = RoomListPanel.getInstance();
//        roomsPanel.loadRoomsList(rooms);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        testFrame.add(roomsPanel, c);
        
        testFrame.setSize(300, 500);
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
