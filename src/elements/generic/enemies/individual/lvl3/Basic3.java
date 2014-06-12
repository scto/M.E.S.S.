package elements.generic.enemies.individual.lvl3;

import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.enemies.individual.lvl1.Basic;

public class Basic3 extends Basic {
	
	public static final Pool<Basic3> POOL = Pools.get(Basic3.class);
	public static final int PK = 17;
	private static final int HP = initHp(getModulatedPv(Stats.HP_DE_BASE, 4), PK);
	private static final int XP = initXp(getXp(BASE_XP, 4), PK);
	private static final float SPEED = initSpeed(Basic.SPEED/2, PK);
//	private static final Phase[] PHASES = {		new Phase(				
//			initBehavior(Behavior.STRAIGHT_ON.pk, PK),
//			null,
//			null,
//			Animations.BASIC_ENEMY_BLUE
//			)};
	private static final Phase[] PHASES = {		
		new Phase(
				initBehavior(Behavior.STRAIGHT_ON.pk, PK),
				initWeapon(0, PK),
				initShot(0, PK),
				initAnimation(Animations.BASIC_ENEMY_BLUE.pk, PK)
				)};
	
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public void free() {						POOL.free(this);				}
	@Override	public int getBonusValue() {				return BASE_XP;					}
	@Override	public Phase[] getPhases() {				return PHASES;					}
	@Override	public float getSpeed() {					return SPEED;					}
	@Override	public int getColor() {						return BLUE;					}
	@Override	protected int getMaxHp() {					return HP;						}
	@Override	public int getXp() {						return XP;						}
}
