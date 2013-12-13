package othello.ui.control.graphic;

import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import othello.configuration.Configuration;

/**
 *
 * @author Hien Hoang
 */
public class FeatureAndBoardPanel extends JPanel {
    
    private BoardPanel boardPanel = BoardPanel.getInstance();
    private GameFeaturePanel featurePanel = new GameFeaturePanel();
    Configuration cfg = Configuration.getInstance();
    
    public FeatureAndBoardPanel() {
        initialize();
    }
    
    private void initialize() {
        
        boardPanel.setPreferredSize(new Dimension(cfg.board.height * PiecePanel.PIC_SPACE, 
                                                  cfg.board.width * PiecePanel.PIC_SPACE));
        boardPanel.setMinimumSize(boardPanel.getPreferredSize());
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(boardPanel)
                    .addComponent(featurePanel)
                )
         );
        
         layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(boardPanel)
                .addComponent(featurePanel)
         );
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public GameFeaturePanel getFeaturePanel() {
        return featurePanel;
    }
    
}
