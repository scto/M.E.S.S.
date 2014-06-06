package elements.generic.weapons.enemies;

import jeu.CSG;
import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;

public class SmallFireball extends Fireball implements Poolable, InvocableWeapon {
	
	public static final int WIDTH = Fireball.WIDTH / 2, HALF_WIDTH = WIDTH/2, HEIGHT = (int) (WIDTH * 1.5), HALF_HEIGHT = HEIGHT / 2; 
	public static final Pool<SmallFireball> POOL = Pools.get(SmallFireball.class);
	public final float color = elements.generic.weapons.player.Fireball.couleurs[CSG.R.nextInt(elements.generic.weapons.player.Fireball.couleurs.length)];
	public static final int PK = 15;
	private static final float SPEED = initSpeed(36, PK);
	protected static final Phase[] PHASES = {new Phase(Behavior.STRAIGHT_ON, null, null, Animations.FIREBALL)};
	
	@Override	public void free() {					POOL.free(this);	}
	@Override	public float getWidth() {				return WIDTH;	}
	@Override	public float getHeight() {				return HEIGHT;	}
	@Override	public float getHalfHeight() {			return HALF_HEIGHT;	}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;	}
	@Override	public float getSpeed() {				return SPEED;	}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
	@Override	public Phase[] getPhases() {			return PHASES;	}
}
