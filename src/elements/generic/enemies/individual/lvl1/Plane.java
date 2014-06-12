package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
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
import elements.generic.components.positionning.Up;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.SmallFireball;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;


public class Plane extends Enemy {
	
	public static final int PK = 8, HP = initHp(Stats.PLANE_HP, PK), HALF_HP = HP / 2, EXPLOSION_MIN_PARTICLES = initExplosion(55, PK), BASE_XP = Enemy.initXp(45, PK), XP = getXp(BASE_XP, 1);
	public static final float WIDTH = Stats.PLANE_WIDTH, HALF_WIDTH = WIDTH / 2, HEIGHT = Stats.PLANE_HEIGHT, HALF_HEIGHT = HEIGHT / 2, QUART_WIDTH = WIDTH / 4, OFFSET_SMOKE_LEFT = (int) (WIDTH * .48f), 
		OFFSET_SMOKE_RIGHT = (int) (WIDTH * .52f), OFFSET_SMOKE = (int) (HEIGHT * 0.98f), OFFSET_WEAPON_RIGHT = (int) (WIDTH - SmallFireball.HALF_WIDTH * 1.5f), OFFSET_WEAPON_LEFT = SmallFireball.HALF_WIDTH / 2, 
		OFFSET_WEAPON_Y = HALF_HEIGHT - SmallFireball.HEIGHT, FIRERATE = initFirerate(0.8f, PK), INIT_NEXT_SHOT = initNextShot(1f, PK), SPEED = initSpeed(19, PK), HALF_SPEED = SPEED / 2;
	public static final Pool<Plane> POOL = Pools.get(Plane.class);
	private static final Pos POS = initPositionnement(Up.PK, PK);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.SMALL_FIREBALL,				Shot.DOUBLE_SHOT_DOWN,				Animations.PLANE_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.SMALL_FIREBALL,				Shot.SHOT_DOWN,						Animations.PLANE_BAD				)		};
	
	@Override
	public Vector2 getShotPosition(int shotNumber) {
		// this method is called here because it's sufficient to make it stay on screen and it won't take as much resources as calling it every couple of frame
		Physic.stayOnScreen(pos, WIDTH);
		switch (shotNumber) {
		case 1:		TMP_POS.x = pos.x - OFFSET_WEAPON_LEFT;			break;
		case 2:		TMP_POS.x = pos.x + OFFSET_WEAPON_RIGHT;		break;
		}
		TMP_POS.y = pos.y + OFFSET_WEAPON_Y;
		return TMP_POS;
	}

	@Override
	public void isMoving() {
		if (EndlessMode.alternate)	Particles.smokeMoving(pos.x + OFFSET_SMOKE_LEFT, pos.y + OFFSET_SMOKE, true, getColor());
		else						Particles.smokeMoving(pos.x + OFFSET_SMOKE_RIGHT, pos.y + OFFSET_SMOKE, true, getColor());
	}
	
	@Override
	public float getSpeed() {
		if (index == 0)
			return SPEED;
		return HALF_SPEED;
	}
	
	public void init() {
		POS.set(this);
		pos.y = CSG.SCREEN_HEIGHT;
		nextShot = INIT_NEXT_SHOT;
		dir.x = 0;
		dir.y = -getSpeed();
	}
	
	@Override
	public boolean stillAlive(PlayerWeapon p) {
		if (hp-p.getPower() < getHalfHp()) {
			dir.y = -getSpeed();
			for (int i = 0; i < 10; i++) 
				Particles.smoke(pos.x + QUART_WIDTH + (CSG.R.nextFloat() * HALF_WIDTH), pos.y + CSG.R.nextFloat() * HEIGHT, false);
			index = 1;
		}
		return super.stillAlive(p);
	}
	
	protected int getHalfHp() {							return HALF_HP;									}
	@Override	public int getExplosionCount() {		return EXPLOSION_MIN_PARTICLES;					}
	@Override	protected String getLabel() {			return getClass().toString();					}
	@Override	protected Sound getExplosionSound() {	return SoundMan.explosion5;						}
	@Override	public float getHalfHeight() {			return HALF_HEIGHT;								}
	@Override	public float getDirectionY() {			return -getSpeed();								}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;								}
	@Override 	public float getFirerate() {			return FIRERATE;								}
	@Override	public void free() {					POOL.free(this);								}
	@Override	public int getBonusValue() {			return BASE_XP;									}
	@Override	public Phase[] getPhases() {			return PHASES;									}
	@Override	public float getHeight() {				return HEIGHT;									}
	@Override	public float getWidth() {				return WIDTH;									}
	@Override	protected int getMaxHp() {				return HP;										}
	@Override	public int getXp() {					return XP;										}
	@Override	public float getBulletSpeedMod() {		return 1;										}
}
