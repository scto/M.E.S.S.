package assets.animation;

import vaisseaux.ennemis.particuliers.EnnemiBossQuad;
import menu.CSG;
import jeu.Stats;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationBossQuad{
	
	public static AtlasRegion bonEtat, moyenEtat, mauvaisEtat;
	
	public static void initAnimation(){
		bonEtat = CSG.getAssetMan().getAtlas().findRegion("bossquad1");
		moyenEtat = CSG.getAssetMan().getAtlas().findRegion("bossquad2");
		mauvaisEtat = CSG.getAssetMan().getAtlas().findRegion("bossquad3");
	}
	
	public static TextureRegion getTexture(int phase) {
		if (phase == 3)	return mauvaisEtat;
		if (phase == 2)	return moyenEtat;
		return bonEtat;
	}
}
