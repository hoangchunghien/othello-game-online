package othello.ui.control.graphic;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import othello.command.CommandFactory;
import othello.command.Commandable;
import othello.command.LoginCmd;
import othello.command.response.ILoginResExec;

/**
 *
 * @author Hien Hoang
 */
public class LoginFrame extends JFrame implements ILoginResExec, ActionListener {
    
    private static LoginFrame singletonObject;
    
    public static LoginFrame getInstance() {
        if (singletonObject == null) {
            singletonObject = new LoginFrame();
        }
        return singletonObject;
    }
    
    private JLabel lbUsername;
    private JTextField txtUsername;
    private JLabel lbPassword;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnLater;
    private JLabel lbRemember;
    private JCheckBox chkRemember;
    private JLabel lbMessage;
    
    private JPanel loginPanel;
    
    public LoginFrame() {
        
        initialize();
        this.getContentPane().add(loginPanel);
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setName("Login");
    }
    
    private void initialize() {
        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setSize(300, 200);
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        lbMessage = new JLabel();
        loginPanel.add(lbMessage, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        lbUsername = new JLabel("Username");
        loginPanel.add(lbUsername, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        txtUsername = new JTextField();
        loginPanel.add(txtUsername, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        lbPassword = new JLabel("Password");
        loginPanel.add(lbPassword, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        txtPassword = new JPasswordField();
        loginPanel.add(txtPassword, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        chkRemember = new JCheckBox();
        loginPanel.add(chkRemember, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        lbRemember = new JLabel("Remember me");
        loginPanel.add(lbRemember, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        btnLogin = new JButton("Login");
        btnLogin.setName("btnLogin");
        btnLogin.addActionListener(this);
        loginPanel.add(btnLogin, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        btnLater = new JButton("Later");
        btnLater.setName("btnLater");
        btnLater.addActionListener(this);
        loginPanel.add(btnLater, c);
    }
    
    @Override
    public void makeLogin() {
        
    }

    @Override
    public void notifyFailure(String msg) {
        
        lbMessage.setForeground(Color.red);
        lbMessage.setText(msg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        if (button.getName().equalsIgnoreCase("btnlogin")) {
            
//            ICommand cmd = CommandFactory.getCommand(LoginCmd.NAME + " " + 
//                    txtUsername.getText() + " " + new String(txtPassword.getPassword()));
//            cmd.execute();
        }
        
        if (button.getName().equalsIgnoreCase("btnlater")) {
            
            lbMessage.setText(new String(txtPassword.getPassword()));
        }
    }
        
}

