package vaisseaux.armes;

import menu.CSG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

/**
 * Sert à créer les projectiles de l'arme bleu, s'adapte suivant le niveau et donne un effet de balayage
 * @author Julien
 */
public class ManagerArmeBalayage {
	
	private static boolean versDroite;
	private static int placeBalle = 0;
	private static int rotation = 0;
	private static int demiRotation = 0;
	private static int doubleRotation = 0;
	private static Sound son = Gdx.audio.newSound(Gdx.files.internal("sons/164102__bmaczero__laser.wav"));
	
	public static void init(float posX, float posY, float dirX, float dirY, boolean ennemi){
		son.play(CSG.profil.volumeArme);
		Vector2 dir = new Vector2(dirX, dirY);
		// On ne tient compte du niveau que si c'est un joueur qui tire
		if(!ennemi){
			switch(CSG.profil.NvArmeBalayage){
			case 1:
				nv1(posX, posY, dir, ennemi);
				break;
			case 2:
				nv2(posX, posY, dir, ennemi);
				break;
			case 3:
				nv3(posX, posY, dir, ennemi);
				break;
			case 4:
				nv4(posX, posY, dir, ennemi);
				break;
			case 5:
				nv5(posX, posY, dir, ennemi);
				break;
			default:
				nv6(posX, posY, dir, ennemi);
				break;
			}
		} else nv1(posX, posY, dir, ennemi);
		changerAngle();
	}

	private static void nv6(float posX, float posY, Vector2 dir, boolean ennemi) {
		nv5(posX, posY, dir, ennemi);
		dir.rotate(-(doubleRotation + rotation) );
		creerArme(posX, posY, dir, ennemi);
	}

	/**
	 * fait un quart de rotation negatif, ça s'intercale entre les deux
	 */
	private static void nv5(float posX, float posY, Vector2 dir, boolean ennemi) {
		nv4(posX, posY, dir, ennemi);
		dir.rotate(-demiRotation);
		creerArme(posX, posY, dir, ennemi);
	}

	/**
	 * Fait une quadruple rotation positive pour ajouter de la simetrie(?) au tir plus évasé 
	 */
	private static void nv4(float posX, float posY, Vector2 dir, boolean ennemi) {
		nv3(posX, posY, dir, ennemi);
		dir.rotate((doubleRotation + doubleRotation));
		creerArme(posX, posY, dir, ennemi);
	}

	/**
	 * Fait une rotation negative. Ce qui ajoute à la précédente rotation négative simple et donne un tir plus évasé
	 */
	private static void nv3(float posX, float posY, Vector2 dir, boolean ennemi) {
		nv2(posX, posY, dir, ennemi);
		dir.rotate(-rotation);
		creerArme(posX, posY, dir, ennemi);
	}

	/**
	 * Fait une double rotation negative
	 */
	private static void nv2(float posX, float posY, Vector2 dir, boolean ennemi) {
		nv1(posX, posY, dir, ennemi);
		dir.rotate(-doubleRotation);
		creerArme(posX, posY, dir, ennemi);
	}

	/**
	 * Fait une rotation positive et régle la valeur de la rotation et de la double rotation
	 */
	private static void nv1(float posX, float posY, Vector2 dir, boolean ennemi) {
		switch (placeBalle) {
		case (-4):			rotation = -7; 	doubleRotation = -14; 	demiRotation = -4;			break;
		case (-3):			rotation = -6;	doubleRotation = -12; 	demiRotation = -3;			break;
		case (-2):			rotation = -4;	doubleRotation = -8; 	demiRotation = -2;			break;
		case (-1):			rotation = -1;	doubleRotation = -2;  	demiRotation = -1;			break;
		case (1):			rotation = 1;	doubleRotation = 2;	  	demiRotation = 1;			break;
		case (2):			rotation = 4;	doubleRotation = 8;  	demiRotation = 2;			break;
		case (3):			rotation = 6;	doubleRotation = 12;  	demiRotation = 3;			break;
		case (4):			rotation = 7;	doubleRotation = 14;  	demiRotation = 4;			break;
		default:			rotation = 0;	doubleRotation = 0;   	demiRotation = 0;			break;
		}
		dir.rotate(rotation);
		creerArme(posX, posY, dir, ennemi);
	}

	/**
	 * Instancie le projectile
	 * @param posX Position en X
	 * @param posY Position en Y
	 * @param dir Direction
	 * @param ennemi Si c'est un ennemi ou pas
	 */
	private static void creerArme(float posX, float posY, Vector2 dir, boolean ennemi) {
		ArmesBalayage a = ArmesBalayage.pool.obtain();
		a.init(posX, posY, dir.x, dir.y, dir.angle());
		if(ennemi)
			Armes.listeTirsDesEnnemis.add(a);
		else
			Armes.liste.add(a);
	}
	
	/**
	 * Change la place suivant le sens.
	 * Appelée après chaque passage
	 */
	private static void changerAngle() {
		// si le rayon se deplace vers la droite
		if(versDroite){
			placeBalle++;
			if(placeBalle >= 4)		versDroite = false;
		} else {
			placeBalle--;
			if(placeBalle <= -4)	versDroite = true;
		}
	}
}
