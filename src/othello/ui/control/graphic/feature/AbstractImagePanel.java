package othello.ui.control.graphic.feature;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Hien Hoang
 */
public abstract class AbstractImagePanel extends JPanel {

    protected static final String PIC_PATH = "images";
    
    protected String imgLocation;
    protected ImageIcon image;
    protected int imgWidth = 60;
    protected String imgType;
    protected String imgTypeActive;
    protected String imgTypeDisable;
    
    public AbstractImagePanel() {

    }
    
    public void setActive(boolean flag) {
        
        if (flag) {
            imgLocation = imgTypeActive;
        }
        else {
            imgLocation = imgType;
        }
        updateUI();
    }
    
    public void activeService(boolean flag) {
        if (flag) {
            imgLocation = imgType;
        }
        else {
            imgLocation = imgTypeDisable;
        }
        updateUI();
    }
    
    protected void initialize() {
        
        imgLocation = imgTypeDisable;
        this.setMinimumSize(new Dimension(imgWidth, imgWidth));
        this.setMaximumSize(new Dimension(imgWidth, imgWidth));
    }
    
    @Override
    public void paint(Graphics g) {
        
        image = new ImageIcon(UndoImagePanel.class.getResource(imgLocation));
        int x = (this.getWidth() - imgWidth) / 2;
        int y = (this.getHeight() - imgWidth) / 2;
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(image.getImage(),x, y, imgWidth, imgWidth, this);
    }
}
