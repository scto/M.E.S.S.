package assets;

import jeu.CSG;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public final class SoundMan {

	public static Sound explosion1, explosion2, bigExplosion, explosion3, explosion4, explosion5, explosion6, shotRocket, bonusTaken, sunWeapon, xp;
	public static Music outsideNorm;
	private static final float MIN = 0.1f;
	private static float originalVolume, changedVolume, gap;
	private static final int SOUNDS_PER_FRAME = 8;
//	private static final AudioDevice audio = initAudio();

	private SoundMan() {
	}

//	private static AudioDevice initAudio() {
//		return Gdx.audio.newAudioDevice(44, true);
//	}

	public static void playBulletSound(Sound s) {
		if (CSG.profile.weaponVolume > MIN) {
			s.play(CSG.profile.weaponVolume);
		}
	}

	public static void playBruitage(Sound s) {
		if (CSG.profile.effectsVolume > MIN) {
			s.play(CSG.profile.effectsVolume);
		}
	}

	public synchronized static void playMusic() {
		if (CSG.profile.musicVolume > MIN && outsideNorm != null) {
			try {
				outsideNorm.play();
				outsideNorm.setVolume(CSG.profile.musicVolume);
				outsideNorm.setLooping(true);
			} catch (Exception e) {
			}
		}
	}

	public static void stopMusic() {
		if (outsideNorm != null)
			outsideNorm.stop();
	}

	public static void halfVolume() {
		try {
			if (outsideNorm != null && outsideNorm.isPlaying()) {
				originalVolume = outsideNorm.getVolume();
				outsideNorm.setVolume(originalVolume / 2);
				changedVolume = originalVolume / 3;
				gap = originalVolume - changedVolume;
			}
		} catch (Exception e) {

		}
	}

	public static void transitionUp(float f) {
		try {
			if (outsideNorm != null && outsideNorm.isPlaying()) {
				outsideNorm.setVolume(changedVolume + (gap * f));
			}
		} catch (Exception e) {

		}
	}

	public static void setOriginalVolume() {
		if (CSG.profile.musicVolume > MIN && outsideNorm != null) {
			try {
				outsideNorm.setVolume(CSG.profile.musicVolume);
				outsideNorm.setLooping(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void generate(float t) {
//		audio.writeSamples(samples, offset, numSamples);
	}
	
	
}
