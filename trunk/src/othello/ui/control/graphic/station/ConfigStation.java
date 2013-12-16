package othello.ui.control.graphic.station;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Hien Hoang
 */
public class ConfigStation extends JPanel {
    
    JLabel titleLabel = new JLabel();
    JLabel lbTPM = new JLabel();
    JLabel lbTPG = new JLabel();
    JLabel lbSPG = new JLabel();
    JComboBox cbTPM = new JComboBox();
    JComboBox cbTPG = new JComboBox();
    JComboBox cbSPG = new JComboBox();
    BackNextGroup btnBackNext = new BackNextGroup();
    
    public ConfigStation() {
        initialize();
    }
    
    private void initialize() {
        
        titleLabel.setText("CONFIG");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 40);
        titleLabel.setFont(othelloFont);
        titleLabel.setForeground(Color.GRAY);
        
        lbTPM.setText("SECONDS PER MOVE");
        lbTPG.setText("SECONDS PER GAME");
        lbSPG.setText("SCORE PER GAME");
        cbTPG.setMaximumSize(new Dimension(500, 30));
        cbTPM.setMaximumSize(new Dimension(500, 30));
        cbSPG.setMaximumSize(new Dimension(500, 30));
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        contentPanel.add(lbTPM , BorderLayout.CENTER);
        contentPanel.add(cbTPM, BorderLayout.CENTER);
        contentPanel.add(Box.createRigidArea(new Dimension(0,25)));
        contentPanel.add(lbTPG, BorderLayout.CENTER);
        contentPanel.add(cbTPG, BorderLayout.CENTER);
        contentPanel.add(Box.createRigidArea(new Dimension(0,25)));
        contentPanel.add(lbSPG, BorderLayout.CENTER);
        contentPanel.add(cbSPG, BorderLayout.CENTER);
        contentPanel.add(Box.createVerticalGlue());
        
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
                .addComponent(titleLabel).addGap(50)
                .addComponent(contentPanel)
                .addComponent(btnBackNext)

         );
    }
}
