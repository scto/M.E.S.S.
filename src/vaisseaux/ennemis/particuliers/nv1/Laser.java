package vaisseaux.ennemis.particuliers.nv1;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.ArmeLaser;
import vaisseaux.armes.Armes;
import vaisseaux.armes.typeTir.TireurAngle;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.Ennemis;
import assets.SoundMan;
import assets.animation.AnimationEnnemiAileDeployee;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class Laser extends Ennemis implements TireurAngle {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 15;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Tirs TIR = new Tirs(1.1f);
	// ** ** caracteristiques variables.
	protected float prochainTir = 1f;
	public static Pool<Laser> pool = Pools.get(Laser.class);
	// ** ** particules
	private ParticulesExplosionPetite explosion;
	private float angle = -90;
	private Vector2 direction = new Vector2();
	private boolean versGauche;

	@Override
	protected Sound getSonExplosion() {
		return SoundMan.explosionennemidebasequitir;
	}
	
	public void init() {
		tpsAnimationExplosion = 0;
		if (CSG.profil.particules && explosion == null) explosion = ParticulesExplosionPetite.pool.obtain();
	}
	
	@Override
	protected void free() {
		pool.free(this);
	}
	
	@Override
	public void reset() {
		maintenant = 0;
		position.x = Positionnement.getEmplacementX(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		mort = false;
		pv = Stats.PVMAX_LASER;
		prochainTir = .2f;
		initPositionEtAngle();
	}

	public Laser() {
		super(Positionnement.getEmplacementX(DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + HAUTEUR, Stats.PVMAX_LASER);
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
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) || Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		maintenant += Endless.delta + Endless.delta; // Pour rotationner plus vite, du coup attention à la cadence de tir
		Physique.mouvementDeBase(direction, position, Stats.VITESSE_ENNEMI_LASER, HAUTEUR, LARGEUR);
		if (maintenant < 50) {
			if (versGauche) {
				direction.rotate(maintenant * Endless.delta);
				angle += maintenant * Endless.delta;
			} else {
				direction.rotate(-maintenant * Endless.delta);
				angle -= maintenant * Endless.delta;
			}
		}
		return true;
	}

	@Override
	protected TextureRegion getTexture() {
		return AnimationEnnemiAileDeployee.getTexture(3);
	}
	
	@Override
	public float getAngle() {
		return angle;
	}
	
	@Override
	protected void tir() {
		TIR.tirToutDroit(this, mort, maintenant, prochainTir);
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiLaser.COUT;
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
	public Armes getArme() {			return ArmeLaser.pool.obtain();	}
	
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}

	@Override
	public float getModifVitesse() {	return 1;	}

	@Override
	public float getAngleTir() {			return angle+90;	}
	
	@Override
	public Vector2 getDirectionTir() {
		return direction;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = (position.x + DEMI_LARGEUR - ArmeLaser.DEMI_LARGEUR) + (direction.x * 32);
		tmpPos.y = (position.y + DEMI_HAUTEUR - ArmeLaser.DEMI_LARGEUR) + (direction.y * 32);
		return tmpPos;
	}
	
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
}
