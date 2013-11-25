/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.common;

/**
 *
 * @author Hien Hoang
 * @since Oct 22, 2013
 * 
 */
public enum Piece {
    EMPTY,
    BLACK,
    WHITE,
    UNDEFINED;
    
    //private Piece opp;
    @Override
    public String toString() {
        switch (this) {
            case EMPTY: 
                return ".";
            case BLACK:
                return "X";
            case WHITE:
                return "O";
            case UNDEFINED:
                return "U";
            default: 
                return "-";
        }
    }
    
    public static Piece toPiece(String str) {
        switch (str) {
            case ".":
                return Piece.EMPTY;
            case "X":
                return Piece.BLACK;
            case "O":
                return Piece.WHITE;
            default:
                return Piece.UNDEFINED;
        }
    }
    
    public Piece getOpposite() {
        switch (this) {
                case BLACK:
                    return WHITE;
                case WHITE:
                    return BLACK;
                default:
                    return UNDEFINED;
        }
    }
    
    
}
