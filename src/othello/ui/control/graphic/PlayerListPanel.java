package othello.ui.control.graphic;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import othello.command.Commandable;
import othello.command.CommandFactory;
import othello.command.notify.IPlayerDisjoinNtfExec;
import othello.command.notify.IPlayerJoinNtfExec;
import othello.command.response.IListPlayersResExec;
import othello.models.Player;

/**
 *
 * @author Hien Hoang
 */
public class PlayerListPanel extends JPanel implements 
        IPlayerJoinNtfExec, IPlayerDisjoinNtfExec, IListPlayersResExec{
    
    private static PlayerListPanel singletonObject;
    
    private DefaultListModel playersModel = new DefaultListModel();
    private JList playerList = new JList(playersModel);
    private JScrollPane scrollPane = new JScrollPane(playerList);
    
    public static PlayerListPanel getInstance() {
        if (singletonObject == null) {
            singletonObject = new PlayerListPanel();
        }
        return singletonObject;
    }
    
    public PlayerListPanel() {
        initialize();
//        ICommand cmd = CommandFactory.getCommand(ListCmd.NAME + " " + ListCmd.PLAYER);
//        cmd.execute();
        this.setVisible(true);
        
    }
    
    private void initialize() {
        this.setLayout(new GridLayout(1,1));
        GridBagConstraints c = new GridBagConstraints();
        
        this.add(scrollPane, c);
    }
    

    @Override
    public void addPlayer(Player player) {
        
        playersModel.addElement(player);
    }

    @Override
    public void removePlayer(Player player) {
        
         playersModel.removeElement(player);
    }

    @Override
    public void loadPlayersList(java.util.List<Player> playersList) {
        playersModel.clear();

        for (Player player : playersList) {
            
            playersModel.addElement(player);
        }
    }
}
