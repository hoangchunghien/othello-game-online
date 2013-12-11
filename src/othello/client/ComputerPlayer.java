package othello.client;

import othello.command.ClientCommandExecutorManager;
import othello.command.IGetMoveCmdExec;
import othello.command.MoveCmd;
import othello.command.notify.IGameOverNtfExec;
import othello.command.response.GetMoveRes;
import othello.command.response.ResponseExecutorManager;
import othello.common.AbstractPlayer;
import othello.common.Board;
import othello.common.Piece;
import othello.common.Position;
import othello.engine.AbstractEngine;
import othello.engine.EngineFactory;
import othello.game.GameState;

/**
 *
 * @author Hien Hoang
 * @since Dec 8, 2013
 * @version Dec 8, 2013
 * Description 
 * . TODO
 */
public class ComputerPlayer extends AbstractPlayer implements IGetMoveCmdExec {

    public static final String TYPE = "computer";
    
    private Board currentBoardClone;
    private AbstractEngine engine;
    private GameState currentState;
    
    public ComputerPlayer(Piece piece) {
        super(piece);
        engine = EngineFactory.getEngine();
    }
    
    public ComputerPlayer(Piece piece, String name) {
        this(piece);
        this.setName(name);
    }
    
    @Override
    public void fireMoveTurn() {
        
//        GetMoveCmd getMoveCommand = 
//                new GetMoveCmd(ClientCommandExecutorManager.getGetMoveCommandExecutor(this), this);
//        getMoveCommand.execute();
        getMoveFor(this);
    }

    @Override
    public void makeMoving(Position p) {
        MoveCmd moveCommand = 
                new MoveCmd(ClientCommandExecutorManager.getMoveCommandExecutor(),this, p);
        moveCommand.execute();
    }

    @Override
    public void getMoveFor(AbstractPlayer player) {
        
//        engine.setBoard(currentBoardClone);
        Position p = engine.getMove(getPiece());
        GetMoveRes getMoveResponse = new GetMoveRes(
                ResponseExecutorManager.getGetMoveResponseExecutor(player), p);
        getMoveResponse.execute();
    }

    @Override
    public AbstractPlayer clone() {
        AbstractPlayer dup = new ComputerPlayer(getPiece(), getName());
        dup.setScore(getScore());
        return dup;
    }

    @Override
    public void makeOverGame() {
        // Game over
    }

    @Override
    public void makePassing() {
        // Pass the game
        System.out.println("Computer have no move, passed!!");
    }

    @Override
    public void fireStateChanged(GameState state) {
        // Process the state changed
        this.currentBoardClone = state.getBoard();
        this.engine.setBoard(currentBoardClone);    
    }

    @Override
    public void processMoveAccepted(Position position) {
        System.out.println("Computer move accepted.");
    }

    @Override
    public void processMoveRejected(String msg) {
        System.out.println("Computer move rejected !!!");
    }

}
