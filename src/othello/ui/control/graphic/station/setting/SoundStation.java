package othello.ui.control.graphic.station.setting;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class SoundStation extends JPanel implements MouseListener {
	private static final String PLAY_MOVE_SOUND = "PLAY_MOVE_SOUND";
	private static final String PLAY_PASSTURN_SOUND = "PLAY_PASSTURN_SOUND";
	private static final String PLAY_GAMEOVER_SOUND = "PLAY_GAMEOVER_SOUND";
	
	JLabel titleLabel = new JLabel();
	JLabel lbMove = new JLabel();
	JLabel lbPassTurn = new JLabel();
	JLabel lbGameOver = new JLabel();
	OnOffButton btnMove = new OnOffButton();
	OnOffButton btnPassTurn = new OnOffButton();
	OnOffButton btnGameOver = new OnOffButton();
	BackNextGroup btnBackNext = new BackNextGroup();
	
	Configuration cfg = Configuration.getInstance();
	
	public SoundStation() {
		
		initialize();
	}
	
	private void initialize() {
		
		titleLabel.setText("PLAY SOUND");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 38);
        titleLabel.setFont(othelloFont);
        titleLabel.setForeground(Color.GRAY);
        
        Font lbFont = new Font(this.getFont().getFontName(), Font.BOLD, 20);
        
        lbMove.setText("MOVE");
        lbMove.setFont(lbFont);
        
        lbPassTurn.setText("PASS TURN");
        lbPassTurn.setFont(lbFont);
        
        lbGameOver.setText("GAME OVER");
        lbGameOver.setFont(lbFont);
        
        btnMove.setOn(cfg.sounds.getMoveSound().isPlay);
        btnMove.setName(PLAY_MOVE_SOUND);
        btnMove.addMouseListener(this);
        
        btnPassTurn.setOn(cfg.sounds.getPassSound().isPlay);
        btnPassTurn.setName(PLAY_PASSTURN_SOUND);
        btnPassTurn.addMouseListener(this);
        
        btnGameOver.setOn(cfg.sounds.getGameOverSound().isPlay);
        btnGameOver.setName(PLAY_GAMEOVER_SOUND);
        btnGameOver.addMouseListener(this);
        
        JPanel contentPanel = new JPanel();
        GroupLayout contentLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(contentLayout);
        
        contentLayout.setHorizontalGroup(
            contentLayout.createSequentialGroup()
            	.addGroup(contentLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
        			.addComponent(lbMove)
                	.addComponent(lbPassTurn)
                	.addComponent(lbGameOver)
                ).addGap(50)
                .addGroup(contentLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
        			.addComponent(btnMove)
                	.addComponent(btnPassTurn)
                	.addComponent(btnGameOver)
                )
         );
 
        contentLayout.setVerticalGroup(
            contentLayout.createSequentialGroup()
                .addGroup(contentLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(lbMove)
                    .addComponent(btnMove).addGap(50)
                )
                .addGroup(contentLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(lbPassTurn)
                    .addComponent(btnPassTurn).addGap(50)
                )
                .addGroup(contentLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(lbGameOver)
                    .addComponent(btnGameOver).addGap(50)
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
		case PLAY_MOVE_SOUND:
			cfg.sounds.getMoveSound().isPlay = onOff.isOn();
			break;
		case PLAY_PASSTURN_SOUND:
			cfg.sounds.getPassSound().isPlay = onOff.isOn();
			break;
		case PLAY_GAMEOVER_SOUND:
			cfg.sounds.getGameOverSound().isPlay = onOff.isOn();
			break;
		}
		cfg.serialize(Configuration.CONFIG_FILEPATH);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
