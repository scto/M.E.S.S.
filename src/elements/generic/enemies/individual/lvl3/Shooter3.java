package elements.generic.enemies.individual.lvl3;

import jeu.Stats;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Shooter;

public class Shooter3 extends Shooter {
	
	public static final int LVL = 3, HP = getModulatedPv(Stats.HP_QUI_TIR, LVL), DEMI_HP = HP / 2, XP = getXp(BASE_XP, LVL);
	public static final Pool<Shooter3> POOL = Pools.get(Shooter3.class);
	private static final float SPEED = Shooter.SPEED * Stats.SLVL3, FIRERATE = Shooter.FIRERATE * 0.4f  * MOD_FIRERATE;
	private int shotNumber = 0;
	
	@Override
	protected void shoot() {
		super.shoot();
		interval(0);
	}
	protected void interval(int init) {
		if (++shotNumber > 2) {
			shotNumber = init;
			nextShot += FIRERATE * 2;
		}
	}
	@Override	public void free() {				POOL.free(this);				}
	@Override	public float getFirerate() {		return FIRERATE;				}
	@Override	protected int getDemiPv() {			return DEMI_HP;					}
	@Override	public int getBonusValue() {		return BASE_XP;					}
	@Override	public float getSpeed() {			return SPEED;					}		 
	@Override	public int getXp() {				return XP;						}
	@Override	protected int getMaxHp() {			return HP;						}
}
