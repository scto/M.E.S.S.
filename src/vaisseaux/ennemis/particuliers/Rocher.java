package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.ennemis.Ennemis;
import assets.AssetMan;
import assets.SoundMan;
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
public class Rocher extends Ennemis{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR_DE_BASE = CSG.LARGEUR_ECRAN / 10;
	public int largeur;
	public int demiLargeur; 
	public static Pool<Rocher> pool = Pools.get(Rocher.class);
	// ** ** animations
	protected float tpsAnimationExplosion;
	private ParticulesExplosionPetite explosion;
	private float angle;
	private float vitesse;

	/** 
	 * Appel� par les sous classes
	 * @param posX
	 * @param posY
	 * @param dirX
	 * @param dirY
	 * @param pv
	 */
	public Rocher(float posX, float posY, int pv) {
		super(posX, posY, pv);
		initDimensionsEtPosition();
	}
	
	private void initDimensionsEtPosition() {
		largeur = (int) ((Math.random() + .5) * LARGEUR_DE_BASE);
		demiLargeur = largeur / 2;
		angle = largeur;
		position.x = getRandX();
		position.y = CSG.HAUTEUR_ECRAN + largeur;
		pv = largeur * 15;
		vitesse = (CSG.DIXIEME_HAUTEUR - demiLargeur);
	}

	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosiontoupie);
		if(CSG.profil.particules){
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.setPosition(position.x + demiLargeur, position.y + demiLargeur);
			explosion.start();
		} else {
			tpsAnimationExplosion = 0;
		}
	}

	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public Rocher() {
		super(CSG.LARGEUR_BORD,	CSG.HAUTEUR_ECRAN + LARGEUR_DE_BASE, Stats.PVMAX_DE_BASE);
		initDimensionsEtPosition();
		liste.add(this);
	}
	
	private float getRandX() {
		if (Math.random() > .5f)			return CSG.LARGEUR_ZONE_JEU - largeur;
		return 0;
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
		initDimensionsEtPosition();
		mort = false;
		if (!CSG.profil.particules)		tpsAnimationExplosion = 0;
	}

	@Override
	public void afficher(SpriteBatch batch) {
		if(mort){
			explosion.draw(batch, Endless.delta);
		} else {
			batch.draw(AssetMan.rocher , position.x, position.y, demiLargeur, demiLargeur, largeur, largeur, 1, 1, angle, false);
		}
	}
	
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if(mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, largeur, largeur);
			tpsAnimationExplosion += Endless.delta;
		} else {
			batch.draw(AssetMan.rocher , position.x, position.y, demiLargeur, demiLargeur, largeur, largeur, 1, 1, angle, false);
		}
	}

	@Override
	public boolean mouvementEtVerif() {
		if( (mort && explosion.isComplete()) | Physique.toujoursAfficher(position, largeur, largeur) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		angle += vitesse * Endless.delta;
		position.y -= (vitesse * Endless.delta);
		return true;
	}
	
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if( (mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) | Physique.toujoursAfficher(position, largeur, largeur) == false){
			pool.free(this);
			return false;
		}
		angle += vitesse * Endless.delta;
		position.y -= (vitesse * Endless.delta);
		return true;
	}


	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, largeur, largeur);
		return collision;
	}

	@Override
	public int getXp() {
		return ((largeur / CSG.DIXIEME_LARGEUR)*3) + 1;
	}

	@Override
	public int getHauteur() {
		return largeur;
	}

	@Override
	public int getLargeur() {
		return largeur;
	}
	
	@Override
	public int getDemiHauteur() {
		return demiLargeur;
	}

	@Override
	public int getDemiLargeur() {
		return demiLargeur;
	}
	
}
