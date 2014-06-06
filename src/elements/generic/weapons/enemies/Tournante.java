package elements.generic.weapons.enemies;

import assets.AssetMan;
import assets.sprites.Animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;

public class Tournante extends LaserWeapon implements Poolable, InvocableWeapon{
	
	public static final Pool<Tournante> POOL = Pools.get(Tournante.class);
	public static final float COLOR = AssetMan.convertARGB(1, 1, 1, .85f);
	public static final int PK = 14;
	public static final float SPEED = initSpeed(10, PK);
	protected static final Phase[] PHASES = {new Phase(Behavior.COMMA_RIGHT_MUL2, null, null, Animations.BLUE_BALL)};
	
	@Override
	public void draw(SpriteBatch batch) {
		batch.setColor(COLOR);
		super.draw(batch);
		batch.setColor(AssetMan.WHITE);
	}

	@Override	public Phase[] getPhases() {			return PHASES;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
	@Override	public float getSpeed() {				return SPEED;	}
}
