package othello.ui.control.graphic.feature;

import static othello.ui.control.graphic.feature.AbstractImagePanel.PIC_PATH;

/**
 *
 * @author Hien Hoang
 */
public class DrawImagePanel extends AbstractImagePanel {
    
    public static final String TYPE_BLACK = PIC_PATH + "/DRAW_BLACK.png";
    public static final String TYPE_BLACK_ACTIVE = PIC_PATH + "/DRAW_BLACK_ACTIVE.png";
    public static final String TYPE_BLUE = PIC_PATH + "/DRAW_BLUE.png";
    public static final String TYPE_BLUE_ACTIVE = PIC_PATH + "/DRAW_BLUE_ACTIVE.png";
    public static final String TYPE_DISABLE = PIC_PATH + "/DRAW_GREY.png";
    
    public DrawImagePanel() {
        
        this.imgType = TYPE_BLACK;
        this.imgTypeActive = TYPE_BLACK_ACTIVE;
        this.imgTypeDisable = TYPE_DISABLE;
        this.initialize();
    }
}
