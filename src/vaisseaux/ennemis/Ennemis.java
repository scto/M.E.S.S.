package vaisseaux.ennemis;

import jeu.Endless;
import vaisseaux.Vaisseaux;
import vaisseaux.bonus.Bonus;
import vaisseaux.ennemis.particuliers.EnnemiBossQuad;
import vaisseaux.ennemis.particuliers.EnnemiPorteNef;
import vaisseaux.ennemis.particuliers.nv1.Avion;
import vaisseaux.ennemis.particuliers.nv1.BouleQuiSArrete;
import vaisseaux.ennemis.particuliers.nv1.Cylon;
import vaisseaux.ennemis.particuliers.nv1.DeBase;
import vaisseaux.ennemis.particuliers.nv1.Insecte;
import vaisseaux.ennemis.particuliers.nv1.Kinder;
import vaisseaux.ennemis.particuliers.nv1.Laser;
import vaisseaux.ennemis.particuliers.nv1.PorteRaisin;
import vaisseaux.ennemis.particuliers.nv1.QuiTir;
import vaisseaux.ennemis.particuliers.nv1.QuiTir2;
import vaisseaux.ennemis.particuliers.nv1.QuiTourne;
import vaisseaux.ennemis.particuliers.nv1.Toupie;
import vaisseaux.ennemis.particuliers.nv1.ZigZag;
import vaisseaux.ennemis.particuliers.nv2.Kinder2;
import vaisseaux.ennemis.particuliers.nv2.BouleTirCote;
import vaisseaux.ennemis.particuliers.nv2.BouleTirCoteRotation;
import vaisseaux.ennemis.particuliers.nv3.EnnemiAvionNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiBouleQuiSArreteNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiCylonNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiDeBaseNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiInsecteNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiKinderNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiPorteRaisinNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiQuiTir2Nv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiQuiTirNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiQuiTourneNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiToupieNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiZigZagNv3;
import assets.SoundMan;
import assets.animation.AnimationArmeFusee;
import assets.animation.AnimationExplosion1;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;

public abstract class Ennemis extends Vaisseaux implements Poolable, Invocable{
	
	// voir a quelle taille l'initialiser
	public static Array<Ennemis> liste = new Array<Ennemis>(30);
	protected static final Vector2 tmpPos = new Vector2(), tmpDir = new Vector2();
	public static final Invocable[] LISTE_LV1 = {
		new Insecte(),
		Laser.pool.obtain(),
		PorteRaisin.pool.obtain(),
		Avion.pool.obtain(),
		QuiTir2.pool.obtain(),
		Kinder.pool.obtain(),
		Cylon.pool.obtain(),
		Toupie.pool.obtain(),
		QuiTourne.pool.obtain(),
		BouleQuiSArrete.pool.obtain(),
		QuiTir.pool.obtain(),
		ZigZag.pool.obtain(),
		DeBase.pool.obtain(),
		};//EnnemiInsecte.pool.obtain()};
	public static final Invocable[] LISTE_LV2 = {
		new Insecte(),
		Kinder2.pool.obtain(),
		BouleTirCote.pool.obtain(),
		Laser.pool.obtain(),
		Kinder2.pool.obtain(),
		BouleTirCoteRotation.pool.obtain(),
		PorteRaisin.pool.obtain(),
		Avion.pool.obtain(),
		QuiTir2.pool.obtain(), 
		Kinder.pool.obtain(), 
		Cylon.pool.obtain(), 
		Toupie.pool.obtain(), 
		QuiTourne.pool.obtain(), 
		BouleQuiSArrete.pool.obtain(),
		QuiTir.pool.obtain(),
		ZigZag.pool.obtain(),
		DeBase.pool.obtain(),
		};//EnnemiInsecte.pool.obtain()};
	public static final Invocable[] LISTE_LV3 = {
		new EnnemiInsecteNv3(),
		BouleTirCote.pool.obtain(), 
		EnnemiPorteRaisinNv3.pool.obtain(), 
		EnnemiAvionNv3.pool.obtain(),
		EnnemiQuiTir2Nv3.pool.obtain(), 
		EnnemiKinderNv3.pool.obtain(), 
		EnnemiCylonNv3.pool.obtain(), 
		EnnemiToupieNv3.pool.obtain(), 
		EnnemiQuiTourneNv3.pool.obtain(), 
		EnnemiBouleQuiSArreteNv3.pool.obtain(),
		EnnemiQuiTirNv3.pool.obtain(),
		EnnemiZigZagNv3.pool.obtain(),
		EnnemiDeBaseNv3.pool.obtain(),
		};//EnnemiInsecte.pool.obtain()};
	public static float derniereApparition = 0;
	public boolean mort = false;
	protected static Rectangle collision = new Rectangle();
	protected int pv;
	protected float maintenant = 0, tpsAnimationExplosion = 0;
	
	/**
	 * Initialise l'ennemi
	 * @param posX
	 * @param posY
	 * @param direction
	 * @param pv 
	 */
	protected Ennemis(float posX, float posY, int pv) {
		position = new Vector2(posX, posY);
		this.pv = pv;
	}
	
	public static void affichageEtMouvementSansParticules(SpriteBatch batch) {
		for (Ennemis e : liste){
			e.afficherSansParticules(batch);
			e.tir();
			if (e.mouvementEtVerifSansParticules() == false) 	liste.removeValue(e, true);
		}
	}
	
	public void afficherSansParticules(SpriteBatch batch) {
		if (mort) {
			batch.draw(getExplosion(), position.x, position.y, getLargeur(), getHauteur());
			tpsAnimationExplosion += Endless.delta;
		} else {
			batch.draw(getTexture(), position.x, position.y, getDemiLargeur(), getDemiHauteur(), getLargeur(), getHauteur(), 1,1, getAngle());
			maintenant += Endless.delta;
		}
	}
	
	protected float getAngle() {				return 0;	}
	protected TextureRegion getExplosion() {	return AnimationExplosion1.getTexture(tpsAnimationExplosion);	}
	protected TextureRegion getTexture() {		return AnimationArmeFusee.getTexture(maintenant);	}
	protected void tir() {	}

	public static void affichageSansParticules(SpriteBatch batch) {
		for(Ennemis e : liste)
			e.afficherSansParticules(batch);
	}
	
	static int cpt = 0;
	/**
	 * fait apparaitre les ennemis si il faut.
	 * Il les fait apparaitre suivant la difficult�
	 * voir pour virer le system.currentTime
	 * @param tempsEcoule
	 */
	public static void possibleApparitionEtUpdateScore() {
		if (Progression.frequenceApparition + derniereApparition < Endless.maintenant) {
			Progression.invoqueEnnemis();
			derniereApparition = Endless.maintenant;
		}
	}
	/**
	 * Renvoie le rectangle de collision de l'objet
	 * @return
	 */
	abstract public Rectangle getRectangleCollision();

	/**
	 * On decremente les pvs de la force de l'arme. Si c'est 0 ou moins on le condamne � mort. Ca ajoute les bonus eventuellement
	 * @param force
	 * @return return true si vivant.
	 */
	public boolean touche(int force) {
		pv -= force;
		if (pv <= 0 && !mort) {			mourrir();		}
		return !mort;
	}

	/**
	 * Joue l'explosion (bruit)
	 * met l'xp
	 * initialise les bonus
	 * appele mort()
	 */
	public void mourrir() {
		mort = true;
		Bonus.ajoutBonus(position.x + getDemiLargeur(), position.y + getDemiHauteur(), getXp());
		tpsAnimationExplosion = 0;
		SoundMan.playBruitage(getSonExplosion());
	}

	/**
	 * renvoie la valeur en xp de l'ennemi
	 * @return
	 */
	public abstract int getXp();
	public abstract int getHauteur();
	public abstract int getLargeur();
	public abstract int getDemiHauteur();
	public abstract int getDemiLargeur();
	public abstract boolean mouvementEtVerifSansParticules();
	protected Sound getSonExplosion() {		return null;	}

	public static void randomApparition() {
		double f = Math.random();
		if (f < 0.1) {				liste.add(EnnemiPorteNef.pool.obtain());
		} else if (f < .2) {		liste.add(BouleQuiSArrete.pool.obtain());
		} else if (f < .3) {		liste.add(QuiTir.pool.obtain());
		} else if (f < .4) {		liste.add(ZigZag.pool.obtain());
		} else if (f < .5) {		liste.add(Kinder.pool.obtain());
		} else if (f < .6) {		liste.add(DeBase.pool.obtain());
		} else if (f < .7) {		liste.add(QuiTourne.pool.obtain());
		} else if (f < .8) {		liste.add(Toupie.pool.obtain());
		} else if (f < .9) {		liste.add(Cylon.pool.obtain());
		} else if (f < .95) {		liste.add(PorteRaisin.pool.obtain());
		}
	}

	public static void bombe() {
		for (Ennemis e : liste)
			if (e.mort == false)
				e.mourrir();
		Endless.effetBloom();
	}

	public static void initLevelBoss(int level) {
		EnnemiBossQuad.setLevel(level);
	}
}
