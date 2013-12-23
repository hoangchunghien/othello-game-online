package othello.ui.control.graphic;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.JPanel;

import othello.command.IGetMoveCmdExec;
import othello.command.response.GetMoveRes;
import othello.command.response.IResponse;
import othello.command.response.ResponseExecutorManager;
import othello.common.AbstractPlayer;
import othello.common.Board;
import othello.common.Position;
import othello.configuration.Configuration;
import othello.game.Notifiable;
import othello.game.NotificationBoard;
import othello.sound.SoundManager;

/**
 *
 * @author Hien Hoang
 * @version Nov 10, 2013
 */
public class BoardPanel extends JPanel implements MouseListener, ActionListener, IGetMoveCmdExec, Notifiable {
    
    private Configuration cfg = Configuration.getInstance();
    private NotificationBoard nb = NotificationBoard.getInstance();
    private AbstractPlayer getMoveCaller;
    private boolean allowGetMove = false;
    private static Object lock = new Object();
    
    private PiecePanel pieces[][] = new PiecePanel[cfg.board.height][cfg.board.width];
    private List<Position> validMoves;
    private Board board;
    Timer timer;
    int pixWidth;
    int pixHeight;
    
    private static BoardPanel singletonObject;
    
    public static BoardPanel getInstance() {
        synchronized(lock) {
            if (singletonObject == null) {
                singletonObject = new BoardPanel();
            }

            return singletonObject;
        }
    }
    
    public BoardPanel() {
        
        this(8, 8);
    }
    
    public BoardPanel(int rows, int cols) {
        
        super(new GridLayout(rows, cols));
        initialize();
    }
    
    public BoardPanel(LayoutManager layout) {
        
        super(layout);
        initialize();
    }
    
    private void initialize() {
        
        int height = cfg.board.height;
        int width = cfg.board.width;
        this.setSize(width*PiecePanel.PIC_SPACE, height*PiecePanel.PIC_SPACE);
        board = new Board(width, height);
        pixWidth = board.getWidth() * PiecePanel.PIC_SPACE;
        pixHeight = board.getHeight() * PiecePanel.PIC_SPACE;
        for (int i = 0; i < height; i++) {
            
            for (int j = 0; j < width; j++) {
                
                pieces[i][j] = new PiecePanel();
                pieces[i][j].setPiece(board.getPiece(j, i));
                pieces[i][j].setPositionX(j);
                pieces[i][j].setPositionY(i);
                pieces[i][j].addMouseListener(this);
                this.add(pieces[i][j]);
            }
        }
        nb.subscribe(this, NotificationBoard.NF_VALID_MOVE_CHANGED);
    }
    
    public void renderBoard(Board board) throws InterruptedException {
    	// JOptionPane.showMessageDialog(null, "Board changed");
    	for (int i = 0; i < board.getHeight(); i++) {
            
        	for (int j = 0; j < board.getWidth(); j++) {
        		pieces[i][j].displayValid(false);
        	}
        }
    	
        List<PiecePanel> changedPieces = getChangedPieces(board);
        for (PiecePanel item : changedPieces) {
            
        	Position po = new Position(item.getPositionX(), item.getPositionY());
            item.changePieceTo(board.getPiece(po));
            // this.board.setPiece(board.getPiece(po), po);
        }
        this.board = board.clone();
        
        this.showValidMove(validMoves);
    }
    
    public void showValidMove(List<Position> validMoves) {
    	// JOptionPane.showMessageDialog(null, "Show valid move");
    	if (getMoveCaller != null && cfg.userInterfaces.controlUI.showValidMove && allowGetMove) {

    		// JOptionPane.showMessageDialog(null, board.serializeJson());
    		for (Position p : validMoves) {
    			if (board.isValidMove(getMoveCaller.getPiece(), p))
    				pieces[p.getY()][p.getX()].displayValid(true);
    		}
    	}
    }
    
    public List<PiecePanel> getChangedPieces(Board board) {
        
        List<PiecePanel> result = new ArrayList<PiecePanel>();
        for (int i = 0; i < board.getHeight(); i++) {
            
            for (int j = 0; j < board.getWidth(); j++) {
                
                if (board.getPiece(j, i) != this.board.getPiece(j, i)) {
                    
                    result.add(pieces[i][j]);
                }
            }
        }
        return result;
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.allowGetMove) {
            this.allowGetMove = false;
        	
            PiecePanel piece = (PiecePanel) e.getSource();
            // JOptionPane.showMessageDialog(null, "Postion: " + piece.getPositionX() + "-" + piece.getPositionY());
            GetMoveRes getMoveResponse = 
                    new GetMoveRes(ResponseExecutorManager.getGetMoveResponseExecutor(getMoveCaller), 
                                    new Position(piece.getPositionX(), piece.getPositionY()));
            ResponseExecuting executing = new ResponseExecuting(getMoveResponse);
            executing.start();
        } 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        System.out.println("Timer");
    }

    @Override
    public void getMoveFor(AbstractPlayer player) {
        this.allowGetMove = true;
        this.getMoveCaller = player;
        // JOptionPane.showMessageDialog(null, "Get move for me now");
        // showValidMove();
    }

	@Override
	public void receiveChangeNotification(int category, Object detail) {
		if (category == NotificationBoard.NF_VALID_MOVE_CHANGED) {
			List<Position> validMoves = (List<Position>)detail;
			this.validMoves = validMoves;
			this.showValidMove(validMoves);
		}
	}
}

class ResponseExecuting extends Thread {
    private IResponse response;
    public ResponseExecuting(IResponse response) {
        this.response = response;
    }
    @Override
    public void run() {
        this.response.execute();
    }
}

