package vaisseaux.armes.joueur;

import menu.CSG;

import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

/**
 * Sert � cr�er les projectiles de l'arme bleu, s'adapte suivant le niveau et donne un effet de balayage
 * @author Julien
 */
public class ManagerArmeTrois {
	private static Sound son = Gdx.audio.newSound(Gdx.files.internal("sons/156895__halgrimm__shot-2-0.wav"));
	
	public static void init(float posX, float posY){
		SoundMan.playTir(son);
		// On ne tient compte du niveau que si c'est un joueur qui tire
		switch (CSG.profil.NvArmeTrois) {
//		switch (6) {
		case 1:			nv1(posX, posY);			break;
		case 2:			nv2(posX, posY);			break;
		case 3:			nv3(posX, posY);			break;
		case 4:			nv4(posX, posY);			break;
		case 5:			nv5(posX, posY);			break;
		default:		nv6(posX, posY);			break;
		}
	}

	//-0.9848077:0.17364822
	private static void nv6(float posX, float posY) {
		ArmesTrois gauche80 = ArmesTrois.pool.obtain();
		gauche80.init(posX, posY, -0.9848077f, 0.17364822f, 80);
		ArmesTrois droite80 = ArmesTrois.pool.obtain();
		droite80.init(posX , posY, 0.9848077f, 0.17364822f, -80);
		
		ArmesTrois gauche85 = ArmesTrois.pool.obtain();
		gauche85.init(posX, posY, -0.9961947f, 0.087155804f, 85);
		ArmesTrois droite85 = ArmesTrois.pool.obtain();
		droite85.init(posX , posY, 0.9961947f, 0.087155804f, -85);
		ArmesTrois gauche = ArmesTrois.pool.obtain();
		gauche.init(posX, posY- ArmesTrois.DEMI_LARGEUR, -1, 0, 90);
		ArmesTrois droite = ArmesTrois.pool.obtain();
		droite.init(posX , posY- ArmesTrois.DEMI_LARGEUR, 1, 0, -90);
		ArmesTrois gauche15 = ArmesTrois.pool.obtain();
		gauche15.init(posX, posY, -0.25881904f, 0.9659258f, 15);
		ArmesTrois droite15 = ArmesTrois.pool.obtain();
		droite15.init(posX, posY, 0.25881904f, 0.9659258f, -15);
		ArmesTrois dixgauche = ArmesTrois.pool.obtain();
		dixgauche.init(posX, posY, -0.17364818f, 0.9848077f, 10);
		ArmesTrois dixdroite = ArmesTrois.pool.obtain();
		dixdroite.init(posX, posY, 0.17364818f, 0.9848077f, -10);
		ArmesTrois cinqGauche = ArmesTrois.pool.obtain();
		cinqGauche.init(posX, posY, -0.08715574f, 0.9961947f, 5);
		ArmesTrois cinqDroite = ArmesTrois.pool.obtain();
		cinqDroite.init(posX, posY, 0.08715574f, 0.9961947f, -5);
	}

	//[-0.9961947:0.087155804]
	/**
	 * fait un quart de rotation negatif, �a s'intercale entre les deux
	 */
	private static void nv5(float posX, float posY) {
		ArmesTrois gauche85 = ArmesTrois.pool.obtain();
		gauche85.init(posX, posY, -0.9961947f, 0.087155804f, 85);
		ArmesTrois droite85 = ArmesTrois.pool.obtain();
		droite85.init(posX , posY, 0.9961947f, 0.087155804f, -85);
		ArmesTrois gauche = ArmesTrois.pool.obtain();
		gauche.init(posX, posY- ArmesTrois.DEMI_LARGEUR, -1, 0, 90);
		ArmesTrois droite = ArmesTrois.pool.obtain();
		droite.init(posX , posY- ArmesTrois.DEMI_LARGEUR, 1, 0, -90);
		ArmesTrois gauche15 = ArmesTrois.pool.obtain();
		gauche15.init(posX, posY, -0.25881904f, 0.9659258f, 15);
		ArmesTrois droite15 = ArmesTrois.pool.obtain();
		droite15.init(posX, posY, 0.25881904f, 0.9659258f, -15);
		ArmesTrois dixgauche = ArmesTrois.pool.obtain();
		dixgauche.init(posX, posY, -0.17364818f, 0.9848077f, 10);
		ArmesTrois dixdroite = ArmesTrois.pool.obtain();
		dixdroite.init(posX, posY, 0.17364818f, 0.9848077f, -10);
		ArmesTrois cinqGauche = ArmesTrois.pool.obtain();
		cinqGauche.init(posX, posY, -0.08715574f, 0.9961947f, 5);
		ArmesTrois cinqDroite = ArmesTrois.pool.obtain();
		cinqDroite.init(posX, posY, 0.08715574f, 0.9961947f, -5);
	}

	/**
	 * Fait une quadruple rotation positive pour ajouter de la simetrie(?) au tir plus �vas� 
	 */
	private static void nv4(float posX, float posY) {
		ArmesTrois gauche = ArmesTrois.pool.obtain();
		gauche.init(posX, posY- ArmesTrois.DEMI_LARGEUR, -1, 0, 90);
		ArmesTrois droite = ArmesTrois.pool.obtain();
		droite.init(posX , posY- ArmesTrois.DEMI_LARGEUR, 1, 0, -90);
		ArmesTrois gauche15 = ArmesTrois.pool.obtain();
		gauche15.init(posX, posY, -0.25881904f, 0.9659258f, 15);
		ArmesTrois droite15 = ArmesTrois.pool.obtain();
		droite15.init(posX, posY, 0.25881904f, 0.9659258f, -15);
		ArmesTrois dixgauche = ArmesTrois.pool.obtain();
		dixgauche.init(posX, posY, -0.17364818f, 0.9848077f, 10);
		ArmesTrois dixdroite = ArmesTrois.pool.obtain();
		dixdroite.init(posX, posY, 0.17364818f, 0.9848077f, -10);
		ArmesTrois cinqGauche = ArmesTrois.pool.obtain();
		cinqGauche.init(posX, posY, -0.08715574f, 0.9961947f, 5);
		ArmesTrois cinqDroite = ArmesTrois.pool.obtain();
		cinqDroite.init(posX, posY, 0.08715574f, 0.9961947f, -5);
	}

	private static void nv3(float posX, float posY) {
		ArmesTrois gauche15 = ArmesTrois.pool.obtain();
		gauche15.init(posX, posY, -0.25881904f, 0.9659258f, 15);
		ArmesTrois droite15 = ArmesTrois.pool.obtain();
		droite15.init(posX, posY, 0.25881904f, 0.9659258f, -15);
		ArmesTrois dixgauche = ArmesTrois.pool.obtain();
		dixgauche.init(posX, posY, -0.17364818f, 0.9848077f, 10);
		ArmesTrois dixdroite = ArmesTrois.pool.obtain();
		dixdroite.init(posX, posY, 0.17364818f, 0.9848077f, -10);
		ArmesTrois cinqGauche = ArmesTrois.pool.obtain();
		cinqGauche.init(posX, posY, -0.08715574f, 0.9961947f, 5);
		ArmesTrois cinqDroite = ArmesTrois.pool.obtain();
		cinqDroite.init(posX, posY, 0.08715574f, 0.9961947f, -5);
	}

	private static void nv2(float posX, float posY) {
		ArmesTrois dixgauche = ArmesTrois.pool.obtain();
		dixgauche.init(posX, posY, -0.17364818f, 0.9848077f, 10);
		ArmesTrois dixdroite = ArmesTrois.pool.obtain();
		dixdroite.init(posX, posY, 0.17364818f, 0.9848077f, -10);
		ArmesTrois cinqGauche = ArmesTrois.pool.obtain();
		cinqGauche.init(posX, posY, -0.08715574f, 0.9961947f, 5);
		ArmesTrois cinqDroite = ArmesTrois.pool.obtain();
		cinqDroite.init(posX, posY, 0.08715574f, 0.9961947f, -5);
	}

	// rotation 85 : [-0.9961947:0.087155804]
	// rotation 15 : [-0.25881904:0.9659258]
	// rotation 10 : -0.17364818:0.9848077
	// rotation 5 : [-0.08715574:0.9961947]
	private static void nv1(float posX, float posY) {
		Vector2 dir = new Vector2(0,1);
		dir.rotate(80);
		ArmesTrois e = ArmesTrois.pool.obtain();
		e.init(posX, posY, -0.08715574f, 0.9961947f,5f);
		ArmesTrois f = ArmesTrois.pool.obtain();
		f.init(posX, posY, 0.08715574f, 0.9961947f,-5f);
	}

}
