package vaisseaux.ennemis;

import sons.SoundMan;
import vaisseaux.Vaisseaux;
import vaisseaux.bonus.BonusTemps;
import vaisseaux.bonus.XP;
import vaisseaux.ennemis.particuliers.EnnemiAilesDeployees;
import vaisseaux.ennemis.particuliers.EnnemiBouleQuiSArrete;
import vaisseaux.ennemis.particuliers.EnnemiDeBase;
import vaisseaux.ennemis.particuliers.EnnemiDeBaseQuiTir;
import vaisseaux.ennemis.particuliers.EnnemiPorteNef;
import vaisseaux.ennemis.particuliers.EnnemiZigZag;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;


public abstract class Ennemis extends Vaisseaux implements Poolable{
	
	// voir à quelle taille l'initialiser
	public static Array<Ennemis> liste = new Array<Ennemis>(30);
	private static long derniereApparition = 0;
	public boolean mort = false;
	private static Array<TypesEnnemis> ennemisAApparaitre = new Array<TypesEnnemis>(false, 30);
	protected Rectangle collision = new Rectangle();
	protected int pv;
	// ** ** variables utilitaires

	//private float clignotement = 0;
	
	
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
	 * Les fait également tirer
	 * @param batch
	 * @param delta 
	 */
	public static void affichageEtMouvement(SpriteBatch batch, float delta) {
		for(Ennemis e : liste){
			//if(e.clignotement <= 0 | e.mort){
				e.afficher(batch, delta);
			//} else {
			//	e.clignotement -= Gdx.graphics.getDeltaTime();
			//}
			// On le fait tirer
			e.tir();
			// On le vire si hors de l'écran
			if(e.mouvementEtVerif(delta) == false)
				liste.removeValue(e, true);
		}
	}
	
	/**
	 * Méthode de tir, si elle n'est pas redefinie l'ennemi ne tir pas
	 */
	protected void tir() {
		
	}

	/**
	 * Se contente d'afficher simplement les objets
	 * @param batch
	 */
	public static void affichage(SpriteBatch batch, float delta) {
		for(Ennemis e : liste)
			e.afficher(batch, delta);
	}
	
	/**
	 * Methode servant à afficher les balles
	 * @param batch : batch principal
	 * @param delta 
	 */
	abstract public void afficher(SpriteBatch batch, float delta);
	
	/**
	 * Fait bouger les objets et les enlèves si ils ne sont plus à l'écran
	 * On les enlève également si ils sont morts et que l'animation est finie
	 * @param delta 
	 * @param batch
	 */
	abstract public boolean mouvementEtVerif(float delta);
	
	/**
	 * fait apparaitre les ennemis si il faut.
	 * Il les fait apparaitre suivant la difficulté
	 * @param tempsEcoule
	 */
	public static void possibleApparition(long tempsEcoule){
		if (Progression.frequenceApparition	+ derniereApparition < System.currentTimeMillis()) {
			// Si on met l'invocation de la methode directement dans le for on aura que des ennemis de base qui pop alors que
			// pourtant si on affiche les types tous sont là logiquement, bizarre bizarre
			ennemisAApparaitre = Progression.getListeEnnemis(tempsEcoule);
			for (TypesEnnemis type : ennemisAApparaitre) {
				switch (type) {
				case EnnemiPorteNef:
					liste.add(EnnemiPorteNef.pool.obtain());
					break;
				case EnnemiBouleQuiSArrete:
					liste.add(EnnemiBouleQuiSArrete.pool.obtain());
					break;
				case EnnemiDeBaseQuiTir:
					liste.add(EnnemiDeBaseQuiTir.pool.obtain());
					break;
				case EnnemiZigZag:
					liste.add(EnnemiZigZag.pool.obtain());
					break;
				case EnnemiDeBase:
					liste.add(EnnemiDeBase.pool.obtain());
					break;
				}
			}
			derniereApparition = System.currentTimeMillis();
		}
	}
	/**
	 * Renvoie le rectangle de collision de l'objet
	 * @return
	 */
	abstract public Rectangle getRectangleCollision();

	/**
	 * On decremente les pvs de la force de l'arme. Si c'est 0 ou moins on le condamne à mort. Ca ajoute les bonus eventuellement
	 * @param force
	 * @return return true si vivant.
	 */
	public boolean touche(int force) {
		pv -= force;
		if(pv <= 0 & !mort){
			SoundMan.playBruitage(SoundMan.explosionGrosse);
			mort = true;
			new XP(position.x, position.y, getXp());
			BonusTemps.ajoutBonus(position.x, position.y, getXp());
		}
		//clignotement = .08f;
		return !mort;
	}

	/**
	 * renvoie la valeur en xp de l'ennemi
	 * @return
	 */
	public abstract int getXp();

	/**
	 * Je n'ai pas trouvé comment me passer des getters si je veux pouvoir étendre une classe d'ennemi sans devoir tout réimplementer
	 * tout en pouvant malgré tout changer ses caractéristiques
	 * @return
	 */
	public abstract int getHauteur();
	public abstract int getLargeur();
	public abstract int getDemiHauteur();
	public abstract int getDemiLargeur();
	public abstract int getVitesse();
}
