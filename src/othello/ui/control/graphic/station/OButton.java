package othello.ui.control.graphic.station;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import othello.game.NotificationBoard;

/**
 *
 * @author Hien Hoang
 */
public class OButton extends JLabel implements MouseListener {
    
    private int fontSize = 28;
    Font font = new Font(this.getFont().getFontName(), Font.BOLD, fontSize);
    Border defaultBorder = new LineBorder(Color.LIGHT_GRAY, 1, false);
    
    protected NotificationBoard nb = NotificationBoard.getInstance();
    protected String letter;
    protected Integer notifyCategory = NotificationBoard.NF_UI_NEXT;
    protected boolean isDisabled = false;
    
    public void setLetter(String letter) {
    	if (letter == null) {
    		isDisabled = true;
    		this.setForeground(Color.GRAY);
    	}
    	else {
    		this.letter = letter;
    		this.setForeground(Color.BLACK);
    	}
    }
    
    public String getLetter() {
    	return this.letter;
    }
    
    public void setNotifyCategory(int category) {
    	this.notifyCategory = category;
    }
    
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
    	nb.fireChangeNotification(notifyCategory, letter);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    	if (!isDisabled) {
	        this.setBackground(Color.GRAY);
	        this.setOpaque(true);
	        this.setForeground(Color.WHITE);
    	}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    	if (!isDisabled) {
	        this.setBackground(Color.WHITE);
	        this.setOpaque(false);
	        this.setForeground(Color.BLACK);
    	}
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    	if (!isDisabled) {
	        Border border = new LineBorder(Color.BLACK, 1, false);
	        this.setBorder(border);
    	}
    }

    @Override
    public void mouseExited(MouseEvent e) {
    	if (!isDisabled) {
    		this.setBorder(defaultBorder);
    	}
    }
    
    
}
