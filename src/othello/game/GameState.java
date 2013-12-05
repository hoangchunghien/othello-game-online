package othello.game;

import org.json.JSONObject;
import java.util.Date;
import othello.common.Board;
import othello.common.Piece;
import othello.common.Player;
import othello.configuration.Configuration;
/**
 *
 * @author Hien Hoang
 * @since Oct 22, 2013
 * 
 */
public class GameState {
    private Board board;
    private Player currentPlayer;
    private Player players[];
    private long gameBegin;
    private long turnBegin;
    private boolean terminated;
    
    private Configuration config = Configuration.getInstance();
    
    public GameState() {
        board = new Board(config.board.width, config.board.height);
        players = new Player[2];
        players[0] = new Player(config.players.players.get(0).getPiece());
        players[0].setComputerPlayer(config.players.players.get(0).isComputer());
        players[0].setName(config.players.players.get(0).getName());
        players[1] = new Player(config.players.players.get(1).getPiece());
        players[1].setComputerPlayer(config.players.players.get(1).isComputer());
        players[1].setName(config.players.players.get(1).getName());
        currentPlayer = players[config.players.getFirstPlayerIndex()];
        terminated = false;
        turnBegin = 0;
    }
    
    @Override
    public GameState clone() {
        
        GameState _state = new GameState();
        _state.board = this.board.clone();
        _state.players[0] = this.players[0].clone();
        _state.players[1] = this.players[1].clone();
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
    
    public Player[] getPlayers() {
        return this.players;
    }
    
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    
    public void setCurrentPlayer(Player current) {
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
        
        this.board.deserializeJson(jObj);
        this.gameBegin = jObj.getLong("gameBegin");
        this.turnBegin = jObj.getLong("turnBegin");
        this.currentPlayer.deserializeJson(jObj.getJSONObject("currentPlayer"));
        this.players = new Player[2];
        this.players[0] = new Player(Piece.EMPTY);
        this.players[0].deserializeJson(jObj.getJSONObject("player1"));
        this.players[1] = new Player(Piece.EMPTY);
        this.players[1].deserializeJson(jObj.getJSONObject("player2"));
        if (this.currentPlayer.getName().equals(this.players[0].getName()))
            this.currentPlayer = this.players[0];
        else
            this.currentPlayer = this.players[1];
                
    }
    
    public void calculateScore() {
        
        Player p1 = this.getPlayers()[0];
        p1.setScore(this.getBoard().getPiecesCount(p1.getPiece()));
        Player p2 = this.getPlayers()[1];
        p2.setScore(this.getBoard().getPiecesCount(p2.getPiece()));
    }
}
