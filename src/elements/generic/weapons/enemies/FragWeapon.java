package elements.generic.weapons.enemies;

import jeu.CSG;
import assets.animation.Animated;
import assets.animation.AnimationFragWeapon;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.particles.Particles;

public class FragWeapon extends EnemyWeapon implements Poolable, InvocableWeapon{
	
	public static final int WIDTH = CSG.screenWidth / 30, HALF_WIDTH = WIDTH/2;
	public static final float CADENCETIR = .2f;
	public static final Pool<FragWeapon> POOL = Pools.get(FragWeapon.class);
	public static final int PK = 3;
	private static final float SPEED = initSpeed(26, PK);
	private static final Animated ANIMATED = initAnimation(1, PK);
	
	@Override
	public boolean mouvementEtVerif() {
		if (AnimationFragWeapon.TOTAL_TIME < now) {
			Particles.addSparkle(this);
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

			return false;
		} 
		return super.mouvementEtVerif();
	}
	
	@Override	public TextureRegion getTexture() {		return ANIMATED.getTexture(now);		}
	@Override	protected float getSpeed() {			return SPEED;						}
	@Override	public int getWidth() {					return WIDTH;									}
	@Override	public int getHeight() {				return WIDTH;									}
	@Override	public void free() {					POOL.free(this);								}
	@Override	public int getHalfHeight() {			return HALF_WIDTH;								}
	@Override	public int getHalfWidth() {				return HALF_WIDTH;								}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
}
