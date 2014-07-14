package elements.generic.enemies.individual.bosses;

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
import elements.generic.enemies.Progression;
import elements.generic.weapons.enemies.Fireball;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;
import elements.particular.particles.ParticuleBundles;

public class Quad extends Enemy {

	protected static final Dimensions DIMENSIONS = Dimensions.BOSS_QUAD;
	private static final int OFFSET_W_2 = (int) (DIMENSIONS.width /6), OFFSET_W_3 = (int) ((DIMENSIONS.width /3)*2), OFFSET_OUTSIDE_W_Y = (int) (DIMENSIONS.height / 3), pvMaxPhase2 = getPvBoss(Stats.QUAD_HP) / 3 * 2, pvMinPhase2 = getPvBoss(Stats.QUAD_HP) / 3;
	private static final float FIRERATE = .2f * MOD_FIRERATE, FIRERATE2 = .12f * MOD_FIRERATE, FIRERATE3 = 0.07f * MOD_FIRERATE;
	public static Pool<Quad> POOL = Pools.get(Quad.class);
	private int shotNumber = 1, animIndex;
	private static boolean tmp;
	
	public Quad() {
		super();
		init();
	}

	public void init() {
		pos.set(CSG.screenHalfWidth - DIMENSIONS.halfWidth, CSG.screenHeight);
		dir.set(-getSpeed() * 4f, -getSpeed());
		animIndex = 0;
	}

	public void reset() {
		super.reset();
		nextShot = .2f;
		init();
	}
	
	@Override
	protected void move() {
		Mover.ancorY(this, CSG.HEIGHT_8_10);
		Mover.oscillateX(this, 20);
		Mover.ball(this, 1);
	}

	@Override
	public boolean stillAlive(PlayerWeapon p) {
		tmp = super.stillAlive(p);

		switch (animIndex) {
		case 0:
			if (hp < pvMaxPhase2)
				changePhase();
			break;
		case 1:
			if (hp < pvMinPhase2)
				changePhase();
			break;
		}
		return tmp;
	}

	protected void changePhase() {
		for (int i = -EndlessMode.fps; i < 15 * animIndex; i++)
			Particles.smoke(pos.x + (CSG.R.nextFloat() * DIMENSIONS.width), pos.y + (CSG.R.nextFloat() * DIMENSIONS.height), false, ParticuleBundles.SMOKE.colors);
		if (animIndex < 2)
			animIndex++;
	}
	
	@Override
	protected void shoot() {
		SoundMan.playBruitage(SoundMan.shotRocket);
		if (animIndex == 0) {
			fire(pos.x, pos.y - OFFSET_OUTSIDE_W_Y);
			fire(pos.x + DIMENSIONS.width - Fireball.DIMENSIONS.width, pos.y - OFFSET_OUTSIDE_W_Y);
		} else {
			fire(pos.x, pos.y - OFFSET_OUTSIDE_W_Y);
			fire(pos.x + DIMENSIONS.width - Fireball.DIMENSIONS.width, pos.y - OFFSET_OUTSIDE_W_Y);
		}
		
		// inside
		if (animIndex == 0) {
			fire(pos.x + OFFSET_W_3, pos.y - Fireball.DIMENSIONS.halfHeight);
			fire(pos.x + OFFSET_W_2, pos.y - Fireball.DIMENSIONS.halfHeight);
		}
		
		shotNumber = AbstractShot.interval(this, 2 * (animIndex+1), 1, shotNumber);
	}

	protected void fire(float x, float y) {
		pos.y++;
		TMP_POS.set(x, y);
		AbstractShot.shootDown(Gatling.FIREBALL, TMP_POS, Stats.U12);
	}


	public void die() {
		Progression.bossDied();
		super.die();
	}

	@Override	
	public float getFirerate() {
		switch (animIndex) {
		case 0:			return FIRERATE;
		case 1:			return FIRERATE2;
		}
		return FIRERATE3;	
	}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	@Override	protected int getMaxHp() {				return super.getPvBoss(Stats.QUAD_HP);	}
	@Override	protected Sound getExplosionSound() {	return SoundMan.bigExplosion;			}
	@Override	public float getSpeed() {				return Stats.QUAD_SPEED;				}
	@Override	public Animations getAnimation() {		return Animations.QUAD;					}
	@Override	public void addShots(int i) {			this.shotNumber += i;					}
	@Override	public int getShotNumber() {			return shotNumber;						}
	@Override	public int getAnimIndex() {				return animIndex;						}
	@Override	public void free() {					POOL.free(this);						}
	@Override	public int getXp() {					return 200;								}
	@Override	public int getBonusValue() {			return 200;								}
	@Override	public int getExplosionCount() {		return 180;								}
}

