package othello.ui.control.graphic;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import othello.common.AbstractPlayer;
import othello.game.GameState;
import othello.game.Notifiable;
import othello.game.NotificationBoard;

/**
 *
 * @author Hien Hoang
 */
public class PlayerInfoPanel extends JPanel implements Notifiable {
    
    NotificationBoard nb = NotificationBoard.getInstance();
    
    protected AbstractPlayer player;
    protected boolean onTurn = false;
    protected JLabel lbDigitalClock = new JLabel();
    protected PieceImagePanel pieceImagePanel = new PieceImagePanel();
    
    protected JLabel lbPlayerName = new JLabel();
    protected String PiecesCountStr = "Pieces: ";
    protected JLabel lbPiecesCount = new JLabel();
    protected String PlayerScoreStr = "Score: ";
    protected JLabel lbPlayerScore = new JLabel();
    
    public void setPlayer(AbstractPlayer player) {
        this.player = player;
        pieceImagePanel.setPiece(player.getPiece());
        lbPlayerName.setText(player.getName());
        lbPlayerScore.setText(PlayerScoreStr + player.getScore());
        updateUI();
    }
    
    public PlayerInfoPanel(AbstractPlayer player) {
        this.player = player;
        initialize();
        nb.subscribe(this, NotificationBoard.NF_GAMESTATE_CHANGED);
        nb.subscribe(this, NotificationBoard.NF_TIMEGAME_CHANGED);
        nb.subscribe(this, NotificationBoard.NF_MOVE_TURN);
        nb.subscribe(this, NotificationBoard.NF_TIMER_START);
    }
    
    private void initialize() {
        Font clockFont = new Font(this.getFont().getFontName(), Font.BOLD, 28);
        lbDigitalClock.setFont(clockFont);
        lbDigitalClock.setText(String.format("%d:%02d", 30, 0));
        pieceImagePanel.setPiece(player.getPiece());
        lbPlayerName.setText(player.getName());
        lbPlayerScore.setText(PlayerScoreStr + player.getScore());
        lbPiecesCount.setText(PiecesCountStr);
        
        this.setMinimumSize(new Dimension(150, 300));
        this.setMaximumSize(new Dimension(150, 300));
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);      
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
               .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(lbDigitalClock)
                    .addComponent(pieceImagePanel)
                    .addComponent(lbPlayerName)
                    .addComponent(lbPlayerScore)
                    .addComponent(lbPiecesCount)
                )
         );
        
         layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lbDigitalClock)
                .addComponent(pieceImagePanel)
                .addComponent(lbPlayerName)
                .addComponent(lbPlayerScore)
                .addComponent(lbPiecesCount)
         );
         
    }

    @Override
    public void receiveChangeNotification(int category, Object detail) {
        
        if (category == NotificationBoard.NF_GAMESTATE_CHANGED) {
            
            GameState state = (GameState)detail;
            pieceImagePanel.setPiece(player.getPiece());
            int pieceCount = state.getBoard().getPiecesCount(player.getPiece());
            lbPiecesCount.setText(PiecesCountStr + pieceCount);
        }
        
        if (category == NotificationBoard.NF_MOVE_TURN) {
            AbstractPlayer turnedPlayer = (AbstractPlayer)detail;
            if (turnedPlayer == this.player) {
                onTurn = true;
            } 
            else {
                onTurn = false;
            }
        }
        
        if (category == NotificationBoard.NF_TIMEGAME_CHANGED) {
            if (onTurn) {
                int seconds = (Integer)detail;
                displayTime(seconds);
            }
        }
        
        if (category == NotificationBoard.NF_TIMER_START) {
            int seconds = (Integer)detail;
            displayTime(seconds);
        }
    }
    
    protected void displayTime(int seconds) {
        
        int mins = seconds / 60;
        int secs = seconds % 60;
        lbDigitalClock.setText(String.format("%d:%02d", mins, secs));
    }
}
