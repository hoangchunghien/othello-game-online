package othello.ui.control;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
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
    FeatureAndBoardPanel fboardPanel = new FeatureAndBoardPanel();
    InfoPanel infoPanel = new InfoPanel();
    Configuration cfg = Configuration.getInstance();
    
    // Online components
    PlayerListPanel plPanel;
    
    
    public GraphicCtrl() {
        
        initialize();
    }
    
    private void initialize() {
        
        controlFrame.setLayout(new GridBagLayout());
        controlFrame.setSize(900, 700);
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        bPanel.setMinimumSize(new Dimension(800, 600));
        bPanel.setMaximumSize(new Dimension(800, 600));
        GroupLayout layout = new GroupLayout(bPanel);
        bPanel.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addComponent(fboardPanel)
                .addComponent(infoPanel)
         );
        
         layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(fboardPanel)
                    .addComponent(infoPanel)
                )
         );
         
         controlFrame.add(bPanel);
        
    }
    
    @Override
    public void renderGameState(GameState gameState) {
        try {
            
            fboardPanel.getBoardPanel().renderBoard(gameState.getBoard());
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
        System.out.println("State changed");
        if (!controlFrame.isVisible())
            controlFrame.setVisible(true);
        this.renderGameState(newState);
    }
    
    
}
