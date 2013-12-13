package othello.ui.control.graphic.feature;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import othello.game.Notifiable;

/**
 *
 * @author Hien Hoang
 */
public abstract class AbstractFeaturePanel extends JPanel implements MouseListener, Notifiable{
    protected boolean onService;
    protected AbstractImagePanel imagePanel;
    protected JLabel lbText;
    
    public AbstractFeaturePanel() {
        
    }
    
    protected void initialize() {

        // this.setBorder(BorderFactory.createLineBorder(Color.red));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(100, 100));
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(imagePanel, GroupLayout.Alignment.CENTER, 60, 60, 60)
                    .addComponent(lbText))
         );
        
         layout.setVerticalGroup(
            layout.createSequentialGroup()
               .addComponent(imagePanel)
               .addComponent(lbText)
         );
         
         this.addMouseListener(this);
    }
    
    public void activeService(boolean flag) {

        onService = flag;
        imagePanel.activeService(flag);
        if (flag) {
            lbText.setForeground(Color.BLACK);
            Font boldFont = new Font(lbText.getFont().getFontName(), Font.BOLD, 12);
            lbText.setFont(boldFont);
        }
        else {
            lbText.setForeground(Color.LIGHT_GRAY);
        }
        updateUI();
    }

    public abstract void mouseClicked(MouseEvent e);

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (onService) {
            imagePanel.setActive(true);
            lbText.setForeground(Color.BLUE);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (onService) {
            imagePanel.setActive(false);
            lbText.setForeground(Color.BLACK);
        }
    }
}
