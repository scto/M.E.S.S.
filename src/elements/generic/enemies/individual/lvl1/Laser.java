package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;
import elements.generic.components.behavior.Mover;
import elements.generic.components.shots.AbstractShot;
import elements.generic.components.shots.Gatling;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.LaserWeapon;


public class Laser extends Enemy {
	
	protected static final Dimensions DIMENSIONS = Dimensions.LASER;
	public static final Pool<Laser> POOL = Pools.get(Laser.class);
	protected static final int BASE_XP = 32, HP = Stats.LASER_HP, EXPLOSION = 45, XP = getXp(BASE_XP, 1), LVL = 1;
	protected static final float FIRERATE = 0.8f * MOD_FIRERATE, INIT_NEXT_SHOT = 1, SPEED14 = getModulatedSpeed(14, LVL), PHASE_DURATION = 12;
	protected float rotation = 0;
	private boolean left;
	
	public void init() {
		if (CSG.R.nextBoolean())
			pos.set((CSG.gameZoneHalfWidth - DIMENSIONS.halfWidth) - DIMENSIONS.width, CSG.screenHeight);
		else
			pos.set((CSG.gameZoneHalfWidth - DIMENSIONS.halfWidth) + DIMENSIONS.width, CSG.screenHeight);
		nextShot = 1f;
		
		if (pos.x + DIMENSIONS.halfWidth > CSG.gameZoneHalfWidth) {
			left = true;
			dir.set(-1, -1);
		} else {
			dir.set(1, -1);
			left = false;
		}
		angle = dir.angle() + 90;
		dir.scl(getSpeed());
		now = 0.5f;
	}
	
	@Override
	protected void move() {
		if (now < getRotateTime()) {
			if (left) {
				Mover.sink(this, 5f);
			} else {
				Mover.sink(this, -5f);
			}
		} else {
			Mover.goAway(this, 0.01f);
		}
		if (!EndlessMode.alternate)
			angle = dir.angle() + 90;
	}
	
	protected float getRotateTime() {
		return 12;
	}

	@Override
	protected void shoot() {
		TMP_POS.set((pos.x + DIMENSIONS.halfWidth - LaserWeapon.DIMENSIONS.halfWidth) + (dir.x / 3), (pos.y + DIMENSIONS.halfWidth - LaserWeapon.DIMENSIONS.halfWidth) + (dir.y / 3));
		AbstractShot.straight(Gatling.LASER, TMP_POS, dir, 1.5f);
	}
	
	@Override	public Animations getAnimation() {		return Animations.AILE_DEPLOYEES;					}
	@Override	protected Sound getExplosionSound() {	return SoundMan.explosion5;							}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;									}
	@Override	public int getExplosionCount() {		return EXPLOSION;									}
	@Override	public void free() {					POOL.free(this);									}
	@Override	public float getRotation() {			return rotation;									}
	@Override	public float getFirerate() {			return FIRERATE;									}
	@Override	public float getSpeed() {				return SPEED14;										}
	@Override	public int getBonusValue() {			return BASE_XP;										}
	@Override	public void setRotation(float f) {		rotation = f;										}
	@Override	public boolean toLeft() {				return left;										}
	@Override	public int getXp() {					return XP;											}
	@Override	protected int getMaxHp() {				return HP;											}
}
