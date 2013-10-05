package objets.armes.joueur;

import java.util.Random;

import objets.armes.Armes;

import com.badlogic.gdx.math.Rectangle;

public abstract class ArmeJoueur extends Armes{
	
	protected static int numeroCouleur = 1;
	protected static Random r = new Random();
	protected static Rectangle tmpRectangle = new Rectangle();
	public static void roueCouleurs() {
		numeroCouleur++;
		if (numeroCouleur > 1) numeroCouleur = 0;
	}
	
	public abstract float getColor();
	
	/**
	 * Retourne la force de l'arme
	 * Si on a besoin de la force c'est qu'on peut la virer sans doute.. ? En tout cas pour le moment oui ! Donc free
	 * @return FORCE
	 */
	public abstract int getForce();

	public Rectangle getRectangleCollision() {
		tmpRectangle.x = position.x;
		tmpRectangle.y = position.y;
		tmpRectangle.height = getHauteur();
		tmpRectangle.width = getLargeur();
		return tmpRectangle;
	}
}

