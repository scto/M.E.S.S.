package elements.generic.weapons.enemies;

import jeu.CSG;
import assets.sprites.Animations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;

public class FragmentedMeteorite extends EnemyWeapon implements Poolable{
	
	private static final int WIDTH = CSG.screenWidth / 11, HALF_WIDTH = WIDTH/2;
	public static final Pool<FragmentedMeteorite> POOL = Pools.get(FragmentedMeteorite.class);
	private static final int PK = 10;
	private static final float SPEED = initSpeed(13, PK);
	protected static final Phase[] PHASES = {new Phase(Behavior.STRAIGHT_ON, null, null, Animations.METEORITE_SOLO)};
	
	public void init(final float x, final float y, Vector2 dir) {		
		this.dir.x = dir.x;
		this.dir.y = dir.y;
		this.dir.scl(getSpeed() * ((CSG.R.nextFloat()/2)+.5f) );
		pos.x = x;
		pos.y = y;
		ENEMIES_LIST.add(this);
	}
	
	@Override	public float getSpeed() {		return SPEED;	}
	@Override	public float getHalfHeight() {		return HALF_WIDTH;	}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;	}
	@Override	public void free() {				POOL.free(this);	}
	@Override	public float getWidth() {				return WIDTH;	}
	@Override	public float getHeight() {			return WIDTH;	}
	@Override	public Phase[] getPhases() {		return PHASES;	}
}
