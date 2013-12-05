/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.common;

import org.json.JSONObject;

/**
 *
 * @author Hien
 */
public class Position {
    public final static Position UNDEFINED = new Position(-1, -1);
    
    private int x;
    private int y;
    
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public Position clone() {
        
        Position p = new Position(this.x, this.y);
        return p;
    }
    
    public int getX()
    {
        return this.x;
    }
    public void setX(int value)
    {
        this.x = value;
    }
    
    public int getY()
    {
        return this.y;
    }
    public void setY(int value){
        this.y = value;
    }
    
    public Position neighbor(Neighbor n){
        return new Position(x + n.x, y + n.y);
    }
    
    public JSONObject serializeJSON() {
        
        String result = "{'x':" + x + ",'y':" + y + "}";
        JSONObject jObj = new JSONObject(result);
        return jObj;
    }
    
    public void deserializeJSON(JSONObject jObj) {
        
        this.x = jObj.getInt("x");
        this.y = jObj.getInt("y");
    }
    
    @Override
    public String toString(){
        return x + "," + y;
    }
}
