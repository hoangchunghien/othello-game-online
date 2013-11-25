/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.common;

/**
 *
 * @author Hien
 */
public enum Neighbor {
    UPPERLEFT(-1, -1),
    UPPER(0, -1),
    UPPERRIGHT(1, -1),
    
    LEFT(-1, 0),
    RIGHT(1, 0),
    
    BOTTOMLEFT(-1, 1),
    BOTTOM(0, 1),
    BOTTOMRIGHT(1, 1);
    
    int x = 0;
    int y = 0;
    
    Neighbor(int x, int y){
        this.x = x;
        this.y = y;
    }
}
