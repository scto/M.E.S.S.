package assets.sprites;

import com.badlogic.gdx.math.Vector2;

public interface AnimUser {

	public float getNow();
	public float getPvPercentage();
	public boolean isInGoodShape();
	public int getAnimIndex();
	public Vector2 getPosition();

}
