package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeBossQuad;
import vaisseaux.armes.Armes;
import vaisseaux.armes.typeTir.Tireur;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.AssetMan;
import assets.SoundMan;
import assets.animation.AnimationBossQuad;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiBossQuad extends Ennemis implements Tireur {

	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR = (int) (CSG.LARGEUR_ECRAN / 2.6);
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR / 2;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2;
	private static final int DECALAGE_ARME_2 = (LARGEUR /6);
	private static final int DECALAGE_ARME_3 = (LARGEUR /3)*2;
	private static final int DECALAGE_ARME_EXTERIEUR_Y = HAUTEUR / 3;
	public static int PV_MAX_PHASE2;
	public static int PV_MIN_PHASE2;
	private static int PV_MAX;
	public static Tirs tirPhase1;
	public static Tirs tirPhase2;
	public static Tirs tirPhase3;
	// ** ** caracteristiques variables.
	private float prochainTir = .8f;
	private float maintenant = 0;
	private float tpsAnimationExplosion = 0;
	public static Pool<EnnemiBossQuad> pool = Pools.get(EnnemiBossQuad.class);
	// ** ** particules
	private ParticleEffect explosion;
	// direction
	private float dirY = 1;
	private float dirX = -2;
	// tourelles
	private boolean explosionTourellesCentre = false; 
	private float tpsExplosionTourellesCentre = 0;
	private int largeurExplosionTourelle = (int) (ArmeBossQuad.LARGEUR * 1.5);
	private ParticulesExplosionPetite explosionTourelle1;
	private ParticulesExplosionPetite explosionTourelle2;
	private int phase = 1;
	
	public static int divisionPv = 27;
	
	public static void setLevel(int level) {
		tirPhase1 = new Tirs(.9f - (0.09f * level));
		tirPhase2 = new Tirs(.5f - (0.05f * level));
		tirPhase3 = new Tirs(.3f - (0.03f * level));
		PV_MAX = Stats.PVMAX_BOSS_QUAD * level;
		PV_MAX_PHASE2 = PV_MAX / 3 * 2;
		PV_MIN_PHASE2 = PV_MAX / 3;
	}
	
	public EnnemiBossQuad() {
		super(0,0, PV_MAX);
		position.x = CSG.DEMI_LARGEUR_ECRAN - DEMI_LARGEUR;
		position.y = CSG.HAUTEUR_ECRAN;
		init();
	}
	
	@Override
	protected void mort() {
		Endless.effetBloom();
		SoundMan.playBruitage(SoundMan.explosionGrosse);
		if (CSG.profil.particules) {
			if (explosion == null) {
				explosion = new ParticleEffect(AssetMan.explosionGros);
				explosion.start();
			} else explosion.reset();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
		}
	}
	
	/**
	 * Ajoute � la liste
	 */
	public void init() {
		if (CSG.profil.particules){
			if (explosion == null) explosion = new ParticleEffect(AssetMan.explosionGros);
			explosionTourelle1 = ParticulesExplosionPetite.pool.obtain();
			explosionTourelle1 = ParticulesExplosionPetite.pool.obtain();
		} else {
			tpsAnimationExplosion = 0;
			tpsExplosionTourellesCentre = 0;
		}
	}

	@Override
	public void reset() {
		mort = false;	
		tpsAnimationExplosion = 0;
		pv = PV_MAX;
		phase = 1;
		prochainTir = .2f;
		maintenant = 0;
		init();
		dirY = 1;
		dirX = 2;
		position.x = CSG.DEMI_LARGEUR_ECRAN - DEMI_LARGEUR;
		position.y = CSG.HAUTEUR_ECRAN;
		explosionTourellesCentre = false;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		if( (mort && explosion.isComplete()) || Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			if (explosionTourelle1 != null) explosionTourelle1.free();
			if (explosionTourelle2 != null) explosionTourelle2.free();
			return false;
		}
		if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_2 | dirY < 1) 	position.y -= (dirY * Stats.VITESSE_BOSS_QUAD * Endless.delta);
		if (dirY < 1) 												dirY += 30 * Endless.delta;
		if (position.x + DEMI_LARGEUR > CSG.DEMI_LARGEUR_ZONE_JEU)	dirX -= 4 * Endless.delta;
		else														dirX += 4 * Endless.delta;
		position.x += dirX * Stats.VITESSE_BOSS_QUAD * Endless.delta;
		return true;
	}
	
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) | Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_2 | dirY < 1) 	position.y -= (dirY * Stats.VITESSE_BOSS_QUAD * Endless.delta);
		if (dirY < 1) 												dirY += 30 * Endless.delta;
		if (position.x + DEMI_LARGEUR > CSG.DEMI_LARGEUR_ZONE_JEU)	dirX -= 4 * Endless.delta;
		else														dirX += 4 * Endless.delta;
		position.x += dirX * Stats.VITESSE_BOSS_QUAD * Endless.delta;
		return true;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficher(SpriteBatch batch) {
		if (mort) {
			explosion.draw(batch, Endless.delta);
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
		} else {
			batch.draw(AnimationBossQuad.getTexture(phase), position.x, position.y, LARGEUR, HAUTEUR);
			maintenant += Endless.delta;
		}
		if (explosionTourellesCentre && explosionTourelle1 != null && explosionTourelle2 != null && !explosionTourelle1.isComplete() && !explosionTourelle2.isComplete()) {
			explosionTourelle1.draw(batch, Endless.delta);
			explosionTourelle1.setPosition( position.x + DECALAGE_ARME_2 + ArmeBossQuad.DEMI_LARGEUR, position.y + ArmeBossQuad.DEMI_HAUTEUR);
			
			explosionTourelle2.draw(batch, Endless.delta);
			explosionTourelle2.setPosition( position.x + DECALAGE_ARME_3+ ArmeBossQuad.DEMI_LARGEUR, position.y + ArmeBossQuad.DEMI_HAUTEUR);
		}
	}
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if(mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnimationExplosion += Endless.delta;
		} else {
			batch.draw(AnimationBossQuad.getTexture(phase), position.x, position.y, LARGEUR, HAUTEUR);
			maintenant += Endless.delta;
		}
		if (explosionTourellesCentre && tpsExplosionTourellesCentre < AnimationExplosion1.tpsTotalAnimationExplosion1) {
			batch.draw(AnimationExplosion1.getTexture(tpsExplosionTourellesCentre), position.x + DECALAGE_ARME_3, position.y , largeurExplosionTourelle, largeurExplosionTourelle);
			batch.draw(AnimationExplosion1.getTexture(tpsExplosionTourellesCentre), position.x + DECALAGE_ARME_2, position.y , largeurExplosionTourelle, largeurExplosionTourelle);
			tpsExplosionTourellesCentre += Endless.delta;
		}
	}
	
	@Override
	protected void tir() {
		switch(phase) {
		case 1:			tirPhase1.tirMultiplesVersBas(this, 4, mort, maintenant, prochainTir);			break;
		case 2:			tirPhase2.tirMultiplesVersBas(this, 2, mort, maintenant, prochainTir);			break;
		case 3:			tirPhase3.tirMultiplesVersBas(this, 2, mort, maintenant, prochainTir);			break;
		}
	}
	
	
	@Override
	public boolean touche(int force) {
		// si mes pvs sont inf�rieurs � �a je suis en phase 2 ou 3
		if (pv < PV_MAX_PHASE2 ) { 
			if (pv > PV_MIN_PHASE2) {
				if (explosionTourellesCentre == false) {
					explosionTourellesCentre = true;
					if (CSG.profil.particules) {
						if (explosionTourelle1 == null){
							explosionTourelle1 = ParticulesExplosionPetite.pool.obtain();
							explosionTourelle1.start();
						} else explosionTourelle1.reset();
						explosionTourelle1.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
						
						if (explosionTourelle2 == null){
							explosionTourelle2 = ParticulesExplosionPetite.pool.obtain();
							explosionTourelle2.start();
						} else explosionTourelle2.reset();
						explosionTourelle2.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
					}
				}
				phase = 2;
			} else {
				phase = 3;
			}
		} 
		return super.touche(force);
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiBossQuad.COUT;
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
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}
	
	@Override
	public Armes getArme() {			return ArmeBossQuad.pool.obtain();	}
	
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}

	@Override
	public float getModifVitesse() {	return 1;	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		switch (numeroTir) {
		// Attention on donne en premier les exterieurs 
		case 1:
			SoundMan.playBruitage(SoundMan.tirRocket);
			dirY = -1.2f;
			tmpPos.x = position.x;
			tmpPos.y = position.y - DECALAGE_ARME_EXTERIEUR_Y;
			break;
		case 2:
			tmpPos.x = position.x + LARGEUR - ArmeBossQuad.LARGEUR;
			tmpPos.y = position.y - DECALAGE_ARME_EXTERIEUR_Y;
			break;
		case 3:
			tmpPos.x = position.x + DECALAGE_ARME_3;
			tmpPos.y = position.y - ArmeBossQuad.DEMI_HAUTEUR;
			break;
		case 4:
			tmpPos.x = position.x + DECALAGE_ARME_2;
			tmpPos.y = position.y - ArmeBossQuad.DEMI_HAUTEUR;
			break;
		default:
			tmpPos.x = position.x + DEMI_LARGEUR - ArmeBossQuad.DEMI_LARGEUR;
			tmpPos.y = position.y - ArmeBossQuad.DEMI_HAUTEUR;
			break;
		}
		return tmpPos;
	}
	
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
}

