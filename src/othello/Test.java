package othello;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import othello.client.OnlineGameMonitor;
import othello.command.JoinPlayerCmd;
import othello.command.response.JoinPlayerResExecutable;
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
public class Test  {
    
    private static Test singtonObject;
    
    public static Test getInstance() {
        if (singtonObject == null) {
            singtonObject = new Test();
        }
        return singtonObject;
    }
    
    Configuration cfg = Configuration.getInstance();
    public static void main(String[] args) {
        
//        if (Configuration.getInstance().getPlayingType().name.equalsIgnoreCase(TypeCfg.TYPE_ONLINE)) {
//            JoinPlayerCmd joinPlayer = new JoinPlayerCmd(OnlineGameMonitor.getInstance(), null);
//            joinPlayer.execute();
//        }
    }

//    @Override
//    public void joinAccepted(AbstractPlayer player) {
//        cfg.players.players.clear();
//        PlayerCfg player1 = new PlayerCfg();
//        player1.setName(player.getName());
//        player1.setId(1);
//        player1.setPiece(player.getPiece());
//        if(player.getPiece() == Piece.BLACK) {
//            cfg.players.setFirstPlayerId(1);
//        } else {
//            cfg.players.setFirstPlayerId(2);
//        }
//        player1.setType(PlayerCfg.TYPE_HUMAN);
//        
//        cfg.players.players.add(player1);
//        
//        PlayerCfg online = new PlayerCfg();
//        online.setId(2);
//        online.setPiece(player.getPiece().getOpposite());
//        online.setType(PlayerCfg.TYPE_ONLINE);
//        
//        cfg.players.players.add(online);
//        
//        Game game = new Game();     
//        game.play();
//    }

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

class SystemEnviroment {

	public static void main(String args[]) {
		Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                              envName,
                              env.get(envName));
        }
	}
}

class PassSystemEnviroment {
	
	public static void main(String args[]) {
		
		File file = new File(OthelloPlay.class.getResource("OthelloPlay.class").getPath()).getParentFile();
		File dir = file.getParentFile();
		Runtime runTime = Runtime.getRuntime();
		String env[] = new String[] {"classpath=%classpath%;.;", "playticket=11223344"};
		try {
			Process process = runTime.exec("java othello.SystemEnviroment", env, dir);
			InputStream inputStream = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(inputStream);
			InputStream errorStream = process.getErrorStream();
			InputStreamReader esr = new InputStreamReader(errorStream);

			int n1;
			char[] c1 = new char[1024];
			StringBuffer standardOutput = new StringBuffer();
			while ((n1 = isr.read(c1)) > 0) {
				standardOutput.append(c1, 0, n1);
			}
			System.out.println("Standard Output: \n" + standardOutput.toString());

			int n2;
			char[] c2 = new char[1024];
			StringBuffer standardError = new StringBuffer();
			while ((n2 = esr.read(c2)) > 0) {
				standardError.append(c2, 0, n2);
			}
			System.out.println("Standard Error: \n" + standardError.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
