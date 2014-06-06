package elements.generic.weapons.enemies;

import jeu.CSG;
import jeu.mode.EndlessMode;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.particular.particles.individual.explosions.Explosion;

public class Meteorite extends EnemyWeapon implements Poolable, InvocableWeapon {
	
	public static final int WIDTH = CSG.screenWidth / 6, HALF_WIDTH = WIDTH/2;
	public static final Pool<Meteorite> POOL = Pools.get(Meteorite.class);
	private static final Vector2 tmp_dir = new Vector2(0,1);
	public static final int PK = 11;
	private static final float SPEED = initSpeed(26, PK);
	protected static final Phase[] PHASES = {new Phase(Behavior.STRAIGHT_ON, null, null, Animations.METEORITE)};
	
	@Override
	public boolean move() {
		if (Animations.METEORITE_TOTAL_TIME < now) {
			SoundMan.playBruitage(SoundMan.explosion1);
			Explosion.add(HALF_WIDTH, this);
			for (int i = CSG.R.nextInt(6 - EndlessMode.difficulty); i < 10; i++) {
				FragmentedMeteorite.POOL.obtain().init(pos.x + HALF_WIDTH,  pos.y + HALF_WIDTH, tmp_dir.rotate(CSG.R.nextFloat() * 360));
			}
			return true;
		}
		return super.move();
	}

	@Override	public float getWidth() {					return WIDTH;								}
	@Override	public float getHeight() {					return WIDTH;								}
	@Override	public void free() {						POOL.free(this);							}
	@Override	public float getHalfHeight() {				return HALF_WIDTH;							}
	@Override	public float getHalfWidth() {				return HALF_WIDTH;							}
	@Override	public float getSpeed() {					return SPEED;								}
	@Override	public EnemyWeapon invoke() {				return POOL.obtain();						}
	@Override	public Phase[] getPhases() {				return PHASES;								}

}
