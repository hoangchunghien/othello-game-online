package othello.ui.control;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import othello.configuration.Configuration;
import othello.configuration.TypeCfg;
import othello.game.GameState;
import othello.game.NotificationBoard;
import othello.ui.control.graphic.*;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
public class GraphicCtrl extends AbstractControlUI implements WindowListener {

    JFrame controlFrame = new JFrame();
    JPanel bPanel = new JPanel();
    FeatureAndBoardPanel fboardPanel = new FeatureAndBoardPanel();
    InfoPanel infoPanel = new InfoPanel();
    Configuration cfg = Configuration.getInstance();
    NotificationBoard nb = NotificationBoard.getInstance();
    
    // Online components
    PlayerListPanel plPanel;
    
    
    public GraphicCtrl() {
        
        initialize();
    }
    
    private void initialize() {
        
        controlFrame.setLayout(new GridBagLayout());
        controlFrame.setSize(900, 700);
        controlFrame.addWindowListener(this);
        // controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
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

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		nb.fireChangeNotification(NotificationBoard.NF_GAME_EXITED, Boolean.TRUE);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
    
    
}
