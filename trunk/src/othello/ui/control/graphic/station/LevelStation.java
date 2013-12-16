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
public class LevelStation extends JPanel {
    JLabel titleLabel = new JLabel();
    ButtonGroup group = new ButtonGroup();
    JRadioButton rdEasy = new JRadioButton();
    JRadioButton rdNormal = new JRadioButton();
    JRadioButton rdHard = new JRadioButton();
    JRadioButton rdExpert = new JRadioButton();
    BackNextGroup btnBackNext = new BackNextGroup();
    
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
        rdEasy.setFont(radFont);
        
        rdNormal.setText("NORMAL");
        rdNormal.setFont(radFont);
        
        rdHard.setText("HARD");
        rdHard.setFont(radFont);
        
        rdExpert.setText("EXPERT");
        rdExpert.setFont(radFont);
        
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
}
