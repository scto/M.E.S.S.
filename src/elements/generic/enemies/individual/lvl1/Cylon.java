package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.SoundMan;
import assets.animation.AnimationCylon;
import assets.animation.AnimationCylonCasse;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.enemies.Meteorite;
import elements.generic.weapons.patterns.TireurAngle;
import elements.generic.weapons.patterns.Tirs;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;
import elements.positionning.Pos;
import elements.positionning.UpWide;

public class Cylon extends Enemy implements TireurAngle {
	
	private static final int LARGEUR = Stats.LARGEUR_CYLON, DEMI_LARGEUR = LARGEUR/2, QUART_WIDTH = LARGEUR / 4;
	public static final Pool<Cylon> POOL = Pools.get(Cylon.class);
	public static final int PK = 2;
	protected static final float CADENCE = initFirerate(3, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(2.6f, PK);
	protected static final float SPEED = initSpeed(8, PK);
	private static final int PV = initPv(Stats.PV_CYLON, PK), HALF_HP = PV/2;
	private static final int EXPLOSION = initExplosion(35, PK);
	protected static final int BASE_XP = Enemy.initXp(12, PK);
	private static final int XP = getXp(BASE_XP, 1);
	private static final Behavior behavior = initBehavior(PK,Behavior.STRAIGHT);
	protected static final Tirs tir = new Tirs(CADENCE);
	public static final InvocableWeapon weapon = initEnemyWeapon(Meteorite.PK, PK);
	public static final Invocable ref = new Cylon();
	private static final Pos positionning = initPositionnement(UpWide.PK, PK);
	protected float prochainTir;
	private boolean goodShape;

	protected void init() {
		positionning.set(this);
		if (pos.x + DEMI_LARGEUR < CSG.screenHalfWidth)
			dir.x = 0.26f * Stats.V_ENN_CYLON;
		else
			dir.x = -0.26f * Stats.V_ENN_CYLON;
		dir.y = -0.83f * Stats.V_ENN_CYLON;
		goodShape = true;
		prochainTir = 2.6f;
		angle = dir.angle();
	}
	
	@Override
	protected TextureRegion getTexture() {
		if (goodShape) 	return AnimationCylon.getTexture(now);
		else 			return AnimationCylonCasse.getTexture(now);
	}

	@Override
	public void mouvementEtVerif() {
		if (EndlessMode.alternate && !goodShape)
			Particles.smoke(pos.x + QUART_WIDTH, pos.y + QUART_WIDTH, false);
		super.mouvementEtVerif();
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = (pos.x) + dir.x*0.8f;
		TMP_POS.y = (pos.y) + (dir.y*0.8f);
		return TMP_POS;
	}
	
	@Override
	public Invocable invoquer() {
		final Cylon l = POOL.obtain();
		l.init();
		LIST.add(l);
		return l;
	}
	
	@Override
	public boolean stillAlive(PlayerWeapon p) {
		if (p.getPower() < getDemiPv() && goodShape) {
			goodShape = false;
			dir.rotate(CSG.R.nextInt(30)-15);
			angle = dir.angle();
		}
		return super.stillAlive(p);
	}
	
	@Override	protected void tir() {					tir.tirToutDroit(this, now, prochainTir);	}
	@Override	public int getValeurBonus() {			return BASE_XP;			}
	@Override	public int getXp() {					return XP;				}
	@Override	public EnemyWeapon getArme() {			return weapon.invoke();				}
	@Override	protected String getLabel() {			return getClass().toString();				}
	@Override	protected Sound getSonExplosion() {		return SoundMan.explosion4;					}
	@Override	protected int getPvMax() {				return PV;						}
	@Override	public int getHalfHeight() {			return DEMI_LARGEUR;						}
	@Override	public int getHalfWidth() {				return DEMI_LARGEUR;						}
	@Override	public float getDirectionY() {			return dir.y;								}
	@Override	public float getDirectionX() {			return dir.x;								}
	@Override	public Vector2 getDirectionTir() {		return dir;									}
	@Override	public void setProchainTir(float f) {	prochainTir = f;							}
	@Override	public void free() {					POOL.free(this);							}
	@Override	public int getHeight() {				return LARGEUR;								}
	@Override	public int getWidth() {					return LARGEUR;								}
	@Override	public float getModifVitesse() {		return 0.01f;								}
	@Override	public float getAngleTir() {			return dir.angle();							}
	@Override	public int getExplosionCount() {		return EXPLOSION;							}
	@Override	public Behavior getBehavior() {			return behavior;							}
	protected int getDemiPv() {							return HALF_HP;								}
}
