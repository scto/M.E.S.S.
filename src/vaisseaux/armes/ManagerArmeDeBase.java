package vaisseaux.armes;

import menu.CSG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Sert à créer les projectiles de l'arme bleu, s'adapte suivant le niveau et donne un effet de balayage
 * @author Julien
 */
public class ManagerArmeDeBase {
	
	public static void init(float posX, float posY, boolean ennemi){
		// On ne tient compte du niveau que si c'est un joueur qui tire
		if(!ennemi){
			switch (CSG.profil.NvArmeDeBase) {
			case 1:
				nv1(posX, posY, ennemi);
				break;
			case 2:
				nv2(posX, posY, ennemi);
				break;
			case 3:
				nv3(posX, posY, ennemi);
				break;
			case 4:
				nv4(posX, posY, ennemi);
				break;
			case 5:
				nv5(posX, posY, ennemi);
				break;
			default:
				nv6(posX, posY, ennemi);
				break;
			}
		} else nv1(posX, posY, ennemi);

	}

	private static void nv6(float posX, float posY, boolean ennemi) {
		creerArme(posX - ArmesDeBase.DEMI_LARGEUR, posY, ennemi);
		creerArme(posX + ArmesDeBase.DEMI_LARGEUR, posY, ennemi);
		creerArme(posX - ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR, ennemi);
		creerArme(posX + ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR, ennemi);
		creerArme(posX, posY + ArmesDeBase.DEMI_HAUTEUR, ennemi);
		creerArme(posX, posY - ArmesDeBase.DEMI_HAUTEUR, ennemi);
	}

	/**
	 * fait un quart de rotation negatif, ça s'intercale entre les deux
	 */
	private static void nv5(float posX, float posY, boolean ennemi) {
		creerArme(posX - ArmesDeBase.DEMI_LARGEUR, posY, ennemi);
		creerArme(posX + ArmesDeBase.DEMI_LARGEUR, posY, ennemi);
		creerArme(posX - ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR, ennemi);
		creerArme(posX + ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR, ennemi);
		creerArme(posX, posY + ArmesDeBase.DEMI_HAUTEUR, ennemi);
	}

	/**
	 * Fait une quadruple rotation positive pour ajouter de la simetrie(?) au tir plus évasé 
	 */
	private static void nv4(float posX, float posY, boolean ennemi) {
		creerArme(posX - ArmesDeBase.DEMI_LARGEUR, posY, ennemi);
		creerArme(posX + ArmesDeBase.DEMI_LARGEUR, posY, ennemi);
		creerArme(posX - ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR, ennemi);
		creerArme(posX + ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR, ennemi);
	}

	private static void nv3(float posX, float posY, boolean ennemi) {
		creerArme(posX - ArmesDeBase.LARGEUR, posY, ennemi);
		creerArme(posX + ArmesDeBase.LARGEUR, posY, ennemi);
		creerArme(posX, posY + ArmesDeBase.DEMI_HAUTEUR, ennemi);
	}

	private static void nv2(float posX, float posY, boolean ennemi) {
		creerArme(posX - ArmesDeBase.DEMI_LARGEUR, posY, ennemi);
		creerArme(posX + ArmesDeBase.DEMI_LARGEUR, posY, ennemi);
	}

	private static void nv1(float posX, float posY, boolean ennemi) {
		ArmesDeBase e = ArmesDeBase.pool.obtain();
		e.init(posX, posY, 0, 1, false);
	}

	/**
	 * Instancie le projectile
	 * @param posX Position en X
	 * @param posY Position en Y
	 * @param dir Direction
	 * @param ennemi Si c'est un ennemi ou pas
	 */
	private static void creerArme(float posX, float posY, boolean ennemi) {
		ArmesDeBase e = ArmesDeBase.pool.obtain();
		e.init(posX, posY, 0, 1, false);
	}
}
