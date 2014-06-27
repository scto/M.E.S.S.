package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
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
import elements.generic.weapons.Weapon;

public class Insect extends Enemy {
	
	protected static final Dimensions DIMENSIONS = Dimensions.INSECT;
	public static final int BASE_XP = 86, HP = Stats.INSECT_HP, HALF_HP = HP / 2, EXPLOSION = 50, XP = getXp(BASE_XP, 1);
	public static final Pool<Insect> POOL = Pools.get(Insect.class);
	protected static final float FIRERATE = 1.5f, INIT_NEXT_SHOT = 1, SPEED12 = getModulatedSpeed(12, 1);
	private boolean goodShape;
	
	public void init() {
		nextShot = INIT_NEXT_SHOT;
		
		dir.set(0, -getSpeed()).rotate(-20);
		Positionner.MIDDLE.set(this);
		goodShape = true;
		angle = dir.angle() + 90;
	}
	
	@Override
	protected void move() {
		Mover.insectMove(this, 4, 12, Stats.uSur8);
	}

	@Override
	protected void shoot() {
		TMP_POS.set(pos.x + DIMENSIONS.halfWidth, pos.y + DIMENSIONS.halfWidth);
		TMP_DIR.set(dir.x, dir.y).nor().scl(Stats.U10);
		AbstractShot.shotgun(Gatling.INSECT, TMP_DIR, TMP_POS, 3, getNumberOfShotsRandom(), 15, 0, DIMENSIONS.halfWidth);
	}

	protected int getNumberOfShotsRandom() {
		return 3;
	}
	@Override	protected Sound getExplosionSound() {		return SoundMan.explosion5;											}
	@Override	public Animations getAnimation() {			return Animations.INSECT;											}
	@Override	public Dimensions getDimensions() {			return DIMENSIONS;													}
	@Override	public boolean isInGoodShape() {			return goodShape;													}
	@Override	public int getExplosionCount() {			return EXPLOSION;													}
	@Override	public float getFirerate() {				return FIRERATE;													}
	@Override	public void free() {						POOL.free(this);													}
	@Override	public int getBonusValue() {				return BASE_XP;														}
	@Override	public float getSpeed() {					return SPEED12;														}
	@Override	public float getAngle() {					return angle;														}
	@Override	public int getXp() {						return XP;															}
	@Override	protected int getMaxHp() {					return HP;															}
	@Override
	public boolean isTouched(Weapon a) {
		if (hp < HALF_HP) {
			goodShape = false;
		}
		return super.isTouched(a);
	}
}
