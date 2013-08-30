package vaisseaux.armes.joueur;

import menu.CSG;

import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class ManagerArmeTrois {
	// rotation 85 : [-0.9961947:0.087155804]
	// rotation 15 : [-0.25881904:0.9659258]
	// rotation 10 : -0.17364818:0.9848077
	// rotation 5 : [-0.08715574:0.9961947]
	private static Sound son = Gdx.audio.newSound(Gdx.files.internal("sons/156895__halgrimm__shot-2-0.wav"));
	
	public static void init(float posX, float posY){
		SoundMan.playTir(son);
		ArmesTrois.pool.obtain().init(posX, posY, -0.08715574f, 0.9961947f, 5f);
		ArmesTrois.pool.obtain().init(posX, posY, 0.08715574f, 0.9961947f, -5f);
		switch (CSG.profil.NvArmeTrois) {
		case 6:
			ArmesTrois.pool.obtain().init(posX, posY, -0.9848077f, 0.17364822f, 80f);
			ArmesTrois.pool.obtain().init(posX , posY, 0.9848077f, 0.17364822f, -80f);
		case 5:
			ArmesTrois.pool.obtain().init(posX, posY, -0.9961947f, 0.087155804f, 85f);
			ArmesTrois.pool.obtain().init(posX , posY, 0.9961947f, 0.087155804f, -85f);
		case 4:
			ArmesTrois.pool.obtain().init(posX, posY - ArmesTrois.DEMI_LARGEUR, -1, 0, 90f);
			ArmesTrois.pool.obtain().init(posX , posY - ArmesTrois.DEMI_LARGEUR, 1, 0, -90f);
		case 3:
			ArmesTrois.pool.obtain().init(posX, posY, -0.25881904f, 0.9659258f, 15f);
			ArmesTrois.pool.obtain().init(posX, posY, 0.25881904f, 0.9659258f, -15f);
		case 2:
			ArmesTrois.pool.obtain().init(posX, posY, -0.17364818f, 0.9848077f, 10f);
			ArmesTrois.pool.obtain().init(posX, posY, 0.17364818f, 0.9848077f, -10f);
		}
	}
}
