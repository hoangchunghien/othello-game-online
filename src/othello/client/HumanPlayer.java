package othello.client;

import othello.command.ClientCommandExecutorManager;
import othello.command.GetMoveCmd;
import othello.command.MoveCmd;
import othello.command.notify.IGameOverNtfExec;
import othello.command.notify.IMoveNtfExec;
import othello.common.AbstractPlayer;
import othello.common.Piece;
import othello.common.Position;
import othello.game.GameState;
import othello.ui.UIFactory;

/**
 *
 * @author Hien Hoang
 * @version Dec 8, 2013
 * Description
 * . The human player will interact with UI to get move, or to render move to
 * . user. Unlike computer player that doesn't interact with UI
 */
public class HumanPlayer extends AbstractPlayer implements IMoveNtfExec{
    
    public static final String TYPE = "human";

    public HumanPlayer(Piece piece) {
        super(piece);
    }
    
    public HumanPlayer(Piece piece, String name) {
        this(piece);
        this.setName(name);
    }
    
    @Override
    public void fireMoveTurn() {
        GetMoveCmd getMoveCmd = 
                new GetMoveCmd(ClientCommandExecutorManager.getGetMoveCommandExecutor(this), this);
        getMoveCmd.execute();
    }

    @Override
    public void makeMoving(Position p) {
        
        MoveCmd moveCmd = new MoveCmd(ClientCommandExecutorManager.getMoveCommandExecutor(),this, p);
        moveCmd.execute();
    }

    @Override
    public AbstractPlayer clone() {
        AbstractPlayer dup = new HumanPlayer(getPiece(), getName());
        dup.setScore(getScore());
        
        return dup;
    }

    @Override
    public void makeOverGame() {
        // Notify game over here
        System.out.println("GAMEOVER");
    }

    @Override
    public void makePassing() {
        // Notify Passing here
        System.out.println("You have no move, passed!!");
    }

    @Override
    public void fireStateChanged(GameState state) {
        
        UIFactory.getControlUI().fireStateChanged(state);
    }

    @Override
    public void processMoveAccepted(Position position) {
        System.out.println("Human move accepted.");
    }

    @Override
    public void processMoveRejected(String message) {
        System.out.println("Human move rejected, error: " + message);
    }

    @Override
    public void notifyMoving(Position p) {
        
    }

}
