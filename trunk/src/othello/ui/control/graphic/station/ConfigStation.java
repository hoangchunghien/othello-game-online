package othello.ui.control.graphic.station;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import othello.configuration.Configuration;
import othello.configuration.TimeCfg;
import othello.configuration.TimerCfg;
import othello.configuration.TypeCfg;

/**
 *
 * @author Hien Hoang
 */
public class ConfigStation extends JPanel implements ActionListener {
    
    JLabel titleLabel = new JLabel();
    JLabel lbTPM = new JLabel();
    JLabel lbTPG = new JLabel();
    JLabel lbSPG = new JLabel();
    
    JComboBox cbTPM = new JComboBox();
    JComboBox cbTPG = new JComboBox();
    JComboBox cbSPG = new JComboBox();
    BackNextGroup btnBackNext = new BackNextGroup();
    
    Configuration cfg = Configuration.getInstance();
    
    public ConfigStation() {
    	
        initialize();
    }
    
    private void initialize() {
        
        titleLabel.setText("CONFIG");
        Font othelloFont = new Font(this.getFont().getFontName(), Font.BOLD, 40);
        titleLabel.setFont(othelloFont);
        titleLabel.setForeground(Color.GRAY);
        
        btnBackNext.setNextName("PLAY");
        btnBackNext.setNextLetter(StationUIManager.STATION_PLAY_GAME);
        
        lbTPM.setText("SECONDS PER MOVE");
        lbTPG.setText("SECONDS PER GAME");
        lbSPG.setText("SCORE PER GAME");

        cbTPG.setMaximumSize(new Dimension(500, 30));
        cbTPG.setName(TimerCfg.TYPE_GAME);
        cbTPG.addActionListener(this);
        DefaultComboBoxModel<TimeCfg> cbTPGModel = new DefaultComboBoxModel<TimeCfg>();
        for (TimeCfg item : cfg.timer.gameTimer.times) {
        	cbTPGModel.addElement(item);
        }
        cbTPG.setModel(cbTPGModel);
        cbTPG.setSelectedItem(cfg.timer.getSelectedGameTimer());
        
        
        cbTPM.setMaximumSize(new Dimension(500, 30));
        cbTPM.setName(TimerCfg.TYPE_MOVE);
        cbTPM.addActionListener(this);
        DefaultComboBoxModel<TimeCfg> cbTPMModel = new DefaultComboBoxModel<TimeCfg>();
        for (TimeCfg item : cfg.timer.moveTimer.times) {
        	cbTPMModel.addElement(item);
        }
        cbTPM.setModel(cbTPMModel);
		cbTPM.setSelectedItem(cfg.timer.getSelectedMoveTimer());
        
        cbSPG.setMaximumSize(new Dimension(500, 30));
        if (cfg.getPlayingType().name.equalsIgnoreCase(TypeCfg.TYPE_OFFLINE)) {
        	cbSPG.setEnabled(false);
        	lbSPG.setEnabled(false);
        }
        
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
        contentPanel.add(lbTPM , BorderLayout.CENTER);
        contentPanel.add(cbTPM, BorderLayout.CENTER);
        contentPanel.add(Box.createRigidArea(new Dimension(0,25)));
        contentPanel.add(lbTPG, BorderLayout.CENTER);
        contentPanel.add(cbTPG, BorderLayout.CENTER);
        contentPanel.add(Box.createRigidArea(new Dimension(0,25)));
        contentPanel.add(lbSPG, BorderLayout.CENTER);
        contentPanel.add(cbSPG, BorderLayout.CENTER);
        contentPanel.add(Box.createVerticalGlue());
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(titleLabel)
                    .addComponent(contentPanel)
                    .addComponent(btnBackNext)
                )
         );
 
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(titleLabel).addGap(50)
                .addComponent(contentPanel)
                .addComponent(btnBackNext)

         );
    }
    
    public void setBackLetter(String letter) {
    	btnBackNext.setBackLetter(letter);
    }
    
    public void setNextLetter(String letter) {
    	btnBackNext.setNextLetter(letter);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
		TimeCfg time = (TimeCfg)cb.getSelectedItem();
		setSelectedTimer(cb.getName(), time.name);
	}
	
	private void setSelectedTimer(String type, String name) {
		switch(type) {
			case TimerCfg.TYPE_GAME:
				cfg.timer.setSelectedGameTimer(name);
				break;
			case TimerCfg.TYPE_MOVE:
				cfg.timer.setSelectedMoveTimer(name);
				break;
		}
		cfg.serialize(Configuration.CONFIG_FILEPATH);
	}
}
