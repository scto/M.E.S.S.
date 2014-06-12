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


public class AddStat extends Enemy {

	
	public static final int PK = 1, LVL = 1, HP = initHp(25, PK), EXPLOSION = initExplosion(15, PK), BASE_XP = Enemy.initXp(25, PK), XP = getXp(BASE_XP, LVL);
	static final float WIDTH = Stats.WIDTH_ADD_BOSS_SAT, HALF_WIDTH = WIDTH / 2, OFFSET_TIR = HALF_WIDTH - BlueBulletFast.HALF_WIDTH, FIRERATE = initFirerate(1.7f, PK), INIT_NEXT_SHOT = initNextShot(0, PK), SPEED = initSpeed(10, PK);
	public static Pool<AddStat> pool = Pools.get(AddStat.class);
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
	@Override	public float getFloatFactor() {				return 0.1f;					}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	protected Sound getExplosionSound() {		return SoundMan.explosion2;		}
	@Override	public void setAngle(float angle) {			super.setAngle(angle+90);		}
	@Override	public float getHalfHeight() {				return HALF_WIDTH;				}
	@Override	public float getHalfWidth() {				return HALF_WIDTH;				}
	@Override	public int getExplosionCount() {			return EXPLOSION;				}
	@Override	public void free() {						pool.free(this);				}
	@Override	public float getFirerate() {				return FIRERATE;				}
	@Override	public int getBonusValue() {				return BASE_XP;					}
	@Override	public float getBulletSpeedMod() {			return 0.015f;					}
	@Override	public Phase[] getPhases() {				return PHASES;					}
	@Override	public float getHeight() {					return WIDTH;					}
	@Override	public float getWidth() {					return WIDTH;					}
	@Override	public float getDirectionY() {				return dir.y;					}
	@Override	public float getDirectionX() {				return dir.x;					}
	@Override	public float getSpeed() {					return SPEED;					}
	@Override	public float getShootingAngle() {			return angle;					}
	@Override	public Vector2 getShootingDir() {			return dir;						}
	@Override	protected int getMaxHp() {					return HP;						}
	@Override	public int getXp() {						return XP;						}
	
}
