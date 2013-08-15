package vaisseaux.armes.joueur;

import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import menu.CSG;

/**
 * Sert � cr�er les projectiles de l'arme bleu, s'adapte suivant le niveau et donne un effet de balayage
 * @author Julien
 */
public class ManagerArmeDeBase {
	
	private static Sound son = Gdx.audio.newSound(Gdx.files.internal("sons/156895__halgrimm__shot-2-0.wav"));

	public static void init(float posX, float posY){
		SoundMan.playTir(son);
		// On ne tient compte du niveau que si c'est un joueur qui tire
		switch (CSG.profil.NvArmeDeBase) {
//		switch (6) {
		case 1:			nv1(posX, posY);			break;
		case 2:			nv2(posX, posY);			break;
		case 3:			nv3(posX, posY);			break;
		case 4:			nv4(posX, posY);			break;
		case 5:			nv5(posX, posY);			break;
		default:		nv6(posX, posY);			break;
		}
	}

	private static void nv6(float posX, float posY) {
		posX -= ArmesDeBase.DEMI_LARGEUR;
		ArmesDeBase a = ArmesDeBase.pool.obtain();
		a.init(posX - ArmesDeBase.DEMI_LARGEUR, posY);
		
		ArmesDeBase b = ArmesDeBase.pool.obtain();
		b.init(posX + ArmesDeBase.DEMI_LARGEUR, posY);
		
		ArmesDeBase c = ArmesDeBase.pool.obtain();
		c.init(posX - ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR);
		
		ArmesDeBase d = ArmesDeBase.pool.obtain();
		d.init(posX + ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR);
		
		ArmesDeBase e = ArmesDeBase.pool.obtain();
		e.init(posX, posY + ArmesDeBase.DEMI_HAUTEUR);
		
		ArmesDeBase f = ArmesDeBase.pool.obtain();
		f.init(posX, posY - ArmesDeBase.DEMI_HAUTEUR);
	}

	private static void nv5(float posX, float posY) {
		posX -= ArmesDeBase.DEMI_LARGEUR;
		ArmesDeBase a = ArmesDeBase.pool.obtain();
		a.init(posX - ArmesDeBase.DEMI_LARGEUR, posY);
		
		ArmesDeBase b = ArmesDeBase.pool.obtain();
		b.init(posX + ArmesDeBase.DEMI_LARGEUR, posY);
		
		ArmesDeBase c = ArmesDeBase.pool.obtain();
		c.init(posX - ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR);
		
		ArmesDeBase d = ArmesDeBase.pool.obtain();
		d.init(posX + ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR);
		
		ArmesDeBase e = ArmesDeBase.pool.obtain();
		e.init(posX, posY + ArmesDeBase.DEMI_HAUTEUR);
	}

	private static void nv4(float posX, float posY) {
		posX -= ArmesDeBase.DEMI_LARGEUR;
		ArmesDeBase a = ArmesDeBase.pool.obtain();
		a.init(posX - ArmesDeBase.DEMI_LARGEUR, posY);
		ArmesDeBase b = ArmesDeBase.pool.obtain();
		b.init(posX + ArmesDeBase.DEMI_LARGEUR, posY);
		ArmesDeBase c = ArmesDeBase.pool.obtain();
		c.init(posX - ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR);
		ArmesDeBase d = ArmesDeBase.pool.obtain();
		d.init(posX + ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR);
	}

	private static void nv3(float posX, float posY) {
		posX -= ArmesDeBase.DEMI_LARGEUR;
		ArmesDeBase a = ArmesDeBase.pool.obtain();
		a.init(posX - ArmesDeBase.LARGEUR, posY);
		ArmesDeBase b = ArmesDeBase.pool.obtain();
		b.init(posX + ArmesDeBase.LARGEUR, posY);
		ArmesDeBase c = ArmesDeBase.pool.obtain();
		c.init(posX, posY + ArmesDeBase.DEMI_HAUTEUR);
	}

	private static void nv2(float posX, float posY) {
		posX -= ArmesDeBase.DEMI_LARGEUR;
		ArmesDeBase a = ArmesDeBase.pool.obtain();
		a.init(posX - ArmesDeBase.DEMI_LARGEUR, posY);
		ArmesDeBase b = ArmesDeBase.pool.obtain();
		b.init(posX + ArmesDeBase.DEMI_LARGEUR, posY);
	}

	private static void nv1(float posX, float posY) {
		posX -= ArmesDeBase.DEMI_LARGEUR;
		ArmesDeBase e = ArmesDeBase.pool.obtain();
		e.init(posX, posY);
		e.getDirection().y = 1;
	}
}
