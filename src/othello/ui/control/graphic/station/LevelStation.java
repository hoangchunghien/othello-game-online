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
import othello.configuration.LevelCfg;

/**
 *
 * @author Hien Hoang
 */
public class LevelStation extends JPanel implements ActionListener {
    JLabel titleLabel = new JLabel();
    ButtonGroup group = new ButtonGroup();
    JRadioButton rdEasy = new JRadioButton();
    JRadioButton rdNormal = new JRadioButton();
    JRadioButton rdHard = new JRadioButton();
    JRadioButton rdExpert = new JRadioButton();
    BackNextGroup btnBackNext = new BackNextGroup();
    
    protected Configuration cfg = Configuration.getInstance();
    
    public LevelStation() {
        initialize();
    }
     
    private void initialize() {

        titleLabel.setText("LEVEL");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 40);
        titleLabel.setFont(othelloFont);
        titleLabel.setForeground(Color.GRAY);
        
        Font radFont = new Font(this.getFont().getFontName(), Font.BOLD, 26);
        rdEasy.setText("EASY");
        rdEasy.setName("easy");
        rdEasy.addActionListener(this);
        rdEasy.setFont(radFont);
        
        rdNormal.setText("NORMAL");
        rdNormal.setName("normal");
        rdNormal.addActionListener(this);
        rdNormal.setFont(radFont);
        
        rdHard.setText("HARD");
        rdHard.setName("hard");
        rdHard.addActionListener(this);
        rdHard.setFont(radFont);
        
        rdExpert.setText("EXPERT");
        rdExpert.setName("expert");
        rdExpert.addActionListener(this);
        rdExpert.setFont(radFont);
        
        setSelectedLevel(cfg.getSelectedLevel().name);
        setNextLetter(StationUIManager.STATION_PLAY_FIRST);
        
        group.add(rdEasy);
        group.add(rdNormal);
        group.add(rdHard);
        group.add(rdExpert);
        
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
        radioPanel.add(rdEasy);
        radioPanel.add(Box.createRigidArea(new Dimension(0,25)));
        radioPanel.add(rdNormal);
        radioPanel.add(Box.createRigidArea(new Dimension(0,25)));
        radioPanel.add(rdHard);
        radioPanel.add(Box.createRigidArea(new Dimension(0,25)));
        radioPanel.add(rdExpert);
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
    
    public void setSelectedLevel(String name) {
    	switch (name.toLowerCase()) {
    		case LevelCfg.EASY:
    			cfg.setSelectedLevel(LevelCfg.EASY);
    			rdEasy.setSelected(true);
    			break;
    		case LevelCfg.NORMAL:
    			cfg.setSelectedLevel(LevelCfg.NORMAL);
    			rdNormal.setSelected(true);
    			break;
    		case LevelCfg.HARD:
    			cfg.setSelectedLevel(LevelCfg.HARD);
    			rdHard.setSelected(true);
    			break;
    		case LevelCfg.EXPERT:
    			cfg.setSelectedLevel(LevelCfg.EXPERT);
    			rdExpert.setSelected(true);
    			break;
			default:
				cfg.setSelectedLevel(LevelCfg.NORMAL);
    			rdNormal.setSelected(true);
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
		setSelectedLevel(rd.getName());
	}
}
