package elements.generic.enemies.individual.demo;

import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Pool;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.positionning.Pos;
import elements.generic.components.positionning.Up;
import elements.generic.enemies.Enemy;
import elements.particular.particles.Particles;

public class LeftToRightCurve extends Enemy {
	
	public static final int 
		WIDTH = Stats.WIDTH_DE_BASE, 
		HALF_WIDTH = WIDTH / 2, HEIGHT = Stats.HEIGHT_DE_BASE, 
		HALF_HEIGHT = HEIGHT / 2,
		OFFSET_SMOKE = (int) (HEIGHT * .75f),
		PK = 3,
		HP = initHp(Stats.HP_DE_BASE, PK);
	protected static final int BASE_XP = Enemy.initXp(1, PK);
	public static final Pool<LeftToRightCurve> POOL = new Pool<LeftToRightCurve>(16) {
		@Override
		protected LeftToRightCurve newObject() {
			return new LeftToRightCurve();
		}
	};
	protected static final float SPEED = initSpeed(18, PK);
	private static final int 
		EXPLOSION = initExplosion(25, PK),
		XP = getXp(BASE_XP, 1);
//	private static final Phase[] PHASES = {		new Phase(				Behavior.STRAIGHT_ON,				null,				null,				Animations.BASIC_ENEMY_RED				)};
	private static final Phase[] PHASES = {		new Phase(				Behavior.PROTOTYPE,				null,				null,				Animations.BASIC_ENEMY_RED				)};
	private static final Pos POS = initPositionnement(Up.PK, PK);
	private float xMeter = 0, yMeter = 0;
	
	@Override
	public void reset() {
		xMeter = 0;
		yMeter = 0;
		super.reset();
	}
	
	@Override
	public void isMoving() {
		if (EndlessMode.alternate)
			Particles.smokeMoving(pos.x + HALF_WIDTH, pos.y + OFFSET_SMOKE, true, getColor());
	}
	
	@Override
	public void setXMeter(float f) {
		xMeter = f;
	}
	@Override
	public void setYMeter(float f) {
		yMeter = f;
	}
	@Override
	public float getXMeter() {
		return xMeter;
	}
	@Override
	public float getYMeter() {
		return yMeter;
	}
	@Override	protected Sound getExplosionSound() {	return SoundMan.explosion6;		}
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	public float getHalfHeight() {			return HALF_HEIGHT;			}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;			}
	@Override	public int getExplosionCount() {		return EXPLOSION;									}
	@Override	public int getBonusValue() {			return BASE_XP;	}
	@Override	public float getHeight() {				return HEIGHT;	}
	@Override	public float getWidth() {				return WIDTH;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	public Phase[] getPhases() {			return PHASES;	}
	@Override	public float getDirectionY() {			return -SPEED;	}
	@Override	public float getSpeed() {				return SPEED;	}
	@Override	protected int getMaxHp() {				return HP;	}
	@Override	public int getXp() {					return XP;						}

}
