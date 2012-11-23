package vaisseaux;

import com.badlogic.gdx.math.Vector2;
public abstract class Vaisseaux {
	
	public Vector2 position;
	public Vector2 direction;
	public Vector2 oldPosition;
	
	/**
	 * Constructeur qui initialise simplement le vecteur oldposition
	 */
	public Vaisseaux() {
		super();
		oldPosition = new Vector2();
	}

	abstract public int getLargeur();
	abstract public int getHauteur();

}
