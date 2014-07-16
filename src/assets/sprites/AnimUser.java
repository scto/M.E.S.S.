package assets.sprites;

import com.badlogic.gdx.math.Vector2;

public interface AnimUser {

	float getNow();
	float getPvPercentage();
	boolean isInGoodShape();
	int getAnimIndex();
	Vector2 getPosition();

}
