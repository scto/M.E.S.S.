package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.positionning.Middle;
import elements.generic.components.positionning.Pos;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.Weapon;

public class Insect extends Enemy {
	
	public static final int 
		WIDTH = Stats.INSECT_WIDTH,  
		HALF_WIDTH = WIDTH / 2;
	public static final Pool<Insect> POOL = Pools.get(Insect.class);
	public static final int PK = 5;
	protected static final int BASE_XP = Enemy.initXp(86, PK);
	protected static final float 
		FIRERATE = initFirerate(1.5f, PK),
		INIT_NEXT_SHOT = initNextShot(1, PK),
		SPEED = initSpeed(Stats.INSECT_SPEED, PK);
	private static final int 
		HP = initHp(Stats.INSECT_HP, PK),
		HALF_HP = HP / 2,
		EXPLOSION = initExplosion(50, PK),
		XP = getXp(BASE_XP, 1);
	private static final int GOOD_STRAIGHT = 0, BAD_STRAIGHT = 1, GOOD_ROTATE = 2, BAD_ROTATE = 3, GOOD_GO_AWAY = 4, BAD_GO_AWAY = 5;
	private static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.INSECT,				Shot.SHOTGUN,				Animations.INSECT_GOOD				), 
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.INSECT,				Shot.SHOTGUN,				Animations.INSECT_BAD				), 
		new Phase(				Behavior.ROTATE,					Gatling.INSECT,				Shot.SHOTGUN,				Animations.INSECT_GOOD				), 
		new Phase(				Behavior.ROTATE,					Gatling.INSECT,				Shot.SHOTGUN,				Animations.INSECT_BAD				), 
		new Phase(				Behavior.GO_AWAY,					Gatling.INSECT,				Shot.SHOTGUN,				Animations.INSECT_GOOD				),
		new Phase(				Behavior.GO_AWAY,					Gatling.INSECT,				Shot.SHOTGUN,				Animations.INSECT_BAD				)		};
	private static final Pos POS = initPositionnement(Middle.PK, PK);
	
	public void init() {
		nextShot = INIT_NEXT_SHOT;
		dir.x = 0;
		dir.y = -getSpeed();
		dir.rotate(-20);
		POS.set(this);
		index = 0;
		angle = dir.angle() + 90;
	}
	
	@Override
	protected void isMoving() {
		switch (index) {
		case GOOD_STRAIGHT:
		case BAD_STRAIGHT:
			checkPhase();
			break;
		case GOOD_ROTATE:
		case BAD_ROTATE:
			angle = dir.angle() + 90;
			checkPhase();
			break;
		case GOOD_GO_AWAY:
		case BAD_GO_AWAY:
			angle -= EndlessMode.delta4;
		}
	}

	private void checkPhase() {
		if (phaseTime > 3) {
			changePhase(index + 2, INIT_NEXT_SHOT);
		}
	}

	@Override
	public Vector2 getShotPosition(int numeroTir) {
		TMP_POS.x = pos.x + HALF_WIDTH;
		TMP_POS.y = pos.y + HALF_WIDTH;
		return TMP_POS;
	}
	
	/*
	 * Used for the number of shots rnd
	 */
	@Override	public int getIntFactor() {					return 3;	}
	/**
	 * Used for the minimal number of bullets
	 */
	@Override	public int getNumberOfShots() {				return 3;	}
	/**
	 * Will set the dispersion angle and the speed randomness (is that even a word ?)
	 */
	@Override	public float getFloatFactor() {				return 24;	}
	@Override	public float getFirerate() {				return FIRERATE;	}
	@Override	protected String getLabel() {				return getClass().toString();										}
	@Override	protected Sound getExplosionSound() {		return SoundMan.explosion5;											}
	@Override	public Phase[] getPhases() {				return PHASES;														}
	@Override	public float getHalfHeight() {				return HALF_WIDTH;													}
	@Override	public float getHalfWidth() {				return HALF_WIDTH;													}
	@Override	public int getExplosionCount() {			return EXPLOSION;													}
	@Override	public void free() {						POOL.free(this);													}
	@Override	public int getBonusValue() {				return BASE_XP;														}
	@Override	public float getDirectionY() {				return dir.y;														}
	@Override	public float getDirectionX() {				return dir.x;														}
	@Override	public float getAngle() {					return angle;														}
	@Override	public float getHeight() {					return WIDTH;														}
	@Override	public float getWidth() {					return WIDTH;														}
	@Override	public float getSpeed() {					return SPEED;														}
	@Override	public int getXp() {						return XP;															}
	@Override	protected int getMaxHp() {					return HP;															}
	@Override	public float getBulletSpeedMod() {			return 1;															}
	@Override	public float getShootingAngle() {			return angle;	}
	@Override
	public boolean isTouched(Weapon a) {
		if (hp < HALF_HP && index%2 == 0) {
			index++;
		}
		return super.isTouched(a);
	}
}
