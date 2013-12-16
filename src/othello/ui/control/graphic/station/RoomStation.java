package othello.ui.control.graphic.station;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

/**
 *
 * @author Hien Hoang
 */
public class RoomStation extends JPanel {
    
    JLabel titleLabel = new JLabel();
    private DefaultListModel roomsModel = new DefaultListModel();
    private JList roomsList = new JList(roomsModel);
    private JScrollPane scrollPane = new JScrollPane(roomsList);
    BackNextGroup btnBackNext = new BackNextGroup();
    
    public RoomStation() {
        initialize();
    }
    
    private void initialize() {
        
        titleLabel.setText("ROOM");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 40);
        titleLabel.setFont(othelloFont);
        titleLabel.setForeground(Color.GRAY);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(1, 0));
        contentPanel.add(scrollPane);
//        contentPanel.add(Box.createVerticalGlue());
//        contentPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        
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
                .addGap(30)
                .addComponent(titleLabel).addGap(10)
                .addComponent(contentPanel)
                .addComponent(btnBackNext)

         );
    }
}
