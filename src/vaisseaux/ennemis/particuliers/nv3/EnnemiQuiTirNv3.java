package vaisseaux.ennemis.particuliers.nv3;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeBossQuad;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.EnnemiQuiTir;
import assets.SoundMan;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiQuiTirNv3 extends Ennemis{
	
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 13;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	// ** ** caracteristiques variables.
	private float dernierTir = .2f;
	private float maintenant = 0;
	public static Pool<EnnemiQuiTirNv3> pool = Pools.get(EnnemiQuiTirNv3.class);
	// ** ** particules
	private ParticulesExplosionPetite explosion;
	protected float tpsAnimationExplosion;
	
	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR3)
			return EnnemiQuiTir.mauvaisEtat;
		return EnnemiQuiTir.bonEtat;
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
		if(CSG.profil.particules & explosion == null) explosion = ParticulesExplosionPetite.pool.obtain();
		else tpsAnimationExplosion = 0;
	}
	
	@Override
	public void reset() {
		position.x = Physique.getEmplacementX(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		mort = false;
		pv = Stats.PVMAX_DE_BASE_QUI_TIR;
		dernierTir = .2f;
	}

	public EnnemiQuiTirNv3() {
		super(Physique.getEmplacementX(DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + HAUTEUR, Stats.PVMAX_DE_BASE_QUI_TIR);
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
		position.y += (Stats.VITESSE_MAX_DE_BASE_QUI_TIR3 * Endless.delta);
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR3) position.x += (Stats.DERIVE_DE_BASE_QUI_TIR * Endless.delta);
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
		position.y += (Stats.VITESSE_MAX_DE_BASE_QUI_TIR3 * Endless.delta);
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR3) position.x += (Stats.DERIVE_DE_BASE_QUI_TIR * Endless.delta);
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
		if(mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		}
		else	batch.draw(getTexture(pv), position.x, position.y, LARGEUR, HAUTEUR);
	}
	
	@Override
	protected void tir() {
		if (!mort && maintenant > dernierTir	+ ArmeBossQuad.CADENCETIR) {
			ArmeBossQuad gauche = ArmeBossQuad.pool.obtain();
			gauche.init(position.x - ArmeBossQuad.DEMI_LARGEUR, position.y - ArmeBossQuad.HAUTEUR, 1);
			ArmeBossQuad droite = ArmeBossQuad.pool.obtain();
			droite.init(position.x + LARGEUR - ArmeBossQuad.DEMI_LARGEUR, position.y - ArmeBossQuad.HAUTEUR, 1);
			dernierTir = maintenant + ArmeBossQuad.CADENCETIR + ArmeBossQuad.CADENCETIR;
		}
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiDeBaseQuiTirNv3.COUT;
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
