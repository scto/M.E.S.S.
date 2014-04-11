package assets.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface Animated {
	
	int BLUE_BULLET = 1;
//	int FIREBALL = 2;
	int METEORITE = 3;
//	int T_WEAPON = 4;
	int MINE = 5;

	 TextureRegion getTexture(float tps);
}
