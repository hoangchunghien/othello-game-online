package othello.ui.control.graphic;

import java.awt.Font;
import java.util.HashMap;
import javax.swing.JPanel;
import othello.common.AbstractPlayer;

/**
 *
 * @author Hien Hoang
 */
public class ThinkingTimerPanel extends JPanel {
    
    Font clockFont = new Font(this.getFont().getFontName(), Font.BOLD, 72);
    int seconds;
    HashMap<AbstractPlayer, Boolean> onTiming;
}
