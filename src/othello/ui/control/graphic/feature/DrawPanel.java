package othello.ui.control.graphic.feature;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import othello.game.NotificationBoard;

/**
 *
 * @author Hien Hoang
 */
public class DrawPanel extends AbstractFeaturePanel{
    
    NotificationBoard nb = NotificationBoard.getInstance();

    public DrawPanel() {
        
        imagePanel = new DrawImagePanel();
        lbText = new JLabel("Draw");
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.red));
        this.activeService(false);
        initialize();
        nb.subscribe(this, NotificationBoard.NF_DRAWABLE);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receiveChangeNotification(int category, Object detail) {
        Boolean isAllow = (Boolean)detail;
        this.activeService(isAllow.booleanValue());
    }

}
