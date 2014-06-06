package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
import jeu.Stats;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.positionning.Pos;
import elements.generic.components.positionning.UpWide;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.BlueBullet;

public class QuiTourne extends Enemy {
	
	private static final int WIDTH = Stats.WIDTH_QUI_TOURNE, HALF_WIDTH = WIDTH/2, WIDTH2 = WIDTH * 2;
	public static final Pool<QuiTourne> POOL = Pools.get(QuiTourne.class);
	public static final int PK = 12;
	protected static final float FIRERATE = initFirerate(.25f, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(6, PK);
	protected static final float SPEED = initSpeed(18, PK);
	private static final int HP = initHp(Stats.HP_QUI_TOURNE, PK);
	private static final int EXPLOSION = initExplosion(25, PK);
	protected static final int BASE_XP = Enemy.initXp(19, PK);
	private static final int XP = getXp(BASE_XP, 1);
	private static final Pos positionning = initPositionnement(UpWide.PK, PK);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,		Gatling.BLUE_BULLET,				Shot.SHOT_EN_RAFALE,				Animations.ENNEMI_TOURNE				), 
		new Phase(				Behavior.OVALE,				Gatling.BLUE_BULLET,				Shot.SHOT_EN_RAFALE,				Animations.ENNEMI_TOURNE				), 
		new Phase(				Behavior.GO_AWAY,			null,								null,								Animations.ENNEMI_TOURNE				)		};
	private int numeroTir;
	private static final int PHASE_DURATION = 15;

	public void init() {
		positionning.set(this);
		if (CSG.R.nextBoolean())
			pos.x = CSG.gameZoneHalfWidth - Stats.U10;
		else
			pos.x = CSG.gameZoneHalfWidth + Stats.U10;
		pos.y = CSG.SCREEN_HEIGHT;
		dir.x = 0;
		dir.y = -getSpeed();
		nextShot = INIT_NEXT_SHOT;
	}
	
	@Override
	protected void isMoving() {
		switch (index) {
		case 0:
			if (pos.y + WIDTH2 < CSG.halfHeight)
				changePhase();
			break;
		case 1:
			if (phaseTime > getPhaseDuration())
				changePhase();
			break;
		}
	}
	
	protected float getPhaseDuration() {
		return PHASE_DURATION;
	}

	@Override
	public Vector2 getShotPosition(int numeroTir) {
		TMP_POS.x = (pos.x + HALF_WIDTH - BlueBullet.HALF_WIDTH);
		TMP_POS.y = (pos.y + HALF_WIDTH - BlueBullet.HALF_WIDTH);
		return TMP_POS;
	}
	
	/**
	 * The attraction of the center
	 */
	@Override	public float getFloatFactor() {			return 0.05f;					}
	@Override	public int getNumberOfShots() {			return 2;				}
	@Override 	public float getFirerate() {			return FIRERATE;								}
	@Override	public float getSpeed() {				return SPEED;					}
	@Override	protected Sound getExplosionSound() {	return SoundMan.explosion1;					}
	@Override	protected int getMaxHp() {				return HP;										}
	@Override	public void free() {					POOL.free(this);								}
	@Override	public int getXp() {					return XP;										}
	@Override	public int getBonusValue() {			return BASE_XP;									}
	@Override	public float getHeight() {				return WIDTH;									}
	@Override	public float getWidth() {				return WIDTH;									}
	@Override	public float getHalfHeight() {			return HALF_WIDTH;							}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;							}
	@Override	public float getBulletSpeedMod() {		return 0.005f;									}
	@Override	public float getShootingAngle() {		return 0;										}
	@Override	public Vector2 getShootingDir() {		return dir;										}
	@Override	public int getShotNumber() {			return numeroTir;								}
	@Override	public void addShots(int i) {			numeroTir += i;									}
	@Override	public float getDirectionY() {			return dir.y;									}
	@Override	public float getDirectionX() {			return dir.x;									}
	@Override	protected String getLabel() {			return getClass().toString();					}
	protected float getDemiVitesse() {					return Stats.DEMI_V_ENN_QUI_TOURNE;				}
	@Override	public int getExplosionCount() {		return EXPLOSION;										}
	@Override	public Phase[] getPhases() {			return PHASES;								}
}
