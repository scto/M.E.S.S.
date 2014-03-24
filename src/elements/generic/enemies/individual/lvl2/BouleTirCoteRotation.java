package elements.generic.enemies.individual.lvl2;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import jeu.Stats;

public class BouleTirCoteRotation extends BouleTirCote {

	public static final float VITESSE_ANGLE = 100f, CADENCETIR = .04f;
	public static final Pool<BouleTirCoteRotation> POOL = Pools.get(BouleTirCoteRotation.class);
	public static final Invocable ref = new BouleTirCoteRotation();
	
	@Override
	public Vector2 getDirectionTir() {
		super.getDirectionTir();
		return TMP_DIR.rotate(angle);
	}
	
	public void setProchainTir(float f) {
		if (++numeroTir > 10) numeroTir = 1;
		prochainTir = f + CADENCETIR * numeroTir;
	}
	
	@Override
	public Invocable invoquer() {
		final BouleTirCoteRotation l = POOL.obtain();
		LIST.add(l);
		l.placement();
		return l;
	}
	
	@Override	public float getVitesse() {				return  Stats.V_BOULE_TIR_COTE;	}
	@Override	public int getXp() {					return 61;	}
	@Override	public int getValeurBonus() {			return 70;	}
	@Override	public float getModifVitesse() {		return 1.3f;	}
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	public void free() {					POOL.free(this);	}
}
