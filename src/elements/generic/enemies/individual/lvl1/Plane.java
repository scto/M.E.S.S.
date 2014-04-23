package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Physic;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationAvion;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.HalfSizeFireball;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.patterns.Tireur;
import elements.generic.weapons.patterns.Tirs;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;
import elements.particular.particles.individual.MovingSmoke;
import elements.positionning.Pos;
import elements.positionning.Up;


public class Plane extends Enemy implements Tireur {
	
	public static final int WIDTH = Stats.PLANE_WIDTH, HALF_WIDTH = WIDTH/2, HEIGHT = Stats.PLANE_HEIGHT, HALF_HEIGHT = HEIGHT / 2, QUART_WIDTH = WIDTH / 4, OFFSET_SMOKE_LEFT = (int) (WIDTH * .48f), OFFSET_SMOKE_RIGHT = (int) (WIDTH * .52f), OFFSET_SMOKE = (int) (HEIGHT * 0.98f);  
	private static final int offsetWeaponRight = WIDTH - HalfSizeFireball.WIDTH, offsetWeaponY = HALF_HEIGHT - HalfSizeFireball.HEIGHT;
	public static final Pool<Plane> POOL = Pools.get(Plane.class);
	public static final Invocable ref = new Plane();
	protected boolean goodShape;
	
	public static final int PK = 8;
	protected static final float CADENCE = initFirerate(0.8f, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(1.5f, PK);
	protected static final float SPEED = initSpeed(19, PK), HALF_SPEED = SPEED / 2;
	private static final int PV = initPv(Stats.PV_AVION, PK), HALF_HP = PV/2;
	private static final int EXPLOSION = initExplosion(55, PK);
	protected static final int BASE_XP = Enemy.initXp(45, PK);
	private static final int XP = getXp(BASE_XP, 1);
	private static final Behavior behavior = initBehavior(PK,Behavior.STRAIGHT);
	protected float prochainTir = INIT_NEXT_SHOT;
	protected static final Tirs tir = new Tirs(CADENCE);
	private static final InvocableWeapon weapon = initEnemyWeapon(HalfSizeFireball.PK, PK);
	private static final Pos positionning = initPositionnement(Up.PK, PK);
	
	@Override
	public Vector2 getPositionDuTir(int shotNumber) {
		Physic.spray(pos, WIDTH);
		switch (shotNumber) {
		case 1:		TMP_POS.x = pos.x;						break;
		case 2:		TMP_POS.x = pos.x + offsetWeaponRight;	break;
		}
		TMP_POS.y = pos.y + offsetWeaponY;
		return TMP_POS;
	}

	@Override
	public void mouvementEtVerif() {
		if (EndlessMode.alternate)	Particles.smokeMoving(pos.x + OFFSET_SMOKE_LEFT, pos.y + OFFSET_SMOKE, true, MovingSmoke.colorsRed);
		else						Particles.smokeMoving(pos.x + OFFSET_SMOKE_RIGHT, pos.y + OFFSET_SMOKE, true, MovingSmoke.colorsRed);
		super.mouvementEtVerif();
	}
	
	@Override
	public float getVitesse() {
		if (goodShape)
			return SPEED;
		return HALF_SPEED;
	}
	@Override
	public Invocable invoquer() {
		final Plane l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	
	protected void init() {
		positionning.set(this);
		pos.y = CSG.SCREEN_HEIGHT;
		goodShape = true;
		prochainTir = INIT_NEXT_SHOT;
		dir.x = 0;
		dir.y = -getVitesse();
		angle = -90;
	}
	
	@Override
	public boolean stillAlive(PlayerWeapon p) {
		if (pv-p.getPower() < getDemiPv()) {
			goodShape = false;
			dir.y = -getVitesse();
			for (int i = 0; i < 10; i++) 
				Particles.smoke(pos.x + QUART_WIDTH + (CSG.R.nextFloat() * HALF_WIDTH), pos.y + CSG.R.nextFloat() * HEIGHT, false);
		}
		return super.stillAlive(p);
	}
	
	@Override	public void free() {					POOL.free(this);								}
	@Override	protected int getPvMax() {				return PV;							}
	@Override	protected Sound getSonExplosion() {		return SoundMan.explosion5;						}
	@Override	public int getHeight() {				return HEIGHT;									}
	@Override	public int getWidth() {				    return WIDTH;									}
	@Override	public int getHalfHeight() {			return HALF_HEIGHT;								}
	@Override	public int getHalfWidth() {				return HALF_WIDTH;								}
	@Override	public EnemyWeapon getArme() {			return weapon.invoke();			}
	@Override	public void setProchainTir(float f) {	prochainTir = f;								}
	@Override	public float getModifVitesse() {		return 1;										}
	@Override	protected TextureRegion getTexture() {	return AnimationAvion.getTexture(goodShape);	}
	@Override	protected void tir() {					tir.doubleTirVersBas(this, now, prochainTir);	}
	@Override	public int getXp() {					return XP;					}
	@Override	public int getValeurBonus() {			return BASE_XP;			}
	@Override	protected String getLabel() {			return getClass().toString();					}
	@Override	public float getDirectionY() {			return -getVitesse();							}
	@Override	public int getExplosionCount() {		return EXPLOSION;										}
	@Override	public Behavior getBehavior() {			return behavior;								}
	protected int getDemiPv() {							return HALF_HP;				}
}
