package vaisseaux.armes;

import java.util.ArrayList;
import java.util.List;

import vaisseaux.Vaisseaux;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public abstract class Armes extends Vaisseaux{
	
	protected Vector2 direction;
	protected Rectangle collision = new Rectangle();
	//public static List<Armes> liste = new ArrayList<Armes>(30);
	public static Array<Armes> liste = new Array<Armes>(30);
	public static List<Armes> listeTirsDesEnnemis = new ArrayList<Armes>(30);
	
	
	/** on positionne la balle et on lui donne sa vitesse et sa direction. La vitesse est gerée en interne avec un vecteur qui va etre multiplié par
	 * celui de la direction pour tout gérer en un seul vecteur. Le boolean sert a dire si c'est en haut ou en bas (donc joueur ou ennemi)
	 * @param position2 ne jamais croiser les effluves
	 * @param directionDeLaBalle Entre 0 ET 1 !!
	 * @param hautTrue
	 */
	//abstract public void ajouterBalle(Vector2 position2, Vector2 directionDeLaBalle);
	
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
		if(ennemis) listeTirsDesEnnemis.add(this);
		else liste.add(this);
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
		if(ennemi) listeTirsDesEnnemis.add(this);
		else liste.add(this);
	}


	// OLD public static void affichageEtMouvement(List<Armes> listeTirs, SpriteBatch batch) {
	/**
	 * Parcourt la liste une fois invoquant la methode mouvement et la methode afficher
	 * @param batch
	 */
	public static void affichageEtMouvement(SpriteBatch batch) {
//		for (int a = 0; a < liste.size(); a++) {
//			liste.get(a).afficher(batch);
//			if(liste.get(a).mouvementEtVerif() == false) liste.remove(a);
//		}
		for(Armes a : liste){
			a.afficher(batch);
			if(a.mouvementEtVerif() == false)
				liste.removeValue(a, true);
		}
		for (int e = 0; e < listeTirsDesEnnemis.size(); e++) {
			listeTirsDesEnnemis.get(e).afficher(batch);
			if(listeTirsDesEnnemis.get(e).mouvementEtVerif() == false) listeTirsDesEnnemis.remove(e);
		}
	}

	/**
	 * Methode servant à afficher les balles
	 * @param batch : batch principal
	 */
	abstract public void afficher(SpriteBatch batch);
	
	/**
	 * Fait bouger les objets et les enlèves si ils ne sont plus à l'écran
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
		//for (int a = 0; a < liste.size(); a++) liste.get(a).afficher(batch);
		for(Armes a : liste) a.afficher(batch);
		for (int a = 0; a < listeTirsDesEnnemis.size(); a++) listeTirsDesEnnemis.get(a).afficher(batch);
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
