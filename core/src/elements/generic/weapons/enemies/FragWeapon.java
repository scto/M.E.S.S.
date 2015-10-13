package elements.generic.weapons.enemies;

import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class FragWeapon extends EnemyWeapon {
	
	public static final Dimensions DIMENSIONS = Dimensions.FRAG_WEAPON;
	public static final float TIME = 2;
	public static final Pool<FragWeapon> POOL = Pools.get(FragWeapon.class);
	
	@Override
	public void move() {
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

			pos.x = -10000;
		}
		super.move();
	}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	@Override	public void free() {					POOL.free(this);								}
	@Override	public Animations getAnimation() {		return Animations.BLUE_BALL;	}
}
