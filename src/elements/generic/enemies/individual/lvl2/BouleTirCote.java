package elements.generic.enemies.individual.lvl2;

import jeu.CSG;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimBall;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.behavior.StraightOn;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.ArmeBouleTir;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.patterns.TireurAngle;
import elements.generic.weapons.patterns.Tirs;

public class BouleTirCote extends Enemy implements TireurAngle {
	
	private static final int LARGEUR = Stats.LARGEUR_BOULE_TIR_COTE, DEMI_LARGEUR = LARGEUR/2;
	private static final float CADENCETIR = .12f, OFFSET = DEMI_LARGEUR - ArmeBouleTir.DEMI_LARGEUR;
	public static final Pool<BouleTirCote> POOL = Pools.get(BouleTirCote.class);
	protected final static Tirs TIR = new Tirs(.12f);
	public static final Invocable ref = new BouleTirCote(); 
	protected float prochainTir;
	protected int numeroTir;
	private static final Behavior behavior = new StraightOn();
	
	protected void placement() {
		dir.x = 0;
		dir.y = -getVitesse();
		prochainTir = 1;
		numeroTir = 1;
		pos.x = CSG.gameZoneHalfWidth;
		pos.y = CSG.SCREEN_HEIGHT;
		angle = CSG.gameZoneHalfWidth - (pos.x + LARGEUR*2);
		angle /= 4;
		dir.rotate(angle);
		angle += 180;
	}
	
	public void setProchainTir(float f) {
		if (++numeroTir > 7) numeroTir = 1;
		prochainTir = f + CADENCETIR * numeroTir;
	}

	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = pos.x + OFFSET; 
		TMP_POS.y = pos.y + OFFSET;
		return TMP_POS;
	}
	
	public Vector2 getDirectionTir() {
		TMP_DIR.x = dir.x;
		TMP_DIR.y = dir.y;
		return TMP_DIR;
	}

	@Override
	public Invocable invoquer() {
		final BouleTirCote l = POOL.obtain();
		LIST.add(l);
		l.placement();
		return l;
	}

	@Override	protected float getAngle() {				return angle;	}
	@Override	public float getVitesse() {					return Stats.V_BOULE_TIR_COTE;	}
	@Override	protected TextureRegion getTexture() {		return AnimBall.getTexture(now);	}
	@Override	protected void tir() {						TIR.tirSurCote(this, now, prochainTir);	}
	@Override	public int getXp() {						return 73;	}
	@Override	public int getValeurBonus() {				return 80;	}
	@Override	public int getHeight() {					return LARGEUR;	}
	@Override	public int getWidth() {						return LARGEUR;	}
	@Override	public int getHalfHeight() {				return DEMI_LARGEUR;	}
	@Override	public int getHalfWidth() {					return DEMI_LARGEUR;	}
	@Override	public EnemyWeapon getArme() {				return ArmeBouleTir.POOL.obtain();	}
	@Override	public float getModifVitesse() {			return 1;	}
	@Override	protected int getPvMax() {					return Stats.PV_BOULE_COTE_PETIT;	}
	@Override	protected Sound getSonExplosion() {			return SoundMan.explosion4;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	public float getAngleTir() {				return angle;	}
	@Override	public float getDirectionY() {				return dir.y;	}
	@Override	public float getDirectionX() {				return dir.x;	}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public int getExplosionCount() {			return 40;											}
	@Override	public Behavior getBehavior() {				return behavior;	}
}
