package objets;

import jeu.CSG;

import com.badlogic.gdx.math.Vector2;
public abstract class Objet {
	
	public Vector2 position = new Vector2(CSG.DEMI_LARGEUR_ZONE_JEU, CSG.HAUTEUR_ECRAN);

	public abstract int getLargeur();
	public abstract int getHauteur();

}
