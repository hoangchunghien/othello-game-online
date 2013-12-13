package othello.client;

import othello.command.UndoCmd;
import othello.common.AbstractPlayer;
import othello.common.Piece;
import othello.configuration.Configuration;
import othello.game.GameMonitor;
import othello.game.GameState;
import othello.game.NotificationBoard;

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
    
    @Override
    public void makeUndo(AbstractPlayer caller) {
        // Alway accept
        if (undoState.size() > 0 ) {
            GameState undo;
            do {
                undo = (GameState)undoState.pop();
                redoState.push(undo);
            } while (undo.getCurrentPlayer() != caller && undoState.size() > 0);
            
            this.state = undo;
            nb.fireChangeNotification(NotificationBoard.NF_GAMESTATE_CHANGED, state);
            
            if (undoState.size() <= 0) {
                nb.fireChangeNotification(NotificationBoard.NF_UNDOABLE, Boolean.FALSE);
            }
            
            if (redoState.size() > 0) {
                nb.fireChangeNotification(NotificationBoard.NF_REDOABLE, Boolean.TRUE);
            }
            
            nb.fireChangeNotification(NotificationBoard.NF_MOVE_TURN, state.getCurrentPlayer());
        }
    }
    
}
