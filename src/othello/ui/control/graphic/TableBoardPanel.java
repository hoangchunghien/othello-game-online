package othello.ui.control.graphic;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Hien Hoang
 */
public class TableBoardPanel extends JButton{

    String imgLocation = "pixmaps/OthelloBoard.jpg";
    ImageIcon boardImg = new ImageIcon() ;
    
    public TableBoardPanel() {
        initialize();
    }
    
    private void initialize() {
        boardImg = new ImageIcon(TableBoardPanel.class.getResource(imgLocation));       
        this.setIcon(new ImageIcon(boardImg.getImage()
                .getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH)));
        this.setSize(75, 75);
        this.setPreferredSize(new Dimension(50, 50));
    }
    
//    @Override
//    public void paint(Graphics g) {
//        g.drawImage(boardImg.getImage(), 0, 0, width, width, this);
//    }
}
