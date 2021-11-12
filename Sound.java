import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	private AudioClip clip;
	
	public static Sound test =new Sound("/test.wav");
	public Sound(String path) {
		clip = 	Applet.newAudioClip(getClass().getResource(path));
	}
	public void play() {
		new Thread() {
			public void run() {
				clip.play();
			}
		}.start();
	}
}
