package elements.generic.enemies.individual.bosses;

import jeu.Stats;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.BlueBulletFast;


public class AddBossStat extends Enemy {

	
	static final float WIDTH = Stats.WIDTH_ADD_BOSS_SAT;
	static final float HALF_WIDTH = WIDTH / 2;
	public static Pool<AddBossStat> pool = Pools.get(AddBossStat.class);
	private static final float OFFSET_TIR = HALF_WIDTH - BlueBulletFast.HALF_WIDTH;
	
	public static final int PK = 16;
	private static final int LVL = 1;
	protected static final float FIRERATE = initFirerate(1.7f, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(0, PK);
	protected static final float SPEED = initSpeed(10, PK);
	private static final int HP = initHp(25, PK);
	private static final int EXPLOSION = initExplosion(15, PK);
	protected static final int BASE_XP = Enemy.initXp(25, PK);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final Phase[] PHASES = {
		new Phase(				Behavior.GO_ON_PLAYER,				Gatling.BLUE_BULLET_FAST,				Shot.TIR_TOUT_DROIT,				Animations.AILE_DEPLOYEES				)		};	

	@Override
	public void reset() {
		super.reset();
		nextShot = INIT_NEXT_SHOT;
		pos.x = 200;
		pos.y = 200;
		dir.x = 0;
		dir.y = getSpeed();
	}

	public void lancer(float dirX, float dirY, float x, float y, float angle) {
		dir.x = dirX;
		dir.y = dirY;
		pos.x = x;
		pos.y = y;
		this.angle = angle;
		LIST.add(this);
	}

	@Override
	public Vector2 getShotPosition(int numeroTir) {
		TMP_POS.x = pos.x + OFFSET_TIR;
		TMP_POS.y = pos.y + OFFSET_TIR;
		return TMP_POS;
	}
	
	/**
	 * Rotation factor
	 */
	@Override	public float getFloatFactor() {				return 0.1f;	}
	@Override	public void setAngle(float angle) {				super.setAngle(angle+90);	}
	@Override	protected Sound getExplosionSound() {			return SoundMan.explosion2;	}
	@Override	public void free() {						pool.free(this);		}
	@Override	protected int getMaxHp() {					return HP;	}
	@Override	public int getXp() {						return XP;	}
	@Override	public float getHeight() {					return (int)WIDTH;	}
	@Override	public float getWidth() {						return (int)WIDTH;	}
	@Override	public float getHalfHeight() {				return (int)HALF_WIDTH;	}
	@Override	public float getHalfWidth() {					return (int)HALF_WIDTH;	}
	@Override	public float getBulletSpeedMod() {			return 0.015f;	}
	@Override	public float getShootingAngle() {				return angle;	}
	@Override	public Vector2 getShootingDir() {			return dir;	}
	@Override	public float getDirectionY() {				return dir.y;	}
	@Override	public float getDirectionX() {				return dir.x;	}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public int getBonusValue() {				return BASE_XP;			}
	@Override	public int getExplosionCount() {			return EXPLOSION;									}
	@Override	public Phase[] getPhases() {				return PHASES;	}
	@Override	public float getSpeed() {					return SPEED;	}
	@Override	public float getFirerate() {				return FIRERATE;	}
	
}
