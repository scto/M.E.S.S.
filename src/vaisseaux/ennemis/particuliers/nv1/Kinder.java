package vaisseaux.ennemis.particuliers.nv1;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeKinder;
import vaisseaux.armes.Armes;
import vaisseaux.armes.typeTir.TireurAngle;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.SoundMan;
import assets.animation.AnimationExplosion1;
import assets.animation.AnimationKinder;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class Kinder extends Ennemis implements TireurAngle {

	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 8;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR - DEMI_LARGEUR / 2;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	protected static final Tirs tir = new Tirs(.5f);
	// ** ** caracteristiques variables.
	protected float prochainTir = 1f;
	public static Pool<Kinder> pool = Pools.get(Kinder.class);
	// ** ** particules
	private ParticulesExplosionPetite explosion;
	// direction
	private Vector2 direction = new Vector2();
	protected float angle = 0;
	protected boolean gauche = true;
	
	public Kinder() {
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
	 * Ajoute � la liste
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
			gauche = false;
		} else {
			gauche = true;
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
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
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
	
	@Override
	protected TextureRegion getTexture() {
		return AnimationKinder.getTexture(maintenant);
	}
	
	@Override
	protected float getAngle() {
		return angle;
	}
	
	@Override
	protected void tir() {
		tir.tirToutDroit(this, mort, maintenant, prochainTir);
	}


	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiKinder.COUT;
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
		if (maintenant > 11.6 && maintenant < 12.4) {// On se pr�pare � bouger
			angle = direction.angle();
		}
		prochainTir = f;
	}

	@Override
	public float getModifVitesse() {	return 1;	}

	@Override
	public float getAngleTir() {			return angle;	}
	
	@Override
	public Vector2 getDirectionTir() {
		tmpDir.x = 1;
		tmpDir.y = 0;
		tmpDir.rotate(angle);
		return tmpDir;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = (position.x + DEMI_LARGEUR - ArmeKinder.DEMI_LARGEUR);// + (direction.x * 16);
		tmpPos.y = (position.y + DEMI_HAUTEUR - ArmeKinder.DEMI_LARGEUR);//+ (direction.y * 16);
		return tmpPos;
	}
	
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
}

