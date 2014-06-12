package elements.generic.weapons.enemies;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;
import assets.sprites.Animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;

public class ViciousBullet extends EnemyWeapon implements InvocableWeapon{
	
	public static final Pool<ViciousBullet> POOL = Pools.get(ViciousBullet.class);
	public static final int WIDTH = (int) (Stats.WIDTH_WEAPON_NORMAL * 0.90f), HALF_WIDTH = WIDTH / 2;
	public static final float COLOR = AssetMan.convertARGB(1, 1, .5f, 1f);
	private static final float SLOW = 0.96f; 
	private boolean change = false;
	public static final int PK = 6;
	private static final float SPEED = initSpeed(40, PK);
	protected static final Phase[] PHASES = {
		new Phase(Behavior.SLOW, 				null, null, Animations.BLUE_BALL),
		new Phase(Behavior.STRAIGHT_ON,	 		null, null, Animations.BLUE_BALL)
		};
	
	@Override
	public boolean mouvementEtVerif() {
		if (!EndlessMode.alternate) {
			if (!change)
				dir.scl(SLOW);
			if (dir.len() < CSG.gameZoneWidthDiv20 && !change) {
				Physic.dirToPlayer(dir, pos, WIDTH, HALF_WIDTH);
				change = true;
				dir.scl(getSpeed());
			}
		}
		return super.mouvementEtVerif();
	}
	
	@Override
	public boolean move() {
		if (dir.len() < Stats.uDiv4 && index == 0) {
			changePhase();
			Physic.dirToPlayer(dir, pos, WIDTH, HALF_WIDTH);
			dir.scl(getSpeed());
		}
		return super.move();
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		batch.setColor(COLOR);
		super.draw(batch);
		batch.setColor(AssetMan.WHITE);
	}
	@Override
	public void reset() {
		change = false;
		index = 0;
		super.reset();
	}
	@Override
	public void init(Vector2 position, Vector2 direction) {
		this.pos.x = position.x;
		this.pos.y = position.y;
		this.dir.x = direction.x * 2;
		this.dir.y = direction.y * 2;
		index = 0;
		ENEMIES_LIST.add(this);
	}
	/**
	 * Slowing factor
	 */
	@Override	public float getFloatFactor() {				return 0.96f;	}
	@Override	public Phase[] getPhases() {				return PHASES;	}
	@Override	public float getWidth() {						return WIDTH;	}
	@Override	public float getHeight() {					return WIDTH;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	public float getHalfHeight() {				return HALF_WIDTH;	}
	@Override	public float getHalfWidth() {					return HALF_WIDTH;	}
	@Override	public float getSpeed() {				return SPEED;	}
	@Override	public EnemyWeapon invoke() {				return POOL.obtain();	}
}
