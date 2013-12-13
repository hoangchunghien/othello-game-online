package othello.client;

import othello.command.ClientCommandExecutorManager;
import othello.command.IGetMoveCmdExec;
import othello.command.MoveCmd;
import othello.command.notify.IGameOverNtfExec;
import othello.command.response.AnswerRequestRes;
import othello.command.response.GetMoveRes;
import othello.command.response.ResponseExecutorManager;
import othello.common.AbstractPlayer;
import othello.common.Board;
import othello.common.Piece;
import othello.common.Position;
import othello.engine.AbstractEngine;
import othello.engine.EngineFactory;
import othello.game.GameState;
import othello.game.NotificationBoard;


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
    private ComputerThinking thinking;
    
    public ComputerPlayer(Piece piece) {
        super(piece);
        engine = EngineFactory.getEngine();
    }
    
    public ComputerPlayer(Piece piece, String name) {
        this(piece);
        this.setName(name);
    }
    

    @Override
    public void makeMoving(Position p) {
        MoveCmd moveCommand = 
                new MoveCmd(ClientCommandExecutorManager.getMoveCommandExecutor(),this, p);
        moveCommand.execute();
    }

    @Override
    public void getMoveFor(AbstractPlayer player) {
        
        thinking = new ComputerThinking(player, engine, getPiece());
        thinking.start();
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
    public void processMoveAccepted(Position position) {
        System.out.println("Computer move accepted.");
    }

    @Override
    public void processMoveRejected(String msg) {
        System.out.println("Computer move rejected !!!");
    }

    @Override
    public void answerRequest(int reqType) {
        AnswerRequestRes answerRes = new AnswerRequestRes(ResponseExecutorManager.getAnswerRequestResponseExecutor(),
                reqType, AnswerRequestRes.ACCEPTED, "OK");
        if (thinking != null && thinking.isAlive()) {
            thinking.interrupt();
        }
        answerRes.execute();
    }

    @Override
    public void receiveChangeNotification(int category, Object detail) {
        if (category == NotificationBoard.NF_GAMESTATE_CHANGED) {
            if (thinking != null && thinking.isAlive()) {
                thinking.interrupt();
            }
            GameState newState = (GameState)detail;
            this.currentBoardClone = newState.getBoard();
            this.engine.setBoard(currentBoardClone);    
        }
        
        if (category == NotificationBoard.NF_MOVE_TURN) {
            AbstractPlayer player = (AbstractPlayer)detail;
            if (player == this) {
                getMoveFor(this);
            }
        }
    }

}
class ComputerThinking extends Thread {
    
    AbstractPlayer player;
    AbstractEngine engine;
    Piece piece;
    
    public ComputerThinking(AbstractPlayer player, AbstractEngine engine, Piece piece) {
        this.player = player;
        this.engine = engine;
        this.piece = piece;
    }
    
    @Override
    public void run() {
        Position p = engine.getMove(piece);
        GetMoveRes getMoveResponse = new GetMoveRes(
                ResponseExecutorManager.getGetMoveResponseExecutor(player), p);
        getMoveResponse.execute();
    }
}
