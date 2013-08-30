package vaisseaux.armes.joueur;

import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import menu.CSG;

public class ManagerArmeHantee {
	
	private static Sound son = Gdx.audio.newSound(Gdx.files.internal("sons/armehantee.wav"));

	public static void init(float posX, float posY){
		SoundMan.playTir(son);
		switch (CSG.profil.NvArmeHantee) {
			case 1:				nv1(posX, posY);			break;
			case 2:				nv2(posX, posY);				break;
			case 3:				nv3(posX, posY);				break;
			case 4:				nv4(posX, posY);				break;
			case 5:				nv5(posX, posY);				break;
			default:			nv6(posX, posY);				break;
		}
	}

	private static void nv6(float posX, float posY) {
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		ArmeHantee.pool.obtain().init(posX, posY + ArmeHantee.DEMI_LARGEUR);
		ArmeHantee.pool.obtain().init(posX, posY - ArmeHantee.DEMI_LARGEUR);
	}

	private static void nv5(float posX, float posY) {
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		ArmeHantee.pool.obtain().init(posX, posY + ArmeHantee.DEMI_LARGEUR);
	}

	private static void nv4(float posX, float posY) {
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
	}

	private static void nv3(float posX, float posY) {
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX, posY + ArmeHantee.DEMI_LARGEUR);
	}

	private static void nv2(float posX, float posY) {
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.DEMI_LARGEUR, posY);
	}

	private static void nv1(float posX, float posY) {
		ArmeHantee.pool.obtain().init(posX, posY);
	}
}
