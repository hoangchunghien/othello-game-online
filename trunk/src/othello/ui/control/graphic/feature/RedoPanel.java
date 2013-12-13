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
public class RedoPanel extends AbstractFeaturePanel {
 
    NotificationBoard nb = NotificationBoard.getInstance();
    
    public RedoPanel() {
        imagePanel = new RedoImagePanel();
        lbText = new JLabel("Redo");
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.red));
        this.activeService(false);
        initialize();
        nb.subscribe(this, NotificationBoard.NF_REDOABLE);
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
