package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeBossQuad;
import vaisseaux.armes.Armes;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import assets.SoundMan;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiQuiTir extends Ennemis implements Tireur{
	
	// ** ** caracteristiques g�n�rales
	public static final float LARGEUR= CSG.LARGEUR_ECRAN / 15;
	public static final float DEMI_LARGEUR = LARGEUR/2;
	public static final float HAUTEUR = LARGEUR + DEMI_LARGEUR;
	public static final float DEMI_HAUTEUR = HAUTEUR / 2;
	public static final float CADENCE = 1.2f;
	public static final Tirs tir = new Tirs(CADENCE);
	// ** ** caracteristiques variables.
	private float prochainTir = .1f;
	private float maintenant = 0;
	public static Pool<EnnemiQuiTir> pool = Pools.get(EnnemiQuiTir.class);
	// ** ** particules
	private ParticulesExplosionPetite explosion;
	protected float tpsAnimationExplosion;
	
	// Animation
	public static AtlasRegion bonEtat;
	public static AtlasRegion mauvaisEtat;
	
	public static void initAnimation(){
		bonEtat = CSG.getAssetMan().getAtlas().findRegion("fusee");
		mauvaisEtat = CSG.getAssetMan().getAtlas().findRegion("fuseeamochee");
	}
	
	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR)
			return mauvaisEtat;
		return bonEtat;
	}
	
	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosionennemidebasequitir);
		if (CSG.profil.particules){
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
			explosion.start();
		} else {
			tpsAnimationExplosion = 0;
		}
	}
	
	public void init() {
		if (CSG.profil.particules & explosion == null) explosion = ParticulesExplosionPetite.pool.obtain();
		else tpsAnimationExplosion = 0;
	}
	
	@Override
	public void reset() {
		position.x = Physique.getEmplacementX((int) DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		mort = false;
		pv = Stats.PVMAX_DE_BASE_QUI_TIR;
		prochainTir = .2f;
	}

	public EnnemiQuiTir() {
		super(Physique.getEmplacementX((int) DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + HAUTEUR, Stats.PVMAX_DE_BASE_QUI_TIR);
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		if( (mort && explosion.isComplete()) | Physique.toujoursAfficher(position, (int)HAUTEUR, (int)LARGEUR) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		position.y += (Stats.VITESSE_MAX_DE_BASE_QUI_TIR * Endless.delta);
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR) position.x += (Stats.DERIVE_DE_BASE_QUI_TIR * Endless.delta);
		return true;
	}
	
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) | Physique.toujoursAfficher(position, (int)HAUTEUR, (int)LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.y += (Stats.VITESSE_MAX_DE_BASE_QUI_TIR * Endless.delta);
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR) position.x += (Stats.DERIVE_DE_BASE_QUI_TIR * Endless.delta);
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
		}
		else	batch.draw(getTexture(pv), position.x, position.y, LARGEUR, HAUTEUR);
	}
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		maintenant += Endless.delta;
		if (mort) {
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		}
		else	batch.draw(getTexture(pv), position.x, position.y, LARGEUR, HAUTEUR);
	}
	
	@Override
	protected void tir() {
		tir.tirVersBas(this, mort, maintenant, prochainTir);
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return TypesEnnemis.EnnemiDeBaseQuiTir.COUT;
	}
	
	@Override
	public int getHauteur() {			return (int)HAUTEUR;	}

	@Override
	public int getLargeur() {			return (int)LARGEUR;	}

	@Override
	public int getDemiHauteur() {		return (int)DEMI_HAUTEUR;	}

	@Override
	public int getDemiLargeur() {		return (int)DEMI_LARGEUR;	}

	@Override
	public Armes getArme() {			return ArmeBossQuad.pool.obtain();	}
	
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}

	@Override
	public float getModifVitesse() {	return 1;	}

	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = position.x + DEMI_LARGEUR - ArmeBossQuad.DEMI_LARGEUR;
		tmpPos.y = position.y - ArmeBossQuad.HAUTEUR;
		return tmpPos;
	}

}
