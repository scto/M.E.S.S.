package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeInsecte;
import vaisseaux.armes.Armes;
import vaisseaux.armes.ArmesBouleVerte;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import vaisseaux.tirs.Tirs;
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


public class EnnemiInsecte extends Ennemis implements TireurAngle {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 4;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (LARGEUR / 2) + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Tirs tir = new Tirs(.7f);
	private static final Vector2 tmpVector = new Vector2();
	private float prochainTir = 1f;
	private float maintenant = 0;
	public static Pool<EnnemiInsecte> pool = Pools.get(EnnemiInsecte.class);
	private boolean tirGauche = true;
	private float impulsion;
	private float angle = 0;
	private Vector2 direction = new Vector2();
	// ** ** particules
	private ParticulesExplosionPetite explosion;
	protected float tpsAnimationExplosion;
	
	// Animation
	public static AtlasRegion bonEtat;
	public static AtlasRegion mauvaisEtat;
	
	public static void initAnimation(){
		bonEtat = CSG.getAssetMan().getAtlas().findRegion("insecte");
		mauvaisEtat = CSG.getAssetMan().getAtlas().findRegion("insectecasse");
	}
	
	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.PVMAX_INSECTE_DEMI)	return mauvaisEtat;
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
		mort = false;
		pv = Stats.PVMAX_INSECTE;
		prochainTir = .2f;
		initPlacement();
	}

	public EnnemiInsecte() {
		super(Physique.getEmplacementX(DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + HAUTEUR, Stats.PVMAX_INSECTE);
		initPlacement();
	}

	private void initPlacement() {
		angle = 90;
		impulsion = -10;
		position.y = CSG.HAUTEUR_ECRAN_PALLIER_3;
		if (Math.random() > .5) {
			direction.x = -1;
			position.x = CSG.LARGEUR_ZONE_JEU;
		} else {
			direction.x = 1;
			position.x = -LARGEUR;
		}
		direction.y = 0;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		if ( (mort && explosion.isComplete()) || Physique.mouvementDeBase(direction, position, Stats.VITESSE_ENNEMI_INSECTE, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		direction.rotate(impulsion);
		angle += impulsion;
		impulsion /= 1.2f;
		return true;
	}
	
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) || Physique.mouvementDeBase(direction, position, Stats.VITESSE_ENNEMI_INSECTE, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		direction.rotate(impulsion);
		angle += impulsion;
		impulsion /= 1.2f;
		return true;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficher(SpriteBatch batch) {
		maintenant += Endless.delta;
		if (mort) {
			explosion.draw(batch, Endless.delta);
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
		} else	batch.draw(getTexture(pv), position.x, position.y,	DEMI_LARGEUR,DEMI_LARGEUR, LARGEUR, LARGEUR, 1,1, angle, false);
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
		else	batch.draw(getTexture(pv), position.x, position.y,	DEMI_LARGEUR,DEMI_LARGEUR, LARGEUR, LARGEUR, 1,1, angle, false);
	}
	
	@Override
	protected void tir() {
		tir.tirToutDroit(this, mort, maintenant, prochainTir);
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return TypesEnnemis.EnnemiInsecte.COUT;
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
	public Armes getArme() {			return ArmeInsecte.pool.obtain();	}

	@Override
	public void setProchainTir(float f) {
		if (tirGauche) impulsion = 10;
		else impulsion = -10;
		tirGauche = !tirGauche;
		prochainTir = f;
	}

	@Override
	public float getModifVitesse() {	return 1;	}

	@Override
	public float getAngleTir() {			return angle;	}
	
	@Override
	public Vector2 getDirectionTir() {
		return tmpVector;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		majVecteurTir();
		if (!tirGauche) {
			tmpPos.x = (position.x) + (tmpVector.x * 16);
			tmpPos.y = (position.y + ArmeInsecte.DEMI_HAUTEUR)+ (tmpVector.y * 16);
		} else {
			tmpPos.x = (position.x + DEMI_LARGEUR) + (tmpVector.x * 16);
			tmpPos.y = (position.y - ArmeInsecte.DEMI_HAUTEUR) + (tmpVector.y * 16);
		}
		return tmpPos;
	}

	private void majVecteurTir() {
		tmpVector.x = direction.x;
		tmpVector.y = direction.y;
		if (direction.x < 0) tmpVector.rotate(90);
		else tmpVector.rotate(-90);
	}
}
