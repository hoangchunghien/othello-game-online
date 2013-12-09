package othello.ui.control.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import othello.command.ChatCmd;
import othello.command.ClientCommandExecutorManager;
import othello.command.IChatCmdExec;
import othello.command.notify.IChatNtfExec;
import othello.command.response.IChatResExec;

/**
 *
 * @author Hien Hoang
 * @version Dec 8, 2013
 * Description
 * . TODO
 */
public class ChatPanel extends JPanel implements IChatResExec, IChatNtfExec {
    
    private DefaultListModel messageModel = new DefaultListModel();
    private JList messageList;
    private JTextField txtMessage;
    private JButton btnSend;
    
    private static ChatPanel singletonObject;
    
    public static ChatPanel getInstance() {
        if (singletonObject == null) {
            singletonObject = new ChatPanel();
        }
        return singletonObject;
    }
    
    public ChatPanel() {
        
        this.initialize();
    }
    
    private void initialize() {
        this.setBorder(BorderFactory.createLineBorder(Color.red));
        
        messageList = new JList(messageModel);
        JScrollPane scrollPane = new JScrollPane(messageList);
        scrollPane.setPreferredSize(new Dimension(250, 300));
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);
        
        JPanel messageListPanel = new JPanel();
        messageListPanel.setLayout(new BoxLayout(messageListPanel, BoxLayout.PAGE_AXIS));
        messageListPanel.add(scrollPane);
        messageListPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        
        txtMessage = new JTextField("Input text");
        txtMessage.addKeyListener(new ChatKeyListener());
        JPanel messageEnterPanel = new JPanel();
        messageEnterPanel.setLayout(new BoxLayout(messageEnterPanel, BoxLayout.LINE_AXIS));
        messageEnterPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        //messageEnterPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        txtMessage.setPreferredSize(new Dimension(180, 50));
        messageEnterPanel.add(txtMessage);
        
        btnSend = new JButton("Send");
        btnSend.addActionListener(new BtnSendActionListener());
        btnSend.setPreferredSize(new Dimension(70, 50));
        messageEnterPanel.add(btnSend);
        
        this.add(messageListPanel, BorderLayout.CENTER);
        this.add(messageEnterPanel, BorderLayout.PAGE_END);

    }

    @Override
    public void loadMessage(String msg) {
        
        messageModel.addElement(msg);
    }

    @Override
    public void displayChatMessage(String username, String message) {
        
        messageModel.addElement(username + ": " + message);
    }
    
    private void executeChatCommand() {
        if (txtMessage.getText().equals("")) {
            return;
        }
        
        IChatCmdExec chatCmdExec = ClientCommandExecutorManager.getChatCommandExecutor();
        if (chatCmdExec != null) {
            ChatCmd chatCommand = new ChatCmd(chatCmdExec, txtMessage.getText());
            chatCommand.execute();
        }
        
        txtMessage.setText("");
    }
    
    private class ChatKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                
                executeChatCommand();  
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
        
    }
    
    private class BtnSendActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            executeChatCommand();
        }
        
    }
    
}
