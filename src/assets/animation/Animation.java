package assets.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface Animation {
	
	public abstract TextureRegion getAnimation(Trigger trigger);

}
