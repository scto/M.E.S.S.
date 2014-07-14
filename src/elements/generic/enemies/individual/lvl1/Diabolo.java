package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
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
import elements.generic.weapons.enemies.OrangeBullet;

public class Diabolo extends Enemy {
	
	protected static final Dimensions DIMENSIONS = Dimensions.DIABOLO;
	public static final int EXPLOSION = 25, BASE_XP = 19,  XP = getXp(BASE_XP, 1), HP = Stats.HP_DIABOLO, PHASE_DURATION = 15;
	protected static final float WIDTH2 = DIMENSIONS.width * 2, FIRERATE = .25f * MOD_FIRERATE, INIT_NEXT_SHOT = 3, SPEED18 = getModulatedSpeed(18, 1);
	public static final Pool<Diabolo> POOL = Pools.get(Diabolo.class);
	private int shotNumber, index;

	public void init() {
		Positionner.UP_WIDE.set(this);
		if (CSG.R.nextBoolean())
			pos.set(CSG.gameZoneHalfWidth - Stats.U10, CSG.screenHeight);
		else
			pos.set(CSG.gameZoneHalfWidth + Stats.U10, CSG.screenHeight);
		dir.set(-getSpeed() / 5, -getSpeed());
		nextShot = INIT_NEXT_SHOT;
		index = 0;
	}
	
	@Override
	protected void move() {
		switch (index) {
		case 0:
			Mover.straight(this);
			if (pos.y + WIDTH2 < CSG.halfHeight)
				index++;
			break;
		case 1:
			Mover.ovale(this, 0.05f);
			if (now > getPhaseDuration())
				index++;
			break;
		case 2:
			Mover.goAway(this, 0.03f);
		}
	}

	@Override
	protected void shoot() {
		TMP_POS.set(pos.x + DIMENSIONS.halfWidth - OrangeBullet.DIMENSIONS.halfWidth, pos.y + DIMENSIONS.halfWidth - OrangeBullet.DIMENSIONS.halfHeight);
		AbstractShot.straight(Gatling.ORANGE_BULLET, TMP_POS, TMP_DIR.set(-dir.x, -dir.y).nor(), Stats.U10);
		interval();
	}

	protected float getDemiVitesse() {					return Stats.DIABOLO_HALF_SPEED;				}
	protected void interval() {							AbstractShot.rafale(this);						}
	protected float getPhaseDuration() {				return 6.65f;									}
	@Override	protected Sound getExplosionSound() {	return SoundMan.explosion1;						}
	@Override	public Animations getAnimation() {		return Animations.DIABOLO;						}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;								}
	@Override	public int getShotNumber() {			return shotNumber;								}
	@Override	public int getExplosionCount() {		return EXPLOSION;								}
	@Override	public void addShots(int i) {			shotNumber += i;								}
	@Override	public void free() {					POOL.free(this);								}
	@Override 	public float getFirerate() {			return FIRERATE;								}
	@Override	public int getBonusValue() {			return BASE_XP;									}
	@Override	public float getSpeed() {				return SPEED18;									}
	@Override	protected int getMaxHp() {				return HP;										}
	@Override	public int getXp() {					return XP;										}
	@Override	public int getNumberOfShots() {			return 2;										}
}
