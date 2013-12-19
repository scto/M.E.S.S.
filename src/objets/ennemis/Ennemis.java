package objets.ennemis;

import java.util.HashMap;

import objets.Objet;
import objets.armes.joueur.ArmeJoueur;
import objets.bonus.Bonus;
import objets.ennemis.particuliers.boss.BossQuad;
import objets.ennemis.particuliers.boss.BossSat;
import objets.ennemis.particuliers.nv1.Boule;
import objets.ennemis.particuliers.nv1.Cylon;
import objets.ennemis.particuliers.nv1.DeBase;
import objets.ennemis.particuliers.nv1.Kinder;
import objets.ennemis.particuliers.nv1.PorteRaisin;
import objets.ennemis.particuliers.nv1.QuiTir;
import objets.ennemis.particuliers.nv1.QuiTourne;
import objets.ennemis.particuliers.nv1.Toupie;
import objets.ennemis.particuliers.nv1.ZigZag;
import objets.joueur.VaisseauJoueur;
import jeu.CSG;
import jeu.EndlessMode;
import jeu.Physique;
import jeu.Stats;
import jeu.Strings;
import assets.SoundMan;
import assets.animation.AnimationArmeFusee;
import assets.animation.AnimationExplosion1;
import assets.particules.Particules;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;

public abstract class Ennemis extends Objet implements Poolable, Invocable {

	// voir a quelle taille l'initialiser
	public final static Array<Ennemis> LISTE = new Array<Ennemis>(30);
	public static final HashMap<String, Integer> ennemisTues = new HashMap<String, Integer>(20);
	protected static final Vector2 TMP_POS = new Vector2(), TMP_DIR = new Vector2();
	private static float derniereApparition = 0;
	protected static Rectangle collision = new Rectangle();
	// champs des ennemis
	protected boolean mort = false;
	protected int pv;
	protected float maintenant = 0, tpsAnimation = 0;

	/**
	 * Initialise l'ennemi
	 * 
	 * @param posX
	 * @param posY
	 * @param direction
	 * @param pv
	 */
	protected Ennemis() {
		this.pv = getPvMax();
	}

	public static void affichageEtMouvement(SpriteBatch batch) {
		for (Ennemis e : LISTE) {
			e.afficher(batch);
			e.tir();
			if (e.mouvementEtVerif() == false)
				LISTE.removeValue(e, true);
		}
	}

	public boolean mouvementEtVerif() {
		// if ( (mort && tpsAnimationExplosion >		// AnimationExplosion1.tpsTotalAnimationExplosion1) ||		// Physique.toujoursAfficher(position, getHauteur(), getLargeur()) ==		// false){
		if (mort) {
			free();
			return false;
		}
		if (Physique.toujoursAfficher(position, getHauteur(), getLargeur()) == false) {
			free();
			return false;
		}
		return true;
	}

	protected abstract void free();

	public void afficher(SpriteBatch batch) {
		// if (mort) {		// batch.draw(getExplosion(), position.x, position.y, getLargeur(),		// getHauteur());		// tpsAnimationExplosion += Endless.delta;		// } else {
		batch.draw(getTexture(), position.x, position.y, getDemiLargeur(), getDemiHauteur(), getLargeur(), getHauteur(), 1, 1, getAngle());
		maintenant += EndlessMode.delta;
	}

	protected float getAngle() {
		return 0;
	}

	protected TextureRegion getExplosion() {
		return AnimationExplosion1.getTexture(tpsAnimation);
	}

	protected TextureRegion getTexture() {
		return AnimationArmeFusee.getTexture(maintenant);
	}

	protected void tir() {
	}

	public static void affichage(SpriteBatch batch) {
		for (Ennemis e : LISTE)
			e.afficher(batch);
	}

	static int cpt = 0;

	/**
	 * fait apparaitre les ennemis si il faut. Il les fait apparaitre suivant la
	 * difficult� voir pour virer le system.currentTime
	 * 
	 * @param tempsEcoule
	 */
	public static void possibleApparitionEtUpdateScore() {
		if (Progression.FREQ_APPARATION + derniereApparition < EndlessMode.maintenant) {
			Progression.invoqueEnnemis();
			derniereApparition = EndlessMode.maintenant;
		}
	}

	/**
	 * Renvoie le rectangle de collision de l'objet
	 * 
	 * @return
	 */
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, getLargeur(), getHauteur());
		return collision;
	}

	/**
	 * On decremente les pvs de la force de l'arme. Si c'est 0 ou moins on le condamne a mort. Ca ajoute les bonus eventuellement
	 * 
	 * @param force
	 * @return return true si vivant.
	 */
	public boolean touche(int force) {
		pv -= force;
		if (pv <= 0 && !mort) {
			die();
		}
		return !mort;
	}

	/**
	 * Joue l'explosion (bruit) met l'xp initialise les bonus appele mort()
	 */
	public void die() {
		if (ennemisTues.containsKey(getLabel())) {
			ennemisTues.put(getLabel(), ennemisTues.get(getLabel()) + 1);
		} else {
			ennemisTues.put(getLabel(), 1);
		}
		Particules.explosion(this);
		mort = true;
		Bonus.addBonus(position.x + getDemiLargeur(), position.y + getDemiHauteur(), getXp());
		tpsAnimation = 0;
		SoundMan.playBruitage(getSonExplosion());
	}

	protected abstract String getLabel();

	/**
	 * Reset mort, tpsAnimationExplosion et pv
	 * 
	 * @param pvMax
	 */
	public void reset() {
		mort = false;
		tpsAnimation = 0;
		maintenant = 0;
		pv = getPvMax();
	}

	protected float getVitesse() {
		return 3333;
	}

	protected abstract int getPvMax();

	/**
	 * renvoie la valeur en xp de l'ennemi
	 * 
	 * @return
	 */
	public abstract int getXp();

	public abstract int getHauteur();

	public abstract int getLargeur();

	public abstract int getDemiHauteur();

	public abstract int getDemiLargeur();

	protected Sound getSonExplosion() {
		return null;
	}

	public static void randomApparition() {
		double f = Math.random();
		if (f < 0.1) {
			LISTE.add(BossSat.pool.obtain());
		} else if (f < .2) {
			LISTE.add(Boule.pool.obtain());
		} else if (f < .3) {
			LISTE.add(QuiTir.POOL.obtain());
		} else if (f < .4) {
			LISTE.add(ZigZag.POOL.obtain());
		} else if (f < .5) {
			LISTE.add(Kinder.pool.obtain());
		} else if (f < .6) {
			LISTE.add(DeBase.POOL.obtain());
		} else if (f < .7) {
			LISTE.add(QuiTourne.pool.obtain());
		} else if (f < .8) {
			LISTE.add(Toupie.POOL.obtain());
		} else if (f < .9) {
			LISTE.add(Cylon.pool.obtain());
		} else if (f < .95) {
			LISTE.add(PorteRaisin.POOL.obtain());
		}
	}

	public static void bombe() {
		if (LISTE.size > 25) {
			CSG.google.unlockAchievementGPGS(Strings.ACH_25_ENEMY);
		}
		for (Ennemis e : LISTE)
			if (e.mort == false)
				e.die();
		EndlessMode.effetBloom();
	}

	public static void initLevelBoss(int level) {
		BossQuad.setLevel(level);
	}

	public Vector2 getPosition() {
		return position;
	}

	public static void clear() {
		derniereApparition = 0;
		for (Ennemis e : LISTE)
			e.free();
		LISTE.clear();
		ennemisTues.clear();
	}

	public abstract float getDirectionY();

	public float getDirectionX() {
		return 0;
	}

	/**
	 * Verifie si il y a collision avec la balle.
	 * 
	 * @param a
	 * @return true = collision
	 */
	public boolean checkBullet(ArmeJoueur a) {
		if (mort)
			return false;
		return a.getRectangleCollision().overlaps(getRectangleCollision());
	}
	public boolean checkJoueur() {
		if (getRectangleCollision().contains(VaisseauJoueur.centreX, VaisseauJoueur.centreY)) {
			return true;
		}
		return false;
	}

	public boolean isMort() {
		return mort;
	}

	public static int getPvBoss(int pvDeBase) {
		return pvDeBase + (pvDeBase *  (VaisseauJoueur.arme.nv() / 3) );
	}

}
