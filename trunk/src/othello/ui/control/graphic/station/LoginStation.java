package othello.ui.control.graphic.station;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Hien Hoang
 */
public class LoginStation extends JPanel {
    
    JLabel titleLabel = new JLabel();
    private JTextField txtUsername = new JTextField("username");
    private JPasswordField txtPassword = new JPasswordField("********");
    private JCheckBox chkRemember = new JCheckBox();
    private JLabel lbMessage = new JLabel();
    BackNextGroup btnBackNext = new BackNextGroup();
    
    public LoginStation() {
        initialize();
    }
    
    private void initialize() {
        
        titleLabel.setText("LOGIN");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 40);
        titleLabel.setFont(othelloFont);
        titleLabel.setForeground(Color.GRAY);
        
        chkRemember.setText("Remember");
        chkRemember.setFont(new Font(getFont().getFontName(), Font.TRUETYPE_FONT, 28));
        
        txtUsername.setFont(new Font(getFont().getFontName(), Font.TRUETYPE_FONT, 28));
        txtUsername.setMaximumSize(new Dimension(500, 30));
        
        txtPassword.setFont(new Font(getFont().getFontName(), Font.TRUETYPE_FONT, 28));
        txtPassword.setMaximumSize(new Dimension(500, 30));
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        contentPanel.add(txtUsername);
        contentPanel.add(Box.createRigidArea(new Dimension(0,25)));
        contentPanel.add(txtPassword);
        contentPanel.add(Box.createRigidArea(new Dimension(0,25)));
        contentPanel.add(chkRemember);
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
