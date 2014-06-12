package elements.generic.enemies.individual.lvl3;

import jeu.CSG;
import jeu.Stats;

import com.badlogic.gdx.utils.Pool;

import elements.generic.enemies.individual.lvl1.Group;

public class Group3 extends Group {
	
	public static final Pool<Group3> POOL = new Pool<Group3>() {
		protected Group3 newObject() {
			return new Group3();
		}
	};
	private static final int HP = getModulatedPv(Stats.HP_GROUP, 3), XP = getXp(BASE_XP, 3);
	private static final float SPEED = getModulatedSpeed(Group.SPEED, 3), FIRERATE = Group.FIRERATE * 0.9f;
	
	public static Group3 initAll() {
		Group3 e = POOL.obtain();
		Group3 f = POOL.obtain();
		Group3 g = POOL.obtain();
		e.init(CSG.gameZoneWidth - WIDTH);
		f.init(CSG.gameZoneWidth - WIDTH * 2.1f);
		g.init(CSG.gameZoneWidth - WIDTH * 3.2f);
		return e;
	}
	@Override	public float getFirerate() {							return FIRERATE;			}
	@Override	public void free() {									POOL.free(this);			}
	@Override	public int getBonusValue() {							return BASE_XP;				}
	@Override	public float getSpeed() {								return SPEED;				}
	@Override	public int getXp() {									return XP;					}
	@Override	protected int getMaxHp() {								return HP;					}
	@Override	public int getNumberOfShots() {							return 4;					}
}
