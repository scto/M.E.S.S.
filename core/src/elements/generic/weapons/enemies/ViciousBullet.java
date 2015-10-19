package elements.generic.weapons.enemies;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.sprites.Animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class ViciousBullet extends EnemyWeapon implements InvocableWeapon{
	
	public static final Dimensions DIMENSIONS = Dimensions.VICIOUS_BULLET;
	public static final Pool<ViciousBullet> POOL = Pools.get(ViciousBullet.class);
	public static final float COLOR = CSG.gm.palette().convertARGB(1, 1, .5f, 1f);
	private static final float SLOW = 0.96f; 
	private boolean phase2 = false;
	
	@Override
	public void move() {
		if (!phase2) {
			dir.scl(SLOW);
			if (dir.len() < Stats.uDiv4) {
				Physic.dirToPlayer(dir, pos, DIMENSIONS.width, DIMENSIONS.halfWidth);
				dir.scl(85);
				phase2 = true;
			}
		} 
		angle += EndlessMode.delta25 * (Math.abs(dir.x) + Math.abs(dir.y));
		super.move();
	}
	
	@Override
	protected void setColor(SpriteBatch batch) {
		batch.setColor(COLOR);
	}
	
	@Override
	protected void removeColor(SpriteBatch batch) {
		batch.setColor(CSG.gm.palette().white);
	}
	
	@Override
	public void reset() {
		phase2 = false;
		super.reset();
	}
	@Override
	public void init(Vector2 position, Vector2 direction) {
		pos.set(position);
		dir.set(direction.x * 2, direction.y * 2);
		ENEMIES_LIST.add(this);
	}
	
	@Override	public void free() {						POOL.free(this);					}
	@Override	public Dimensions getDimensions() {			return DIMENSIONS;					}
	@Override	public EnemyWeapon invoke() {				return POOL.obtain();				}
	@Override	public Animations getAnimation() {			return Animations.BLUE_BALL;		}
}
