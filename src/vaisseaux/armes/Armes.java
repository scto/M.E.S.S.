package vaisseaux.armes;

import jeu.Endless;
import vaisseaux.Vaisseaux;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;

public abstract class Armes extends Vaisseaux implements Poolable{
	
	public static Array<Armes> liste = new Array<Armes>(false, 30);
	public static Array<Armes> listeTirsDesEnnemis = new Array<Armes>(false, 30);
	protected Vector2 direction = new Vector2();
	private static boolean testCollision = false;
	protected float angle = 0;
	
	/**
	 * Initialise les vecteurs sans rien de pr�cis
	 */
	public Armes() {
		this.position = new Vector2();
	}
	
	/**
	 * Parcourt la liste une fois invoquant la methode mouvement et la methode afficher
	 * @param batch
	 */
	public static void affichageEtMouvement(SpriteBatch batch) {
		for (Armes a : liste) {
			a.afficher(batch);
			if (a.mouvementEtVerif() == false)			liste.removeValue(a, true);
		}
		testCollision = !testCollision;
		for (Armes a : listeTirsDesEnnemis) {
			a.afficher(batch);
			if (testCollision) {
				if (a.testCollisionVaisseau() == true) {
					// Si on a perdu faut pas la virer sinon on la voit plus � l'�cran
					if (Endless.aPerdu() == false) { 
						listeTirsDesEnnemis.removeValue(a, true);
						a.free();
					}
					break;
				}
			} else if (a.testCollsionAdds()) { // Si elle a touché un add on la vire
				listeTirsDesEnnemis.removeValue(a, true);
				a.free();
				break;
			}
			if (a.mouvementEtVerif() == false)			listeTirsDesEnnemis.removeValue(a, true);
		}
	}
	
	abstract public boolean testCollsionAdds();

	/**
	 * @return true il fait le virer
	 */
	public boolean testCollisionVaisseau() {
		return false;
	}

	public static void affichageEtMouvementSansParticules(SpriteBatch batch) {
		for (Armes a : liste) {
			a.afficherSansParticules(batch);
			if (a.mouvementEtVerif() == false)			liste.removeValue(a, true);
		}
		testCollision = !testCollision;
		for (Armes a : listeTirsDesEnnemis) {
			a.afficherSansParticules(batch);
			if (testCollision) {
				if (a.testCollisionVaisseau() == true) {
					listeTirsDesEnnemis.removeValue(a, true);
					a.free();
					break;
				}
			} else if (a.testCollsionAdds()) { // Si elle a touché un add on la vire
				listeTirsDesEnnemis.removeValue(a, true);
				a.free();
				break;
			}
			if (a.mouvementEtVerif() == false)			listeTirsDesEnnemis.removeValue(a, true);
		}
	}

	abstract public void afficherSansParticules(SpriteBatch batch);

	/**
	 * Methode servant � afficher les balles
	 * @param batch : batch principal

	 */
	abstract public void afficher(SpriteBatch batch);
	
	/**
	 * Fait bouger les objets et les enl�ves si ils ne sont plus � l'�cran
	 * @param batch
	 * return false si on doit le virer de la liste
	 */
	abstract public boolean mouvementEtVerif();

	/**
	 * Se contente d'afficher simplement les objets
	 * @param batch
	 */
	public static void affichage(SpriteBatch batch) {
		for (Armes a : liste)					a.afficher(batch);
		for (Armes a : listeTirsDesEnnemis)		a.afficher(batch);
	}

	public static void affichageSansParticules(SpriteBatch batch) {
		for (Armes a : liste)					a.afficherSansParticules(batch);
		for (Armes a : listeTirsDesEnnemis)		a.afficherSansParticules(batch);
	}
	/**
	 * Retourne la force de l'arme
	 * Si on a besoin de la force c'est qu'on peut la virer sans doute.. ? En tout cas pour le moment oui ! Donc free
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
	
	public static void initEffets() {
		for (Armes a : liste)
			a.initGraphismes();
		for (Armes a : listeTirsDesEnnemis)
			a.initGraphismes();
	}

	public abstract void initGraphismes();
	
	/**
	 * Methode appel�e par Physique quand la balle touche un ennemi
	 */
	public abstract void reset();

	public abstract void free();

	public Vector2 getDirection() {
		return null;
	}


	public void init(Vector2 position, float dEMI_LARGEUR, float demiHauteur, float modifVitesse) {
		System.out.println("Init de : " + position);
		this.position.x = position.x + dEMI_LARGEUR - getLargeur() / 2;
		this.position.y = position.y + demiHauteur - getHauteur() / 2;
		listeTirsDesEnnemis.add(this);
		initGraphismes();
	}


	public void init(Vector2 position, float modifVitesse) {
		this.position.x = position.x;
		this.position.y = position.y;
		this.direction.x = 0;
		this.direction.y = -1 * modifVitesse;
		System.out.println(direction.y);
		listeTirsDesEnnemis.add(this);
		initGraphismes();
	}

	public void init(Vector2 position, float modifVitesse, float angle, Vector2 direction) {
		this.position.x = position.x;
		this.position.y = position.y;
		this.direction.x = direction.x * modifVitesse;
		this.direction.y = direction.y * modifVitesse;
		this.angle = angle;
		listeTirsDesEnnemis.add(this);
		initGraphismes();
	}

	public void init(Vector2 position, Vector2 direction) {
		this.position.x = position.x;
		this.position.y = position.y;
		this.direction.x = direction.x;
		this.direction.y = direction.y;
		listeTirsDesEnnemis.add(this);
		initGraphismes();
	}
	
	public void init(Vector2 position, Vector2 direction, float modifVitesse) {
		this.position.x = position.x;
		this.position.y = position.y;
		this.direction.x = direction.x * modifVitesse;
		this.direction.y = direction.y * modifVitesse;
		listeTirsDesEnnemis.add(this);
		initGraphismes();
	}
}
