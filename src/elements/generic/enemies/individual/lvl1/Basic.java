package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.SoundMan;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Pool;

import elements.generic.components.Phase;
import elements.generic.components.positionning.Pos;
import elements.generic.components.positionning.Up;
import elements.generic.enemies.Enemy;
import elements.particular.particles.Particles;

public class Basic extends Enemy {
	
	public static final int 
		WIDTH = Stats.WIDTH_DE_BASE, 
		HALF_WIDTH = WIDTH / 2, HEIGHT = Stats.HEIGHT_DE_BASE, 
		HALF_HEIGHT = HEIGHT / 2,
		OFFSET_SMOKE = (int) (HEIGHT * .75f),
		PK = 3,
		HP = initHp(Stats.HP_DE_BASE, PK);
	protected static final int BASE_XP = Enemy.initXp(1, PK), EXPLOSION = initExplosion(25, PK), XP = getXp(BASE_XP, 1);
	public static final Pool<Basic> POOL = new Pool<Basic>(16) {		protected Basic newObject() {			return new Basic();		}	};
	protected static final float SPEED = initSpeed(12, PK), FIRERATE = initFirerate(0.5f, PK);
	private static final Phase[] PHASES = {initPhases(PK, 1)};
	private static final Pos POS = initPositionnement(Up.PK, PK);
	private float xMeter = 0, yMeter = 0;
	
	
	@Override
	public void init() {
		POS.set(this);
		super.init();
	}
	
	@Override
	public void reset() {
		xMeter = 0;
		yMeter = 0;
		super.reset();
	}
	
	@Override
	public void isMoving() {
		angle = dir.angle() + 90;
		TMP_POS.x = 0;
		TMP_POS.y = HALF_WIDTH;
		TMP_POS.rotate(angle);
		if (EndlessMode.alternate)
			Particles.smokeMoving((pos.x + HALF_WIDTH) + TMP_POS.x, (pos.y + HALF_HEIGHT) + TMP_POS.y, true, getColor());
	}
	
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	protected Sound getExplosionSound() {	return SoundMan.explosion6;		}
	@Override	public float getHalfHeight() {			return HALF_HEIGHT;				}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;				}
	@Override	public int getExplosionCount() {		return EXPLOSION;				}
	@Override	public void free() {					POOL.free(this);				}
	@Override	public float getFirerate() {			return FIRERATE;				}
	@Override	public int getBonusValue() {			return BASE_XP;					}
	@Override	public float getHeight() {				return HEIGHT;					}
	@Override	public Phase[] getPhases() {			return PHASES;					}
	@Override	public float getDirectionY() {			return -SPEED;					}
	@Override	public float getXMeter() {				return xMeter;					}
	@Override	public float getYMeter() {				return yMeter;					}
	@Override	public float getWidth() {				return WIDTH;					}
	@Override	public float getSpeed() {				return SPEED;					}
	@Override	public void setXMeter(float f) {		xMeter = f;						}
	@Override	public void setYMeter(float f) {		yMeter = f;						}
	@Override	protected int getMaxHp() {				return HP;						}
	@Override	public int getXp() {					return XP;						}
	@Override	public float getFloatFactor() {			return 1;	}

}

//			{
//		new Phase(
//				initBehavior(Behavior.STRAIGHT_ON.pk, PK),
//				initWeapon(0, PK),
//				initShot(0, PK),
//				initAnimation(Animations.BASIC_ENEMY_RED.pk, PK)
//				)};