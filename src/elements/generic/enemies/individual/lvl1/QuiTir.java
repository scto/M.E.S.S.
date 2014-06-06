package elements.generic.enemies.individual.lvl1;

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
import elements.generic.weapons.enemies.Fireball;
import elements.generic.weapons.player.PlayerWeapon;


public class QuiTir extends Enemy {
	
	private static final int 
		WIDTH = Stats.WIDTH_QUI_TIR, 
		HALF_WIDTH = WIDTH/2, 
		HEIGHT = Stats.HEIGHT_QUI_TIR, 
		HALF_HEIGHT = HEIGHT / 2;
	public static final Pool<QuiTir> POOL = Pools.get(QuiTir.class);
	private static final float xOffset = HALF_WIDTH - Fireball.HALF_WIDTH/1.5f;
	public static final int PK = 10;
	protected static final int BASE_XP = Enemy.initXp(10, PK);
	protected static final float 
		FIRERATE = initFirerate(1.2f, PK),
		INIT_NEXT_SHOT = initNextShot(1.5f, PK),
		SPEED = initSpeed(10, PK);
	private static final int 
		HP = initHp(Stats.HP_QUI_TIR, PK), 
		HALF_HP = HP/2,
		EXPLOSION = initExplosion(40, PK),
		XP = getXp(BASE_XP, 1);
	private static final Pos POS = initPositionnement(UpWide.PK, PK);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.FIREBALL,				Shot.SHOT_DOWN,				Animations.QUI_TIR_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.FIREBALL,				Shot.SHOT_DOWN,				Animations.QUI_TIR_BAD				)		};
	public void init() {
		POS.set(this);
		nextShot = 2f;
		dir.x = 0;
		dir.y = -getSpeed();
	}
	
	@Override
	protected void isMoving() {
		if (index == 1)
			pos.x += getDerive() * EndlessMode.delta;
	}
	
	@Override
	public Vector2 getShotPosition(int numeroTir) {
		TMP_POS.x = pos.x + xOffset;
		TMP_POS.y = pos.y - Fireball.HEIGHT;
		return TMP_POS;
	}

	@Override
	public float getDirectionX() {
		if (hp < getDemiPv()) 
			return getDerive();
		else return 0;
	}
	
	@Override
	public boolean stillAlive(PlayerWeapon a) {
		if (hp < getDemiPv()) {
			dir.rotate(2);
			index = 1;
		} else {
			index = 0;
		}
		return super.stillAlive(a);
	}
	@Override 	public float getFirerate() {					return FIRERATE;								}
	@Override	public int getColor() {						return BLUE;	}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	protected Sound getExplosionSound() {			return SoundMan.explosion5;	}
	@Override	public float getDirectionY() {				return getSpeed();	}
	@Override	public float getSpeed() {					return SPEED;	}
	@Override	protected int getMaxHp() {					return HP;	}
	@Override	public int getXp() {						return XP;	}
	@Override	public int getBonusValue() {				return BASE_XP;	}
	@Override	public float getHeight() {					return HEIGHT;	}
	@Override	public float getWidth() {						return WIDTH;	}
	@Override	public float getHalfHeight() {				return HALF_HEIGHT;	}
	@Override	public float getHalfWidth() {					return HALF_WIDTH;	}
	@Override	public float getBulletSpeedMod() {			return 1;	}
	@Override	public int getExplosionCount() {			return EXPLOSION;									}
	@Override	public Phase[] getPhases() {				return PHASES;	}
	protected float getDerive() {							return Stats.DERIVE_QUI_TIR;	}
	protected int getDemiPv() {								return HALF_HP;	}
}
