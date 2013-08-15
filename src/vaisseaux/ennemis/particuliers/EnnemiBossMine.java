package vaisseaux.ennemis.particuliers;

import java.util.Random;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeBossMine;
import vaisseaux.armes.ArmeMine;
import vaisseaux.armes.Armes;
import vaisseaux.armes.typeTir.TireurAngle;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.AssetMan;
import assets.SoundMan;
import assets.animation.AnimationBossMine;
import assets.animation.AnimationExplosion1;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiBossMine extends Ennemis implements TireurAngle{

	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= (int) (CSG.LARGEUR_ECRAN / 9);
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR * 4;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2;
	public static final Tirs tirPhase1 = new Tirs(ArmeBossMine.CADENCETIR);
	public static final Tirs tirPhase2 = new Tirs(ArmeMine.CADENCETIR);
	// ** ** caracteristiques variables.
	private static final Vector2 tmpDirectionTir = new Vector2();
	private float prochainTir = 3f;
	private float maintenant = 0;
	private float tpsAnimationExplosion = 0;
	public static Pool<EnnemiBossMine> pool = Pools.get(EnnemiBossMine.class);
	// ** ** particules
	private ParticleEffect explosion;
	// direction
	private float dirY = 1;
	private float angle;
	private boolean versDroite = false;
	private int phase = 1;
	private static Random r = new Random(); 
	private int nbMinesEtDisspersion = 5;
	
	public EnnemiBossMine() {
		super(0,0, Stats.PVMAX_BOSS_MINE + (CSG.profil.getCoutUpArme() / 30));
		angle = 0;
		position.x = CSG.DEMI_LARGEUR_ZONE_JEU - DEMI_LARGEUR;
		position.y = CSG.HAUTEUR_ECRAN;
		init();
	}
	
	@Override
	protected Sound getSonExplosion() {
		return SoundMan.explosionGrosse;
	}
	
	/**
	 * Ajoute � la liste
	 */
	public void init() {
		if (CSG.profil.particules){
			if (explosion == null) explosion = new ParticleEffect(AssetMan.explosionGros);
		} else {
			tpsAnimationExplosion = 0;
		}
	}

	@Override
	public void reset() {
		angle = 0;
		versDroite = false;
		mort = false;	
		tpsAnimationExplosion = 0;
		pv = Stats.PVMAX_BOSS_MINE + (CSG.profil.getCoutUpArme() / 30);
		prochainTir = 3f;
		maintenant = 0;
		init();
		dirY = 1;
		position.x = CSG.DEMI_LARGEUR_ZONE_JEU - DEMI_LARGEUR;
		position.y = CSG.HAUTEUR_ECRAN;
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

		if (phase == 1){
			if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_3 | dirY < 1) 	position.y -= (dirY * Stats.VITESSE_KINDER * Endless.delta);
			if (dirY < 1)	dirY += 30 * Endless.delta;
		} else {
			if (angle < 180) angle += Endless.delta * 100;
		}
		return true;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if(mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnimationExplosion += Endless.delta;
		}
		else{
			if (pv > Stats.DEUXTIERS_PVMAX_BOSS_MINE)			batch.draw(AnimationBossMine.getTexture(pv), position.x, position.y, LARGEUR, HAUTEUR);
			else 	batch.draw(AnimationBossMine.getTexture(pv), position.x, position.y,
					// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
					DEMI_LARGEUR,DEMI_HAUTEUR,
					// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
					LARGEUR, HAUTEUR,
					//scaleX the scale of the rectangle around originX/originY in x ET Y
					4,0.25f,
					// L'ANGLE DE ROTATION
					angle -90,
					//FLIP OU PAS
					false);
			maintenant += Endless.delta;
		}
	}
	
	@Override
	protected void tir() {
		switch (phase) {
		case 1 : 
			tirPhase1.tirEventail(this, 5, 10, mort, maintenant, prochainTir);
			if (versDroite) {
				angle += Endless.delta * 40;
				if (angle > 20) versDroite = false;
			} else {
				angle -= Endless.delta * 40;
				if (angle < -20) versDroite = true;
			}
			break;
		case 2:
			nbMinesEtDisspersion = r.nextInt(5) + 3;
			tirPhase2.tirEventail(this, nbMinesEtDisspersion, 10 + nbMinesEtDisspersion * 4, mort, maintenant, prochainTir);
			break;
		}
	}
	
	
	@Override
	public boolean touche(int force) {
		if (pv > Stats.DEUXTIERS_PVMAX_BOSS_MINE) {
			phase = 1;
		} else if (phase != 2) {
			// pour lui laisser le temps de se retourner
			prochainTir += 2f;
			phase = 2;
		}
		return super.touche(force);
	}

	@Override
	public int getXp() {				return CoutsEnnemis.EnnemiBossMine.COUT;	}
	@Override
	public int getHauteur() {			return HAUTEUR;	}
	@Override
	public int getLargeur() {			return LARGEUR;	}
	@Override
	public int getDemiHauteur() {		return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}
	
	@Override
	public Armes getArme() {
		if (phase == 1) {
			return ArmeBossMine.pool.obtain();
		}
		else return ArmeMine.pool.obtain();
	}
	
	@Override
	public void setProchainTir(float f) {
		SoundMan.playBruitage(SoundMan.tirRocket);
		prochainTir = f;	
	}

	@Override
	public float getModifVitesse() {	return 1;	}


	@Override
	public float getAngleTir() {			return angle;	}
	
	@Override
	public Vector2 getDirectionTir() {
		tmpDirectionTir.x = 0;
		if (phase == 1)	tmpDirectionTir.y = -1;
		else tmpDirectionTir.y = 1;
		return tmpDirectionTir;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		if (phase == 1)		tmpPos.x = (position.x + DEMI_LARGEUR - ArmeBossMine.DEMI_LARGEUR);
		else tmpPos.x = (position.x + DEMI_LARGEUR - ArmeMine.DEMI_LARGEUR);
		tmpPos.y = position.y;
		return tmpPos;
	}
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
}

