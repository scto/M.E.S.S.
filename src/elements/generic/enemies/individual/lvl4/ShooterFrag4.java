package elements.generic.enemies.individual.lvl4;

import jeu.Stats;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl3.ShooterFrag3;


public class ShooterFrag4 extends ShooterFrag3 {
	
	public static final Pool<ShooterFrag4> POOL = Pools.get(ShooterFrag4.class);
	private static final int LVL = 4, XP = getXp(BASE_XP, LVL);
	
	@Override	public void free() {						POOL.free(this);							}
	@Override	public int getBonusValue() {				return BASE_XP;								}
	@Override	public int getXp() {						return XP;									}
}
