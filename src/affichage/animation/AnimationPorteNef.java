package affichage.animation;

import vaisseaux.ennemis.particuliers.EnnemiPorteNef;
import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationPorteNef{
	
	public static AtlasRegion bonEtat = TexMan.atlas.findRegion("portenef1");
	public static AtlasRegion mauvaisEtat = TexMan.atlas.findRegion("portenef2");
	
	public TextureRegion getTexture(int pv) {
		if (pv < EnnemiPorteNef.DEMI_PVMAX)
			return mauvaisEtat;
		return bonEtat;
	}
}
