package vaisseaux.ennemis;

import jeu.Endless;
import vaisseaux.Vaisseaux;
import vaisseaux.bonus.Bonus;
import vaisseaux.ennemis.particuliers.EnnemiBossQuad;
import vaisseaux.ennemis.particuliers.EnnemiPorteNef;
import vaisseaux.ennemis.particuliers.nv1.EnnemiAvion;
import vaisseaux.ennemis.particuliers.nv1.EnnemiBouleQuiSArrete;
import vaisseaux.ennemis.particuliers.nv1.EnnemiCylon;
import vaisseaux.ennemis.particuliers.nv1.EnnemiDeBase;
import vaisseaux.ennemis.particuliers.nv1.EnnemiInsecte;
import vaisseaux.ennemis.particuliers.nv1.EnnemiKinder;
import vaisseaux.ennemis.particuliers.nv1.EnnemiLaser;
import vaisseaux.ennemis.particuliers.nv1.EnnemiPorteRaisin;
import vaisseaux.ennemis.particuliers.nv1.EnnemiQuiTir;
import vaisseaux.ennemis.particuliers.nv1.EnnemiQuiTir2;
import vaisseaux.ennemis.particuliers.nv1.EnnemiQuiTourne;
import vaisseaux.ennemis.particuliers.nv1.EnnemiToupie;
import vaisseaux.ennemis.particuliers.nv1.EnnemiZigZag;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;


public abstract class Ennemis extends Vaisseaux implements Poolable, Invocable{
	
	// voir a quelle taille l'initialiser
	public static Array<Ennemis> liste = new Array<Ennemis>(30);
	protected static final Vector2 tmpPos = new Vector2();
	protected static final Vector2 tmpDir = new Vector2();
	public static final Invocable[] LISTE_LV1 = {EnnemiDeBase.pool.obtain(), EnnemiZigZag.pool.obtain(), EnnemiQuiTir.pool.obtain(), EnnemiBouleQuiSArrete.pool.obtain()
		,EnnemiQuiTourne.pool.obtain(), EnnemiToupie.pool.obtain(), EnnemiCylon.pool.obtain(), EnnemiKinder.pool.obtain(), EnnemiQuiTir2.pool.obtain(), EnnemiAvion.pool.obtain(),
		EnnemiPorteRaisin.pool.obtain(), EnnemiLaser.pool.obtain(), EnnemiInsecte.pool.obtain()};
	public static float derniereApparition = 0;
	public boolean mort = false;
	protected static Rectangle collision = new Rectangle();
	protected int pv;
	
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
	
	/**
	 * Parcourt la liste une fois invoquant la methode mouvement et la methode afficher
	 * Les fait egalement tirer
	 * @param batch
	 */
	public static void affichageEtMouvement(SpriteBatch batch) {
		for (Ennemis e : liste){
			e.afficher(batch);
			e.tir();
			// On le vire si hors de l'ecran
			if (e.mouvementEtVerif() == false)		liste.removeValue(e, true);
		}
	}
	
	public static void affichageEtMouvementSansParticules(SpriteBatch batch) {
		for (Ennemis e : liste){
			e.afficherSansParticules(batch);
			e.tir();
			if (e.mouvementEtVerifSansParticules() == false) 	liste.removeValue(e, true);
		}
	}
	abstract public void afficherSansParticules(SpriteBatch batch);

	/**
	 * Methode de tir, si elle n'est pas redefinie l'ennemi ne tir pas
	 */
	protected void tir() {
		
	}

	/**
	 * Se contente d'afficher simplement les objets
	 * @param batch
	 */
	public static void affichage(SpriteBatch batch) {
		for(Ennemis e : liste)
			e.afficher(batch);
	}
	public static void affichageSansParticules(SpriteBatch batch) {
		for(Ennemis e : liste)
			e.afficherSansParticules(batch);
	}
	/**
	 * Methode servant a afficher les balles
	 * @param batch : batch principal
	 */
	abstract public void afficher(SpriteBatch batch);
	
	/**
	 * Fait bouger les objets et les enl�ves si ils ne sont plus � l'�cran
	 * On les enl�ve �galement si ils sont morts et que l'animation est finie
	 * @param delta 
	 * @param batch
	 */
	abstract public boolean mouvementEtVerif();
	
	static int cpt = 0;
	/**
	 * fait apparaitre les ennemis si il faut.
	 * Il les fait apparaitre suivant la difficult�
	 * voir pour virer le system.currentTime
	 * @param tempsEcoule
	 */
	public static void possibleApparitionEtUpdateScore(){
		if (Progression.frequenceApparition	+ derniereApparition < Endless.maintenant) {
			switch (Endless.level) {
			case 1:		Progression.listeEnnemisNv1();		break;
			case 2:		Progression.listeEnnemisNv2();		break;
			case 3:		Progression.listeEnnemisNv3();		break;
			}
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
		if (pv <= 0 & !mort) {			mourrir();		}
		return !mort;
	}

	/**
	 * Joue l'explosion (bruit)
	 * met l'xp
	 * initialise les bonus
	 * appele mort()
	 */
	public void mourrir() {
//		SoundMan.playBruitage(SoundMan.explosionGrosse);
		mort = true;
		Bonus.ajoutBonus(position.x + getDemiLargeur(), position.y + getDemiHauteur(), getXp());
//		Endless.score += getXp();
		this.mort();
	}

	protected void mort() {
		
	}

	/**
	 * renvoie la valeur en xp de l'ennemi
	 * @return
	 */
	public abstract int getXp();

	/**
	 * Je n'ai pas trouv� comment me passer des getters si je veux pouvoir �tendre une classe d'ennemi sans devoir tout r�implementer
	 * tout en pouvant malgr� tout changer ses caract�ristiques
	 * @return
	 */
	public abstract int getHauteur();
	public abstract int getLargeur();
	public abstract int getDemiHauteur();
	public abstract int getDemiLargeur();

	public static void randomApparition() {
		double f = Math.random();
		if (f < 0.1){
			liste.add(EnnemiPorteNef.pool.obtain());
		} else if (f < .2){
			liste.add(EnnemiBouleQuiSArrete.pool.obtain());
		} else if (f < .3){
			liste.add(EnnemiQuiTir.pool.obtain());
		} else if (f < .4){
			liste.add(EnnemiZigZag.pool.obtain());
		} else if (f < .5){
			liste.add(EnnemiKinder.pool.obtain());
		} else if (f < .6){
			liste.add(EnnemiDeBase.pool.obtain());
		} else if (f < .7){
			liste.add(EnnemiQuiTourne.pool.obtain());
		} else if (f < .8){
			liste.add(EnnemiToupie.pool.obtain());
		} else if (f < .9){
			liste.add(EnnemiCylon.pool.obtain());
		} else if (f < .95){
				liste.add(EnnemiPorteRaisin.pool.obtain());
		}
	}

	abstract public boolean mouvementEtVerifSansParticules();

	public static void switchParticules(){
		for(Ennemis e : liste)			e.init();
	}

	abstract public void init();

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
