package othello.client;

import othello.common.AbstractPlayer;
import othello.common.Piece;
import othello.configuration.Configuration;
import othello.game.GameMonitor;

/**
 *
 * @author Hien Hoang
 */
public class ClientGameMonitor extends GameMonitor {

    public ClientGameMonitor() {
        super();
        this.initialize();
    }
    
    private void initialize() {
        
        Configuration cfg = Configuration.getInstance();
        int firstIndex = cfg.players.getFirstPlayerIndex();
        int secondIndex = 1 - firstIndex;
        
        AbstractPlayer firstPlayer = PlayerFactory.getPlayer(cfg.players.players.get(firstIndex).getType());
        firstPlayer.setPiece(cfg.players.players.get(firstIndex).getPiece());
        firstPlayer.setName(cfg.players.players.get(firstIndex).getName());
        this.addPlayer(firstPlayer);
        
        AbstractPlayer secondPlayer = PlayerFactory.getPlayer(cfg.players.players.get(secondIndex).getType());
        secondPlayer.setPiece(cfg.players.players.get(secondIndex).getPiece());
        secondPlayer.setName(cfg.players.players.get(secondIndex).getName());
        this.addPlayer(secondPlayer);
        
        turn = 0;
        this.state.setCurrentPlayer(firstPlayer);
        
        if (cfg.players.autoViewer && !cfg.players.hasHumanPlayer()) {
            AbstractPlayer viewer = new HumanPlayer(Piece.UNDEFINED);
            this.addPlayer(viewer);
        }
        
        this.setReady(firstPlayer);
        this.setReady(secondPlayer);

    }
    
    private static ClientGameMonitor singletonObject;
    
    public static ClientGameMonitor getInstance() {
        if (singletonObject == null) {
            singletonObject = new ClientGameMonitor();
        }
        return singletonObject;
    }
    
}
