package othello.ui.control.graphic.station;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        
        onlineButton.setText("ONLINE");
        
        settingButton.setText("SETTING");
        
        helpButton.setText("HELP");
        
        aboutButton.setText("ABOUT");
        

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
}
