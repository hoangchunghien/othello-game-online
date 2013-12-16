package othello.ui.control.graphic.station;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Hien Hoang
 */
public class OButton extends JLabel implements MouseListener {
    
    private int fontSize = 28;
    Font font = new Font(this.getFont().getFontName(), Font.BOLD, fontSize);
    Border defaultBorder = new LineBorder(Color.LIGHT_GRAY, 1, false);
    
    public void setFontSize(int size) {
        this.fontSize = size;
        font = new Font(this.getFont().getFontName(), Font.BOLD, fontSize);
        this.setFont(font);
    }
    
    public OButton() {
        super();
        this.addMouseListener(this);     
        this.setFont(font);      
        this.setBorder(defaultBorder);
        this.setHorizontalAlignment(SwingConstants.CENTER); 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(Color.GRAY);
        this.setOpaque(true);
        this.setForeground(Color.WHITE);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setBackground(Color.WHITE);
        this.setOpaque(false);
        this.setForeground(Color.BLACK);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Border border = new LineBorder(Color.BLACK, 1, false);
        this.setBorder(border);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBorder(defaultBorder);
    }
    
    
}
