package othello.sound;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundManager extends JFrame {
	
	private static SoundManager instance;
	
	public static SoundManager getInstance() {
		if (instance == null) {
			instance = new SoundManager();
		}
		return instance;
	}
	
	public void playSound(String filename) {

		try {
			// Open an audio input stream.
			URL url = SoundManager.class.getResource(filename);
			System.out.println(url);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
         	// Open audio clip and load samples from the audio input stream.
         	clip.open(audioIn);
         	clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
   }

   public static void main(String[] args) {
      new SoundManager().playSound("go.wav");
   }
}