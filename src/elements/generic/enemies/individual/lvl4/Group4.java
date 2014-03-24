package elements.generic.enemies.individual.lvl4;

import jeu.CSG;
import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.Group;
import elements.generic.enemies.individual.lvl3.Group3;
import elements.generic.weapons.patterns.TireurPlusieurFois;

public class Group4 extends Group3 implements Poolable, Invocable, TireurPlusieurFois {
	
	public static final Pool<Group4> POOL = new Pool<Group4>() {
		@Override
		protected Group4 newObject() {
			return new Group4();
		}
	};
	public static final Invocable ref = new Group4();
	private static final int PV = getModulatedPv(Stats.PV_GROUP, 4);
	private static final int XP = getXp(BASE_XP, 4);
	private static final float SPEED = getModulatedSpeed(Group.SPEED, 4);
	
	@Override
	public Invocable invoquer() {
		init(POOL.obtain(), CSG.gameZoneWidth);
		init(POOL.obtain(), CSG.gameZoneWidth - LARGEUR);
		init(POOL.obtain(), CSG.gameZoneWidth - LARGEUR * 2f);
		return ref;
	}
	@Override	public int getXp() {									return XP;	}
	@Override	public int getValeurBonus() {							return BASE_XP;	}
	@Override	protected int getPvMax() {								return PV;	}
	@Override	public void free() {									POOL.free(this);	}
	@Override	protected void tir() {									TIR.tirEnRafale(this, 3, now, prochainTir);	}
	@Override	public float getVitesse() {								return SPEED;	}
	
}
