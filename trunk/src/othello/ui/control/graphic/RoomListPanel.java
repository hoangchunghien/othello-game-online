package othello.ui.control.graphic;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import othello.command.CommandFactory;
import othello.command.Commandable;
import othello.command.response.FetchRoomListResExecutable;
import othello.models.Location;

/**
 *
 * @author Hien Hoang
 */
public class RoomListPanel extends JPanel implements FetchRoomListResExecutable {

    private DefaultListModel roomsModel = new DefaultListModel();
    private JList roomsList = new JList(roomsModel);
    private JScrollPane scrollPane = new JScrollPane(roomsList);
    
    private static RoomListPanel singletonObject;
    
    public static RoomListPanel getInstance() {
        if (singletonObject == null) {
            singletonObject = new RoomListPanel();
        }
        return singletonObject;
    }
    
    public RoomListPanel() {
        super();
        initialize();
//        ICommand cmd = CommandFactory.getCommand(othello.command.ListCmd.NAME + " " 
//                + othello.command.ListCmd.ROOM);
//        cmd.execute();
    }
    
    private void initialize() {
        this.setLayout(new GridLayout(1,1));
        this.add(scrollPane);
        roomsList.getSelectionModel().addListSelectionListener(new RoomListModelListener());
        roomsList.getModel().addListDataListener(null);
    }
    
    @Override
    public void loadRoomList(List<Location> rooms) {

        this.roomsModel.clear();
        System.out.println("Loading rooms...");
        for (Location room : rooms) {
            System.out.println("Adding room: " + room.name);
            this.roomsModel.addElement(room);
        }
    }
    
    private class RoomListModelListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                Location room = (Location)roomsList.getSelectedValue();
                Commandable cmd = CommandFactory.getFetchBoardListCmd(room.id);
                cmd.execute();                
            }
        }
        
    }
    
}
