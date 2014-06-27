package elements.generic.enemies.individual.lvl3;

import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.behavior.Mover;
import elements.generic.enemies.individual.lvl1.Basic;

public class Basic3 extends Basic {
	
	public static final Pool<Basic3> POOL = Pools.get(Basic3.class);
	private static final int LVL = 3, HP = getModulatedPv(Stats.HP_DE_BASE, LVL), XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(16 * 1.9f, LVL);
	
	@Override
	public void move() {
		Mover.detectX(this, 0.2f);
		super.move();
	}
	@Override	public Animations getAnimation() {			return Animations.BASIC_ENEMY_BLUE;	}
	@Override	public void free() {						POOL.free(this);					}
	@Override	public int getBonusValue() {				return BASE_XP;						}
	@Override	public float getSpeed() {					return SPEED;						}
	@Override	public int getColor() {						return BLUE;						}
	@Override	protected int getMaxHp() {					return HP;							}
	@Override	public int getXp() {						return XP;							}
}
