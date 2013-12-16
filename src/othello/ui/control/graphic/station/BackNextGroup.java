package othello.ui.control.graphic.station;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

/**
 *
 * @author Hien Hoang
 */
public class BackNextGroup extends JPanel {
    
    public OButton btnBack = new OButton();
    public OButton btnNext = new OButton();
    
    private int btnW = 125;
    private int btnH = 45;
    
    public BackNextGroup() {
        initialize();
    }
    
    private void initialize() {
        btnBack.setText("BACK");
        btnNext.setText("NEXT");
        
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
