package othello.ui.control;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import othello.configuration.Configuration;
import othello.game.GameMonitor;
import othello.game.GameState;
import othello.ui.control.graphic.*;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class GraphicCtrl implements IControl {

    JFrame controlFrame = new JFrame();
    JPanel bPanel = new JPanel();
    BoardPanel boardPanel = new BoardPanel();
    Configuration cfg = Configuration.getInstance();
    
    // For animation
//    private boolean isOnAnimation = false;
    
    public GraphicCtrl() {
        
        initialize();
        controlFrame.add(bPanel);
    }
    
    private void initialize() {
        
        boardPanel.setPreferredSize(new Dimension(cfg.board.height * PiecePanel.PIC_SPACE, 
                                                  cfg.board.width * PiecePanel.PIC_SPACE));
        boardPanel.setMinimumSize(boardPanel.getPreferredSize());
        
        bPanel.setMinimumSize(new Dimension(520, 520));
        bPanel.add(boardPanel);
        
        controlFrame.setSize(520, 530);
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void renderGameState(GameState gameState) {
        
        //System.out.print("Render board");
        try {
            // boardPanel.repaint();
            Thread.holdsLock(GameMonitor.getInstance());          
            boardPanel.renderBoard(gameState.getBoard());
            // Thread.sleep(10000);
            // boardPanel.repaint();
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphicCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void show() {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                controlFrame.setVisible(true);
            }
        });

    }

    @Override
    public void notifyMessage(String msg) {
        
        System.out.println("notify: " + msg);
    }

    @Override
    public void allowMakeMove() {
        
        System.out.println("allow make move ");
    }

    
    
    
}
