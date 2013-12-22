package othello.ui.control.graphic.station;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import othello.client.GameSelection;
import othello.command.response.FetchBoardListResExecutable;
import othello.configuration.Configuration;
import othello.configuration.TypeCfg;
import othello.game.Notifiable;
import othello.game.NotificationBoard;
import othello.models.Board;
import othello.ui.control.graphic.TablePanel;

/**
 *
 * @author Hien Hoang
 */
public class TableStation extends JPanel implements Notifiable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLabel titleLabel = new JLabel();
	JPanel tableListPanel = new JPanel();
	List<TablePanel> tableList = new ArrayList<>();
	BackNextGroup btnBackNext = new BackNextGroup();
	
	protected Configuration cfg = Configuration.getInstance();
	protected GameSelection gameSelection;
	
	public TableStation() {
		initialize();
		NotificationBoard.getInstance().subscribe(this, NotificationBoard.NF_BOARD_LIST_CHANGED);
	}
	
	private void initialize() {
		
        titleLabel.setText("TABLE");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 40);
        titleLabel.setFont(othelloFont);
        titleLabel.setForeground(Color.GRAY);
        
        tableListPanel.setLayout(new GridLayout(0, 2));
        
        if (cfg.getPlayingType().name.equalsIgnoreCase(TypeCfg.TYPE_ONLINE)) {
        	
        	gameSelection = GameSelection.getInstance();
        }
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 0));

        JScrollPane jscrollPane = new JScrollPane(tableListPanel);
        contentPanel.add(jscrollPane, BorderLayout.CENTER);
        
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
	}
	
    public void loadBoards(List<Board> boards) {
        this.tableList.clear();
        this.tableListPanel.removeAll();
        System.out.println("Loading boards...");
        for (Board board : boards) {
            TablePanel tablePanel = new TablePanel(board);
            System.out.println("Adding board: " + board.getName());
            tableListPanel.add(tablePanel);
//            tableListPanel.add(Box.createRigidArea(new Dimension(0,25)));
            this.tableList.add(tablePanel);
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
		if (category == NotificationBoard.NF_BOARD_LIST_CHANGED) {
			List<Board> boards = (List<Board>)detail;
			loadBoards(boards);
		}
	}
}
