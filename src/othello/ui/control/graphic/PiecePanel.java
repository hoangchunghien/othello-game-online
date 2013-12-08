package othello.ui.control.graphic;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import othello.common.Piece;
import othello.common.Position;
/**
 *
 * @author Hien Hoang
 * @version Nov 10, 2013
 */
public class PiecePanel extends JPanel implements ActionListener {
    
    public final static int PIC_SPACE = 60;
    public final static int MAX_ANIMATE_NUMBER = 32;
    private final static int BLACK_X = 1;
    private final static int BLACK_Y = 0;
    private final static int WHITE_X = 7;
    private final static int WHITE_Y = 3;
    private final static int EMPTY_X = 0;
    private final static int EMPTY_Y = 0;
    
    String imgLocation = "pixmaps/standard-1.png";
    ImageIcon pieceImg = new ImageIcon() ;
    
    private Piece piece;
    private int x;
    private int y;
    private int locationX;
    private int locationY;
    private int width;
    
    // For animation
    ArrayList<Position> animationPieces = new ArrayList<Position>();
    int maxTransisitonNums;
    int pieceIndex;
    public int getMaxTransitionNums() {
        
        return this.maxTransisitonNums;
    }
    
    
    public void setPositionX(int value) {
        
        this.x = value;
    }
    
    public int getPositionX() {
        
        return this.x;
    }
    
    public void setPositionY(int value) {
        
        this.y = value;
    }
    
    public int getPositionY() {
        
        return this.y;
    }
    
    
    public void setSquareWidth(int width) {
        
        this.width = width;
    }
    
    public int getSquareWidth() {
        
        return this.width;
    }
    
    public void setPiece(Piece piece) {
        this.piece = piece;
        switch (piece) {
            case BLACK: 
                locationX = BLACK_X * PIC_SPACE;
                locationY = BLACK_Y * PIC_SPACE;
                pieceIndex = 2;
                break;
            case WHITE:
                locationX = WHITE_X * PIC_SPACE;
                locationY = WHITE_Y * PIC_SPACE;
                pieceIndex = 31;
                break;
            case EMPTY:
                locationX = EMPTY_X * PIC_SPACE;
                locationY = EMPTY_Y * PIC_SPACE;
                pieceIndex = 0;
                break;
            default:
                locationX = EMPTY_X * PIC_SPACE;
                locationY = EMPTY_Y * PIC_SPACE;
                pieceIndex = 0;
                break;
        }
    }
    
    public Piece getPiece() {
        
        return this.piece;
    }
    
    public PiecePanel() {
        
        initialize();
    }
    
    public void changePieceTo(Piece piece) throws InterruptedException {
        
        switch(this.piece) {
            case EMPTY: 
                this.setPiece(piece);
                repaint();
                break;
            case WHITE:
//                while (this.pieceIndex > 1) {
//                    Thread.sleep(50);
//                    this.pieceIndex--;
//                    changePieceTo(this.pieceIndex);
//                };
                this.setPiece(piece);
                repaint();
                break;
            case BLACK:
//                while (this.pieceIndex < 32) {
//                    Thread.sleep(50);
//                    this.pieceIndex++;
//                    changePieceTo(this.pieceIndex);
//                };
                this.setPiece(piece);
                repaint();
                break;
        }
    }
    
    public void changePieceTo( int index) {

        switch(this.piece) {
            case EMPTY: 
                this.setPiece(piece);
                repaint();
                break;
            case WHITE:

                this.locationX = animationPieces.get(index).getX();
                this.locationY = animationPieces.get(index).getY();
                repaint();
                break;
            case BLACK:

                this.locationX = animationPieces.get(index).getX();
                this.locationY = animationPieces.get(index).getY();
                repaint();
                break;
            default:
                this.setPiece(piece);
                repaint();
                break;
        }
    }
    
    private void  initialize() {
        
        this.width = PIC_SPACE;
        pieceImg = new ImageIcon(PiecePanel.class.getResource(imgLocation));
        this.setPiece(Piece.WHITE);
        
        // Initialize for animation
        int j = 0;
        for (int i = 0; i < 32; i++) {
            
            animationPieces.add(j++, new Position((i%8)*PIC_SPACE, (i/8)*PIC_SPACE));
        }
        maxTransisitonNums = j;
        
    }
    
    @Override
    public void paint(Graphics g) {
        
        g.drawImage(pieceImg.getImage(),0, 0, width, width, locationX, locationY, locationX + PIC_SPACE, locationY + PIC_SPACE, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (this.piece == Piece.BLACK) {
            if (this.pieceIndex < 32) {
                this.pieceIndex++;
                changePieceTo(this.pieceIndex);
            }
            else {
                setPiece(Piece.WHITE);
                
            }
        }
        if (this.piece == Piece.WHITE) {
            if (this.pieceIndex > 1) {
                this.pieceIndex--;
                changePieceTo(this.pieceIndex);
            }
            else {
                setPiece(Piece.BLACK);
                
            }
        }
//        if (this.piece == Piece.EMPTY) {
//            timer.stop();
//            setPiece(Piece.BLACK);
//            
//        }
    }
    
}
