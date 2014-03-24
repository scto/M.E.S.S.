package elements.generic.weapons.enemies;

import jeu.Stats;
import assets.animation.Animated;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmeBouleTir extends EnemyWeapon implements Poolable{
	
	public static final int LARGEUR = Stats.WIDTH_WEAPON_MINUS_NORMAL, DEMI_LARGEUR = LARGEUR/2; 
	public static final Pool<ArmeBouleTir> POOL = Pools.get(ArmeBouleTir.class);
	private static final int PK = 2;
	private static final float SPEED = initSpeed(24, PK);
	private static final Animated ANIMATED = initAnimation(1, PK);
	
	@Override	protected float getSpeed() {				return SPEED;	}
	@Override	public int getWidth() {						return LARGEUR;	}
	@Override	public int getHeight() {					return LARGEUR;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	public int getHalfHeight() {				return DEMI_LARGEUR;	}
	@Override	public int getHalfWidth() {					return DEMI_LARGEUR;	}
	@Override	public TextureRegion getTexture() {			return ANIMATED.getTexture(now);	}
}
