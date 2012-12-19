package vaisseaux.armes;

import menu.CSG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;


public class ManagerArmeBalayage {
	
	private static int placeBalle = 0;
	private static boolean versDroite;
	private static int rotation = 0;
	
	public static void init(float posX, float posY, float dirX, float dirY, boolean ennemi){
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
		Vector2 direction2 = new Vector2(dir);
		direction2.rotate(- (rotation + rotation + rotation + rotation + rotation + rotation));
		creerArme(posX, posY, direction2, ennemi);
	}

	private static void nv5(float posX, float posY, Vector2 dir, boolean ennemi) {
		nv4(posX, posY, dir, ennemi);
		Vector2 direction2 = new Vector2(dir);
		direction2.rotate((rotation + rotation + rotation + rotation));
		creerArme(posX, posY, direction2, ennemi);
	}

	private static void nv4(float posX, float posY, Vector2 dir, boolean ennemi) {
		nv3(posX, posY, dir, ennemi);
		Vector2 direction2 = new Vector2(dir);
		direction2.rotate(-(rotation + rotation + rotation + rotation));
		creerArme(posX, posY, direction2, ennemi);
	}

	private static void nv3(float posX, float posY, Vector2 dir, boolean ennemi) {
		nv2(posX, posY, dir, ennemi);
		Vector2 direction2 = new Vector2(dir);
		direction2.rotate(rotation+rotation);
		creerArme(posX, posY, direction2, ennemi);
	}

	private static void nv2(float posX, float posY, Vector2 dir, boolean ennemi) {
		nv1(posX, posY, dir, ennemi);
		Vector2 direction2 = new Vector2(dir);
		direction2.rotate(-(rotation+rotation));
		creerArme(posX, posY, direction2, ennemi);
	}

	private static void nv1(float posX, float posY, Vector2 dir, boolean ennemi) {
		switch (placeBalle) {
		case (-4):			rotation = -10;			break;
		case (-3):			rotation = -9;			break;
		case (-2):			rotation = -7;			break;
		case (-1):			rotation = -4;			break;
		case (1):			rotation = 4;			break;
		case (2):			rotation = 7;			break;
		case (3):			rotation = 9;			break;
		case (4):			rotation = 10;			break;
		default:			rotation = 0;
		}
		dir.rotate(rotation);
		creerArme(posX, posY, dir, ennemi);
	}

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
	 */
	private static void changerAngle() {
		// si le rayon se deplace vers la droite
		if(versDroite){
			placeBalle++;
			if(placeBalle >= 4){
				versDroite = false;
			} 
		} else {
			placeBalle--;
			if(placeBalle <= -4){
				versDroite = true;
			}
		}
	}
}
