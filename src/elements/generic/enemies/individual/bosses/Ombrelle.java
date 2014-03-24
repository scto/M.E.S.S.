package elements.generic.enemies.individual.bosses;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationOmbrelle;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.Fireball;
import elements.generic.weapons.enemies.HalfSizeFireball;
import elements.generic.weapons.patterns.TireurAngle;
import elements.generic.weapons.patterns.Tirs;
import elements.positionning.Middle;
import elements.positionning.Pos;

public class Ombrelle extends Enemy implements TireurAngle {
	
	public static final int LARGEUR = (int) (CSG.screenWidth / 2.1f), DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (int) (LARGEUR - (DEMI_LARGEUR/1.5f)), DEMI_HAUTEUR = HAUTEUR / 2;
	public static final float CADENCE = 0.5f;
	public static final Tirs tir = new Tirs(CADENCE);
	private Vector2 direction = new Vector2();
	protected float prochainTir = .1f;
	public static Pool<Ombrelle> pool = Pools.get(Ombrelle.class);
	private float angle = 0;
	private static final Pos positionnement = new Middle();
	
	public Ombrelle() {
		super();
		init();
	}
	
	public void init() {
		direction.x = 0;
		direction.y = -Stats.V_ENN_OMBRELLE;
		prochainTir = .1f;
		positionnement.set(this);
	}
	
	@Override
	public void reset() {
		super.reset();
		init();
	}
	
	@Override
	public void mouvementEtVerif() {
		angle = Physic.mvtOmbrelle(this, direction);
		super.mouvementEtVerif();
	}
	
	@Override
	protected void tir() {
		tir.tirOmbrelle(this, 11, 10.8f, now, prochainTir, 7);
	}
	@Override
	protected float getAngle() {		return angle + 90;	}
	@Override
	protected TextureRegion getTexture() {		return AnimationOmbrelle.getTexture(pv);	}
	@Override
	protected int getPvMax() {					return Stats.PV_OMBRELLE;	}
	@Override
	public int getXp() {						return 0;	}
	@Override
	protected Sound getSonExplosion() {		return SoundMan.bigExplosion;	}
	@Override
	public int getHeight() {			return HAUTEUR;	}
	@Override
	public int getWidth() {			return LARGEUR;	}
	@Override
	public int getHalfHeight() {		return DEMI_HAUTEUR;	}
	@Override
	public int getHalfWidth() {		return DEMI_LARGEUR;	}
	@Override
	public Invocable invoquer() {
		Ombrelle l = pool.obtain();
		LIST.add(l);
		return l;
	}
	@Override
	public void free() { pool.free(this);	}
	@Override
	public float getDirectionY() {		return direction.y;	}
	@Override
	public EnemyWeapon getArme() {		return HalfSizeFireball.POOL.obtain();	}
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {		
		if (numeroTir != 6) {
			TMP_POS.x = (pos.x + DEMI_LARGEUR) - HalfSizeFireball.HALF_WIDTH;
			TMP_POS.y = (pos.y + DEMI_HAUTEUR) - HalfSizeFireball.HALF_WIDTH;
			TMP_DIR.y = DEMI_HAUTEUR - HalfSizeFireball.HALF_WIDTH;
		} else {
			TMP_POS.x = (pos.x + DEMI_LARGEUR) - Fireball.HALF_WIDTH;
			TMP_POS.y = (pos.y + DEMI_HAUTEUR) - Fireball.HALF_HEIGHT;
			TMP_DIR.y = DEMI_HAUTEUR ;
		}
		TMP_DIR.x = 0;
		// On additionne pour partir du haut de l'ennemi
		TMP_DIR.rotate(angle+90);
		TMP_POS.x += TMP_DIR.x;
		TMP_POS.y += TMP_DIR.y;
		return TMP_POS;
	}
	@Override
	public float getModifVitesse() {		return 200f;	}
	@Override
	public void setProchainTir(float f) { prochainTir = f;	}
	@Override
	public float getAngleTir() {		return angle - 90;	}
	@Override
	public Vector2 getDirectionTir() {		
		TMP_DIR.x = direction.x;
		TMP_DIR.y = direction.y;
		TMP_DIR.nor();
		return TMP_DIR;
	}
	
	@Override
	protected String getLabel() {
		return getClass().toString();
	}

	@Override
	public int getValeurBonus() {
		return 0;
	}

	@Override
	public int getExplosionCount() {
		return 50;
	}

	@Override
	public Behavior getBehavior() {
		return null;
	}
}
