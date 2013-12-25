package othello.ui.control.graphic.station.setting;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import othello.configuration.Configuration;
import othello.ui.control.graphic.station.BackNextGroup;
import othello.ui.control.graphic.station.OnOffButton;

public class GraphicStation extends JPanel implements MouseListener{
	private static final String SHOW_VALID_MOVE = "SHOW_VALID_MOVE";
	
	JLabel titleLabel = new JLabel();
	JLabel lbShowValidMove = new JLabel();
	
	OnOffButton btnShowValidMove = new OnOffButton();

	BackNextGroup btnBackNext = new BackNextGroup();
	
	Configuration cfg = Configuration.getInstance();
	
	public GraphicStation() {
		
		initialize();
	}
	
	private void initialize() {
		
		titleLabel.setText("GRAPHIC");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 38);
        titleLabel.setFont(othelloFont);
        titleLabel.setForeground(Color.GRAY);
        
        Font lbFont = new Font(this.getFont().getFontName(), Font.BOLD, 16);
        
        lbShowValidMove.setText("SHOW VALID MOVE");
        lbShowValidMove.setFont(lbFont);
        
        
        btnShowValidMove.setOn(cfg.userInterfaces.controlUI.showValidMove);
        btnShowValidMove.setName(SHOW_VALID_MOVE);
        btnShowValidMove.addMouseListener(this);
        
        
        JPanel contentPanel = new JPanel();
        GroupLayout contentLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(contentLayout);
        
        contentLayout.setHorizontalGroup(
            contentLayout.createSequentialGroup()
            	.addGroup(contentLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
        			.addComponent(lbShowValidMove)
                ).addGap(25)
                .addGroup(contentLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
        			.addComponent(btnShowValidMove)
                )
         );
 
        contentLayout.setVerticalGroup(
            contentLayout.createSequentialGroup()
                .addGroup(contentLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(lbShowValidMove)
                    .addComponent(btnShowValidMove).addGap(50)
                )
         );
        
        JPanel gapPanel = new JPanel();
        gapPanel.setLayout(new BoxLayout(gapPanel, BoxLayout.PAGE_AXIS));
        gapPanel.add(Box.createVerticalGlue());
        
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(titleLabel)
                    .addComponent(contentPanel)
                    .addComponent(gapPanel)
                    .addComponent(btnBackNext)
                )
         );
 
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(titleLabel).addGap(50)
                .addComponent(contentPanel)
                .addComponent(gapPanel)
                .addComponent(btnBackNext)

         );
        
        setNextLetter(null);
	}
	
	public void setBackLetter(String letter) {
    	btnBackNext.setBackLetter(letter);
    }
	
	public void setNextLetter(String letter) {
		
    	btnBackNext.setNextLetter(letter);
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	public void mousePressed(MouseEvent e) {
		OnOffButton onOff = (OnOffButton)e.getSource();
		switch (onOff.getName()) {
		case SHOW_VALID_MOVE:
			cfg.userInterfaces.controlUI.showValidMove = onOff.isOn();
			break;

		}
		cfg.serialize(Configuration.CONFIG_FILEPATH);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
