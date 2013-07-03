package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeAvion;
import vaisseaux.armes.ArmeBossQuad;
import vaisseaux.armes.Armes;
import vaisseaux.ennemis.TypesEnnemis;
import vaisseaux.tirs.Tirs;
import assets.SoundMan;
import assets.animation.AnimationAvion;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiAvion extends EnnemiDeBase implements DoubleTireur {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 5;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR/2;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final int offsetArmeGauche = DEMI_LARGEUR / 4;
	private static final int offsetArmeDroite = LARGEUR - (DEMI_LARGEUR / 3);
	public static final float CADENCE = .8f;
	public static final Tirs tir = new Tirs(CADENCE);
	// ** ** caracteristiques variables.
	private float prochainTir = 2f;
	private float maintenant = 0;
	public static Pool<EnnemiAvion> pool = Pools.get(EnnemiAvion.class);
	// ** ** particules
	private ParticulesExplosionPetite explosion;
	
	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosionennemidebasequitir);
		if(CSG.profil.particules){
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
			explosion.start();
		} else {
			tpsAnimationExplosion = 0;
		}
	}
	
	public void init() {
		if (CSG.profil.particules && explosion == null) explosion = ParticulesExplosionPetite.pool.obtain();
		else tpsAnimationExplosion = 0;
	}
	
	@Override
	public void reset() {
		position.x = Physique.getEmplacementX(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		mort = false;
		pv = Stats.PVMAX_AVION;
		prochainTir = 2.5f;
	}

	public EnnemiAvion() {
		super(Physique.getEmplacementX(DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + HAUTEUR, Stats.PVMAX_AVION);
	}


	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		if( (mort && explosion.isComplete()) | Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		position.y -= (Stats.VITESSE_AVION * Endless.delta);
		return true;
	}
	
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if( (mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) | Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.y -= (Stats.VITESSE_AVION * Endless.delta);
		return true;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficher(SpriteBatch batch) {
		maintenant += Endless.delta;
		if(mort){
			explosion.draw(batch, Endless.delta);
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
		} else {
			batch.draw(AnimationAvion.getTexture(pv), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimation += Endless.delta;
		}
	}
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		maintenant += Endless.delta;
		if(mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		} else {
			batch.draw(AnimationAvion.getTexture(pv), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimation += Endless.delta;
		}
	}
	
	@Override
	protected void tir() {
		tir.doubleTirVersBas(this, mort, maintenant, prochainTir);
	}


	@Override
	public int getXp() {		return TypesEnnemis.EnnemiAvion.COUT;	}
	
	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}
	
	@Override
	public int getHauteur() {			return HAUTEUR;	}

	@Override
	public int getLargeur() {			return LARGEUR;	}

	@Override
	public int getDemiHauteur() {		return DEMI_HAUTEUR;	}

	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}

	@Override
	public Armes getArme() {			return ArmeBossQuad.pool.obtain();	}
	
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}

	@Override
	public float getModifVitesse() {	return 1;	}
	
	@Override
	public float getXtir1() {
		return position.x - offsetArmeGauche;
	}

	@Override
	public float getXtir2() {
		return position.x + offsetArmeDroite;
	}

	@Override
	public float getYtirs() {
		return position.y;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		switch (numeroTir) {
		case 1: 
			tmpPos.x = position.x - ArmeBossQuad.DEMI_LARGEUR;
			tmpPos.y = position.y;
			break;
		case 2:
			tmpPos.x = position.x + LARGEUR - ArmeBossQuad.DEMI_LARGEUR;
			tmpPos.y = position.y;
			break;
		}
		return tmpPos;
	}

}
