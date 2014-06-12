package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
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
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.LaserWeapon;


public class Laser extends Enemy {
	
	public static final int PK = 7;
	private static final int WIDTH = Stats.LASER_WIDTH, HALF_WIDTH = WIDTH/2;
	public static final Pool<Laser> POOL = Pools.get(Laser.class);
	protected static final int BASE_XP = Enemy.initXp(32, PK);
	protected static final float 
		FIRERATE = initFirerate(0.5f, PK),
		INIT_NEXT_SHOT = initNextShot(1, PK),
		SPEED = initSpeed(14, PK);
	private static final int 
		HP = initHp(Stats.LASER_HP, PK),
		EXPLOSION = initExplosion(45, PK),
		XP = getXp(BASE_XP, 1);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.SINK,				Gatling.LASER,				Shot.TIR_TOUT_DROIT,				Animations.AILE_DEPLOYEES				), 
		new Phase(				Behavior.GO_AWAY,			Gatling.LASER,				Shot.TIR_TOUT_DROIT,				Animations.AILE_DEPLOYEES				)		};
	protected float rotation = 0;
	private boolean left;
	private static final float PHASE_DURATION = 12;
	
	public void init() {
		if (CSG.R.nextBoolean())
			pos.x = (CSG.gameZoneHalfWidth - HALF_WIDTH) - WIDTH;
		else
			pos.x = (CSG.gameZoneHalfWidth - HALF_WIDTH) + WIDTH;
		pos.y = CSG.SCREEN_HEIGHT;
		nextShot = 1f;
		
		if (pos.x + HALF_WIDTH > CSG.gameZoneHalfWidth) {
			left = true;
			dir.x = -1;
			angle = -135;
		} else {
			dir.x = 1;
			left = false;
			angle = -45;
		}
		dir.y = -1;
		dir.scl(getSpeed());
		index = 0;
	}
	
	@Override
	protected void isMoving() {
		if (!EndlessMode.alternate)
			angle = dir.angle();
	}
	
	@Override
	public Vector2 getShotPosition(int numeroTir) {
		if (phaseTime > getPhaseDuration())
			index = 1;
		TMP_POS.x = (pos.x + HALF_WIDTH - LaserWeapon.HALF_WIDTH) + (dir.x / 3);
		TMP_POS.y = (pos.y + HALF_WIDTH - LaserWeapon.HALF_WIDTH) + (dir.y / 3);
		return TMP_POS;
	}
	
	/**
	 * Is used for the rotation
	 */
	@Override	public float getFloatFactor() {
		if (left)
			return 4.5f;
		return -4.5f;
	}
	
	@Override	protected String getLabel() {			return getClass().toString();						}		
	@Override	protected Sound getExplosionSound() {	return SoundMan.explosion5;							}
	@Override	public float getHalfHeight() {			return HALF_WIDTH;									}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;									}
	@Override	public int getExplosionCount() {		return EXPLOSION;									}
	@Override	public float getAngle() {				return angle+90;									}
	@Override	public float getShootingAngle() {		return angle+90;									}
	@Override	public void free() {					POOL.free(this);									}
	@Override	public float getRotation() {			return rotation;									}
	@Override	public float getFirerate() {			return FIRERATE;									}
	@Override	public int getBonusValue() {			return BASE_XP;										}
	@Override	public Phase[] getPhases() {			return PHASES;										}
	@Override	public float getBulletSpeedMod() {		return 0.010f;										}
	@Override	public float getHeight() {				return WIDTH;										}
	@Override	public float getWidth() {				return WIDTH;										}
	@Override	public float getSpeed() {				return SPEED;										}
	@Override	public float getDirectionY() {			return dir.y;										}
	@Override	public float getDirectionX() {			return dir.x;										}
	@Override	public void setRotation(float f) {		rotation = f;										}
	@Override	public boolean toLeft() {				return left;										}
	@Override	public Vector2 getShootingDir() {		return dir;											}
	@Override	public int getXp() {					return XP;											}
	@Override	protected int getMaxHp() {				return HP;											}
	public float getPhaseDuration() {					return PHASE_DURATION;								}
}
