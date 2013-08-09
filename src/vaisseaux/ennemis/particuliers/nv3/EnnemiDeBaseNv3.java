package vaisseaux.ennemis.particuliers.nv3;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.SoundMan;
import assets.animation.AnimationEnnemiDeBase;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * Ennemi de base, g�re son pool, va tout droit et ni tire pas.
 * @author Julien
 *
 */
public class EnnemiDeBaseNv3 extends Ennemis{
	
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 13;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR/2;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static Pool<EnnemiDeBaseNv3> pool = Pools.get(EnnemiDeBaseNv3.class);
	// ** ** animations
	protected float tpsAnimation;
	protected float tpsAnimationExplosion;
	private ParticulesExplosionPetite explosion;
	// ** ** moins al�atoire
	private static int nbEnnemisAvant = 0;
	private static float posXInitiale;
	
	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosiontoupie);
		if (CSG.profil.particules){
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
			explosion.start();
		} else {
			tpsAnimationExplosion = 0;
		}
	}


	public EnnemiDeBaseNv3() {
		super(CSG.LARGEUR_BORD,	CSG.HAUTEUR_ECRAN + HAUTEUR, Stats.PVMAX_DE_BASE_NV3);
		setPosX();
	}

	private void setPosX() {
		if (nbEnnemisAvant == 0) {
			posXInitiale = Physique.getEmplacementX(DEMI_LARGEUR);
			position.x = posXInitiale;
		}
		else
			if (posXInitiale + DEMI_LARGEUR > CSG.DEMI_LARGEUR_ZONE_JEU)	position.x = posXInitiale - LARGEUR * nbEnnemisAvant;
			else															position.x = posXInitiale + LARGEUR * nbEnnemisAvant;
		nbEnnemisAvant++;
		if (nbEnnemisAvant > 6) nbEnnemisAvant = 0;
	}
	
	public EnnemiDeBaseNv3(float emplacementX, int i, int pvmaxAvion) {
		super(emplacementX, i, pvmaxAvion);
	}

	/**
	 * Initialise l'ennemi
	 */
	public void init() {
		tpsAnimationExplosion = 0;
		if(CSG.profil.particules & explosion == null)	explosion = ParticulesExplosionPetite.pool.obtain();
	}

	@Override
	public void reset() {
		setPosX();
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		mort = false;
		if (!CSG.profil.particules)		tpsAnimationExplosion = 0;
		pv = Stats.PVMAX_DE_BASE_NV3;
	}

	@Override
	public void afficher(SpriteBatch batch) {
		if(mort){
			explosion.draw(batch, Endless.delta);
		}
		else{
			batch.draw(AnimationEnnemiDeBase.getTexture(tpsAnimation), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimation += Endless.delta;
		}
	}
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if(mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		}
		else{
			batch.draw(AnimationEnnemiDeBase.getTexture(tpsAnimation), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimation += Endless.delta;
		}
	}

	@Override
	public boolean mouvementEtVerif() {
		if( (mort && explosion.isComplete()) | Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		position.y -= (Stats.VITESSE_MAX_DE_BASE_NV3 * Endless.delta);
		return true;
	}
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if( (mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) | Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.y -= (Stats.VITESSE_MAX_DE_BASE_NV3 * Endless.delta);
		return true;
	}


	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiDeBaseNv3.COUT;
	}

	@Override
	public int getHauteur() {
		return HAUTEUR;
	}

	@Override
	public int getLargeur() {
		return LARGEUR;
	}
	
	@Override
	public int getDemiHauteur() {
		return DEMI_HAUTEUR;
	}

	@Override
	public int getDemiLargeur() {
		return DEMI_LARGEUR;
	}
	
	@Override
	public void invoquer() {		liste.add(pool.obtain());	}
}
