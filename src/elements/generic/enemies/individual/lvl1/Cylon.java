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
import elements.generic.components.positionning.Positionner;
import elements.generic.components.shots.AbstractShot;
import elements.generic.components.shots.Gatling;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.Meteorite;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;
import elements.particular.particles.ParticuleBundles;

public class Cylon extends Enemy {
	
	protected static final Dimensions DIMENSIONS = Dimensions.CYLON;
	protected static final int THRUSTER_OFFSET = (int) (DIMENSIONS.width * 0.45f), LVL = 1;
	public static final Pool<Cylon> POOL = Pools.get(Cylon.class);
	protected static final float FIRERATE = 3 * MOD_FIRERATE, INIT_NEXT_SHOT = 2.6f, SPEED12 = getModulatedSpeed(12, LVL);
	protected static final int BASE_XP = 12, HP = Stats.HP_CYLON, HP_BAD = (int) (HP * 0.66f), HP_WORST = (int) (HP * 0.33f), EXPLOSION = 35, XP = getXp(BASE_XP, 1);
	private int index = 0;

	public void init() {
		Positionner.UP_WIDE.set(this);
		if (pos.x + DIMENSIONS.halfWidth < CSG.screenHalfWidth)
			dir.set(0.26f * getSpeed(), -0.83f * getSpeed());
		else
			dir.set(-0.26f * getSpeed(), -0.83f * getSpeed());
		nextShot = 2.6f;
		angle = dir.angle() + 90;
		index = 0;
	}
	
	@Override
	protected void move() {
		TMP_POS.set(0, THRUSTER_OFFSET).rotate(angle);
		if (EndlessMode.oneToFour > index + 1)
			Particles.smokeMoving(pos.x + DIMENSIONS.halfWidth + TMP_POS.x, pos.y + DIMENSIONS.halfWidth + TMP_POS.y, TMP_POS.nor().scl(Stats.uDiv2), getColor());
		super.move();
	}
	
	@Override
	protected void shoot() {
		TMP_POS.set((pos.x + DIMENSIONS.halfWidth) - Meteorite.DIMENSIONS.halfWidth, (pos.y + DIMENSIONS.halfWidth) - Meteorite.DIMENSIONS.halfWidth);
		AbstractShot.straight(Gatling.METEORITE, TMP_POS, dir, 1.5f);
	}
	
	@Override
	public boolean stillAlive(PlayerWeapon p) {
		switch (index) {
		case 0:
			if (hp - p.getPower() < getPvBad()) 
				changeState(1);
			break;
		case 1:
			if (hp - p.getPower() < getPvWorst()) 
				changeState(2);
			break;
		}
		return super.stillAlive(p);
	}

	private void changeState(int nextState) {
		index = nextState;
		dir.rotate(CSG.R.nextInt(20)-10);
		dir.scl(0.7f);
		angle = dir.angle() + 90;
		for (int i = 0; i < 10; i++) 
			Particles.smoke(pos.x + DIMENSIONS.quartWidth + (CSG.R.nextFloat() * DIMENSIONS.halfWidth), pos.y + CSG.R.nextFloat() * DIMENSIONS.width, false, ParticuleBundles.SMOKE.colors);
	}
	
	@Override	public Animations getAnimation() {		return Animations.CYLON_RED;				}
	@Override	protected Sound getExplosionSound() {	return SoundMan.explosion4;					}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;							}
	@Override	public int getExplosionCount() {		return EXPLOSION;							}
	@Override	public float getFirerate() {			return FIRERATE;							}
	@Override	public void free() {					POOL.free(this);							}
	@Override	public float getSpeed() {				return SPEED12;								}
	@Override	public int getBonusValue() {			return BASE_XP;								}
	@Override	public int getAnimIndex() {				return index;								}
	@Override	public int getXp() {					return XP;									}
	@Override	protected int getMaxHp() {				return HP;									}
	protected int getPvBad() {							return HP_BAD;								}
	protected int getPvWorst() {						return HP_WORST;							}
}
