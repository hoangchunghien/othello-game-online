package othello.ui.control.graphic.feature;

/**
 *
 * @author Hien Hoang
 */
public class UndoImagePanel extends AbstractImagePanel{
    
    public static final String TYPE_BLACK = PIC_PATH + "/UNDO_BLACK.png";
    public static final String TYPE_BLACK_ACTIVE = PIC_PATH + "/UNDO_BLACK_ACTIVE.png";
    public static final String TYPE_BLUE = PIC_PATH + "/UNDO_BLUE.png";
    public static final String TYPE_BLUE_ACTIVE = PIC_PATH + "/UNDO_BLUE_ACTIVE.png";
    public static final String TYPE_DISABLE = PIC_PATH + "/UNDO_GREY.png";
    
    public UndoImagePanel() {
        
        this.imgType = TYPE_BLACK;
        this.imgTypeActive = TYPE_BLACK_ACTIVE;
        this.imgTypeDisable = TYPE_DISABLE;
        this.initialize();
    }

}
