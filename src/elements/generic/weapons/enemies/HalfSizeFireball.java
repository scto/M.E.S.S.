package elements.generic.weapons.enemies;

import jeu.CSG;
import assets.animation.Animated;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class HalfSizeFireball extends Fireball implements Poolable, InvocableWeapon {
	
	public static final int WIDTH = Fireball.WIDTH / 2, HALF_WIDTH = WIDTH/2, HEIGHT = (int) (WIDTH * 1.5), HALF_HEIGHT = HEIGHT / 2; 
	public static final Pool<HalfSizeFireball> POOL = Pools.get(HalfSizeFireball.class);
	public final float color = elements.generic.weapons.player.Fireball.couleurs[CSG.R.nextInt(elements.generic.weapons.player.Fireball.couleurs.length)];
	public static final int PK = 15;
	private static final float SPEED = initSpeed(36, PK);
	private static final Animated ANIMATED = initAnimation(2, PK);
	
	@Override	public void free() {					POOL.free(this);	}
	@Override	public int getWidth() {					return WIDTH;	}
	@Override	public int getHeight() {				return HEIGHT;	}
	@Override	public int getHalfHeight() {			return HALF_HEIGHT;	}
	@Override	public int getHalfWidth() {				return HALF_WIDTH;	}
	@Override	protected float getSpeed() {			return SPEED;	}
	@Override	public TextureRegion getTexture() {		return ANIMATED.getTexture(now);	}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
}
