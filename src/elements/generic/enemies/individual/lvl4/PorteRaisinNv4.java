package elements.generic.enemies.individual.lvl4;

import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Crusader;
import elements.generic.enemies.individual.lvl3.PorteRaisinNv3;

public class PorteRaisinNv4 extends PorteRaisinNv3 {

	public static final Pool<PorteRaisinNv4> POOL = Pools.get(PorteRaisinNv4.class);
	private static final int LVL = 4;
	private static final int HP = getModulatedPv(Stats.CRUASER_HP, LVL), DEMI_HP = HP / 2;
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(Crusader.SPEED, LVL);
	
	@Override	public float getSpeed() {				return SPEED;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	protected int getMaxHp() {				return HP;	}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getBonusValue() {			return BASE_XP;	}
	@Override	protected int getPallierPv() {			return DEMI_HP;	}
	@Override	protected String getLabel() {			return getClass().toString();	}
}
