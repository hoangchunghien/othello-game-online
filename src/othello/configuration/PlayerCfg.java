package othello.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import othello.common.Piece;

/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Player")
public class PlayerCfg {
    public static final String TYPE_HUMAN = "human";
    public static final String TYPE_COMPUTER = "computer";
    public static final String TYPE_ONLINE = "online";
    
    @XmlAttribute(name = "id")
    protected int id;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "color")
    protected String color;
    @XmlAttribute(name = "type")
    protected String type;
    
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public Piece getPiece() {
        switch(this.color.toLowerCase()) {
            case "black":
                return Piece.BLACK;
            case "white":
                return Piece.WHITE;
            default:
                return Piece.UNDEFINED;
        } 
    }    
    public void setPiece(Piece piece) {
       switch (piece) {
           case BLACK:
               color = "Black";
               break;
           case WHITE:
               color = "White";
               break;
           default:
               color = "Black";
               break;
       }
    }
    
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
}
