package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.positionning.Pos;
import elements.generic.components.positionning.Sides;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.KinderWeapon;


public class Kinder extends Enemy {

	public static final int WIDTH = Stats.KINDER_WIDTH, HALF_WIDTH = WIDTH / 2, HEIGHT = Stats.KINDER_HEIGHT, HALF_HEIGHT = HEIGHT / 2, PK = 6,	BASE_XP = Enemy.initXp(32, PK), HP = initHp(Stats.KINDER_HP, PK),
			EXPLOSION_COUNT = initExplosion(45, PK), XP = getXp(BASE_XP, 1);
	protected static final float FIRERATE = initFirerate(0.45f, PK), INIT_NEXT_SHOT = initNextShot(Animations.KINDER_TIME_OPEN, PK), SPEED = initSpeed(10, PK), PHASE_DURATION = 12;
	public static final Pool<Kinder> POOL = Pools.get(Kinder.class);
	private static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.KINDER_WEAPON,				null,						Animations.KINDER_OPENING				),
		new Phase(				Behavior.ROTATE,					Gatling.KINDER_WEAPON,				Shot.TIR_TOUT_DROIT,		Animations.KINDER_OPEN					),
		new Phase(				Behavior.GO_AWAY,					Gatling.KINDER_WEAPON,				Shot.TIR_TOUT_DROIT,		Animations.KINDER_OPEN					),		};
	private static final Pos POSITIONNING = initPositionnement(Sides.PK, PK);
	
	public void init() {
		POSITIONNING.set(this);
		nextShot = INIT_NEXT_SHOT;
		index = 0;
	}
	
	@Override
	public Vector2 getShotPosition(int shotNumber) {
		TMP_POS.x = (pos.x + HALF_WIDTH - KinderWeapon.HALF_WIDTH);
		TMP_POS.y = (pos.y + HALF_HEIGHT - KinderWeapon.HALF_WIDTH);
		return TMP_POS;
	}
	
	@Override
	public void isMoving() {
		angle = dir.angle() + 90;
		switch (index) {
		case 0 :
			if (phaseTime > Animations.KINDER_TIME_OPEN)
				changePhase();
			break;
		case 1 :
			if (phaseTime > getPhaseDuration())
				changePhase();
			break;
		}
	}
	
	@Override
	public float getDirectionX() {
		if (now < Animations.KINDER_TIME_OPEN || now > getPhaseDuration()) 
			return dir.x;
		return 0;
	}
	
	@Override	public Vector2 getShootingDir() {
		TMP_DIR.x = -dir.x;
		TMP_DIR.y = -dir.y;
		return TMP_DIR;	
	}
	
	/**
	 * Is used to get rotation speed
	 */
	@Override	public float getFloatFactor() {				return 40;										}
	@Override	protected String getLabel() {				return getClass().toString();					}
	@Override	protected Sound getExplosionSound() {		return SoundMan.explosion6;						}
	@Override	public int getExplosionCount() {			return EXPLOSION_COUNT;							}
	@Override	public float getHalfHeight() {				return HALF_HEIGHT;								}
	@Override	public float getHalfWidth() {				return HALF_WIDTH;								}
	@Override	public float getFirerate() {				return FIRERATE;								}
	@Override	public void free() {						POOL.free(this);								}
	@Override	public float getBulletSpeedMod() {		return -1.5f;					}
	@Override	public int getBonusValue() {				return BASE_XP;									}
	@Override	public Phase[] getPhases() {				return PHASES;									}
	@Override	public float getHeight() {					return HEIGHT;									}
	@Override	public float getShootingAngle() {			return angle;									}
	@Override	public float getDirectionY() {				return dir.y;									}
	@Override	public float getSpeed() {					return SPEED;									}
	@Override	public float getWidth() {					return WIDTH;									}
	@Override	public int getXp() {						return XP;										}
	@Override	protected int getMaxHp() {					return HP;										}
	public static float getPhaseDuration() {				return PHASE_DURATION;							}
}

