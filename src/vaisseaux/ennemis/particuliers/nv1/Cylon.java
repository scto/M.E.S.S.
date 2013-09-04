package vaisseaux.ennemis.particuliers.nv1;

import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.ennemi.ArmeEnnemi;
import vaisseaux.armes.ennemi.ArmesFragmentation;
import vaisseaux.armes.typeTir.TireurAngle;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.Ennemis;
import assets.SoundMan;
import assets.animation.AnimationCylon;
import assets.animation.AnimationCylonCasse;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Cylon extends Ennemis implements TireurAngle {
	
	// ** ** caracteristiques g�n�rales
	public static final float LARGEUR = CSG.LARGEUR_ECRAN / 9;
	public static final float DEMI_LARGEUR = LARGEUR/2;
	private Vector2 direction = new Vector2(0,-1);
	public static Pool<Cylon> pool = Pools.get(Cylon.class);
	public static final float CADENCE = 3f, VITESSE = Stats.V_ENN_CYLON;
	public static final Tirs tir = new Tirs(CADENCE);
	// ******************************************** T I R ********************************************************************
	private float prochainTir = 3f;
	private float angle = 0;

	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public Cylon() {
		super();
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
		if (position.x + DEMI_LARGEUR < CSG.DEMI_LARGEUR_ECRAN)			direction.x = 0.26f * VITESSE;
		else 			direction.x = -0.26f * VITESSE;
		direction.y = -0.83f * VITESSE;
		
	}
	
	public void reset() {
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
		prochainTir = 2.6f;
		angle = 30;
		if (position.x + DEMI_LARGEUR < CSG.DEMI_LARGEUR_ECRAN){
			direction.x = 0.26f * VITESSE;
		} else {
			direction.x = -0.26f * VITESSE;
		}
		direction.y = -0.83f * VITESSE;
		super.reset();
	}


	protected TextureRegion getTexture() {
		if (pv > Stats.DEMI_PV_CYLON) return AnimationCylon.getTexture(maintenant);
		else return AnimationCylonCasse.getTexture(maintenant);
	}


	public boolean mouvementEtVerif() {
		Physique.mvtSansVerif(position, direction);
		return super.mouvementEtVerif();
	}


	protected void tir() {
		tir.tirToutDroit(this, mort, maintenant, prochainTir);
	}

	public int getXp() {
		return CoutsEnnemis.EnnemiCylon.COUT;
	}


	public int getHauteur() {		return (int)LARGEUR;	}


	public int getLargeur() {		return (int)LARGEUR;	}


	public int getDemiHauteur() {		return (int)DEMI_LARGEUR;	}


	public int getDemiLargeur() {		return (int)DEMI_LARGEUR;	}
	

	public ArmeEnnemi getArme() {			return ArmesFragmentation.pool.obtain();	}
	

	public void setProchainTir(float f) {		prochainTir = f;	}


	public float getModifVitesse() {		return 0.01f;	}
	

	public float getAngleTir() {				return angle;	}


	public Vector2 getDirectionTir() {			return direction;	}

	protected int getPvMax() {					return Stats.PV_CYLON;	}

	protected Sound getSonExplosion() {			return SoundMan.explosioncylon;	}

	protected void free() {
		pool.free(this);
	}


	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = position.x + (direction.x / 2);
		TMP_POS.y = position.y + (direction.y / 2);
		return TMP_POS;
	}
	

	public void invoquer() {
		LISTE.add(pool.obtain());
	}

	@Override
	public float getDirectionY() {
		return direction.y;
	}
	
	@Override
	public float getDirectionX() {
		return direction.x;
	}

}
