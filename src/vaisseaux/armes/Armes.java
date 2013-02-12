package vaisseaux.armes;

import vaisseaux.Vaisseaux;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public abstract class Armes extends Vaisseaux{
	
	protected Vector2 direction;
	protected Rectangle collision = new Rectangle();
	public static Array<Armes> liste = new Array<Armes>(false, 30);
	public static Array<Armes> listeTirsDesEnnemis = new Array<Armes>(false, 30);
	
	/**
	 * On donne la balle sa position et direction initiale. 
	 * @param position : la position de celui qui tir
	 * @param direction : la direction, habituellement (0,1) ou (0,-1)
	 * @param listeTir 
	 */
	public Armes(float posX, float posY, float dirX, float dirY, boolean ennemis) {
		super();
		this.position = new Vector2(posX, posY);
		this.direction = new Vector2(dirX, dirY);
		if (ennemis)
			listeTirsDesEnnemis.add(this);
		else
			liste.add(this);
	}
	
	/**
	 * Initialise les vecteurs sans rien de précis
	 */
	public Armes() {
		this.direction = new Vector2();
		this.position = new Vector2();
	}
	
	/**
	 * Constructeur passant un vecteur en direction pour les armes qui en ont besoin en interne
	 * @param posX
	 * @param posY
	 * @param dir
	 * @param ennemi
	 */
	public Armes(float posX, float posY, Vector2 dir, boolean ennemi) {
		position = new Vector2(posX, posY);
		direction = dir;
		if (ennemi)
			listeTirsDesEnnemis.add(this);
		else
			liste.add(this);
	}

	/**
	 * Parcourt la liste une fois invoquant la methode mouvement et la methode afficher
	 * @param batch
	 */
	public static void affichageEtMouvement(SpriteBatch batch) {
		for (Armes a : liste) {
			a.afficher(batch);
			if (a.mouvementEtVerif() == false)
				liste.removeValue(a, true);
		}
		for (Armes a : listeTirsDesEnnemis) {
			a.afficher(batch);
			if (a.mouvementEtVerif() == false)
				listeTirsDesEnnemis.removeValue(a, true);
		}
	}

	/**
	 * Methode servant à afficher les balles
	 * @param batch : batch principal
	 * @param delta 
	 */
	abstract public void afficher(SpriteBatch batch);
	
	/**
	 * Fait bouger les objets et les enlèves si ils ne sont plus à l'écran
	 * @param delta 
	 * @param batch
	 */
	abstract public boolean mouvementEtVerif();

	/**
	 * Renvoie le rectangle de collision de l'objet
	 * @return
	 */
	abstract public Rectangle getRectangleCollision();
	/**
	 * Se contente d'afficher simplement les objets
	 * @param batch
	 */
	public static void affichage(SpriteBatch batch) {
		for (Armes a : liste)
			a.afficher(batch);
		for (Armes a : listeTirsDesEnnemis)
			a.afficher(batch);
	}
	/**
	 * Retourne la force de l'arme
	 * @return FORCE
	 */
	public abstract int getForce();

	/**
	 * necessaire pour tester les collisions des balles ennemies avec le centre du vaisseau
	 * @return La largeur
	 */
	public abstract int getLargeur();
	/**
	 * necessaire pour tester les collisions des balles ennemies avec le centre du vaisseau
	 * @return La hauteur
	 */
	public abstract int getHauteur();

	public abstract void init(float posX, float posY, int dirX, int dirY, boolean ennemi);
	
	public abstract void free();

}
