package othello.ui.control.graphic;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import javax.swing.JPanel;
import othello.common.Board;
import othello.common.Position;
import othello.configuration.Configuration;
import othello.game.GameMonitor;
/**
 *
 * @author Hien Hoang
 * @version Nov 10, 2013
 */
public class BoardPanel extends JPanel implements MouseListener, ActionListener {
    
    private Configuration cfg = Configuration.getInstance();
    private PiecePanel pieces[][] = new PiecePanel[cfg.board.height][cfg.board.width];
    private Board board;
    Timer timer;
    int pixWidth;
    int pixHeight;
    
    public BoardPanel() {
        
        this(8, 8);
    }
    
    public BoardPanel(int rows, int cols) {
        
        super(new GridLayout(rows, cols));
        initialize();
    }
    
    public BoardPanel(LayoutManager layout) {
        
        super(layout);
        // this.timer = new Timer(50, this);
        initialize();
    }
    
    private void initialize() {
        
        int height = cfg.board.height;
        int width = cfg.board.width;
        this.setSize(width*PiecePanel.PIC_SPACE, height*PiecePanel.PIC_SPACE);
        board = new Board(width, height);
        pixWidth = board.getWidth() * PiecePanel.PIC_SPACE;
        pixHeight = board.getHeight() * PiecePanel.PIC_SPACE;
//        renderBoard(board);
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
    }
    
    public void renderBoard(Board board) throws InterruptedException {
        
        List<PiecePanel> changedPieces = getChangedPieces(board);
//        this.timer = new Timer(50, this);
//        for (PiecePanel item : changedPieces) {
//            item.timer.start();
//        }
        //timer.start();
        
        // System.out.println("number of change: " + changedPieces.size());
        for (PiecePanel item : changedPieces) {
            
            item.changePieceTo(board.getPiece(item.getPositionX(), item.getPositionY()));
            // System.out.println(item);
        }
        this.board = board.clone();
    }
    
    public List<PiecePanel> getChangedPieces(Board board) {
        
        List<PiecePanel> result = new ArrayList<PiecePanel>();
        for (int i = 0; i < board.getHeight(); i++) {
            
            for (int j = 0; j < board.getWidth(); j++) {
                
                if (board.getPiece(j, i) != this.board.getPiece(j, i)) {
                    
                    // System.out.println(board.getPiece(j, i).toString() + " " + this.board.getPiece(j, i).toString());
                    result.add(pieces[i][j]);
                }
            }
        }
        return result;
    }
    
//    @Override
//    public void paint(Graphics g) {
//        
//       
//    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        PiecePanel piece = (PiecePanel) e.getSource();
        GameMonitor.getInstance().makeMove(new Position(piece.getPositionX(), piece.getPositionY()));
        // System.out.println(piece.getPositionX() + " : " + piece.getPositionY());
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
}

//class BoardPanel_timer_actionAdapter implements java.awt.event.ActionListener
//{
//    BoardPanel adaptee ;
//    List<PiecePanel> changedPieces;
//    Board board;
//    Timer timer;
//    int maxTransitionCount = 28;
//
//    BoardPanel_timer_actionAdapter( BoardPanel adaptee, List<PiecePanel> changedPieces, Board board , Timer timer)
//    {
//        this.adaptee = adaptee ;
//        this.changedPieces = changedPieces;
//        this.board = board;
//        this.timer = timer;
//    }
//
//    @Override
//    public void actionPerformed( ActionEvent e )
//    {
//        for (PiecePanel item : changedPieces) {
//            item.transitionPieceTo(board.getPiece(item.getX(), item.getY()), maxTransitionCount);
//        }
//        maxTransitionCount--;
//        if (maxTransitionCount >= 0)
//            timer.stop();
//    }
//}
