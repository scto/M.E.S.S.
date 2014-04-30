package elements.generic.weapons.enemies;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;
import assets.animation.Animated;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class VicousBullet extends EnemyWeapon implements InvocableWeapon{
	
	public static final Pool<VicousBullet> POOL = Pools.get(VicousBullet.class);
	public static final int WIDTH = (int) (Stats.WIDTH_WEAPON_NORMAL * 0.90f), HALF_WIDTH = WIDTH / 2;
	public static final float COLOR = AssetMan.convertARGB(1, 1, .5f, 1f);
	private static final float SLOW = 0.96f; 
	private boolean change = false;
	public static final int PK = 6;
	private static final float SPEED = initSpeed(40, PK);
	private static final Animated ANIMATED = initAnimation(1, PK);
	
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
	public void draw(SpriteBatch batch) {
		batch.setColor(COLOR);
		super.draw(batch);
		batch.setColor(AssetMan.WHITE);
	}
	@Override
	public void reset() {
		change = false;
		super.reset();
	}
	@Override
	public void init(Vector2 position, Vector2 direction) {
		this.pos.x = position.x;
		this.pos.y = position.y;
		this.dir.x = direction.x * 2;
		this.dir.y = direction.y * 2;
		ENEMIES_LIST.add(this);
	}
	
	@Override	public int getWidth() {						return WIDTH;	}
	@Override	public int getHeight() {					return WIDTH;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	public int getHalfHeight() {				return HALF_WIDTH;	}
	@Override	public int getHalfWidth() {					return HALF_WIDTH;	}
	@Override	protected float getSpeed() {				return SPEED;	}
	@Override	public TextureRegion getTexture() {			return ANIMATED.getTexture(now);	}
	@Override	public EnemyWeapon invoke() {				return POOL.obtain();	}
}
