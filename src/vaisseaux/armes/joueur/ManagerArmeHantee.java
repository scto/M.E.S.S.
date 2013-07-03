package vaisseaux.armes.joueur;

import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import menu.CSG;

/**
 * Sert � cr�er les projectiles de l'arme bleu, s'adapte suivant le niveau et donne un effet de balayage
 * @author Julien
 */
public class ManagerArmeHantee {
	
	private static Sound son = Gdx.audio.newSound(Gdx.files.internal("sons/armehantee.wav"));

	public static void init(float posX, float posY){
		SoundMan.playTir(son);
		// On ne tient compte du niveau que si c'est un joueur qui tire
		switch (CSG.profil.NvArmeHantee) {
//		switch(1){
			case 1:				nv1(posX, posY);				break;
			case 2:				nv2(posX, posY);				break;
			case 3:				nv3(posX, posY);				break;
			case 4:				nv4(posX, posY);				break;
			case 5:				nv5(posX, posY);				break;
			default:			nv6(posX, posY);				break;
			}
	}

	private static void nv6(float posX, float posY) {
		creerArme(posX - ArmeHantee.DEMI_LARGEUR, posY);
		creerArme(posX + ArmeHantee.DEMI_LARGEUR, posY);
		creerArme(posX - ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		creerArme(posX + ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		creerArme(posX, posY + ArmeHantee.DEMI_LARGEUR);
		creerArme(posX, posY - ArmeHantee.DEMI_LARGEUR);
	}

	/**
	 * fait un quart de rotation negatif, �a s'intercale entre les deux
	 */
	private static void nv5(float posX, float posY) {
		creerArme(posX - ArmeHantee.DEMI_LARGEUR, posY);
		creerArme(posX + ArmeHantee.DEMI_LARGEUR, posY);
		creerArme(posX - ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		creerArme(posX + ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		creerArme(posX, posY + ArmeHantee.DEMI_LARGEUR);
	}

	/**
	 * Fait une quadruple rotation positive pour ajouter de la simetrie(?) au tir plus �vas� 
	 */
	private static void nv4(float posX, float posY) {
		creerArme(posX - ArmeHantee.DEMI_LARGEUR, posY);
		creerArme(posX + ArmeHantee.DEMI_LARGEUR, posY);
		creerArme(posX - ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		creerArme(posX + ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
	}

	private static void nv3(float posX, float posY) {
		creerArme(posX - ArmeHantee.LARGEUR, posY);
		creerArme(posX + ArmeHantee.LARGEUR, posY);
		creerArme(posX, posY + ArmeHantee.DEMI_LARGEUR);
	}

	private static void nv2(float posX, float posY) {
		creerArme(posX - ArmeHantee.DEMI_LARGEUR, posY);
		creerArme(posX + ArmeHantee.DEMI_LARGEUR, posY);
	}

	private static void nv1(float posX, float posY) {
		ArmeHantee e = ArmeHantee.pool.obtain();
		e.init(posX, posY);
	}

	/**
	 * Instancie le projectile
	 * @param posX Position en X
	 * @param posY Position en Y
	 * @param dir Direction
	 * @param ennemi Si c'est un ennemi ou pas
	 */
	private static void creerArme(float posX, float posY) {
		ArmeHantee e = ArmeHantee.pool.obtain();
		e.init(posX, posY);
	}
}
