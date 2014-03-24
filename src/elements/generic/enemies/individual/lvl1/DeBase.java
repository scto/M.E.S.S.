package elements.generic.enemies.individual.lvl1;

import jeu.EndlessMode;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationEnnemiDeBase;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

import elements.PatternHorizontalPositionnable;
import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.particular.particles.Particles;
import elements.positionning.Pos;
import elements.positionning.Up;

public class DeBase extends Enemy implements PatternHorizontalPositionnable {
	
	protected static final int LARGEUR = Stats.LARGEUR_DE_BASE;
	public static final int DEMI_LARGEUR = LARGEUR / 2;
	private static final int HAUTEUR = Stats.HAUTEUR_DE_BASE;
	public static final int OFFSET_SMOKE = (int) (HAUTEUR * .75f);
	public static final int DEMI_HAUTEUR = HAUTEUR / 2;
	private static final int MAX_ENNEMIS_LIGNE = 4; 
	public static final Pool<DeBase> POOL = new Pool<DeBase>(16,40) {
		@Override
		protected DeBase newObject() {
			return new DeBase();
		}
	};
	public static final Invocable ref = new DeBase();
	private static int nbEnnemisAvant = 0;
	private static float posXInitiale;
	public static final int PK = 3;
	private static final int PV = initPv(Stats.PV_DE_BASE, PK);
	protected static final float SPEED = initSpeed(18, PK);
	private static final int EXPLOSION = initExplosion(25, PK);
	protected static final int BASE_XP = Enemy.initXp(1, PK);
	private static final int XP = getXp(BASE_XP, 1);
	private static final Behavior behavior = initBehavior(PK,Behavior.STRAIGHT);
	private static final Pos positionning = initPositionnement(Up.PK, PK);
	
	@Override
	public void mouvementEtVerif() {
		if (EndlessMode.alternate)
			Particles.smokeMoving(pos.x + DEMI_LARGEUR, pos.y + OFFSET_SMOKE, true);
		super.mouvementEtVerif();
	}
	
	@Override
	public Invocable invoquer() {
		final DeBase e = POOL.obtain();
		LIST.add(e);
		e.init();
		return e;
	}


	@Override
	public void incNbEnnemisAvant() {
		if (++nbEnnemisAvant > MAX_ENNEMIS_LIGNE)
			nbEnnemisAvant = 0;
	}
	
	@Override
	public void reset() {
		positionning.set(this);
		super.reset();
	}

	protected void init() {
		positionning.set(this);
		dir.x = 0;
		dir.y = -getVitesse();
		angle = dir.angle();
	}
	@Override	protected Sound getSonExplosion() {		return SoundMan.explosion6;	}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	public int getHeight() {				return HAUTEUR;	}
	@Override	public int getWidth() {					return LARGEUR;	}
	@Override	public int getHalfHeight() {			return DEMI_HAUTEUR;	}
	@Override	public int getHalfWidth() {				return DEMI_LARGEUR;	}
	@Override	public int getNbEnnemisAvant() {		return nbEnnemisAvant;	}
	@Override	public void setPosXInitiale(float x) {	posXInitiale = x;	}
	@Override	public float getPosXInitiale() {		return posXInitiale;	}
	@Override	protected int getPvMax() {				return PV;	}
	@Override	public TextureRegion getTexture() {		return AnimationEnnemiDeBase.getTexture(now);	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	public float getDirectionY() {			return -SPEED;	}
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	public float getVitesse() {				return SPEED;	}
	@Override	public int getExplosionCount() {		return EXPLOSION;									}
	@Override	public Behavior getBehavior() {			return behavior;	}

}
