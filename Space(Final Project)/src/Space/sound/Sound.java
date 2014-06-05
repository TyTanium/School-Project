package Space.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	public static Sound shot = new Sound("/audio/laser.wav");
	public static Sound count = new Sound("/audio/beeping.wav");
	public static Sound background = new Sound("/audio/Sandstorm.wav");

	private AudioClip clip;

	public Sound(String fileName) {
		clip = Applet.newAudioClip(Sound.class.getResource(fileName));
	}

	public void play() {
		try {
			new Thread("Sound") {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loop() {
		try {
			new Thread("Sound") {
				public void run() {
					clip.loop();
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			new Thread("Sound") {
				public void run() {
					clip.stop();
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
