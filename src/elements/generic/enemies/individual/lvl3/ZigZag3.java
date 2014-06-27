package elements.generic.enemies.individual.lvl3;

import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.ZigZag;

public class ZigZag3 extends ZigZag {

	public static final Pool<ZigZag3> POOL = Pools.get(ZigZag3.class);
	public static final int LVL = 3, HP = getModulatedPv(Stats.HP_ZIGZAG, LVL), XP = getXp(BASE_XP, LVL);
	private static final float SPEED = ZigZag.SPEED * 1.2f;
	private static final Vector2 smokeVector = new Vector2(0, Stats.uSur2);
	
	@Override
	protected float getFloatFactor() {
		return 1.5f;
	}
	@Override	public Animations getAnimation() {			return Animations.ZIG_ZAG_BLUE;	}
	@Override	public void free() {						POOL.free(this);				}
	@Override	public int getBonusValue() {				return BASE_XP;					}
	@Override	public float getSpeed() {					return SPEED;					}
	@Override	public int getColor() {						return BLUE;					}
	@Override	protected int getMaxHp() {					return HP;						}
	@Override	public int getXp() {						return XP;						}
	protected Vector2 getSmokeVector() {					return smokeVector;				}
}
