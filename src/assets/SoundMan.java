package assets;

import jeu.CSG;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public final class SoundMan {
	
	public static Sound explosion1, explosion2, bigExplosion, explosion3, explosion4, explosion5, explosion6, tirRocket, bonusTaken, sunWeapon;
	public static Music outsideNorm;
	private static final float MIN = 0.05f;
	
	private SoundMan() {	}
	
	public static void playBulletSound(Sound s) {
		if (CSG.profile.weaponVolume > MIN)
			s.play(CSG.profile.weaponVolume);
	}

	public static void playBruitage(Sound s) {
		if (CSG.profile.effectsVolume > MIN)
			s.play(CSG.profile.effectsVolume);
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

//	public static void playBruitageLeft(Sound sonExplosion) {
//		if (CSG.profile.effectsVolume > MIN)
//			sonExplosion.play(CSG.profile.effectsVolume, 1, -1);
//	}
//
//	public static void playBruitageRight(Sound sonExplosion) {
//		if (CSG.profile.effectsVolume > MIN)
//			sonExplosion.play(CSG.profile.effectsVolume, 1, 1);
//	}
//
//	public static void playBruitage(Sound sonExplosion, float f) {
//		if (CSG.profile.effectsVolume > MIN)
//			sonExplosion.play(CSG.profile.effectsVolume, 1, (CSG.gameZoneHalfWidth / f) );
//	}

	public static void stopMusic() {
		if (outsideNorm != null)
			outsideNorm.stop();
	}
}
