package othello;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.omg.CORBA.portable.InputStream;

import othello.game.Game;

public class OthelloPlay {
	
	public static void main(String args[]) {
		
		Game game = new Game();     
		game.play();
	}
}
