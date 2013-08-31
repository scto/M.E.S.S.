package assets;

import menu.CSG;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundMan {
	
	public static Sound explosionPetite, explosionpetittetechercheuse, explosionGrosse, explosionboule, explosioncylon, explosionennemidebasequitir, explosionkinder, explosiontoupie, tirRocket, tirLaser2, tirEnergie, tirAvion;
	public static Music outsideNorm;
	
	public static void playTir(Sound s) {
		if (CSG.profil.volumeArme > 0.05)
			s.play(CSG.profil.volumeArme);
	}

	public static void playBruitage(Sound s) {
		if (CSG.profil.volumeBruitages > 0.05)
			s.play(CSG.profil.volumeBruitages);
	}

	public static void playMusic() {
		if (CSG.profil.volumeMusique > 0.05) {
			outsideNorm.setVolume(CSG.profil.volumeMusique);
			outsideNorm.play();
			outsideNorm.setLooping(true);
		}
	}

	public static void checkMusique() {
		if (CSG.profil.volumeMusique < 0.05 && outsideNorm != null) outsideNorm.stop();
		else playMusic();
	}

	public static void stopMusic() {
		if (outsideNorm != null && outsideNorm.isPlaying())
			outsideNorm.stop();
	}

	public static void pauseMusic() {
		if (outsideNorm != null && outsideNorm.isPlaying())
			outsideNorm.pause();
	}

}
