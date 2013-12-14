package othello.ui.control.graphic;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import othello.common.Piece;

/**
 *
 * @author Hien Hoang
 */
public class PieceImagePanel extends JPanel {
    protected static final String PIC_PATH = "pixmaps";
    public final static String BLACK = PIC_PATH + "/BLACK.png";
    public final static String WHITE = PIC_PATH + "/WHITE.png";
    
    protected String imgLocation;
    protected ImageIcon image;
    protected int imgWidth = 60;
    
    public PieceImagePanel() {
        initialize();
    }
    
    public void setSize(int size) {
        imgWidth = size;
        updateUI();
    }
    
    public void setPiece(Piece piece) {
        if (piece == Piece.BLACK) {
            imgLocation = BLACK;
        }
        else {
            imgLocation = WHITE;
        }
        updateUI();
    }
    
    private void initialize() {
        
        imgLocation = BLACK;
        this.setMinimumSize(new Dimension(imgWidth, imgWidth));
        this.setMaximumSize(new Dimension(imgWidth, imgWidth));
    }
    
    @Override
    public void paint(Graphics g) {
        
        image = new ImageIcon(PieceImagePanel.class.getResource(imgLocation));
        int x = (this.getWidth() - imgWidth) / 2;
        int y = (this.getHeight() - imgWidth) / 2;
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(image.getImage(),x, y, imgWidth, imgWidth, this);
    }
}
