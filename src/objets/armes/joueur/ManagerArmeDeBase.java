package objets.armes.joueur;
import objets.joueur.VaisseauJoueur;
import menu.CSG;
import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class ManagerArmeDeBase extends ManagerArme {

	private static Sound son = Gdx.audio.newSound(Gdx.files.internal("sons/156895__halgrimm__shot-2-0.wav"));
	private static float posX, posY;

	public void init(){
		posX = VaisseauJoueur.centreX;
		posY = VaisseauJoueur.position.y + VaisseauJoueur.HAUTEUR;
		SoundMan.playTir(son);
		posX -= ArmesDeBase.DEMI_LARGEUR;
		switch (CSG.profil.NvArmeDeBase) {
		case 1:			nv1(posX, posY);			break;
		case 2:			nv2(posX, posY);			break;
		case 3:			nv3(posX, posY);			break;
		case 4:			nv4(posX, posY);			break;
		case 5:			nv5(posX, posY);			break;
		default:		nv6(posX, posY);			break;
		}
	}

	private static void nv6(float posX, float posY) {
		ArmesDeBase.pool.obtain().init(posX - ArmesDeBase.DEMI_LARGEUR, posY);
		ArmesDeBase.pool.obtain().init(posX + ArmesDeBase.DEMI_LARGEUR, posY);
		ArmesDeBase.pool.obtain().init(posX - ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR);
		ArmesDeBase.pool.obtain().init(posX + ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR);
		ArmesDeBase.pool.obtain().init(posX, posY + ArmesDeBase.DEMI_HAUTEUR);
		ArmesDeBase.pool.obtain().init(posX, posY - ArmesDeBase.DEMI_HAUTEUR);
	}

	private static void nv5(float posX, float posY) {
		ArmesDeBase.pool.obtain().init(posX - ArmesDeBase.DEMI_LARGEUR, posY);
		ArmesDeBase.pool.obtain().init(posX + ArmesDeBase.DEMI_LARGEUR, posY);
		ArmesDeBase.pool.obtain().init(posX - ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR);
		ArmesDeBase.pool.obtain().init(posX + ArmesDeBase.LARGEUR, posY - ArmesDeBase.DEMI_HAUTEUR);
		ArmesDeBase.pool.obtain().init(posX, posY + ArmesDeBase.DEMI_HAUTEUR);
	}

	private static void nv4(float posX, float posY) {
		ArmesDeBase.pool.obtain().init(posX - ArmesDeBase.DEMI_LARGEUR, posY);
		ArmesDeBase.pool.obtain().init(posX + ArmesDeBase.DEMI_LARGEUR, posY);
		ArmesDeBase.pool.obtain().init(posX - ArmesDeBase.LARGEUR, posY -ArmesDeBase.DEMI_HAUTEUR);
		ArmesDeBase.pool.obtain().init(posX + ArmesDeBase.LARGEUR, posY -ArmesDeBase.DEMI_HAUTEUR);
	}

	private static void nv3(float posX, float posY) {
		ArmesDeBase.pool.obtain().init(posX - ArmesDeBase.LARGEUR, posY);
		ArmesDeBase.pool.obtain().init(posX + ArmesDeBase.LARGEUR, posY);
		ArmesDeBase.pool.obtain().init(posX, posY + ArmesDeBase.DEMI_HAUTEUR);
	}

	private static void nv2(float posX, float posY) {
		ArmesDeBase.pool.obtain().init(posX + ArmesDeBase.DEMI_LARGEUR, posY);
		ArmesDeBase.pool.obtain().init(posX - ArmesDeBase.DEMI_LARGEUR, posY);
	}

	private static void nv1(float posX, float posY) {
		ArmesDeBase.pool.obtain().init(posX, posY);
	}
	
	@Override
	protected float getCadenceTir() {
		return ArmesDeBase.CADENCETIR;
	}
	@Override
	public String getLabel() {
		return ArmesDeBase.LABEL;
	}
}