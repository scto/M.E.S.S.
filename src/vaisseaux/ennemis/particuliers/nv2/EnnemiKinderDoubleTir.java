package vaisseaux.ennemis.particuliers.nv2;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeKinder;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.SoundMan;
import assets.animation.AnimationExplosion1;
import assets.animation.AnimationKinder;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiKinderDoubleTir extends Ennemis{

	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 8;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR - DEMI_LARGEUR / 2;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	// ** ** caracteristiques variables.
	private float dernierTir = .1f;
	private float tpsAnimation = 0;
	private float tpsAnimationExplosion = 0;
	public static Pool<EnnemiKinderDoubleTir> pool = Pools.get(EnnemiKinderDoubleTir.class);
	// ** ** particules
	private ParticulesExplosionPetite explosion;
	// direction
	private Vector2 direction = new Vector2();
	private float angle = 0;
	
	public EnnemiKinderDoubleTir() {
		super(0,0, Stats.PVMAX_KINDER_DOUBLE);
		init();
	}
	
	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosionkinder);
		if (CSG.profil.particules){
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
			explosion.start();
		} else {
			tpsAnimationExplosion = 0;
		}
	}
	
	/**
	 * Ajoute � la liste
	 */
	public void init() {
		if (CSG.profil.particules && explosion == null) explosion = ParticulesExplosionPetite.pool.obtain();
		else tpsAnimationExplosion = 0;
		randPositionEtDirection();
	}
	
	public static double rand;
	
	private void randPositionEtDirection() {
		rand = Math.random();
		if (rand > 0.5){ // on est � droite
			position.x = CSG.LARGEUR_ZONE_JEU-1;
			position.y = (float) (CSG.HAUTEUR_ECRAN - (rand * CSG.DEMI_HAUTEUR_ECRAN));
			direction.x = 0;
			direction.y = -1;
			// Je veux entre : 45 et 135
			direction.rotate((float) ((( (rand + rand) - 1) * 90) + 45) );
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
		pv = Stats.PVMAX_KINDER_DOUBLE;
		dernierTir = .2f;
		tpsAnimation = 0;
		init();
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
		if (tpsAnimation < AnimationKinder.TPS_ANIM_OUVERT | tpsAnimation > 12) {
			position.y -= (direction.y * Stats.VITESSE_KINDER * Endless.delta);
			position.x -= (direction.x * Stats.VITESSE_KINDER * Endless.delta);
		} else {
			angle += Stats.VITESSE_KINDER * Endless.delta;
		}
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
		if (tpsAnimation < AnimationKinder.TPS_ANIM_OUVERT | tpsAnimation > 12) {
			position.y -= (direction.y * Stats.VITESSE_KINDER * Endless.delta);
			position.x -= (direction.x * Stats.VITESSE_KINDER * Endless.delta);
		} else {
			angle += Stats.VITESSE_KINDER * Endless.delta;
		}
		return true;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficher(SpriteBatch batch) {
		if(mort){
			explosion.draw(batch, Endless.delta);
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
		}
		else{
			batch.setColor(Endless.colorRapide, .6f, 1, 1);
			batch.draw(AnimationKinder.getTexture(tpsAnimation), position.x, position.y,
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
			tpsAnimation += Endless.delta;
			batch.setColor(Color.WHITE);
		}
	}
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if(mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		}
		else{
			batch.setColor(Endless.colorRapide, .6f, 1, 1);
			batch.draw(AnimationKinder.getTexture(tpsAnimation), position.x, position.y,
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
			tpsAnimation += Endless.delta;
			batch.setColor(Color.WHITE);
		}
	}
	
	@Override
	protected void tir() {
		if (!mort && tpsAnimation > dernierTir + ArmeKinder.CADENCETIR && tpsAnimation > AnimationKinder.TPS_ANIM_OUVERT) {
			ArmeKinder milieu = ArmeKinder.pool.obtain();
			if (tpsAnimation < 12) {
//				milieu.init(position.x + DEMI_LARGEUR - ArmeKinder.DEMI_LARGEUR, position.y + DEMI_HAUTEUR - ArmeKinder.DEMI_LARGEUR, direction.x, direction.y, angle+10);
				direction.setAngle(angle);
				dernierTir = tpsAnimation + .1f;
				ArmeKinder deuxieme = ArmeKinder.pool.obtain();
//				deuxieme.init(position.x + DEMI_LARGEUR - ArmeKinder.DEMI_LARGEUR, position.y + DEMI_HAUTEUR - ArmeKinder.DEMI_LARGEUR, direction.x, direction.y, angle-10);
			} else { // Si il est en train de se barrer
//				milieu.init(position.x + DEMI_LARGEUR - ArmeKinder.DEMI_LARGEUR, position.y + DEMI_HAUTEUR - ArmeKinder.DEMI_LARGEUR, -direction.x, -direction.y, angle+10);
				direction.setAngle(angle);
				dernierTir = tpsAnimation + .5f;
			}
		}
	}


	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiKinderDoubleNv2.COUT;
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
	public void invoquer() {
		liste.add(pool.obtain());
	}
}

