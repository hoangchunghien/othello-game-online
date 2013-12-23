package othello.client;

import javax.swing.JOptionPane;

import othello.command.ClientCommandExecutorManager;
import othello.command.GetMoveCmd;
import othello.command.MoveCmd;
import othello.command.UndoCmd;
import othello.command.notify.IMoveNtfExec;
import othello.common.AbstractPlayer;
import othello.common.Piece;
import othello.common.Position;
import othello.configuration.Configuration;
import othello.game.GameState;
import othello.game.NotificationBoard;
import othello.sound.SoundManager;
import othello.ui.UIFactory;

/**
 *
 * @author Hien Hoang
 * @version Dec 8, 2013
 * Description
 * . The human player will interact with UI to get move, or to render move to
 * . user. Unlike computer player that doesn't interact with UI
 */
public class HumanPlayer extends AbstractPlayer implements IMoveNtfExec{
    
    public static final String TYPE = "human";
    
    NotificationBoard nb = NotificationBoard.getInstance();
    Configuration cfg = Configuration.getInstance();

    public HumanPlayer(Piece piece) {
        super(piece);
        nb.subscribe(this, NotificationBoard.NF_UNDOCALLED);
    }
    
    public HumanPlayer(Piece piece, String name) {
        this(piece);
        this.setName(name);
    }
    

    @Override
    public void makeMoving(Position p) {
        
        MoveCmd moveCmd = new MoveCmd(ClientCommandExecutorManager.getMoveCommandExecutor(),this, p);
        moveCmd.execute();
    }

    @Override
    public AbstractPlayer clone() {
        AbstractPlayer dup = new HumanPlayer(getPiece(), getName());
        dup.setScore(getScore());
        
        return dup;
    }

    @Override
    public void makeOverGame() {
        // Notify game over here
    	if (cfg.sounds.playSound && cfg.sounds.getGameOverSound()!= null) {
    		SoundManager.getInstance().playSound(cfg.sounds.getGameOverSound().file);
    	}
    	JOptionPane.showMessageDialog(null,"GAMEOVER");
    }

    @Override
    public void makePassing() {
        // Notify Passing here
    	if (cfg.sounds.playSound && cfg.sounds.getPassSound()!= null) {
    		SoundManager.getInstance().playSound(cfg.sounds.getPassSound().file);
    	}
    	JOptionPane.showMessageDialog(null, "You have no move, passed!!");
    }

    @Override
    public void processMoveAccepted(Position position) {
    	if (cfg.sounds.playSound && cfg.sounds.getMoveSound()!= null) {
    		SoundManager.getInstance().playSound(cfg.sounds.getMoveSound().file);
    	}
    }

    @Override
    public void processMoveRejected(String message) {
        System.out.println("Human move rejected, error: " + message);
    }

    @Override
    public void notifyMoving(Position p) {
        
    }

    @Override
    public void answerRequest(int reqType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receiveChangeNotification(int category, Object detail) {
        if (category == NotificationBoard.NF_GAMESTATE_CHANGED) {
        	// JOptionPane.showMessageDialog(null, "Human state changed");
        	
            GameState newState = (GameState)detail; 
            UIFactory.getControlUI().fireStateChanged(newState);
        }
        if (category == NotificationBoard.NF_UNDOCALLED) {
            
            UndoCmd undoCmd = new UndoCmd(ClientCommandExecutorManager.getUndoCommandExecutor(), this);
            undoCmd.execute();
        }
        if (category == NotificationBoard.NF_MOVE_TURN) {
            AbstractPlayer player = (AbstractPlayer)detail;
            if (player == this) {
            	
                GetMoveCmd getMoveCmd = 
                    new GetMoveCmd(ClientCommandExecutorManager.getGetMoveCommandExecutor(this), this);
                getMoveCmd.execute();
                nb.fireChangeNotification(NotificationBoard.NF_VALID_MOVE_CHANGED, validMoves);
            }
        }
        
        if (category == NotificationBoard.NF_GAMEOVER) {
            System.out.println("Human: GAMEOVER");
        }
    }

}
