package othello.ui.control.graphic;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import othello.ui.control.graphic.feature.DrawPanel;
import othello.ui.control.graphic.feature.RedoPanel;
import othello.ui.control.graphic.feature.ResignPanel;
import othello.ui.control.graphic.feature.UndoPanel;

/**
 *
 * @author Hien Hoang
 */
public class GameFeaturePanel extends JPanel {
    
    private DrawPanel drawPanel;
    private RedoPanel redoPanel;
    private ResignPanel resignPanel;
    private UndoPanel undoPanel;

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }

    public RedoPanel getRedoPanel() {
        return redoPanel;
    }

    public ResignPanel getResignPanel() {
        return resignPanel;
    }

    public UndoPanel getUndoPanel() {
        return undoPanel;
    }
    
    public GameFeaturePanel() {
        // this.setBorder(BorderFactory.createLineBorder(Color.red));
        initialize();
    }
    
    private void initialize() {
        
        drawPanel = new DrawPanel();
        redoPanel = new RedoPanel();
        resignPanel = new ResignPanel();
        undoPanel = new UndoPanel();
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addComponent(undoPanel)
                .addComponent(redoPanel)
                .addComponent(drawPanel)
                .addComponent(resignPanel)
         );
        
         layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(undoPanel)
                    .addComponent(redoPanel)
                    .addComponent(drawPanel)
                    .addComponent(resignPanel)
                 )
         );
    }
}
