package othello.ui.control.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import othello.common.Piece;
import othello.models.Board;

/**
 *
 * @author Hien Hoang
 */
public class TablePanel extends JPanel {
    
    Board board;
    
    JLabel boardName = new JLabel("Board name");
    TableBoardPanel boardPanel = new TableBoardPanel();
    PiecePanel seatPanel1 = new PiecePanel();
    PiecePanel seatPanel2 = new PiecePanel();
    
    public TablePanel(Board board) {
        
        this.board = board;
        boardName.setText(this.board.getName());
        this.setPreferredSize(new Dimension(300, 150));
        this.initialize();
        seatPanel1.setPiece(Piece.EMPTY);
        seatPanel2.setPiece(Piece.EMPTY);
        this.updateBoard(board);
    }
    private void initialize() {
        this.setLayout(new GridBagLayout());
         GridBagConstraints c = new GridBagConstraints();
         
         c.fill = GridBagConstraints.CENTER;
         c.gridx = 0;
         c.gridy = 0;
         c.gridwidth = 3;
         this.add(boardName,c);
         
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 0;
         c.gridy = 1;
         c.ipadx = 25;
         c.ipady = 25;
         c.gridwidth = 1;
         this.add(new JPanel(), c);
         
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 0;
         c.gridy = 2;
         c.ipadx = 40;
         c.ipady = 40;
         c.gridwidth = 1;
         seatPanel1.setSquareWidth(40);
         this.add(seatPanel1, c);
         
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 1;
         c.gridy = 1;
         c.ipady = 75;
         c.ipadx = 75;
         c.gridheight = 3;
         this.add(boardPanel, c);
         
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 2;
         c.gridy = 2;
         c.ipadx = 40;
         c.ipady = 40;
         c.gridheight = 1;
         seatPanel2.setSquareWidth(40);
         this.add(seatPanel2, c);
         
         this.setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    public void updateBoard(Board board) {
        this.board = board;
        if (board.getSeats().get(Piece.BLACK) != null) {
            seatPanel1.setPiece(Piece.BLACK);
        }
        if (board.getSeats().get(Piece.WHITE) != null) {
            seatPanel2.setPiece(Piece.WHITE);
        }
    }
}
