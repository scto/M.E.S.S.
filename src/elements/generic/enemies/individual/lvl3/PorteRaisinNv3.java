package elements.generic.enemies.individual.lvl3;

import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Crusader;

public class PorteRaisinNv3 extends Crusader {

	public static final Pool<PorteRaisinNv3> POOL = Pools.get(PorteRaisinNv3.class);
	private static final int HP = getModulatedPv(Stats.CRUASER_HP, 3), DEMI_HP = HP / 2;
	private static final int XP = getXp(BASE_XP, 3);
	
	@Override	public void free() {				POOL.free(this);	}
	@Override	protected int getMaxHp() {			return HP;	}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getBonusValue() {		return BASE_XP;	}
	@Override	protected int getPallierPv() {		return DEMI_HP;	}
	@Override	protected String getLabel() {		return getClass().toString();	}
}
