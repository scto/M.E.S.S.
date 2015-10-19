package elements.generic.weapons.enemies;

import jeu.CSG;
import jeu.mode.EndlessMode;
import behind.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;
import elements.particular.particles.individual.PrecalculatedParticles;
import elements.particular.particles.individual.explosions.Explosion;

public class Meteorite extends EnemyWeapon implements Poolable, InvocableWeapon {
	
	public static final Dimensions DIMENSIONS = Dimensions.METEORITE;
	public static final Pool<Meteorite> POOL = Pools.get(Meteorite.class);
	private static final Vector2 tmp_dir = new Vector2(0,1);
	
	@Override
	public void move() {
		if (Animations.METEORITE_TOTAL_TIME < now) {
			SoundMan.playBruitage(SoundMan.explosion1);
			Explosion.add(10, this, PrecalculatedParticles.RANDDOM_REDS);
			for (int i = CSG.R.nextInt(6 - EndlessMode.difficulty); i < 10; i++)
				FragmentedMeteorite.POOL.obtain().init(pos.x + DIMENSIONS.halfWidth,  pos.y + DIMENSIONS.halfWidth, tmp_dir.rotate(CSG.R.nextFloat() * 360));
			now = 0;
			pos.x = -3000;
		}
		super.move();
	}

	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	@Override	public void free() {						POOL.free(this);							}
	@Override	public EnemyWeapon invoke() {				return POOL.obtain();						}
	@Override	public Animations getAnimation() {			return Animations.METEORITE;				}

}
