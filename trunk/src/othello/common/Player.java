/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.common;

import org.json.JSONObject;
import org.json.JSONArray;
/**
 *
 * @author Hien Hoang
 * @since Oct 22, 2013
 * 
 */
public class Player {
    Piece piece;
    int score = 0;
    String name = "undefined";
    boolean isComputer = false;
    
    public Player(Piece piece) {
        
        this.piece = piece;
    }
    
    @Override
    public Player clone() {
        
        Player _player = new Player(piece);
        _player.score = score;
        _player.name = name;
        _player.isComputer = this.isComputer;
        
        return _player;
    }
    
    public JSONObject serializeJson() {
        
        String result = "{'player':{'piece':" + piece.toString() + ","
                                 + "'score':" + score + ","
                                 + "'name':" + name + ","
                                 + "'isComputer':" + isComputer + "}}";
        JSONObject jObj = new JSONObject(result);
        return jObj;
    }
    
    public void deserializeJson(JSONObject jObj) {
        
        JSONObject player = (JSONObject)jObj.get("player");
        this.piece = Piece.toPiece(player.getString("piece"));
        this.score = player.getInt("score");
        this.name = player.getString("name");
        this.isComputer = player.getBoolean("isComputer");       
    }
    
    public Piece getPiece() {
        
        return this.piece;
    }
    
    public void setPiece(Piece piece) {
        
        this.piece = piece;
    }
    
    public boolean isComputerPlayer() {
        
        return isComputer;
    }
    
    public void setComputerPlayer(boolean flag) {
        
        this.isComputer = flag;
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
    public String toString() {
        
        return "p[" + piece.toString() + "] score[" + score + "] computer[" + isComputer + "]";
    }
}
