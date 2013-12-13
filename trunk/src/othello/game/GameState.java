package othello.game;

import org.json.JSONObject;
import java.util.Date;
import othello.client.ComputerPlayer;
import othello.client.HumanPlayer;
import othello.common.Board;
import othello.common.Piece;
import othello.common.AbstractPlayer;
import othello.configuration.Configuration;
import othello.configuration.TypeCfg;
/**
 *
 * @author Hien Hoang
 * @since Oct 22, 2013
 * 
 */
public class GameState {
    protected Board board;
    protected AbstractPlayer currentPlayer;
    protected AbstractPlayer players[];
    protected long gameBegin;
    protected long turnBegin;
    protected boolean terminated;
    
    protected Configuration config = Configuration.getInstance();
    
    public GameState() {
        board = new Board(config.board.width, config.board.height);
        players = new AbstractPlayer[2];
        terminated = false;
        turnBegin = 0;
    }
    
    @Override
    public GameState clone() {
        
        GameState _state = new GameState();
        _state.board = this.board.clone();
        _state.players[0] = this.players[0];
        _state.players[1] = this.players[1];
        _state.currentPlayer = _state.players[this.players[0]==this.currentPlayer?0:1];
        _state.terminated = this.terminated;
        _state.gameBegin = this.gameBegin;
        _state.turnBegin = this.turnBegin;
        
        return _state;
    }
    
    public int getTurnNumber() {
        if (this.currentPlayer.getName().equals(this.players[0].getName()))
            return 0;
        else 
            return 1;
    }
    
    public void beginGameTimer() {
        gameBegin = new Date().getTime();
    }
    
    public AbstractPlayer[] getPlayers() {
        return this.players;
    }
    
    public AbstractPlayer getCurrentPlayer() {
        return this.currentPlayer;
    }
    
    public void setCurrentPlayer(AbstractPlayer current) {
        this.currentPlayer = current;
    }
    
    public long getGameBeginTimer() {
        return this.gameBegin;
    }
    
    public void setGameBeginTimer(long value) {
        this.gameBegin = value;
    } 
    
    public long getTurnBeginTimer() {
        return this.turnBegin;
    }
    
    public void setTurnBeginTimer(long value) {
        this.turnBegin = value;
    }
    
    public void setTerminated(boolean value) {
        this.terminated = value;
    }
    
    public boolean isTerminated() {
        return this.terminated;
    }
    
    public Board getBoard() {
        return this.board;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }
    
    public JSONObject serializeJson() {
        
        JSONObject obj = new JSONObject();
        JSONObject jsBoard = board.serializeJson();
        JSONObject jsCurrentPlayer = currentPlayer.serializeJson();
        JSONObject jsGameBegin = new JSONObject("{'gameBegin':" + this.gameBegin + "}");
        JSONObject jsTurnBegin = new JSONObject("{'turnBegin':" + this.turnBegin + "}");
        JSONObject jsPlayer1 = this.players[0].serializeJson();
        JSONObject jsPlayer2 = this.players[1].serializeJson();
        obj.put(jsBoard.names().getString(0), jsBoard.get(jsBoard.names().getString(0)));
        obj.put("currentPlayer", jsCurrentPlayer);
        obj.put(jsGameBegin.names().getString(0), jsGameBegin.get(jsGameBegin.names().getString(0)));
        obj.put(jsTurnBegin.names().getString(0), jsTurnBegin.get(jsTurnBegin.names().getString(0)));
        obj.put("player1", jsPlayer1);
        obj.put("player2", jsPlayer2);
        
        return obj;
    }
    
    public void deserializeJson(JSONObject jObj){
        this.board = new Board();
        this.board.deserializeJson(jObj);
        
        this.gameBegin = jObj.getLong("gameBegin");
        this.turnBegin = jObj.getLong("turnBegin");
        
        if (config.getPlayingType().name.equalsIgnoreCase(TypeCfg.TYPE_OFFLINE)) {
            this.currentPlayer = new ComputerPlayer(Piece.EMPTY);
            this.currentPlayer.deserializeJson(jObj.getJSONObject("currentPlayer"));
            this.players = new AbstractPlayer[2];

            if (jObj.getJSONObject("player1").getString("type").equalsIgnoreCase(HumanPlayer.TYPE)) {
                this.players[0] = new HumanPlayer(Piece.EMPTY);
            }
            else {
                this.players[0] = new ComputerPlayer(Piece.EMPTY);
            }
            this.players[0].deserializeJson(jObj.getJSONObject("player1"));

            if (jObj.getJSONObject("player2").getString("type").equalsIgnoreCase(HumanPlayer.TYPE)) {
                this.players[1] = new HumanPlayer(Piece.EMPTY);
            }
            else {
                this.players[1] = new ComputerPlayer(Piece.EMPTY);
            }
            this.players[1].deserializeJson(jObj.getJSONObject("player2"));


            if (this.currentPlayer.getName().equals(this.players[0].getName()))
                this.currentPlayer = this.players[0];
            else
                this.currentPlayer = this.players[1];
        }
                
    }
    
    public void calculateScore() {
        
        AbstractPlayer p1 = this.getPlayers()[0];
        p1.setScore(this.getBoard().getPiecesCount(p1.getPiece()));
        AbstractPlayer p2 = this.getPlayers()[1];
        p2.setScore(this.getBoard().getPiecesCount(p2.getPiece()));
    }
}
