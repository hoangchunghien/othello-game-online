package othello.ui.control.graphic.station;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Hien Hoang
 */
public class PlayWithStation extends JPanel {
    
    JLabel playWithLabel = new JLabel();
    ButtonGroup group = new ButtonGroup();
    JRadioButton radComputer = new JRadioButton();
    JRadioButton radHuman = new JRadioButton();
    BackNextGroup btnBackNext = new BackNextGroup();
    
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
        
        radHuman.setText("FRIEND");
        radHuman.setFont(radFont);
        
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
}
