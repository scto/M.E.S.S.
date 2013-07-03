package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeKinder;
import vaisseaux.armes.Armes;
import vaisseaux.armes.ArmesBouleVerte;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import vaisseaux.tirs.Tirs;
import assets.SoundMan;
import assets.animation.AnimationExplosion1;
import assets.animation.AnimationKinder;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiKinder extends Ennemis implements TireurAngle {

	// ** ** caracteristiques gï¿½nï¿½rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 8;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR - DEMI_LARGEUR / 2;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final Tirs tir = new Tirs(.4f);
	// ** ** caracteristiques variables.
	private float prochainTir = 1f;
	private float maintenant = 0;
	private float tpsAnimationExplosion = 0;
	public static Pool<EnnemiKinder> pool = Pools.get(EnnemiKinder.class);
	// ** ** particules
	private ParticulesExplosionPetite explosion;
	// direction
	private Vector2 direction = new Vector2();
	private float angle = 0;
	private Vector2 tmpDirectionTir = new Vector2();
	
	public EnnemiKinder() {
		super(0,0, Stats.PVMAX_KINDER);
		init();
	}
	
	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosionkinder);
		if (CSG.profil.particules) {
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y	+ DEMI_HAUTEUR);
			explosion.start();
		} else {
			tpsAnimationExplosion = 0;
		}
	}
	
	/**
	 * Ajoute ï¿½ la liste
	 */
	public void init() {
		if (CSG.profil.particules & explosion == null) explosion = ParticulesExplosionPetite.pool.obtain();
		else tpsAnimationExplosion = 0;
		randPositionEtDirection();
	}
	
	public static double rand;
	
	private void randPositionEtDirection() {
		rand = Math.random();
		if (rand > 0.5){ // on est a droite
			position.x = CSG.LARGEUR_ZONE_JEU-1;
			position.y = (float) (CSG.HAUTEUR_ECRAN - (rand * CSG.DEMI_HAUTEUR_ECRAN));
			direction.x = 0;
			direction.y = -1.1f;
			direction.rotate((float) ((( (rand + rand) - 1) * 55) + 55) );
		} else {
			position.x = -LARGEUR+1;
			position.y = (float) (CSG.HAUTEUR_ECRAN - ((rand+rand) * CSG.DEMI_HAUTEUR_ECRAN));
			direction.x = 0;
			direction.y = -1;
			direction.rotate((float) -((( (rand + rand)) * 90) + 45) );
		}
		angle = direction.angle();
	}

	@Override
	public void reset() {
		mort = false;
		pv = Stats.PVMAX_KINDER;
		prochainTir = .2f;
		maintenant = 0;
		init();
	}

	/**
	 * Exactement la mï¿½me que dans la super classe mais ï¿½a ï¿½vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		if( (mort && explosion.isComplete()) | Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		if (maintenant < AnimationKinder.TPS_ANIM_OUVERT || maintenant > 12) {
			position.y -= (direction.y * Stats.VITESSE_KINDER * Endless.delta);
			position.x -= (direction.x * Stats.VITESSE_KINDER * Endless.delta);
		} else {
			angle += Stats.VITESSE_KINDER * Endless.delta;
		}
		return true;
	}
	
	/**
	 * Exactement la mï¿½me que dans la super classe mais ï¿½a ï¿½vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) || Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		if (maintenant < AnimationKinder.TPS_ANIM_OUVERT || maintenant > 12) {
			position.y -= (direction.y * Stats.VITESSE_KINDER * Endless.delta);
			position.x -= (direction.x * Stats.VITESSE_KINDER * Endless.delta);
		} else {
			angle += Stats.VITESSE_KINDER * Endless.delta;
		}
		return true;
	}

	/**
	 * Exactement la mï¿½me que dans la super classe mais ï¿½a ï¿½vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficher(SpriteBatch batch) {
		if(mort){
			explosion.draw(batch, Endless.delta);
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
		}
		else{
			batch.draw(AnimationKinder.getTexture(maintenant), position.x, position.y,
					// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
					DEMI_LARGEUR,DEMI_HAUTEUR,
					// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
					LARGEUR, HAUTEUR,
					//scaleX the scale of the rectangle around originX/originY in x ET Y
					1,1,
					// L'ANGLE DE ROTATION
					angle,
					//FLIP OU PAS
					false);
			maintenant += Endless.delta;
		}
	}
	/**
	 * Exactement la mï¿½me que dans la super classe mais ï¿½a ï¿½vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if(mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		}
		else{
			batch.draw(AnimationKinder.getTexture(maintenant), position.x, position.y,
					// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
					DEMI_LARGEUR,DEMI_HAUTEUR,
					// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
					LARGEUR, HAUTEUR,
					//scaleX the scale of the rectangle around originX/originY in x ET Y
					1,1,
					// L'ANGLE DE ROTATION
					angle,
					//FLIP OU PAS
					false);
			maintenant += Endless.delta;
		}
	}
	
	@Override
	protected void tir() {
		tir.tirToutDroit(this, mort, maintenant, prochainTir);
	}


	@Override
	public int getXp() {
		return TypesEnnemis.EnnemiKinder.COUT;
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
	public Armes getArme() {			return ArmeKinder.pool.obtain();	}

	@Override
	public void setProchainTir(float f) {
		if (maintenant > 11.6 && maintenant < 12.4) {// On se prépare à bouger
			angle = direction.angle();
//			angle
		}
		prochainTir = f;
	}

	@Override
	public float getModifVitesse() {	return 1;	}

	@Override
	public float getAngleTir() {			return angle;	}
	
	@Override
	public Vector2 getDirectionTir() {
		tmpDirectionTir.x = 1;
		tmpDirectionTir.y = 0;
		tmpDirectionTir.rotate(angle);
		return tmpDirectionTir;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = (position.x + DEMI_LARGEUR - ArmeKinder.DEMI_LARGEUR);// + (direction.x * 16);
		tmpPos.y = (position.y + DEMI_HAUTEUR - ArmeKinder.DEMI_LARGEUR);//+ (direction.y * 16);
		return tmpPos;
	}
}

