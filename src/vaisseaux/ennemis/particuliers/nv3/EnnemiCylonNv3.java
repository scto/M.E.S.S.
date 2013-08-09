package vaisseaux.ennemis.particuliers.nv3;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmesFragmentation;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.SoundMan;
import assets.animation.AnimationCylon;
import assets.animation.AnimationCylonCasse;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class EnnemiCylonNv3 extends Ennemis{
	
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 8;
	public static final int DEMI_LARGEUR = LARGEUR / 2;
	private Vector2 direction = new Vector2(0,-1);
	public static Pool<EnnemiCylonNv3> pool = Pools.get(EnnemiCylonNv3.class);
	// ******************************************** T I R ********************************************************************
	public float qdEnnemiATire = 0;
	public static final float CADENCE_TIR = 2.5f;
	private float angle = 0;
	// ** ** animations
	protected float tpsAnimation;
	protected float tpsAnimationExplosion;
	private ParticulesExplosionPetite explosion;
	// ** ** caracteristiques variables.

	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosioncylon);
		if (CSG.profil.particules) {
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR);
			explosion.start();
		} else {
			tpsAnimationExplosion = 0;
		}
	}
	
	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public EnnemiCylonNv3() {
		super((float) (Math.random() * CSG.LARGEUR_ECRAN - DEMI_LARGEUR),	CSG.HAUTEUR_ECRAN + LARGEUR, Stats.PVMAX_CYLON3);
		if (position.x + DEMI_LARGEUR < CSG.DEMI_LARGEUR_ECRAN)			direction.x = 0.26f;
		else 			direction.x = -0.26f;
		direction.y = -0.83f;
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
		tpsAnimation = 0;
		qdEnnemiATire = 0;
		mort = false;
		pv = Stats.PVMAX_CYLON3;
		angle = 30;
		if (position.x + DEMI_LARGEUR < CSG.DEMI_LARGEUR_ECRAN){
			direction.x = 0.26f;
		} else {
			direction.x = -0.26f;
		}
		direction.y = -0.83f;
	}

	@Override
	public void afficher(SpriteBatch batch) {
		if (mort){
			explosion.draw(batch, Endless.delta);
		} else {
			if (pv > Stats.DEMI_PV_CYLON3) batch.draw(AnimationCylon.getTexture(tpsAnimation), position.x, position.y, LARGEUR, LARGEUR);
			else batch.draw(AnimationCylonCasse.getTexture(tpsAnimation), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnimation += Endless.delta;
		}
	}
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if (mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnimationExplosion += Endless.delta;
		} else {
			if (pv > Stats.DEMI_PV_CYLON3) batch.draw(AnimationCylon.getTexture(tpsAnimation), position.x, position.y, LARGEUR, LARGEUR);
			else batch.draw(AnimationCylonCasse.getTexture(tpsAnimation), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnimation += Endless.delta;
		}
	}

	@Override
	public boolean mouvementEtVerif() {
		if ((mort && explosion.isComplete()) | Physique.toujoursAfficher(position, LARGEUR) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		position.x += direction.x * Stats.VITESSE_CYLON3 * Endless.delta;
		position.y += direction.y * Stats.VITESSE_CYLON3 * Endless.delta;
		return true;
	}
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1 || Physique.toujoursAfficher(position, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.x += direction.x * Stats.VITESSE_CYLON3 * Endless.delta;
		position.y += direction.y * Stats.VITESSE_CYLON3 * Endless.delta;
		return true;
	}

	

	@Override
	protected void tir() {
		if (tpsAnimation > qdEnnemiATire + CADENCE_TIR){
			ArmesFragmentation a = ArmesFragmentation.pool.obtain();
//			a.init(position.x + DEMI_LARGEUR - ArmesFragmentation.DEMI_LARGEUR,	position.y - ArmesFragmentation.DEMI_LARGEUR, direction.x, direction.y, angle);
			if (tpsAnimation > qdEnnemiATire + CADENCE_TIR)				qdEnnemiATire = tpsAnimation;
		}
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, LARGEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiCylonNv3.COUT;
	}

	@Override
	public int getHauteur() {		return LARGEUR;	}

	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getDemiHauteur() {		return DEMI_LARGEUR;	}

	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}
	
	@Override
	public void invoquer() {		liste.add(pool.obtain());	}
}
