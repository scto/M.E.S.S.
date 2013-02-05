package affichage.animation;

import vaisseaux.ennemis.particuliers.EnnemiPorteNef;
import menu.CSG;
import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationPorteNef{
	
	private static final int COLONNES = 2;
	private static final int LIGNES = 0;
	public static TextureRegion[] tr = ModeleAnimation.getTextureRegion(COLONNES, LIGNES, TexMan.porteNef, 68, 55); 
	
	public TextureRegion getTexture(int pv) {
		if (pv < EnnemiPorteNef.DEMI_PVMAX)
			return tr[1];
		return tr[0];
	}
}
