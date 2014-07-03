package elements.generic.enemies.individual.lvl4;

import jeu.Stats;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.shots.AbstractShot;
import elements.generic.components.shots.Gatling;
import elements.generic.enemies.individual.lvl1.Shooter;
import elements.generic.enemies.individual.lvl3.Shooter3;
import elements.generic.weapons.enemies.Fireball;

public class Shooter4 extends Shooter3 {
	
	public static final int HP = getModulatedPv(Stats.HP_QUI_TIR, LVL), DEMI_HP = HP / 2, XP = getXp(BASE_XP, LVL);
	public static final Pool<Shooter4> POOL = Pools.get(Shooter4.class);
	private static final float SPEED = Shooter.SPEED * Stats.VNV4;
	
	@Override
	protected void shoot() {
		TMP_POS.set(pos.x + (DIMENSIONS.halfWidth - Fireball.DIMENSIONS.halfWidth), pos.y - Fireball.DIMENSIONS.height);
		AbstractShot.shootDownRandom(Gatling.FIREBALL, TMP_POS, Stats.U12, 10);
		interval(-1);
	}
	
	@Override	public int getXp() {				return XP;						}
	@Override	protected int getMaxHp() {			return HP;						}
	@Override	public float getSpeed() {			return SPEED;					}		 
	@Override	public int getBonusValue() {		return BASE_XP;					}
	@Override	protected int getDemiPv() {			return DEMI_HP;					}
	@Override	public void free() {				POOL.free(this);				}
}
