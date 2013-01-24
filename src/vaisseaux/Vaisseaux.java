package vaisseaux;

import com.badlogic.gdx.math.Vector2;
public abstract class Vaisseaux {
	
	public Vector2 position;
	
	/**
	 * Constructeur qui initialise simplement le vecteur oldposition
	 */
	public Vaisseaux() {
		super();
	}

	abstract public int getLargeur();
	abstract public int getHauteur();

}
