package menu;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import elements.generic.enemies.individual.lvl1.Basic;

public class BasicMenu extends Basic {
	
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
}
