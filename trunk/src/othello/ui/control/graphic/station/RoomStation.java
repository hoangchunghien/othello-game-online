package othello.ui.control.graphic.station;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import othello.client.GameSelection;
import othello.command.CommandFactory;
import othello.command.Commandable;
import othello.command.response.FetchRoomListResExecutable;
import othello.configuration.Configuration;
import othello.configuration.TypeCfg;
import othello.game.Notifiable;
import othello.game.NotificationBoard;
import othello.models.Location;

/**
 *
 * @author Hien Hoang
 */
public class RoomStation extends JPanel implements Notifiable {
    
    JLabel titleLabel = new JLabel();
    private DefaultListModel roomsModel = new DefaultListModel();
    private JList roomsList = new JList(roomsModel);
    private JScrollPane scrollPane = new JScrollPane(roomsList);
    BackNextGroup btnBackNext = new BackNextGroup();
    
    protected GameSelection gameSelection;
    protected Configuration cfg = Configuration.getInstance();
    
    public RoomStation() {
    	
        initialize();
        NotificationBoard.getInstance().subscribe(this, NotificationBoard.NF_ROOM_LIST_CHANGED);
    }
    
    private void initialize() {
        
        titleLabel.setText("ROOM");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 40);
        titleLabel.setFont(othelloFont);
        titleLabel.setForeground(Color.GRAY);
        
        roomsList.setFont(new Font(this.getFont().getFontName(), Font.TRUETYPE_FONT, 28));
        roomsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        btnBackNext.setNextLetter(StationUIManager.STATION_TABLE);
        
        if (cfg.getPlayingType().name.equalsIgnoreCase(TypeCfg.TYPE_ONLINE)) {
        	
        	gameSelection = GameSelection.getInstance();
        }
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 0));
        contentPanel.add(scrollPane);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(titleLabel)
                    .addComponent(contentPanel)
                    .addComponent(btnBackNext)
                )
         );
 
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(30)
                .addComponent(titleLabel).addGap(10)
                .addComponent(contentPanel)
                .addComponent(btnBackNext)

         );
        
        roomsList.getSelectionModel().addListSelectionListener(new RoomListModelListener());
    }
    
    public void loadRoomsList(List<Location> rooms) {

        this.roomsModel.clear();
        System.out.println("Loading rooms...");
        for (Location room : rooms) {
            System.out.println("Adding room: " + room.name);
            this.roomsModel.addElement(room);
            this.updateUI();
        }
    }
    
    public void setBackLetter(String letter) {
    	btnBackNext.setBackLetter(letter);
    }
    
    public void setNextLetter(String letter) {
    	btnBackNext.setNextLetter(letter);
    }

	@Override
	public void receiveChangeNotification(int category, Object detail) {
		if (category == NotificationBoard.NF_ROOM_LIST_CHANGED) {
			List<Location> rooms = (List<Location>)detail;
			loadRoomsList(rooms);
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
