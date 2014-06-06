package elements.generic.weapons.enemies;

import jeu.CSG;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class FragWeapon extends EnemyWeapon {
	
	public static final int WIDTH = CSG.screenWidth / 30, HALF_WIDTH = WIDTH/2;
	public static final float FIRERATETIR = .2f, TIME = 2;
	public static final Pool<FragWeapon> POOL = Pools.get(FragWeapon.class);
	public static final int PK = 3;
	private static final float SPEED = initSpeed(26, PK);
	
	@Override
	public boolean move() {
		if (TIME < now) {
			final FragWeapon a = FragWeapon.POOL.obtain();
			a.pos.x = pos.x;
			a.pos.y = pos.y;
			a.dir.x = dir.x;
			a.dir.y = dir.y;
			a.dir.rotate(5);
			ENEMIES_LIST.add(a);
			
			final FragWeapon b = FragWeapon.POOL.obtain();
			b.pos.x = pos.x;
			b.pos.y = pos.y;
			b.dir.x = dir.x;
			b.dir.y = dir.y;
			b.dir.rotate(-5);
			ENEMIES_LIST.add(b);

			return true;
		} 
		return super.move();
	}
	
	@Override	public float getSpeed() {				return SPEED;						}
	@Override	public float getWidth() {				return WIDTH;									}
	@Override	public float getHeight() {				return WIDTH;									}
	@Override	public void free() {					POOL.free(this);								}
	@Override	public float getHalfHeight() {			return HALF_WIDTH;								}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;								}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
}
