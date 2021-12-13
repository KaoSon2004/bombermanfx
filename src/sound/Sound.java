package sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	Clip clip;
	String[] urlStrings = new String[10];
	public Sound() {
		urlStrings[0] = "src/Menu/resource/Title.wav";
		urlStrings[1] = "res/sound/1-03. World Map.wav";
		urlStrings[2] = "res/sound/1-11. Fail.wav"; 
		urlStrings[3] = "res/sound/BombSet.wav";
		urlStrings[4] = "res/sound/BombExplode.wav";
		urlStrings[5] = "res/sound/Item.wav";
		urlStrings[6] = "res/sound/enemy_die.wav";
		urlStrings[7] = "res/sound/Mousclik.wav";
	}
	public void setFile(int i) {
		try {
			File file = new File(urlStrings[i]);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue((float) 0.1);
		
	}
	
	public void stop() {
		if(clip != null)
			clip.stop();
	}
	
}
