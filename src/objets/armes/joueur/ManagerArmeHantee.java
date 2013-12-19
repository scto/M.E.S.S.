package objets.armes.joueur;

import jeu.CSG;
import objets.joueur.VaisseauJoueur;
import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class ManagerArmeHantee extends ManagerArme {
	
	private static Sound son = Gdx.audio.newSound(Gdx.files.internal("sons/armehantee.wav"));
	private static float posX, posY;
	
	public void init(){
		posX = VaisseauJoueur.centreX - ArmeHantee.DEMI_LARGEUR;
		posY = VaisseauJoueur.position.y + VaisseauJoueur.HAUTEUR;
		SoundMan.playTir(son);
		switch (CSG.profil.NvArmeHantee) {
			case 1:				nv1();			break;
			case 2:				nv2();				break;
			case 3:				nv3();				break;
			case 4:				nv4();				break;
			case 5:				nv5();				break;
			default:			nv6();				break;
		}
	}

	private static void nv6() {
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		ArmeHantee.pool.obtain().init(posX, posY + ArmeHantee.DEMI_LARGEUR);
		ArmeHantee.pool.obtain().init(posX, posY - ArmeHantee.DEMI_LARGEUR);
	}

	private static void nv5() {
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		ArmeHantee.pool.obtain().init(posX, posY + ArmeHantee.DEMI_LARGEUR);
	}

	private static void nv4() {
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.LARGEUR, posY - ArmeHantee.DEMI_LARGEUR);
	}

	private static void nv3() {
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX, posY + ArmeHantee.DEMI_LARGEUR);
	}

	private static void nv2() {
		ArmeHantee.pool.obtain().init(posX - ArmeHantee.DEMI_LARGEUR, posY);
		ArmeHantee.pool.obtain().init(posX + ArmeHantee.DEMI_LARGEUR, posY);
	}

	private static void nv1() {
		ArmeHantee.pool.obtain().init(posX, posY);
	}

	@Override
	protected float getCadenceTir() {
		return ArmeHantee.CADENCETIR;
	}
	@Override
	public String getLabel() {
		return ArmeHantee.LABEL;
	}

	@Override
	public int nv() {
		return CSG.profil.NvArmeHantee;
	}
}
