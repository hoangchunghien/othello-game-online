package othello.ui.control.graphic.station;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import othello.ui.control.graphic.InfoPanel;

public class OnOffButton extends JPanel implements MouseListener {
	public static final String PIC_PATH = "pixmaps/";
	public static final String OFF_NORMAL = PIC_PATH + "OFF_NORMAL.png";
	public static final String OFF_ACTIVE = PIC_PATH + "OFF_ACTIVE.png";
	public static final String OFF_DISABLED = PIC_PATH + "OFF_DISABLED.png";
	public static final String ON_NORMAL = PIC_PATH + "ON_NORMAL.png";
	public static final String ON_ACTIVE = PIC_PATH + "ON_ACTIVE.png";
	public static final String ON_DISABLED = PIC_PATH + "ON_DISABLED.png";
	
	protected String imgLocation;
    protected ImageIcon image;
    protected int imgWidth = 60;
    protected int imgHeight = 24;
    protected String imgType;
    private boolean on = false;
    protected boolean isDisabled = false;
    
    public OnOffButton() {
    	imgLocation = OFF_NORMAL;
    	initialize();
        this.addMouseListener(this);
    }
    
    protected void initialize() {
        
        imgLocation = OFF_NORMAL;
        this.setMinimumSize(new Dimension(imgWidth, imgHeight));
        this.setMaximumSize(new Dimension(imgWidth, imgHeight));
    }

    public void setActive(boolean active) {
    	if (!isDisabled) {
	    	if (active) {
	    		if (isOn()) {
	    			imgLocation = ON_ACTIVE;
	    		}
	    		else {
	    			imgLocation = OFF_ACTIVE;
	    		}
	    	}
	    	else {
	    		if (isOn()) {
	    			imgLocation = ON_NORMAL;
	    		}
	    		else {
	    			imgLocation = OFF_NORMAL;
	    		}
	    	}
	    	updateUI();
    	}
    }
    
    public void setDisabled(boolean disabled) {
    	if (!disabled) {
    		if (isOn())
    			imgLocation = ON_NORMAL;
    		else 
    			imgLocation = OFF_NORMAL;
    	}
    	else {
    		if (isOn()) {
    			imgLocation = ON_DISABLED;
    		}
    		else {
    			imgLocation = OFF_DISABLED;
    		}
    	}
    	isDisabled = disabled;
    	updateUI();
    }
    
    public void turnOn() {
    	if (!isDisabled) {
	    	this.on = true;
	    	imgLocation = ON_NORMAL;
	    	updateUI();
    	}
    }
    
    public void turnOff() {
    	if (!isDisabled) {
	    	this.on = false;
	    	imgLocation = OFF_NORMAL;
	    	updateUI();
    	}
    }
    
    public void setOn(boolean isOn) {
    	if (isOn) {
    		turnOn();
    	}
    	else {
    		turnOff();
    	}
    }
    
    @Override
    public void paint(Graphics g) {
        
        image = new ImageIcon(InfoPanel.class.getResource(imgLocation));
        int x = (this.getWidth() - imgWidth) / 2;
        int y = (this.getHeight() - imgHeight) / 2;
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(image.getImage(),x, y, imgWidth, imgHeight, this);
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (!isDisabled) {
			setActive(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (!isDisabled) {
			setActive(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (isOn()) {
			turnOff();
		}
		else {
			turnOn();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean isOn() {
		return on;
	}
}
