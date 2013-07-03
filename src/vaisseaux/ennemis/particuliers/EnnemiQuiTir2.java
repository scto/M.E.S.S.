package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeBossQuad;
import vaisseaux.armes.ArmeFusee;
import vaisseaux.armes.Armes;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import vaisseaux.tirs.Tirs;
import assets.SoundMan;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiQuiTir2 extends Ennemis implements Tireur {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 9;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Tirs TIR = new Tirs(0.7f);
	// ** ** caracteristiques variables.
	private float prochainTir = .1f;
	private float maintenant = 0;
	public static Pool<EnnemiQuiTir2> pool = Pools.get(EnnemiQuiTir2.class);
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
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR2)
			return mauvaisEtat;
		return bonEtat;
	}
	
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
		tpsAnimationExplosion = 0;
		if(CSG.profil.particules & explosion == null) explosion = ParticulesExplosionPetite.pool.obtain();
	}
	
	@Override
	public void reset() {
		position.x = Physique.getEmplacementX(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		mort = false;
		pv = Stats.PVMAX_DE_BASE_QUI_TIR2;
		prochainTir = .2f;
	}

	public EnnemiQuiTir2() {
		super(Physique.getEmplacementX(DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + HAUTEUR, Stats.PVMAX_DE_BASE_QUI_TIR2);
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
		position.y += (Stats.VITESSE_MAX_DE_BASE_QUI_TIR2 * Endless.delta);
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR2) position.x += (Stats.DERIVE_DE_BASE_QUI_TIR2 * Endless.delta);
			
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
		position.y += (Stats.VITESSE_MAX_DE_BASE_QUI_TIR2 * Endless.delta);
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR2) position.x += (Stats.DERIVE_DE_BASE_QUI_TIR2 * Endless.delta);
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
		else batch.draw(getTexture(pv), position.x, position.y, LARGEUR, HAUTEUR);
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
		}
		else batch.draw(getTexture(pv), position.x, position.y, LARGEUR, HAUTEUR);
	}
	
	@Override
	protected void tir() {
		TIR.tirVersBas(this, mort, maintenant, prochainTir);
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}

	@Override
	public int getXp() {		return TypesEnnemis.EnnemiQuiTir2.COUT;	}
	
	@Override
	public int getHauteur() {		return HAUTEUR;	}

	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getDemiHauteur() {		return DEMI_HAUTEUR;	}

	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}
	
	@Override
	public Armes getArme() {			return ArmeFusee.pool.obtain();	}
	
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}

	@Override
	public float getModifVitesse() {	return 1;	}

	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = position.x + DEMI_LARGEUR - ArmeFusee.DEMI_LARGEUR;
		tmpPos.y = position.y - ArmeFusee.LARGEUR;
		return tmpPos;
	}

}
