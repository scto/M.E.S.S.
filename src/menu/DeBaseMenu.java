package menu;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.DeBase;

public class DeBaseMenu extends DeBase {
	
	public final Vector2 dir = new Vector2();
	public final Vector2 steer = new Vector2();
	public final Vector2 desired = new Vector2();
	public float steerScl;
	 
	public static final Pool<DeBaseMenu> POOL = new Pool<DeBaseMenu>(16,40) {
		@Override
		protected DeBaseMenu newObject() {
			return new DeBaseMenu();
		}
	};
	public static final Invocable ref = new DeBaseMenu();
	
	@Override
	public Invocable invoquer() {
		DeBaseMenu e = POOL.obtain();
		LIST.add(e);
		e.init();
		return e;
	}
}
