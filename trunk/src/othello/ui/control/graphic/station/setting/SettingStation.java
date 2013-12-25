package othello.ui.control.graphic.station.setting;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import othello.ui.control.graphic.station.BackNextGroup;
import othello.ui.control.graphic.station.OButton;
import othello.ui.control.graphic.station.StationUIManager;

public class SettingStation extends JPanel {
	
	JLabel titleLabel = new JLabel();
	OButton soundButton = new OButton();
	OButton graphicButton = new OButton();
	BackNextGroup btnBackNext = new BackNextGroup();
	
    private int btnW = 250;
    private int btnH = 45;
	
	public SettingStation() {
		initialize();
	}
	
	private void initialize() {
		
		titleLabel.setText("SETTINGS");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 40);
        titleLabel.setFont(othelloFont);
        titleLabel.setForeground(Color.GRAY);
        
        soundButton.setText("SOUNDS");
        soundButton.setLetter(StationUIManager.STATION_SETTINGS_SOUNDS);
        
        graphicButton.setText("GRAPHIC");
        graphicButton.setLetter(StationUIManager.STATION_SETTINGS_GRAPHIC);
        
        JPanel gapPanel = new JPanel();
        gapPanel.setLayout(new BoxLayout(gapPanel, BoxLayout.PAGE_AXIS));
        gapPanel.add(Box.createVerticalGlue());
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(titleLabel)
                    .addComponent(soundButton, btnW, btnW, Integer.MAX_VALUE)
                    .addComponent(graphicButton, btnW, btnW, Integer.MAX_VALUE)
                    .addComponent(gapPanel)
                    .addComponent(btnBackNext)
                )
         );
 
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(titleLabel).addGap(50)
                .addComponent(soundButton, btnH, btnH, btnH).addGap(25)
                .addComponent(graphicButton, btnH, btnH, btnH)
                .addComponent(gapPanel)
                .addComponent(btnBackNext)
         );
        
        setNextLetter(null);
	}
	
	public void setBackLetter(String letter) {
    	btnBackNext.setBackLetter(letter);
    }
    
    public void setNextLetter(String letter) {
    	btnBackNext.setNextLetter(letter);
    }
} 
