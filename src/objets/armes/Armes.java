package objets.armes;

import objets.Objet;
import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.joueur.ArmeJoueur;
import jeu.EndlessMode;
import jeu.Physique;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;

public abstract class Armes extends Objet implements Poolable{
	
	public static Array<ArmeJoueur> liste = new Array<ArmeJoueur>(false, 50);
	public static Array<ArmeEnnemi> listeTirsDesEnnemis = new Array<ArmeEnnemi>(false, 50);
	public Vector2 direction = new Vector2();
	private static boolean testCollision = false;
	public float angle = 0, maintenant = 0;

	public static void affichageEtMouvement(SpriteBatch batch) {
		for (ArmeJoueur a : liste) {
			a.maintenant += EndlessMode.delta;
			a.afficher(batch);
			if (a.mouvementEtVerif() == false) {
				liste.removeValue(a, true);
				a.free();
			}
		}
		
		testCollision = !testCollision;
		for (ArmeEnnemi a : listeTirsDesEnnemis) {
			a.maintenant += EndlessMode.delta;
			a.afficher(batch);
			if (testCollision) {
				if (a.testCollisionVaisseau()) {
					enlever(a);
					break;
				}
			} else if (a.testCollsionAdds()) {
				enlever(a);
				break;
			}
			if (a.mouvementEtVerif() == false) {
				enlever(a);
			}
		}
	}

	private static void enlever(ArmeEnnemi a) {
		listeTirsDesEnnemis.removeValue(a, true);
		a.free();
	}

	public void afficher(SpriteBatch batch) {
		batch.draw(getTexture(), position.x, position.y,
		// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
		getDemiLargeur(), getDemiHauteur(),
		// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
		getLargeur(), getHauteur(),
		//scaleX the scale of the rectangle around originX/originY in x ET Y
		1,1,
		// L'ANGLE DE ROTATION
		angle,
		//FLIP OU PAS
		false);
	}
	
	abstract public TextureRegion getTexture();
	abstract public float getDemiLargeur();
	abstract public float getDemiHauteur();
	
	/**
	 * Fait bouger les objets et les enl�ves si ils ne sont plus � l'�cran
	 * @param batch
	 * return false si on doit le virer de la liste et appeler free()
	 */
	public boolean mouvementEtVerif() {
		return Physique.mouvementDeBase(getLargeur(), getHauteur(), direction, position);
	}

	public static void affichage(SpriteBatch batch) {
		for (ArmeJoueur a : liste) {
			a.afficher(batch);
			a.maintenant += EndlessMode.delta;
		}
		for (Armes a : listeTirsDesEnnemis)
			a.afficher(batch);
	}

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
	
	/**
	 * Methode appel�e par Physique quand la balle touche un ennemi
	 */
	public void reset() {
		maintenant = 0;
	}

	public abstract void free();

	public Vector2 getDirection() {
		return direction;
	}

	public static void clear() {
		for (Armes a : liste) a.free();
		liste.clear();
		for (Armes a : listeTirsDesEnnemis) a.free();
		listeTirsDesEnnemis.clear();
	}


}
