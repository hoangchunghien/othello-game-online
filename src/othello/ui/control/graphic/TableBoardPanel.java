package othello.ui.control.graphic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import othello.command.CommandFactory;
import othello.command.Commandable;

/**
 *
 * @author Hien Hoang
 */
public class TableBoardPanel extends JPanel implements MouseListener {

    public static final String PIC_PATH = "pixmaps/";
    public static final String TABLE_ENABLE = PIC_PATH + "TABLE_ENABLE.png";
    public static final String TABLE_DISABLE = PIC_PATH + "TABLE_DISABLE.png";
    public static final String TABLE_ACTIVE = PIC_PATH + "TABLE_ACTIVE.png";
    public static final String TABLE_ACTIVE_PRESS = PIC_PATH + "TABLE_ACTIVE_PRESS.png";
    
    String boardId;
    String imgLocation;
    protected int imgWidth = 90;
    ImageIcon image = new ImageIcon() ;
    protected boolean isDisabled = false;
    protected boolean isActivated = false;
    
    
    public TableBoardPanel() {
        initialize();
        this.addMouseListener(this);
    }
    
    
    
    public String getBoardId() {
		return boardId;
	}



	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}



	public void setDisabled(boolean flag) {
        if (flag) {
            imgLocation = TABLE_DISABLE;
            isDisabled = true;
        } 
        else {
            imgLocation = TABLE_ENABLE;
            isDisabled = false;
        }
        updateUI();
    }
    
    private void initialize() {
        
        imgLocation = TABLE_ENABLE;
        this.setMinimumSize(new Dimension(imgWidth, imgWidth));
        this.setMaximumSize(new Dimension(imgWidth, imgWidth));
    }
    
@Override
    public void paint(Graphics g) {
        
        image = new ImageIcon(TableBoardPanel.class.getResource(imgLocation));
        int x = (this.getWidth() - imgWidth) / 2;
        int y = (this.getHeight() - imgWidth) / 2;
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(image.getImage(),x, y, imgWidth, imgWidth, this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	Commandable joinPlayerCmd = CommandFactory.getJoinPlayerCmd(boardId);
		joinPlayerCmd.execute();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    	if (!isDisabled) {
    		imgLocation = TABLE_ACTIVE_PRESS;
    		updateUI();
    	}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    	if (!isDisabled) {
    		if (isActivated)
    			imgLocation = TABLE_ACTIVE;
    		else 
    			imgLocation = TABLE_ENABLE;
    	}
    	else {
    		imgLocation = TABLE_DISABLE;
    	}
    	updateUI();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!isDisabled) {
        	isActivated = true;
            imgLocation = TABLE_ACTIVE;
            updateUI();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!isDisabled) {
            imgLocation = TABLE_ENABLE;
            
        }
        else {
            imgLocation = TABLE_DISABLE;
        }
        isActivated = false;
        updateUI();
    }
}
