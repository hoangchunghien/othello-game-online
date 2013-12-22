package othello.ui.control.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import othello.command.CommandFactory;
import othello.command.Commandable;
import othello.command.JoinPlayerCmd;
import othello.common.Piece;
import othello.models.Board;

/**
 *
 * @author Hien Hoang
 */
public class TablePanel extends JPanel implements MouseListener {
    
    Board board;
    
    JLabel boardName = new JLabel("Board name");
    TableBoardPanel boardPanel = new TableBoardPanel();
    
    public TablePanel(Board board) {
        
        this.board = board;
        boardName.setText(this.board.getName());
        
        this.setPreferredSize(new Dimension(100, 130));
        this.setMaximumSize(new Dimension(100,130));
        
        this.initialize();
        this.updateBoard(board);
    }
    private void initialize() {
    	
    	GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(boardName)
                    .addComponent(boardPanel)
                )
         );
 
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(boardName).addGap(10)
                .addComponent(boardPanel)

         );
    }
    
    public void updateBoard(Board board) {
        this.board = board;

    }
	@Override
	public void mouseClicked(MouseEvent e) {
		Commandable joinPlayerCmd = CommandFactory.getJoinPlayerCmd(board.getId());
		joinPlayerCmd.execute();
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
