package othello.ui.control.graphic;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

/**
 *
 * @author Hien Hoang
 */
public class InfoPanel extends JPanel {
    
    ThinkingTimerPanel thinkingPanel = new ThinkingTimerPanel();
    PlayersPanel playerPanel = new PlayersPanel();
    
    public InfoPanel() {
        initialize();
    }
    
    private void initialize() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(thinkingPanel)
                    .addComponent(playerPanel)
                )
         );
        
         layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(thinkingPanel)
                .addComponent(playerPanel)
         );
    }
}
