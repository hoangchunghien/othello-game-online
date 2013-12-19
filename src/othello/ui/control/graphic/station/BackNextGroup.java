package othello.ui.control.graphic.station;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import othello.game.NotificationBoard;

/**
 *
 * @author Hien Hoang
 */
public class BackNextGroup extends JPanel {
    
    public OButton btnBack = new OButton();
    public OButton btnNext = new OButton();
    
    private int btnW = 125;
    private int btnH = 45;
    
    public void setNextLetter(String letter) {
    	btnNext.setLetter(letter);
    }
    
    public void setBackLetter(String letter) {
    	btnBack.setLetter(letter);
    }
    
    public BackNextGroup() {
        initialize();
    }
    
    public void setNextName(String name) {
    	btnNext.setText(name);
    }
    
    private void initialize() {
        btnBack.setText("BACK");
        btnBack.setNotifyCategory(NotificationBoard.NF_UI_BACK);
        
        btnNext.setText("NEXT");
        btnNext.setNotifyCategory(NotificationBoard.NF_UI_NEXT);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addComponent(btnBack, btnW, btnW, btnW)
                .addComponent(btnNext, btnW, btnW, btnW)
         );
 
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(btnBack, btnH, btnH, btnH)
                    .addComponent(btnNext, btnH, btnH, btnH)
                )
         );
    }
}
