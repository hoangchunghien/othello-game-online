package othello.ui.control.graphic.station;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import othello.game.NotificationBoard;

/**
 *
 * @author Hien Hoang
 */
public class MainStation extends JPanel {
    
    JLabel othelloLabel = new JLabel();
    OButton offlineButton = new OButton();
    OButton onlineButton = new OButton();
    OButton settingButton = new OButton();
    OButton helpButton = new OButton();
    OButton aboutButton = new OButton();
    
    NotificationBoard nb = NotificationBoard.getInstance();
    
    private int btnW = 250;
    private int btnH = 45;
    
    public MainStation() {
        
        initialize();
    }
    
    private void initialize() {
    	
        othelloLabel.setText("OTHELLO");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 40);
        othelloLabel.setFont(othelloFont);
        othelloLabel.setForeground(Color.GRAY);
        
        offlineButton.setText("OFFLINE");
        offlineButton.setLetter(StationUIManager.STATION_PLAY_WITH);
        
        onlineButton.setText("ONLINE");
        onlineButton.setLetter(StationUIManager.STATION_LOGIN);
        
        settingButton.setText("SETTINGS");
        settingButton.setLetter(StationUIManager.STATION_SETTINGS);
        
        helpButton.setText("HELP");
        helpButton.setLetter(null);
        
        aboutButton.setText("ABOUT");
        aboutButton.setLetter(null);
        

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(othelloLabel)
                    .addComponent(offlineButton, btnW, btnW, btnW)
                    .addComponent(onlineButton, btnW, btnW, btnW)
                    .addComponent(settingButton, btnW, btnW, btnW)
                    .addComponent(helpButton, btnW, btnW, btnW)
                    .addComponent(aboutButton, btnW, btnW, btnW)
                )
         );
 
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(othelloLabel).addGap(50)
                .addComponent(offlineButton, btnH, btnH, btnH).addGap(15)
                .addComponent(onlineButton, btnH, btnH, btnH).addGap(15)
                .addComponent(settingButton, btnH, btnH, btnH).addGap(15)
                .addComponent(helpButton, btnH, btnH, btnH).addGap(15)
                .addComponent(aboutButton, btnH, btnH, btnH).addGap(15)
         );
    }
    
    public void setOfflineLetter(String letter) {
    	offlineButton.setLetter(letter);
    }
    
    public void setOnlineLetter(String letter) {
    	onlineButton.setLetter(letter);
    }
    
    public void setSettingLetter(String letter) {
    	settingButton.setLetter(letter);
    }
    
    public void setHelpLetter(String letter) {
    	helpButton.setLetter(letter);
    }
    
    public void setAboutLetter(String letter) {
    	aboutButton.setLetter(letter);
    }
}
