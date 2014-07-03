package elements.generic.enemies.individual.lvl4;

import jeu.CSG;
import jeu.Stats;

import com.badlogic.gdx.utils.Pool;

import elements.generic.enemies.individual.lvl1.Group;
import elements.generic.enemies.individual.lvl3.Group3;

public class Group4 extends Group3 {
	
	public static final Pool<Group4> POOL = new Pool<Group4>() {		protected Group4 newObject() {			return new Group4();		}	};
	private static final int HP = getModulatedPv(Stats.HP_GROUP, 4), XP = getXp(BASE_XP, 4);
	private static final float SPEED = getModulatedSpeed(8, 4), FIRERATE = Group.FIRERATE * 0.8f * MOD_FIRERATE;
	
	public static Group4 initAll() {
		Group4 e = POOL.obtain();
		Group4 f = POOL.obtain();
		Group4 g = POOL.obtain();
		e.init(CSG.gameZoneWidth - DIMENSIONS.width);
		f.init(CSG.gameZoneWidth - DIMENSIONS.width * 2.1f);
		g.init(CSG.gameZoneWidth - DIMENSIONS.width * 3.2f);
		return e;
	}
	@Override	public float getFirerate() {						return FIRERATE;			}
	@Override	public void free() {								POOL.free(this);			}
	@Override	public int getBonusValue() {						return BASE_XP;				}
	@Override	public float getSpeed() {							return SPEED;				}
	@Override	public int getXp() {								return XP;					}
	@Override	protected int getMaxHp() {							return HP;					}
	@Override	public int getNumberOfShots() {						return 5;					}
	
}
