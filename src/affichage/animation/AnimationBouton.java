package affichage.animation;

import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationBouton {

	private static final int COLONNES = 3;
	private static final int LIGNES = 0;
	private static TextureRegion[] tr = initTexture();

	public TextureRegion getTexture() {
		return tr[0];
	}
	private static TextureRegion[] initTexture() {
		TextureRegion[][] sheet = TextureRegion.split(TexMan.boutonRouge, 23, 22); 
		TextureRegion[] tr = new TextureRegion[COLONNES];
	    for(int i = 0; i < COLONNES; i++)
	      	tr[i] = sheet[LIGNES][i];
	    return tr;
	}
}
