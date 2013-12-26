/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.common;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author Hien HC
 */
public class Board {
    private Piece pieces[][];
    private int width;
    private int height;
    
    public Board() {
        this(8, 8);
    }
    
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        pieces = new Piece[height][width];
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
        
        // Initialize 4 piece in new board
        int m = pieces.length / 2;
        pieces[m - 1][m - 1] = Piece.WHITE;
        pieces[m - 1][m] = Piece.BLACK;
        pieces[m][m - 1] = Piece.BLACK;
        pieces[m][m] = Piece.WHITE;
    }
    
    @Override
    public Board clone() {
        
        Board result = new Board(this.width, this.height);
        
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                result.setPiece(this.getPiece(j, i), new Position(j, i));
            }
        }
        
        return result;
    }
    
    public JSONObject serializeJson() {
    	
        JSONArray jarr = new JSONArray();
        
        for (int i = 0; i < pieces.length; i++) {
        	JSONArray iArr = new JSONArray();
            for (int j = 0; j < pieces[i].length; j++) {
            	iArr.put(pieces[i][j]);
            }
            jarr.put(iArr);
        }

        
        JSONObject jObj = new JSONObject();
        jObj.put("board", jarr);
        
        return jObj;
    }
    
    public void deserializeJson(JSONObject jObj) {
    	
        JSONArray jArr = (JSONArray)jObj.get("board");
        //for (int i = 0 ; i < jArr.length(); i ++)
        this.width = jArr.getJSONArray(0).length();
        this.height = jArr.length();
        pieces = new Piece[height][width];
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
        
        for (int i = 0; i < jArr.length(); i++) {
        	JSONArray iArr = jArr.getJSONArray(i);
            for (int j = 0; j < iArr.length(); j++) {
            	Piece p = Piece.toPiece(iArr.getString(j));
            	if (p == Piece.WHITE || p == Piece.BLACK) {
            		pieces[i][j] = p;
            	}
            }
        }
    }
    
    public Piece[][] getPieces() {
        return pieces;
    }
    
    public Piece getPiece(Position p) {
        return getPiece(p.getX(), p.getY());
    }
    
    public Piece getPiece(int x, int y) {
        if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
            return pieces[y][x];
        }
        return Piece.UNDEFINED;
    }
    
    public void setPiece(Piece p, Position pos) {
        pieces[pos.getY()][pos.getX()] = p;
    }
    
    public void placePiece(Piece p, Position pos) {
        for (Neighbor n : Neighbor.values()) {
            //System.out.println("Flipping pieces...");
            setPieceDirection(p, pos, n);
        }
        // Place this piece
        pieces[pos.getY()][pos.getX()] = p;
        for (Position po : getValidMoveList(p.getOpposite())) {
        	System.out.println(po.toString());
        }
    }
    
    public void setValidPosition(Piece p) {
    	for (Position po : getValidMoveList(p)) {
    		setPiece(Piece.VALID, po);
    	}
    }
    
    private void setPieceDirection(Piece p, Position pos, Neighbor n) {
        Position end = findEndPiecePosition(p, pos, n);
        Position nPos = pos;
        if (p == getPiece(end)) {
            
            nPos = nPos.neighbor(n);
            do {    
                // Stop flip pieces while meet the end position
                if (nPos.getX() == end.getX() && nPos.getY() == end.getY()) break;
                    
                setPiece(p, nPos);
                nPos = nPos.neighbor(n);
                //System.out.println("Setting pieces...");
            } while (p.getOpposite() == getPiece(nPos));
        }        
    }
    
    public boolean isValidMove(Piece p, Position pos) {
        Piece opponent = p.getOpposite();
        
        // If position outside the board, it a invalid move
        if (pos.getX() >= this.width)
            return false;
        if (pos.getY() >= this.height)
            return false;
        
        // Only empty slot can be placed
        if (getPiece(pos) == Piece.EMPTY || getPiece(pos) == Piece.VALID) {
            //System.out.println("This slot empty");
            for (Neighbor n : Neighbor.values()) {
                //System.out.println("In the for");
                Position nPos = pos.neighbor(n);
                //System.out.println("(" + nPos.getX() + ", " + nPos.getY() + ")");
                if (opponent == getPiece(nPos)) {
                    //System.out.println("Opp(" + nPos.getX() + ", " + nPos.getY() + ")");
                    Position end = findEndPiecePosition(p, nPos, n);
                    
                    if (p == getPiece(end))
                        return true;
                }
            }
        }
        return false;
    }
    
    public boolean hasAnyValidMove(Piece p) {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                //System.out.println("Checking...");
                if ((pieces[i][j] == Piece.EMPTY || pieces[i][j] == Piece.VALID) && isValidMove(p, new Position(j, i))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isGameOver() {
        if (!hasAnyValidMove(Piece.BLACK) && !hasAnyValidMove(Piece.WHITE)) {
            return true;
        }
        return false;
    }
    
    public List<Position> getValidMoveList(Piece p) {
        List<Position> moveList = new ArrayList<Position>();
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                //System.out.println("Add valid move to move list");
            	Position position = new Position(j, i);
                if (isValidMove(p, position)) {
                    //System.out.println("Add valid move to move list");
                    moveList.add(position);
                }
            }
        }
        return moveList;
    }
    
    private Position findEndPiecePosition(Piece p, Position pos, Neighbor n) {
        Piece end;
        do {
            pos = pos.neighbor(n);
        } while (p.getOpposite() == (end = getPiece(pos)));
        if (p == end) {
            return pos;
        }
        return Position.UNDEFINED;     
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getPiecesCount(Piece p) {
        int result = 0;
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == p) {
                    result++;
                }
            }
        }
        return result;
    }
    
}
