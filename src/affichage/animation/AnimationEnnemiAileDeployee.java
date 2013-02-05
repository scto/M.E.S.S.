package affichage.animation;

import menu.CSG;
import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiAileDeployee extends ModeleAnimation{
	
	private static final int COLONNES = 3;
	private static final int LIGNES = 0;
	public static TextureRegion[] tr = ModeleAnimation.getTextureRegion(COLONNES, LIGNES, TexMan.vaisseauxAileDeployee, 21, 21); 
	
	@Override
	public TextureRegion getTexture(float posY) {
		if (posY > CSG.HAUTEUR_ECRAN_PALLIER_1)									return tr[2];
		if (posY > CSG.HAUTEUR_ECRAN_PALLIER_2)					return tr[1];
		return tr[0];
	}
}
