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
public class PlayFirstStation extends JPanel {
    
    JLabel titleLabel = new JLabel();
    ButtonGroup group = new ButtonGroup();
    JRadioButton radComputer = new JRadioButton();
    JRadioButton radHuman = new JRadioButton();
    BackNextGroup btnBackNext = new BackNextGroup();
    
    public PlayFirstStation() {
        initialize();
    }
     
    private void initialize() {

        titleLabel.setText("PLAY FIRST");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 40);
        titleLabel.setFont(othelloFont);
        titleLabel.setForeground(Color.GRAY);
        
        Font radFont = new Font(this.getFont().getFontName(), Font.BOLD, 26);
        radComputer.setText("CPU");
        radComputer.setFont(radFont);
        
        radHuman.setText("YOU");
        radHuman.setFont(radFont);
        
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
}
