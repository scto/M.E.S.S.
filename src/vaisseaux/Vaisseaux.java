package vaisseaux;

import com.badlogic.gdx.math.Vector2;
public abstract class Vaisseaux {
	
	public Vector2 position = new Vector2();

	abstract public int getLargeur();
	abstract public int getHauteur();

}
