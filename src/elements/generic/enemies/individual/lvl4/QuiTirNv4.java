package elements.generic.enemies.individual.lvl4;

import jeu.EndlessMode;
import jeu.Stats;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.Player;
import elements.generic.enemies.individual.lvl1.QuiTir;
import elements.generic.enemies.individual.lvl3.QuiTirNv3;
import elements.generic.weapons.enemies.Fireball;

public class QuiTirNv4 extends QuiTirNv3 {
	
	public static final int LARGEUR = Stats.LARGEUR_QUI_TIR3, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = Stats.HAUTEUR_QUI_TIR3, DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Pool<QuiTirNv4> POOL = Pools.get(QuiTirNv4.class);
	public static final Invocable ref = new QuiTirNv4();
	private static final int LVL = 4;
	private static final int PV = getModulatedPv(Stats.PV_QUI_TIR, LVL), DEMI_PV = PV / 2;
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(QuiTir.SPEED, LVL);
	
	@Override
	public Invocable invoquer() {
		QuiTirNv4 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override
	public void mouvementEtVerif() {
		if (pos.x < Player.POS.x)	pos.x += EndlessMode.delta15;
		else						pos.x -= EndlessMode.delta15;
		super.mouvementEtVerif();
	}
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		if (numeroTir == 1) TMP_POS.x = pos.x + DEMI_LARGEUR - Fireball.WIDTH;
		else TMP_POS.x = pos.x + DEMI_LARGEUR;
		
		TMP_POS.y = pos.y - Fireball.HEIGHT + Fireball.HALF_HEIGHT;
		return TMP_POS;
	}
	@Override	public void free() {				POOL.free(this);	}
	@Override	public float getVitesse() {			return SPEED;	} 
	@Override	public int getXp() {				return XP;	}
	@Override	public int getValeurBonus() {		return BASE_XP;	}
	@Override	protected int getPvMax() {			return PV;	}
	@Override	protected int getDemiPv() {			return DEMI_PV;	}
	@Override	protected void tir() {				tir.doubleTirVersBas(this, now, prochainTir);	}
	@Override	protected String getLabel() {		return getClass().toString();	}
}
