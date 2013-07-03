package assets.animation;

import menu.CSG;
import jeu.Stats;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationBossMine{
	
	public static AtlasRegion bonEtat, moyenEtat;
	
	public static void initAnimation(){
		bonEtat = CSG.getAssetMan().getAtlas().findRegion("bossmine");
		moyenEtat = CSG.getAssetMan().getAtlas().findRegion("bossminecasse");
	}
	
	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.DEUXTIERS_PVMAX_BOSS_MINE) return moyenEtat;
		return bonEtat;
	}
}
