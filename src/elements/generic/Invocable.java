package elements.generic;

import com.badlogic.gdx.math.Vector2;

public interface Invocable {
	
	public int getXp();
	public Invocable invoke();
	public void setPosition(Vector2 pos);
}
