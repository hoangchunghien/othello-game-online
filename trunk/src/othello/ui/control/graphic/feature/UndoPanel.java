package othello.ui.control.graphic.feature;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import othello.command.UndoCmd;
import othello.game.NotificationBoard;

/**
 *
 * @author Hien Hoang
 */
public class UndoPanel extends AbstractFeaturePanel {
    
    NotificationBoard nb = NotificationBoard.getInstance();
    
    public UndoPanel() {
        
        imagePanel = new UndoImagePanel();
        lbText = new JLabel("Undo");
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.red));
        this.activeService(false);
        initialize();
        nb.subscribe(this, NotificationBoard.NF_UNDOABLE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (onService) {
            nb.fireChangeNotification(NotificationBoard.NF_UNDOCALLED, Boolean.TRUE);
        }
    }

    @Override
    public void receiveChangeNotification(int category, Object detail) {
        Boolean isAllow = (Boolean)detail;
        this.activeService(isAllow.booleanValue());
    }
    
    
}
