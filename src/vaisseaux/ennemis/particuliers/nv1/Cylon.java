package vaisseaux.ennemis.particuliers.nv1;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.Armes;
import vaisseaux.armes.ArmesFragmentation;
import vaisseaux.armes.typeTir.TireurAngle;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.SoundMan;
import assets.animation.AnimationCylon;
import assets.animation.AnimationCylonCasse;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Cylon extends Ennemis implements TireurAngle {
	
	// ** ** caracteristiques g�n�rales
	public static final float LARGEUR = CSG.LARGEUR_ECRAN / 9;
	public static final float DEMI_LARGEUR = LARGEUR/2;
	private Vector2 direction = new Vector2(0,-1);
	public static Pool<Cylon> pool = Pools.get(Cylon.class);
	public static final float CADENCE = 3f;
	public static final Tirs tir = new Tirs(CADENCE);
	// ******************************************** T I R ********************************************************************
	private float prochainTir = 3f;
	private float angle = 0;
	// ** ** animations
	private ParticulesExplosionPetite explosion;
	// ** ** caracteristiques variables.

	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public Cylon() {
		super(Positionnement.getEmplacementX((int) DEMI_LARGEUR),	CSG.HAUTEUR_ECRAN + LARGEUR, Stats.PVMAX_CYLON);
		if(position.x + DEMI_LARGEUR < CSG.DEMI_LARGEUR_ECRAN)			direction.x = 0.26f;
		else 			direction.x = -0.26f;
		direction.y = -0.83f;
	}
	
	@Override
	protected Sound getSonExplosion() {
		return SoundMan.explosioncylon;
	}
	
	/**
	 * Initialise l'ennemi
	 */
	public void init() {
		if (CSG.profil.particules & explosion == null) explosion = ParticulesExplosionPetite.pool.obtain();
		else tpsAnimationExplosion = 0;
	}

	@Override
	public void reset() {
		position.x = (float) (Math.random() * CSG.LARGEUR_ECRAN - DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + LARGEUR;
		maintenant = 0;
		prochainTir = 2.6f;
		mort = false;
		pv = Stats.PVMAX_CYLON;
		angle = 30;
		if(position.x + DEMI_LARGEUR < CSG.DEMI_LARGEUR_ECRAN){
			direction.x = 0.26f;
		} else {
			direction.x = -0.26f;
		}
		direction.y = -0.83f;
	}

	@Override
	protected TextureRegion getTexture() {
		if (pv > Stats.DEMI_PV_CYLON) return AnimationCylon.getTexture(maintenant);
		else return AnimationCylonCasse.getTexture(maintenant);
	}

	@Override
	public boolean mouvementEtVerif() {
		if (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1 || Physique.toujoursAfficher(position, (int)LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.x += direction.x * Stats.VITESSE_CYLON * Endless.delta;
		position.y += direction.y * Stats.VITESSE_CYLON * Endless.delta;
		return true;
	}

	

	@Override
	protected void tir() {
		tir.tirToutDroit(this, mort, maintenant, prochainTir);
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, LARGEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiCylon.COUT;
	}

	@Override
	public int getHauteur() {		return (int)LARGEUR;	}

	@Override
	public int getLargeur() {		return (int)LARGEUR;	}

	@Override
	public int getDemiHauteur() {		return (int)DEMI_LARGEUR;	}

	@Override
	public int getDemiLargeur() {		return (int)DEMI_LARGEUR;	}
	
	@Override
	public Armes getArme() {			return ArmesFragmentation.pool.obtain();	}
	
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}

	@Override
	public float getModifVitesse() {	return 1;	}
	
	@Override
	public float getAngleTir() {			return angle;	}

	@Override
	public Vector2 getDirectionTir() {
		return direction;
	}
	
	@Override
	protected void free() {
		pool.free(this);
	}

	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = position.x + (direction.x * 15);
		tmpPos.y = position.y + (direction.y * 15);
		return tmpPos;
	}
	
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}

}
