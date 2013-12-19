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
public class PlayFirstStation extends JPanel implements ActionListener {
    
    JLabel titleLabel = new JLabel();
    ButtonGroup group = new ButtonGroup();
    JRadioButton radComputer = new JRadioButton();
    JRadioButton radHuman = new JRadioButton();
    BackNextGroup btnBackNext = new BackNextGroup();
    
    Configuration cfg = Configuration.getInstance();
    
    public PlayFirstStation() {
        initialize();
    }
     
    private void initialize() {

        titleLabel.setText("PLAY FIRST");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 40);
        titleLabel.setFont(othelloFont);
        titleLabel.setForeground(Color.GRAY);
        
        Font radFont = new Font(this.getFont().getFontName(), Font.BOLD, 26);
        radComputer.setText("COMPUTER");
        radComputer.setName(PlayerCfg.TYPE_COMPUTER);
        radComputer.addActionListener(this);
        radComputer.setFont(radFont);
        
        radHuman.setText("YOU");
        radHuman.setName(PlayerCfg.TYPE_HUMAN);
        radHuman.addActionListener(this);
        radHuman.setFont(radFont);
        
        setFirstPlayer(cfg.players.players.get(cfg.players.getFirstPlayerIndex()).getType());
        setNextLetter(StationUIManager.STATION_CONFIG);
        
        group.add(radComputer);
        group.add(radHuman);
        
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
        radioPanel.add(radHuman);
        radioPanel.add(Box.createRigidArea(new Dimension(0,25)));
        radioPanel.add(radComputer);
        radioPanel.add(Box.createVerticalGlue());
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(titleLabel)
                    .addComponent(radioPanel)
                    .addComponent(btnBackNext)
                )
         );
 
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(titleLabel).addGap(50)
                .addComponent(radioPanel)
                .addComponent(btnBackNext)

         );
     }
    
    private void setFirstPlayer(String name) {
    	switch (name.toLowerCase()) {
    		case PlayerCfg.TYPE_COMPUTER:
    			cfg.players.setFirstPlayerByType(PlayerCfg.TYPE_COMPUTER);
    			radComputer.setSelected(true);
    			break;
    		case PlayerCfg.TYPE_HUMAN:
    			cfg.players.setFirstPlayerByType(PlayerCfg.TYPE_HUMAN);
    			radHuman.setSelected(true);
    			break;
			default:
				cfg.players.setFirstPlayerByType(PlayerCfg.TYPE_HUMAN);
				radHuman.setSelected(true);
				break;
    	}
    	cfg.serialize(Configuration.CONFIG_FILEPATH);
    }
    
    public void setBackLetter(String letter) {
    	btnBackNext.setBackLetter(letter);
    }
    
    public void setNextLetter(String letter) {
    	btnBackNext.setNextLetter(letter);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		JRadioButton rd = (JRadioButton)e.getSource();
		setFirstPlayer(rd.getName());
	}
}
