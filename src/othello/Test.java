package othello;

import java.io.File;
import java.io.IOException;

import othello.client.OnlineGameMonitor;
import othello.command.JoinPlayerCmd;
import othello.command.response.IJoinPlayerResExec;
import othello.common.AbstractPlayer;
import othello.common.Piece;
import othello.configuration.Configuration;
import othello.configuration.PlayerCfg;
import othello.configuration.TypeCfg;
import othello.game.Game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hien
 */
public class Test implements IJoinPlayerResExec {
    
    private static Test singtonObject;
    
    public static Test getInstance() {
        if (singtonObject == null) {
            singtonObject = new Test();
        }
        return singtonObject;
    }
    
    Configuration cfg = Configuration.getInstance();
    public static void main(String[] args) {
        
        if (Configuration.getInstance().getPlayingType().name.equalsIgnoreCase(TypeCfg.TYPE_ONLINE)) {
            JoinPlayerCmd joinPlayer = new JoinPlayerCmd(OnlineGameMonitor.getInstance(), null);
            joinPlayer.execute();
        }
    }

    @Override
    public void joinAccepted(AbstractPlayer player) {
        cfg.players.players.clear();
        PlayerCfg player1 = new PlayerCfg();
        player1.setName(player.getName());
        player1.setId(1);
        player1.setPiece(player.getPiece());
        if(player.getPiece() == Piece.BLACK) {
            cfg.players.setFirstPlayerId(1);
        } else {
            cfg.players.setFirstPlayerId(2);
        }
        player1.setType(PlayerCfg.TYPE_HUMAN);
        
        cfg.players.players.add(player1);
        
        PlayerCfg online = new PlayerCfg();
        online.setId(2);
        online.setPiece(player.getPiece().getOpposite());
        online.setType(PlayerCfg.TYPE_ONLINE);
        
        cfg.players.players.add(online);
        
        Game game = new Game();     
        game.play();
    }

    @Override
    public void joinRejected(String message) {
        
    }
}

class OthelloPlayTest {
	public static void main(String args[]) {
		File file = new File(OthelloPlay.class.getResource("OthelloPlay.class").getPath()).getParentFile();
		File dir = file.getParentFile();
		Runtime runTime = Runtime.getRuntime();
		try {
			String env[] = new String[] {"classpath=%classpath%;.;"};
			Process process = runTime.exec("java othello.OthelloPlay", env, dir);
			process.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
