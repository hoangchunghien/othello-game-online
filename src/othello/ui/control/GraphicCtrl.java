package othello.ui.control;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import othello.configuration.Configuration;
import othello.configuration.TypeCfg;
import othello.game.GameState;
import othello.ui.control.graphic.*;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class GraphicCtrl extends AbstractControlUI {

    JFrame controlFrame = new JFrame();
    JPanel bPanel = new JPanel();
    BoardPanel boardPanel = BoardPanel.getInstance();
    Configuration cfg = Configuration.getInstance();
    
    // Online components
    PlayerListPanel plPanel;
    
    
    public GraphicCtrl() {
        
        // If playing type is online, let contruct the object for online play
        if (cfg.getPlayingType().name.equalsIgnoreCase(TypeCfg.TYPE_ONLINE)) {
            
            plPanel = PlayerListPanel.getInstance();
        }
        initialize();
    }
    
    private void initialize() {
        
        controlFrame.setLayout(new GridBagLayout());
        controlFrame.setSize(800, 600);
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagConstraints c = new GridBagConstraints();
        
        boardPanel.setPreferredSize(new Dimension(cfg.board.height * PiecePanel.PIC_SPACE, 
                                                  cfg.board.width * PiecePanel.PIC_SPACE));
        boardPanel.setMinimumSize(boardPanel.getPreferredSize());
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 3;
        bPanel.setMinimumSize(new Dimension(520, 520));
        bPanel.add(boardPanel);
        controlFrame.add(bPanel,c);
        
        // Loading online component
        if (cfg.getPlayingType().name.equalsIgnoreCase(TypeCfg.TYPE_ONLINE)) {
            
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 300;
            c.ipadx = 200;
            c.gridx = 1;
            c.gridy = 3;
            c.gridheight = 1;
            controlFrame.add(plPanel,c);
        }
    }
    
    @Override
    public void renderGameState(GameState gameState) {
        try {
            
            boardPanel.renderBoard(gameState.getBoard());
        } 
        catch (InterruptedException ex) {
            
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

    @Override
    public void fireStateChanged(GameState newState) {
        
        if (!controlFrame.isVisible())
            controlFrame.setVisible(true);
        this.renderGameState(newState);
    }

    
    
    
}
