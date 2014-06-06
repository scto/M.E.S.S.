package menu;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.DeBase;

public class BasicMenu extends DeBase {
	
	public final Vector2 dir = new Vector2();
	public final Vector2 steer = new Vector2();
	public final Vector2 desired = new Vector2();
	public float steerScl;
	 
	public static final Pool<BasicMenu> POOL = new Pool<BasicMenu>(16,40) {
		@Override
		protected BasicMenu newObject() {
			return new BasicMenu();
		}
	};
	
	@Override
	public Invocable invoke() {
		BasicMenu e = POOL.obtain();
		LIST.add(e);
		return e;
	}
}
