package elements.generic.enemies.individual.lvl3;

import jeu.Stats;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.QuiTir;
import elements.generic.weapons.enemies.Fireball;

public class QuiTirNv3 extends QuiTir {
	
	public static final int LARGEUR = Stats.LARGEUR_QUI_TIR3, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = Stats.HAUTEUR_QUI_TIR3, DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Pool<QuiTirNv3> POOL = Pools.get(QuiTirNv3.class);
	public static final Invocable ref = new QuiTirNv3();
	private static final int LVL = 3;
	private static final int PV = getModulatedPv(Stats.PV_QUI_TIR, LVL), DEMI_PV = PV / 2;
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(QuiTir.SPEED, LVL);
	
	@Override
	public Invocable invoquer() {
		QuiTirNv3 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		if (numeroTir == 1) TMP_POS.x = pos.x + DEMI_LARGEUR - Fireball.WIDTH;
		else TMP_POS.x = pos.x + DEMI_LARGEUR;
		
		TMP_POS.y = pos.y - Fireball.HEIGHT + Fireball.HALF_HEIGHT;
		return TMP_POS;
	}
	@Override	public void free() {				POOL.free(this);	}
	@Override	protected void tir() {				tir.doubleTirVersBas(this, now, prochainTir);	}
	@Override	public float getVitesse() {			return SPEED;	} 
	@Override	public int getXp() {				return XP;	}
	@Override	public int getValeurBonus() {		return BASE_XP;	}
	@Override	protected int getPvMax() {			return PV;	}
	@Override	protected int getDemiPv() {			return DEMI_PV;	}
	@Override	public int getHeight() {			return HAUTEUR;	}
	@Override	public int getWidth() {				return LARGEUR;	}
	@Override	public int getHalfHeight() {		return DEMI_HAUTEUR;	}
	@Override	public int getHalfWidth() {			return DEMI_LARGEUR;	}
	@Override	protected String getLabel() {		return getClass().toString();	}
}
