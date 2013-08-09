package vaisseaux.ennemis.particuliers.nv3;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeLaser;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import assets.SoundMan;
import assets.animation.AnimationEnnemiAileDeployee;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiLaserNv3 extends Ennemis{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 15;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	// ** ** caracteristiques variables.
	private float dernierTir = .1f;
	private float maintenant = 0;
	public static Pool<EnnemiLaserNv3> pool = Pools.get(EnnemiLaserNv3.class);
	// ** ** particules
	private ParticulesExplosionPetite explosion;
	protected float tpsAnimationExplosion;
	private float angle = -90;
	private Vector2 direction = new Vector2();
	private boolean versGauche;

	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosionennemidebasequitir);
		if (CSG.profil.particules) {
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
			explosion.start();
		} else {
			tpsAnimationExplosion = 0;
		}
	}
	
	public void init() {
		tpsAnimationExplosion = 0;
		if (CSG.profil.particules && explosion == null) explosion = ParticulesExplosionPetite.pool.obtain();
	}
	
	@Override
	public void reset() {
		maintenant = 0;
		position.x = Physique.getEmplacementX(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		mort = false;
		pv = Stats.PVMAX_LASER3;
		dernierTir = .2f;
		initPositionEtAngle();
	}

	public EnnemiLaserNv3() {
		super(Physique.getEmplacementX(DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + HAUTEUR, Stats.PVMAX_LASER3);
		initPositionEtAngle();
	}

	private void initPositionEtAngle() {
		if (position.x + DEMI_LARGEUR > CSG.DEMI_LARGEUR_ZONE_JEU) {
			versGauche = true;
			direction.x = -1;
			angle = -135;
		} else {
			direction.x = 1;
			versGauche = false;
			angle = -45;
		}
		direction.y = -1;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		if( (mort && explosion.isComplete()) || Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		maintenant += Endless.delta + Endless.delta;
		Physique.mouvementDeBase(direction, position, Stats.VITESSE_ENNEMI_LASER3, HAUTEUR, LARGEUR);
		if (maintenant < 50) {
			if (versGauche) {
				direction.rotate(maintenant * (Endless.delta + Endless.delta));
				angle += maintenant * (Endless.delta + Endless.delta);
			} else {
				direction.rotate(-maintenant * (Endless.delta + Endless.delta));
				angle -= maintenant * (Endless.delta + Endless.delta);
			}
		}
		return true;
	}
	
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) || Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		maintenant += Endless.delta + Endless.delta; // Pour rotationner plus vite, du coup attention à la cadence de tir
		Physique.mouvementDeBase(direction, position, Stats.VITESSE_ENNEMI_LASER3, HAUTEUR, LARGEUR);
		if (maintenant < 50) {
			if (versGauche) {
				direction.rotate(maintenant * (Endless.delta + Endless.delta));
				angle += maintenant * (Endless.delta + Endless.delta);
			} else {
				direction.rotate(-maintenant * (Endless.delta + Endless.delta));
				angle -= maintenant * (Endless.delta + Endless.delta);
			}
		}
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
			batch.draw(AnimationEnnemiAileDeployee.getTexture(3), position.x, position.y, DEMI_LARGEUR,DEMI_HAUTEUR, LARGEUR, HAUTEUR, 2,1, angle,	false);
		}
	}
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if (mort) {
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		} else {
			batch.draw(AnimationEnnemiAileDeployee.getTexture(3), position.x, position.y, DEMI_LARGEUR,DEMI_HAUTEUR, LARGEUR, HAUTEUR, 2,1,	angle,	false);
		}
	}
	
	@Override
	protected void tir() {
		if (!mort && maintenant > dernierTir + ArmeLaser.CADENCETIR) {
			ArmeLaser e = ArmeLaser.pool.obtain();
//			e.init(position.x + DEMI_LARGEUR - ArmeLaser.DEMI_LARGEUR, position.y + DEMI_HAUTEUR, angle+90, direction.x, direction.y);
			dernierTir = maintenant;
		}
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return TypesEnnemis.EnnemiLaserNv3.COUT;
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
}
