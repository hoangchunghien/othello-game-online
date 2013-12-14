package othello.ui.control.graphic;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import othello.client.HumanPlayer;
import othello.common.AbstractPlayer;
import othello.common.Piece;
import othello.game.GameState;
import othello.game.Notifiable;
import othello.game.NotificationBoard;

/**
 *
 * @author Hien Hoang
 */
public class PlayersPanel extends JPanel implements Notifiable {
    
    NotificationBoard nb = NotificationBoard.getInstance();
    List<AbstractPlayer> players = new ArrayList<>();
    PlayerInfoPanel playerInfo1 = new PlayerInfoPanel(new HumanPlayer(Piece.BLACK));
    PlayerInfoPanel playerInfo2 = new PlayerInfoPanel(new HumanPlayer(Piece.WHITE));
    
    public PlayersPanel() {
        initialize();
        nb.subscribe(this, NotificationBoard.NF_GAMESTATE_CHANGED);
    }
    
    private void initialize() {
        players.add(null);
        players.add(null);
        
        this.setMinimumSize(new Dimension(300, 300));
        this.setMaximumSize(new Dimension(300, 300));
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addComponent(playerInfo1)
                .addComponent(playerInfo2)
         );

         layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(playerInfo1)
                    .addComponent(playerInfo2)
                )
         );
    }

    @Override
    public void receiveChangeNotification(int category, Object detail) {
        if (category == NotificationBoard.NF_GAMESTATE_CHANGED) {
            GameState state = (GameState)detail;
            
            boolean changed = false;
            // Player 0
            if (players.get(0) == null) {
                players.add(0, state.getPlayers()[0]);
                changed = true;
            }
            else if (players.get(0) != state.getPlayers()[0]) {
                players.set(0, state.getPlayers()[0]);
                changed = true;
            }
            if (changed) {
                playerInfo1.setPlayer(players.get(0));
                changed = false;
            }
            
            // Player 1
            if (players.get(1) == null) {
                players.add(1, state.getPlayers()[1]);
                changed = true;
            }
            else if (players.get(1) != state.getPlayers()[1]) {
                players.set(1, state.getPlayers()[1]);
                changed = true;
            }
            if (changed) {
                playerInfo2.setPlayer(players.get(1));
            }
        }
    }
}
