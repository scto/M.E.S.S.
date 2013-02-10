package sons;

import menu.CSG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundMan {
	
	public static final Sound explosionGrosse = Gdx.audio.newSound(Gdx.files.internal("sons/80500__ggctuk__exp-obj-large03.wav"));
	public static final Music outsideNorm = Gdx.audio.newMusic(Gdx.files.internal("sons/Outside Norm-The 3nd.mp3"));
	public static void playBruitage(Sound s) {
		 s.play(CSG.profil.volumeBruitages);
	}

	public static void playMusic() {
		outsideNorm.setVolume(CSG.profil.volumeMusique);
		outsideNorm.setLooping(true);
		outsideNorm.play();
		
	}

	public static void stopMusic() {
		outsideNorm.stop();
	}

}
