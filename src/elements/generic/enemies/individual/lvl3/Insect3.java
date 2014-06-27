package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Insect;
import jeu.Stats;


public class Insect3 extends Insect {
	
	public static final Pool<Insect3> POOL = Pools.get(Insect3.class);
	private static final int HP = getModulatedPv(Stats.INSECT_HP, 3), XP = getXp(BASE_XP, 3);
	
	@Override
	protected int getNumberOfShotsRandom() {
		return 6;
	}
	@Override	protected int getMaxHp() {					return HP;						}
	@Override	public int getXp() {						return XP;						}
	@Override	public int getBonusValue() {				return BASE_XP;					}
	@Override	public void free() {						POOL.free(this);				}
}
