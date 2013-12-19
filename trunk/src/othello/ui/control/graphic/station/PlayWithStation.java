package othello.ui.control.graphic.station;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import othello.configuration.Configuration;
import othello.configuration.PlayerCfg;

/**
 *
 * @author Hien Hoang
 */
public class PlayWithStation extends JPanel implements ActionListener {
    
    JLabel playWithLabel = new JLabel();
    ButtonGroup group = new ButtonGroup();
    JRadioButton radComputer = new JRadioButton();
    JRadioButton radHuman = new JRadioButton();
    BackNextGroup btnBackNext = new BackNextGroup();
    
    protected Configuration cfg = Configuration.getInstance();
    
    public PlayWithStation() {
        initialize();
    }
     
    private void initialize() {

        playWithLabel.setText("PLAY WITH");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 40);
        playWithLabel.setFont(othelloFont);
        playWithLabel.setForeground(Color.GRAY);
        
        Font radFont = new Font(this.getFont().getFontName(), Font.BOLD, 26);
        radComputer.setText("COMPUTER");
        radComputer.setFont(radFont);
        radComputer.setName("computer");
        radComputer.addActionListener(this);
        
        radHuman.setText("FRIEND");
        radHuman.setFont(radFont);
        radHuman.setName("human");
        radHuman.addActionListener(this);
        
        if (cfg.players.hasComputerPlayer()) {
        	setSelectedType("computer");
        	radComputer.setSelected(true);
        }
        else {
        	setSelectedType("human");
        	radHuman.setSelected(true);
        }
        
        group.add(radComputer);
        group.add(radHuman);
        
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
        radioPanel.add(radComputer);
        radioPanel.add(Box.createRigidArea(new Dimension(0,25)));
        radioPanel.add(radHuman);
        radioPanel.add(Box.createVerticalGlue());
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(playWithLabel)
                    .addComponent(radioPanel)
                    .addComponent(btnBackNext)
                )
         );
 
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(playWithLabel).addGap(50)
                .addComponent(radioPanel)
                .addComponent(btnBackNext)

         );
        
     }
    
    public void setNextLetter(String letter) {
    	btnBackNext.setNextLetter(letter);
    }
    
    public void setBackLetter(String letter) {
    	btnBackNext.setBackLetter(letter);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		JRadioButton rd = (JRadioButton)e.getSource();
		setSelectedType(rd.getName().toLowerCase());
	}
	
	private void setSelectedType(String name) {
		switch (name) {
			case "computer":
				setNextLetter(StationUIManager.STATION_LEVEL);
				cfg.players.players.get(0).setType(PlayerCfg.TYPE_HUMAN);
				cfg.players.players.get(1).setType(PlayerCfg.TYPE_COMPUTER);
				break;
			case "human":
				setNextLetter(StationUIManager.STATION_CONFIG);
				for (PlayerCfg player : cfg.players.players) {
					player.setType(PlayerCfg.TYPE_HUMAN);
				}
				break;
		}
		cfg.serialize(Configuration.CONFIG_FILEPATH);
	}
}
