package othello.ui.control.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import othello.common.AbstractPlayer;
import othello.common.Piece;
import othello.game.Notifiable;
import othello.game.NotificationBoard;

/**
 *
 * @author Hien Hoang
 */
public class ThinkingTimerPanel extends JPanel implements Notifiable{
    
    Font clockFont = new Font(this.getFont().getFontName(), Font.BOLD, 72);
    int seconds;
    AbstractPlayer player;
    PieceImagePanel pieceImagePanel;
    JLabel lbDigitalClock = new JLabel("--:--");
    NotificationBoard nb = NotificationBoard.getInstance();
    
    public ThinkingTimerPanel() {
        initialize();
        nb.subscribe(this, NotificationBoard.NF_MOVE_TURN);
        nb.subscribe(this, NotificationBoard.NF_TIMEMOVE_CHANGED);
    }
    
    private void initialize() {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        pieceImagePanel = new PieceImagePanel();
        pieceImagePanel.setPiece(Piece.WHITE);
        lbDigitalClock.setFont(clockFont);
        
        this.setMinimumSize(new Dimension(300, 150));
        this.setMaximumSize(new Dimension(300, 150));
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addComponent(pieceImagePanel)
                .addComponent(lbDigitalClock)
         );
        
         layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(pieceImagePanel)
                    .addComponent(lbDigitalClock)
                )
         );
    }

    @Override
    public void receiveChangeNotification(int category, Object detail) {
        if (category == NotificationBoard.NF_MOVE_TURN) {
            AbstractPlayer player = (AbstractPlayer)detail;
            this.player = player;
            this.pieceImagePanel.setPiece(player.getPiece());
        }
        
        if (category == NotificationBoard.NF_TIMEMOVE_CHANGED) {
            Integer seconds = (Integer)detail;
            int mins = seconds / 60;
            int secs = seconds % 60;
            lbDigitalClock.setText(String.format("%d:%02d", mins, secs));
        }
    }
}
