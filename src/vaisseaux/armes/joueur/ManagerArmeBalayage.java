package vaisseaux.armes.joueur;

import vaisseaux.armes.Armes;
import menu.CSG;

import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

/**
 * Sert � cr�er les projectiles de l'arme bleu, s'adapte suivant le niveau et donne un effet de balayage
 * @author Julien
 */
public class ManagerArmeBalayage {
	
	private static boolean versDroite;
	private static int placeBalle = 0;
	private static int rotation = 0;
	private static int demiRotation = 0;
	private static int doubleRotation = 0;
	private static int BALLE4ROTA = 7, BALLE3ROTA = 6, BALLE2ROTA = 4, BALLE1ROTA = 1;
	private static Sound son = Gdx.audio.newSound(Gdx.files.internal("sons/164102__bmaczero__laser.wav"));
	private static Vector2 dir = new Vector2(0, 1);
	
	public static void init(float posX, float posY){
		SoundMan.playTir(son);
		dir.x = 0;
		dir.y = 1;
		switch (placeBalle) {
		case (-4):			rotation = -BALLE4ROTA; 	doubleRotation = -14; 	demiRotation = -4;		break;
		case (-3):			rotation = -BALLE3ROTA;	doubleRotation = -12; 	demiRotation = -3;		break;
		case (-2):			rotation = -BALLE2ROTA;	doubleRotation = -8; 	demiRotation = -2;		break;
		case (-1):			rotation = -BALLE1ROTA;	doubleRotation = -2;  	demiRotation = -1;		break;
		case (1):			rotation = BALLE1ROTA;	doubleRotation = 2;	  	demiRotation = 1;		break;
		case (2):			rotation = BALLE2ROTA;	doubleRotation = 8;  	demiRotation = 2;		break;
		case (3):			rotation = BALLE3ROTA;	doubleRotation = 12;  	demiRotation = 3;		break;
		case (4):			rotation = BALLE4ROTA;	doubleRotation = 14;  	demiRotation = 4;		break;
		default:			rotation = 0;	doubleRotation = 0;   	demiRotation = 0;		break;
		}
		posX -= ArmesBalayage.DEMI_LARGEUR;
		switch (CSG.profil.NvArmeBalayage) {
		case 1:			nv1(posX, posY);			break;
		case 2:			Nv2(posX, posY);			break;
		case 3:			Nv3(posX, posY);			break;
		case 4:			Nv4(posX, posY);			break;
		case 5:			Nv5(posX, posY);			break;
		default:		Nv6(posX, posY);			break;
		}
		changerAngle();
	}

	private static void Nv6 (float posX, float posY) {
		dir.rotate(rotation);
		creerArme(posX, posY);
		dir.rotate(-doubleRotation);
		creerArme(posX, posY);
		dir.rotate(-rotation);
		creerArme(posX, posY);
		dir.rotate(doubleRotation + doubleRotation);
		creerArme(posX, posY);
		dir.rotate(-demiRotation);
		creerArme(posX, posY);
		dir.rotate(-(doubleRotation + rotation) );
		creerArme(posX, posY);
	}

	private static void creerArme(float posX, float posY) {
		ArmesBalayage a = ArmesBalayage.pool.obtain();
		a.init(posX, posY, dir.x, dir.y, dir.angle());
		Armes.liste.add(a);
	}

	/**
	 * fait un quart de rotation negatif, �a s'intercale entre les deux
	 */
	private static void Nv5 (float posX, float posY) {
		dir.rotate(rotation);
	
		creerArme(posX, posY);
		
		dir.rotate(-doubleRotation);
		creerArme(posX, posY);
		
		dir.rotate(-rotation);
		creerArme(posX, posY);
		
		dir.rotate(doubleRotation + doubleRotation);
		creerArme(posX, posY);
		
		dir.rotate(-demiRotation);
		creerArme(posX, posY);
	}

	/**
	 * Fait une quadruple rotation positive pour ajouter de la simetrie(?) au tir plus �vas� 
	 */
	private static void Nv4 (float posX, float posY) {
		dir.rotate(rotation);
		creerArme(posX, posY);
		
		dir.rotate(-doubleRotation);
		creerArme(posX, posY);
		
		dir.rotate(-rotation);
		creerArme(posX, posY);
		
		dir.rotate(doubleRotation + doubleRotation);
		creerArme(posX, posY);
	}

	/**
	 * Fait une rotation negative. Ce qui ajoute � la pr�c�dente rotation n�gative simple et donne un tir plus �vas�
	 */
	private static void Nv3 (float posX, float posY) {
		dir.rotate(rotation);
		creerArme(posX, posY);
		
		dir.rotate(-doubleRotation);
		creerArme(posX, posY);
		
		dir.rotate(-rotation);
		creerArme(posX, posY);
	}

	/**
	 * Fait une double rotation negative
	 */
	private static void Nv2(float posX, float posY) {
		dir.rotate(rotation);
		creerArme(posX, posY);
		
		dir.rotate(-doubleRotation);
		creerArme(posX, posY);
	}

	/**
	 * Fait une rotation positive et r�gle la valeur de la rotation et de la double rotation
	 */
	private static void nv1(float posX, float posY) {
		dir.rotate(rotation);
		creerArme(posX, posY);
	}

	
	/**
	 * Change la place suivant le sens.
	 * Appel�e apr�s chaque passage
	 */
	private static void changerAngle() {
		// si le rayon se deplace vers la droite
		if (versDroite){
			placeBalle++;
			if (placeBalle >= 4)		versDroite = false;
		} else {
			placeBalle--;
			if (placeBalle <= -4)	versDroite = true;
		}
	}
}
