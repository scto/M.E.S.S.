package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import jeu.mode.extensions.Score;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.positionning.Middle;
import elements.generic.components.positionning.Pos;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.VicousBullet;

public class Vicious extends Enemy {
	
	public static final int WIDTH = (int) (Stats.INSECT_WIDTH * 0.75f),  HALF_WIDTH = WIDTH / 2;
	public static final int PK = 14;
	protected static final float FIRERATE = initFirerate(2, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(1, PK);
	protected static final float SPEED = initSpeed(3, PK);
	private static final int EXPLOSION = initExplosion(50, PK);
	protected static float tmp;
	public static final Pool<Vicious> POOL = Pools.get(Vicious.class);
	private static final Pos positionning = initPositionnement(Middle.PK, PK);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.VICOUS,				Shot.SHOTGUN,				Animations.INSECT_GOOD				)		};
	private int xp, pv;
	
	public void init() {
		nextShot = INIT_NEXT_SHOT;
		dir.x = 0;
		dir.y = -getSpeed();
		positionning.set(this);
		xp = (int) (Score.score / 80f);
		if (xp > 500)
			xp = 500;
		pv = xp / 2 ;
	}

	@Override
	public Vector2 getShotPosition(int numeroTir) {
		dir.y -= Stats.uSur4;
		TMP_POS.x = pos.x + HALF_WIDTH - VicousBullet.HALF_WIDTH;
		TMP_POS.y = pos.y + HALF_WIDTH - VicousBullet.HALF_WIDTH;
		return TMP_POS;
	}

	@Override
	public float getShootingAngle() {
		return 180;
	}
	
	@Override	public float getFloatFactor() {				return 60;	}
	@Override	public int getIntFactor() {					return 6;	}
	@Override	public int getNumberOfShots() {				return 6;		}
	@Override 	public float getFirerate() {				return FIRERATE;								}
	@Override	protected Sound getExplosionSound() {		return SoundMan.explosion5;											}
	@Override	public int getXp() {						return xp;															}
	@Override	public int getBonusValue() {				return xp;															}
	@Override	protected String getLabel() {				return getClass().toString();										}
	@Override	protected int getMaxHp() {					return pv;															}
	@Override	public float getBulletSpeedMod() {			return 20;															}
	@Override	public void free() {						POOL.free(this);													}
	@Override	public float getHeight() {					return WIDTH;														}
	@Override	public float getWidth() {						return WIDTH;														}
	@Override	public float getHalfHeight() {				return HALF_WIDTH;												}
	@Override	public float getHalfWidth() {					return HALF_WIDTH;												}
	@Override	public float getSpeed() {					return SPEED;												}
	@Override	public float getDirectionY() {				return -1;															}
	@Override	public int getExplosionCount() {			return EXPLOSION;									}
	@Override	public Phase[] getPhases() {				return PHASES;													}
}
