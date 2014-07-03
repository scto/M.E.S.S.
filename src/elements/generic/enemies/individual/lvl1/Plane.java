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
import elements.generic.components.positionning.Positionner;
import elements.generic.components.shots.AbstractShot;
import elements.generic.components.shots.Gatling;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.SmallFireball;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;
import elements.particular.particles.ParticuleBundles;


public class Plane extends Enemy {
	
	protected static final Dimensions DIMENSIONS = Dimensions.PLANE;
	public static final int HP = Stats.PLANE_HP, HALF_HP = HP / 2, EXPLOSION_MIN_PARTICLES = 55, BASE_XP = 45, XP = BASE_XP;
	public static final float OFFSET_SMOKE_LEFT = (int) (DIMENSIONS.width * .48f), 
		OFFSET_SMOKE_RIGHT = (int) (DIMENSIONS.width * .52f), OFFSET_SMOKE = (int) (DIMENSIONS.height * 0.98f), OFFSET_WEAPON_RIGHT = (int) (DIMENSIONS.width - SmallFireball.DIMENSIONS.halfWidth * 1.5f),
		OFFSET_WEAPON_LEFT = SmallFireball.DIMENSIONS.halfWidth / 2, 
		OFFSET_WEAPON_Y = DIMENSIONS.halfHeight - SmallFireball.DIMENSIONS.height, FIRERATE = 0.8f * MOD_FIRERATE, INIT_NEXT_SHOT = 1f, SPEED19 = getModulatedSpeed(19, 1), HALF_SPEED = SPEED19 / 2;
	public static final Pool<Plane> POOL = Pools.get(Plane.class);
	private boolean goodShape = true;
	private int shotNumber = 0;
	
	@Override
	public void move() {
		if (EndlessMode.alternate)	Particles.smokeMoving(pos.x + OFFSET_SMOKE_LEFT, pos.y + OFFSET_SMOKE, true, getColor());
		else						Particles.smokeMoving(pos.x + OFFSET_SMOKE_RIGHT, pos.y + OFFSET_SMOKE, true, getColor());
		Mover.straight(this);
	}
	
	@Override
	protected void shoot() {
		TMP_POS.set(pos.x - OFFSET_WEAPON_LEFT, pos.y + OFFSET_WEAPON_Y);
		if (isInGoodShape()) {
			TMP_DIR.set(pos.x + OFFSET_WEAPON_RIGHT, pos.y + OFFSET_WEAPON_Y);
			shootDouble();
		} else {
			shootSingle();
		}
		shotNumber = AbstractShot.interval(this, 3, FIRERATE, shotNumber);
	}

	protected void shootSingle() {
		AbstractShot.shootDown(Gatling.SMALL_FIREBALL, TMP_POS, Stats.U20);
	}

	protected void shootDouble() {
		AbstractShot.doubleShotDown(Gatling.SMALL_FIREBALL, TMP_POS, TMP_DIR, Stats.U20);
	}
	
	@Override
	public float getSpeed() {
		if (isInGoodShape())
			return SPEED19;
		return HALF_SPEED;
	}
	
	public void init() {
		Positionner.UP.set(this);
		goodShape = true;
		nextShot = INIT_NEXT_SHOT;
		dir.set(0, -getSpeed());
	}
	
	@Override
	public boolean stillAlive(PlayerWeapon p) {
		if (hp-p.getPower() < getHalfHp()) {
			goodShape = false;
			dir.y = -getSpeed();
			for (int i = 0; i < 10; i++) 
				Particles.smoke(pos.x + DIMENSIONS.quartWidth + (CSG.R.nextFloat() * DIMENSIONS.halfWidth), pos.y + CSG.R.nextFloat() * DIMENSIONS.height, false, ParticuleBundles.SMOKE.colors);
		}
		return super.stillAlive(p);
	}
	
	protected int getHalfHp() {							return HALF_HP;									}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	@Override	public Animations getAnimation() {		return Animations.PLANE;						}
	@Override	public int getExplosionCount() {		return EXPLOSION_MIN_PARTICLES;					}
	@Override	protected Sound getExplosionSound() {	return SoundMan.explosion5;						}
	@Override	public boolean isInGoodShape() {		return goodShape;								}
	@Override 	public float getFirerate() {			return FIRERATE;								}
	@Override	public void free() {					POOL.free(this);								}
	@Override	public int getBonusValue() {			return BASE_XP;									}
	@Override	protected int getMaxHp() {				return HP;										}
	@Override	public int getXp() {					return XP;										}
}
