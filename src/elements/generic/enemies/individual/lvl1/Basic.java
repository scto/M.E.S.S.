package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Pool;

import elements.generic.components.Dimensions;
import elements.generic.components.positionning.Positionner;
import elements.generic.enemies.Enemy;
import elements.particular.particles.Particles;

public class Basic extends Enemy {
	
	public static final Dimensions DIMENSIONS = Dimensions.BASIC;
	public static final int OFFSET_SMOKE = (int) (DIMENSIONS.height * .75f), HP = Stats.HP_DE_BASE, LVL = 1;
	protected static final int BASE_XP = 1, EXPLOSION = 25, XP = getXp(BASE_XP, 1);
	public static final Pool<Basic> POOL = new Pool<Basic>(16) {		protected Basic newObject() {			return new Basic();		}	};
	protected static final float SPEED16 = getModulatedSpeed(16, LVL);
	
	@Override
	public void init() {
		Positionner.UP_WIDE.set(this);
		dir.y = -getSpeed();
		super.init();
	}
	
	@Override
	public void move() {
		TMP_POS.set(0, DIMENSIONS.halfHeight);
		if (EndlessMode.alternate)
			Particles.smokeMoving((pos.x + DIMENSIONS.halfWidth) + TMP_POS.x, (pos.y + DIMENSIONS.halfHeight) + TMP_POS.y, true, getColor());
		super.move();
	}
	
	@Override	public Animations getAnimation() {		return Animations.BASIC_ENEMY_RED;	}
	@Override	protected Sound getExplosionSound() {	return SoundMan.explosion6;			}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	@Override	public int getExplosionCount() {		return EXPLOSION;					}	
	@Override	public void free() {					POOL.free(this);					}
	@Override	public float getSpeed() {				return SPEED16;						}
	@Override	public int getBonusValue() {			return BASE_XP;						}
	@Override	protected int getMaxHp() {				return HP;							}
	@Override	public int getXp() {					return XP;							}

}