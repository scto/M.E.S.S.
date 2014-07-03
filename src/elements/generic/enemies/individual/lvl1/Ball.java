package elements.generic.enemies.individual.lvl1;

import jeu.Physic;
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
import elements.generic.weapons.enemies.PinkBullet;
import elements.generic.weapons.player.PlayerWeapon;

public class Ball extends Enemy {
	
	protected static final int BASE_XP = 12, LVL = 1, HP = Stats.HP_BALL, EXPLOSION = 35, XP = getXp(BASE_XP, 1);
	protected static final float FIRERATE = 4f * MOD_FIRERATE, INIT_NEXT_SHOT = 3f, SPEED40 = getModulatedSpeed(40, LVL);
	protected static final Dimensions DIMENSIONS = Dimensions.BALL;
	public static final Pool<Ball> POOL = Pools.get(Ball.class);
	
	public void init() {
		nextShot = INIT_NEXT_SHOT;
		Positionner.UP_WIDE.set(this);
		dir.set(0, - getSpeed());
	}

	@Override
	public float getAngle() {
		if (!EndlessMode.alternate && !EndlessMode.triggerStop)
			angle = Physic.getAngleWithPlayer(pos, DIMENSIONS.halfWidth, DIMENSIONS.halfWidth) + 90;
		return angle;
	}
	
	@Override
	protected void shoot() {
		TMP_POS.set(pos.x + DIMENSIONS.halfWidth - PinkBullet.DIMENSIONS.halfWidth, pos.y + DIMENSIONS.halfHeight - PinkBullet.DIMENSIONS.halfHeight);
		AbstractShot.shootOnPlayer(Gatling.CYAN_BULLET, TMP_POS, DIMENSIONS.halfWidth, Stats.U12);
	}
	
	@Override
	public void move() {
		if (now < 30)	Mover.ball(this, 1.002f);
		else			Mover.goAway(this, 0.007f);
	}
	
	@Override	protected Sound getExplosionSound() {		return SoundMan.explosion3;						}
	@Override	public Animations getAnimation() {			return Animations.BALL;							}
	@Override	public Dimensions getDimensions() {			return DIMENSIONS;								}
	@Override	public int getExplosionCount() {			return EXPLOSION;								}
	@Override	public float getFirerate() {				return FIRERATE;								}
	@Override	public void free() {						POOL.free(this);								}
	@Override	public float getSpeed() {					return SPEED40;									}
	@Override	public int getBonusValue() {				return BASE_XP;									}
	@Override	public int getColor() {						return BLUE;									}
	@Override	protected int getMaxHp() {					return HP;										}
	@Override	public int getXp() {						return XP;										}
	@Override	public boolean stillAlive(PlayerWeapon a) {
		nextShot -= 1;
		return super.stillAlive(a);					
	}

}
