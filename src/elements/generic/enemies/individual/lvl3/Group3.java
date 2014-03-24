package elements.generic.enemies.individual.lvl3;

import jeu.CSG;
import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.Group;
import elements.generic.weapons.patterns.TireurPlusieurFois;
import elements.generic.weapons.patterns.Tirs;

public class Group3 extends Group implements Poolable, Invocable, TireurPlusieurFois {
	
	public static final Pool<Group3> POOL = new Pool<Group3>() {
		protected Group3 newObject() {
			return new Group3();
		}
	};
	public static final Invocable ref = new Group3();
	public static final Tirs TIR = new Tirs(CADENCE * 0.7f);
	private static final int PV = getModulatedPv(Stats.PV_GROUP, 3);
	private static final int XP = getXp(BASE_XP, 3);
	private static final float SPEED = getModulatedSpeed(Group.SPEED, 3);
	
	@Override
	public Invocable invoquer() {
		init(POOL.obtain(), CSG.gameZoneWidth);
		init(POOL.obtain(), CSG.gameZoneWidth - LARGEUR * 1.4f);
		init(POOL.obtain(), CSG.gameZoneWidth - LARGEUR * 2.8f);
		return ref;
	}
	
	@Override	public int getXp() {									return XP;	}
	@Override	public int getValeurBonus() {							return BASE_XP;	}
	@Override	protected int getPvMax() {								return PV;	}
	@Override	public void free() {									POOL.free(this);	}
	@Override	protected void tir() {									TIR.tirEnRafale(this, 4, now, prochainTir);	}
	@Override	public float getVitesse() {								return SPEED;	}
}
