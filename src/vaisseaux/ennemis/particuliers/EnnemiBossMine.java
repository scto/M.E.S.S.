package vaisseaux.ennemis.particuliers;

import java.util.Random;

import jeu.Endless;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeBossMine;
import vaisseaux.armes.ArmeMine;
import vaisseaux.armes.Armes;
import vaisseaux.armes.typeTir.TireurAngle;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.SoundMan;
import assets.animation.AnimationBossMine;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	public static Pool<EnnemiBossMine> pool = Pools.get(EnnemiBossMine.class);
	// direction
	private float dirY = 1;
	private float angle;
	private boolean versDroite = false;
	private int phase = 1;
	private static Random r = new Random(); 
	private int nbMinesEtDisspersion = 5;
	
	public EnnemiBossMine() {
		angle = 0;
		position.x = CSG.DEMI_LARGEUR_ZONE_JEU - DEMI_LARGEUR;
		position.y = CSG.HAUTEUR_ECRAN;
	}
	
	@Override
	protected int getPvMax() {
		return Stats.PVMAX_BOSS_MINE + (CSG.profil.getCoutUpArme() / 30);
	}

	protected void free() {
		pool.free(this);
	}
	
	protected Sound getSonExplosion() {
		return SoundMan.explosionGrosse;
	}

	public void reset() {
		angle = 0;
		versDroite = false;
		phase = 1;
		prochainTir = 3f;
		dirY = 1;
		position.x = CSG.DEMI_LARGEUR_ZONE_JEU - DEMI_LARGEUR;
		position.y = CSG.HAUTEUR_ECRAN;
		super.reset();
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */

	public boolean mouvementEtVerif() {
		if (phase == 1){
			if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_3 | dirY < 1) 	position.y -= (dirY * Stats.VITESSE_KINDER * Endless.delta);
			if (dirY < 1)	dirY += 30 * Endless.delta;
		} else {
			if (angle < 180) angle += Endless.delta * 100;
		}
		return super.mouvementEtVerif();
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */

	public void afficher(SpriteBatch batch) {
		maintenant += Endless.delta;
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
	}
	

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


	public int getXp() {				return CoutsEnnemis.EnnemiBossMine.COUT;	}

	public int getHauteur() {			return HAUTEUR;	}

	public int getLargeur() {			return LARGEUR;	}

	public int getDemiHauteur() {		return DEMI_HAUTEUR;	}

	public int getDemiLargeur() {		return DEMI_LARGEUR;	}

	public Armes getArme() {
		if (phase == 1) {
			return ArmeBossMine.pool.obtain();
		}
		else return ArmeMine.pool.obtain();
	}

	public void setProchainTir(float f) {
		SoundMan.playBruitage(SoundMan.tirRocket);
		prochainTir = f;	
	}

	public float getModifVitesse() {	return 1;	}


	public float getAngleTir() {			return angle;	}
	
	public Vector2 getDirectionTir() {
		tmpDirectionTir.x = 0;
		if (phase == 1)	tmpDirectionTir.y = -1;
		else tmpDirectionTir.y = 1;
		return tmpDirectionTir;
	}
	

	public Vector2 getPositionDuTir(int numeroTir) {
		if (phase == 1)		tmpPos.x = (position.x + DEMI_LARGEUR - ArmeBossMine.DEMI_LARGEUR);
		else tmpPos.x = (position.x + DEMI_LARGEUR - ArmeMine.DEMI_LARGEUR);
		tmpPos.y = position.y;
		return tmpPos;
	}

	public void invoquer() {
		liste.add(pool.obtain());
	}
	
	@Override
	public float getDirectionY() {		return -dirY * Stats.VITESSE_KINDER;	}
}

