/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.common;

import org.json.JSONObject;
import othello.command.response.IGetMoveResExec;
import othello.game.GameState;
/**
 *
 * @author Hien Hoang
 * @since Oct 22, 2013
 * @version Dec 8, 2013
 */
public abstract class AbstractPlayer implements IGetMoveResExec {
    
    
    Piece piece;
    int score = 0;
    String name = "undefined";
    
    public AbstractPlayer(Piece piece) {
        
        this.piece = piece;
    }
    
    
    public JSONObject serializeJson() {
        
        JSONObject jObj = new JSONObject();
        jObj.put("piece", piece.toString());
        jObj.put("score", score);
        jObj.put("name", name);
        
        JSONObject json = new JSONObject();
        json.put("player", jObj);
        return json;
    }
    
    public void deserializeJson(JSONObject jObj) {
        
        JSONObject player = (JSONObject)jObj.get("player");
        this.piece = Piece.toPiece(player.getString("piece"));
        this.score = player.getInt("score");
        this.name = player.getString("name");      
    }
    
    public Piece getPiece() {
        
        return this.piece;
    }
    
    public void setPiece(Piece piece) {
        
        this.piece = piece;
    }
    
    public int getScore() {
        
        return this.score;
    }
    
    public void setScore(int value) {
        
        this.score = value;
    }
    
    public String getName() {
        
        return this.name;
    }
    
    public void setName(String value) {
        
        this.name = value;
    }
    
    @Override
    public abstract AbstractPlayer clone();
    
    public abstract void fireMoveTurn(GameState currentStateClone);
    
    @Override
    public String toString() {
        
        return "p[" + piece.toString() + "] score[" + score + "]";
    }
}
