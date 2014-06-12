package elements.generic.enemies.individual.lvl1;

import jeu.Physic;
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
import elements.generic.components.positionning.Pos;
import elements.generic.components.positionning.UpWide;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.BlueBulletSlow;
import elements.generic.weapons.player.PlayerWeapon;

public class Ball extends Enemy {
	
	public static final int PK = 1;
	protected static final float 
		FIRERATE = initFirerate(6f, PK), 	
		INIT_NEXT_SHOT = initNextShot(.1f, PK), 
		SPEED = initSpeed(40, PK);
	protected static final int BASE_XP = Enemy.initXp(12, PK);
	private static final int 
		HP = initHp(Stats.HP_BALL, PK),
		EXPLOSION = initExplosion(35, PK),
		XP = getXp(BASE_XP, PK),
		WIDTH = Stats.BALL_WIDTH,
		HALF_WIDTH = WIDTH/2;
	private static final Phase[] PHASES = {
		new Phase(				Behavior.BALL,				Gatling.BLUE_BULLET_SLOW,				Shot.SHOT_ON_PLAYER,				Animations.BALL		), 
		new Phase(				Behavior.GO_AWAY,			Gatling.BLUE_BULLET_SLOW,				Shot.SHOT_ON_PLAYER,				Animations.BALL		),		};
	public static final Pool<Ball> POOL = Pools.get(Ball.class);
	private static final Pos POS = initPositionnement(UpWide.PK, PK);
	
	
	@Override
	public Vector2 getShotPosition(int numeroTir) {
		if (now > 12)
			index = 1;
		TMP_POS.x = (pos.x + HALF_WIDTH - BlueBulletSlow.HALF_WIDTH);
		TMP_POS.y = (pos.y + HALF_WIDTH - BlueBulletSlow.HALF_WIDTH);
		return TMP_POS;
	}

	public void init() {
		index = 0;
		nextShot = INIT_NEXT_SHOT;
		POS.set(this);
		dir.x = 0;
		dir.y = -getSpeed();
	}

	@Override
	public float getAngle() {
		if (!EndlessMode.alternate && !EndlessMode.triggerStop)
			angle = Physic.getAngleWithPlayer(pos, HALF_WIDTH, HALF_WIDTH) + 90;
		return angle;
	}
	
	@Override	protected String getLabel() {				return getClass().toString();					}
	@Override	protected Sound getExplosionSound() {		return SoundMan.explosion3;						}
	@Override	public float getHalfHeight() {				return HALF_WIDTH;								}
	@Override	public float getHalfWidth() {				return HALF_WIDTH;								}
	@Override	public int getExplosionCount() {			return EXPLOSION;								}
	@Override	public float getFirerate() {				return FIRERATE;								}
	@Override	public void free() {						POOL.free(this);								}
	@Override	public int getBonusValue() {				return BASE_XP;									}
	@Override	public Phase[] getPhases() {				return PHASES;									}
	@Override	public float getDirectionY() {				return dir.y;									}
	@Override	public float getDirectionX() {				return dir.x;									}
	@Override	public float getHeight() {					return WIDTH;									}
	@Override	public float getWidth() {					return WIDTH;									}
	@Override	public float getSpeed() {					return SPEED;									}
	@Override	public int getColor() {						return BLUE;									}
	@Override	protected int getMaxHp() {					return HP;										}
	@Override	public int getXp() {						return XP;										}
	@Override	public float getBulletSpeedMod() {			return 1;										}
	@Override	public boolean stillAlive(PlayerWeapon a) {
		nextShot -= 1;
		return super.stillAlive(a);					
	}

}
