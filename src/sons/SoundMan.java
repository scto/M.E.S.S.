package sons;

import menu.CSG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundMan {
	
	public static final Sound explosionGrosse = Gdx.audio.newSound(Gdx.files.internal("sons/80500__ggctuk__exp-obj-large03.wav"));

	public static void playBruitage(Sound s) {
		 s.play(CSG.profil.volumeBruitages);
	}

}
