package othello.ui;

import java.awt.BorderLayout;
import java.io.File;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import othello.OthelloPlay;
import othello.configuration.Configuration;
import othello.configuration.TypeCfg;
import othello.game.Game;
import othello.game.Notifiable;
import othello.game.NotificationBoard;
import othello.ui.control.graphic.station.ConfigStation;
import othello.ui.control.graphic.station.LevelStation;
import othello.ui.control.graphic.station.LoginStation;
import othello.ui.control.graphic.station.MainStation;
import othello.ui.control.graphic.station.PlayFirstStation;
import othello.ui.control.graphic.station.PlayWithStation;
import othello.ui.control.graphic.station.RoomStation;
import othello.ui.control.graphic.station.StationUIManager;
import othello.ui.control.graphic.station.TableStation;

/**
 *
 * @author Hien Hoang
 */
public class MainFrame extends JFrame implements Notifiable {
    
	NotificationBoard nb = NotificationBoard.getInstance();
	Configuration cfg = Configuration.getInstance();
	JPanel currentPanel;
	
    MainStation mainStation;
    PlayWithStation playWithStation;
    LevelStation levelStation;
    PlayFirstStation playFirstStation;
    ConfigStation configStation;
    LoginStation loginStation;
    RoomStation roomStation;
    TableStation tableStation;
    
    HashMap<String, JPanel> UI;
    HashMap<JPanel, String> letters;
    
    public MainFrame() {
        initialize();
        nb.subscribe(this, NotificationBoard.NF_UI_NEXT);
        nb.subscribe(this, NotificationBoard.NF_UI_BACK);
        nb.subscribe(this, NotificationBoard.NF_GAME_EXITED);
    }
    
    private void initialize() {
    	
        UI = new HashMap<>();
        letters = new HashMap<>();
        mainStation = new MainStation();
        playWithStation = new PlayWithStation();
        levelStation = new LevelStation();
        playFirstStation = new PlayFirstStation();
        configStation = new ConfigStation();
        loginStation = new LoginStation();
        roomStation = new RoomStation();
        tableStation = new TableStation();
        
        UI.put(StationUIManager.STATION_MAIN, mainStation);
        UI.put(StationUIManager.STATION_PLAY_WITH, playWithStation );
        UI.put(StationUIManager.STATION_LEVEL, levelStation);
        UI.put(StationUIManager.STATION_PLAY_FIRST, playFirstStation);
        UI.put(StationUIManager.STATION_CONFIG, configStation);
        UI.put(StationUIManager.STATION_LOGIN, loginStation);
        UI.put(StationUIManager.STATION_ROOM, roomStation);
        UI.put(StationUIManager.STATION_TABLE, tableStation);
        
        letters.put(mainStation, StationUIManager.STATION_MAIN);
        letters.put(playWithStation, StationUIManager.STATION_PLAY_WITH);
        letters.put(levelStation, StationUIManager.STATION_LEVEL);
        letters.put(playFirstStation, StationUIManager.STATION_PLAY_FIRST);
        letters.put(configStation, StationUIManager.STATION_CONFIG);
        letters.put(loginStation, StationUIManager.STATION_LOGIN);
        letters.put(roomStation, StationUIManager.STATION_ROOM);
        letters.put(tableStation, StationUIManager.STATION_TABLE);
        
        currentPanel = UI.get(StationUIManager.STATION_MAIN);
        this.setSize(300, 600);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(currentPanel);
        this.setVisible(true);
    }
    
    private void changedUI(JPanel panel) {
    	this.getContentPane().removeAll();
    	this.add(panel);
    	this.invalidate();
    	this.validate();
    	this.repaint();
    }

	@Override
	public void receiveChangeNotification(int category, Object detail) {
		if (category == NotificationBoard.NF_UI_NEXT) {
			String letter = (String)detail;
			System.out.println("Received letter: " + letter);
			switch(letter) {
				case StationUIManager.STATION_MAIN:
					currentPanel = mainStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_PLAY_WITH:
					cfg.playing.setSelectedType(TypeCfg.TYPE_OFFLINE);
					playWithStation.setBackLetter(letters.get(currentPanel));
					System.out.println("BACK_LETTER: " + letters.get(currentPanel));
					currentPanel = playWithStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_LEVEL:
					levelStation.setBackLetter(letters.get(currentPanel));
					currentPanel = levelStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_PLAY_FIRST:
					playFirstStation.setBackLetter(letters.get(currentPanel));
					currentPanel = playFirstStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_CONFIG:
					configStation.setBackLetter(letters.get(currentPanel));
					currentPanel = configStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_PLAY_GAME:
					this.setVisible(false);
					UIFactory.getControlUI();
					File file = new File(OthelloPlay.class.getResource("OthelloPlay.class").getPath());
					System.out.println(file.getAbsolutePath());
					break;
				case StationUIManager.STATION_LOGIN:
					cfg.playing.setSelectedType(TypeCfg.TYPE_ONLINE);
					loginStation.setBackLetter(letters.get(currentPanel));
					currentPanel = loginStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_ROOM:
					cfg.playing.setSelectedType(TypeCfg.TYPE_ONLINE);
					roomStation.setBackLetter(letters.get(currentPanel));
					currentPanel = roomStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_TABLE:
					tableStation.setBackLetter(letters.get(currentPanel));
					currentPanel = tableStation;
					changedUI(currentPanel);
					break;
			}
		}
		if (category == NotificationBoard.NF_UI_BACK) {
			String letter = (String)detail;
			System.out.println("Received BACK LETTER: " + letter);
			switch(letter) {
				case StationUIManager.STATION_MAIN:
					currentPanel = mainStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_PLAY_WITH:
					currentPanel = playWithStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_LEVEL:
					currentPanel = levelStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_PLAY_FIRST:
					currentPanel = playFirstStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_CONFIG:
					currentPanel = configStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_LOGIN:
					currentPanel = loginStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_ROOM:
					currentPanel = roomStation;
					changedUI(currentPanel);
					break;
				case StationUIManager.STATION_TABLE:
					currentPanel = tableStation;
					changedUI(currentPanel);
					break;
			}
		}
		
		if (category == NotificationBoard.NF_GAME_EXITED) {
			this.setVisible(true);
		}
	}
    
    
}