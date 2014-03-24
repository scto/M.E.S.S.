package elements.generic.weapons.enemies;

import jeu.CSG;
import jeu.EndlessMode;
import assets.SoundMan;
import assets.animation.Animated;
import assets.animation.AnimationMeteorite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import elements.particular.particles.individual.Explosion;

public class Meteorite extends EnemyWeapon implements Poolable, InvocableWeapon {
	
	public static final int WIDTH = CSG.screenWidth / 6, HALF_WIDTH = WIDTH/2;
	public static final Pool<Meteorite> POOL = Pools.get(Meteorite.class);
	private static final Vector2 tmp_dir = new Vector2(0,1);
	public static final int PK = 11;
	private static final float SPEED = initSpeed(26, PK);
	private static final Animated ANIMATED = initAnimation(3, PK);
	
	@Override
	public boolean mouvementEtVerif() {
		if (AnimationMeteorite.TIME_TOTAL < now) {
			SoundMan.playBruitage(SoundMan.explosion1);
			Explosion.add(HALF_WIDTH, this);
			for (int i = CSG.R.nextInt(6 - EndlessMode.modeDifficulte); i < 10; i++) {
				FragmentedMeteorite.POOL.obtain().init(pos.x + HALF_WIDTH,  pos.y + HALF_WIDTH, tmp_dir.rotate(CSG.R.nextFloat() * 360));
			}
			return false;
		} 
		return super.mouvementEtVerif();
	}

	@Override	public int getWidth() {						return WIDTH;								}
	@Override	public int getHeight() {					return WIDTH;								}
	@Override	public void free() {						POOL.free(this);							}
	@Override	public int getHalfHeight() {				return HALF_WIDTH;							}
	@Override	public int getHalfWidth() {					return HALF_WIDTH;							}
	@Override	protected float getSpeed() {				return SPEED;					}
	@Override	public TextureRegion getTexture() {			return ANIMATED.getTexture(now);	}
	@Override	public EnemyWeapon invoke() {				return POOL.obtain();	}

}
