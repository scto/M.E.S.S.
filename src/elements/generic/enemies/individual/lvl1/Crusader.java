package elements.generic.enemies.individual.lvl1;

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
import elements.generic.weapons.enemies.KinderWeapon;
import elements.generic.weapons.player.PlayerWeapon;

public class Crusader extends Enemy {
	
	private static final int 
		WIDTH = Stats.CRUSADER_WIDTH,
		HALF_WIDTH = WIDTH/2, 
		HEIGHT = Stats.CRUSADER_HEIGHT, 
		HALF_HEIGHT = HEIGHT / 2; 
	public static final Pool<Crusader> POOL = Pools.get(Crusader.class);
	public static final int PK = 9;
	protected static final int BASE_XP = Enemy.initXp(91, PK);
	protected static final float 
		FIRERATE = initFirerate(.4f, PK), 
		INIT_NEXT_SHOT = initNextShot(3, PK);
	protected static final float 
		SPEED = initSpeed(4, PK),
		ROTATION_BETWEEN_SHOTS = 4;
	private static final int 
		HP = initHp(Stats.CRUASER_HP, PK), 
		HALF_HP = HP/2,
		EXPLOSION = initExplosion(40, PK),
		XP = getXp(BASE_XP, 1);
	private static final Pos POS = initPositionnement(UpWide.PK, PK);
	private static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.KINDER_WEAPON,				Shot.SHOT_EN_RAFALE_LEFT_RIGHT,				Animations.BLUE_CRUSADER_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.KINDER_WEAPON,				Shot.SHOT_EN_RAFALE_LEFT_RIGHT,				Animations.BLUE_CRUSADER_BAD				)		};	
	protected float shootingAngle;
	private int	shotNumber = 3;

	public void init() {
		POS.set(this);
		nextShot = INIT_NEXT_SHOT;
		shootingAngle = 0;
		index = 0;
		dir.x = 0;
		dir.y = -getSpeed();
	}
	
	@Override
	public Vector2 getShootingDir() {
		TMP_DIR.x = 0;
		TMP_DIR.y = -1;
		TMP_DIR.rotate(shootingAngle++);
		return TMP_DIR;
	}
	
	@Override
	public Vector2 getShotPosition(int numeroTir) {
		TMP_POS.x = (pos.x + HALF_WIDTH - KinderWeapon.HALF_WIDTH) - (TMP_DIR.x * Stats.U4);
		TMP_POS.y = (pos.y + HALF_WIDTH - KinderWeapon.HALF_WIDTH) - (TMP_DIR.y * Stats.U4);
		return TMP_POS;
	}
	
	@Override
	public void setNextShot(float f) {
		shootingAngle += ROTATION_BETWEEN_SHOTS;
		super.setNextShot(f);
	}
	
	@Override
	public boolean stillAlive(PlayerWeapon p) {
		if (hp <= HALF_HP && index == 0) {
			index = 1;
		}
		return super.stillAlive(p);
	}
	
	@Override	public int getNumberOfShots() {				return 3;	}
	@Override	public float getFirerate() {				return FIRERATE;							}
	@Override	protected String getLabel() {				return getClass().toString();												}
	@Override	protected Sound getExplosionSound() {		return SoundMan.explosion6;													}
	@Override	public float getShootingAngle() {			return shootingAngle;														}
	@Override	public float getHalfHeight() {				return HALF_HEIGHT;															}
	@Override	public float getHalfWidth() {					return HALF_WIDTH;															}
	@Override	public int getShotNumber() {				return shotNumber;															}
	@Override	public int getExplosionCount() {			return EXPLOSION;															}
	@Override	public Phase[] getPhases() {				return PHASES;															}
	@Override	public void free() {						POOL.free(this);															}
	@Override	public void addShots(int i) {				shotNumber += i;															}
	@Override	public int getBonusValue() {				return BASE_XP;																}
	@Override	public float getDirectionY() {				return -SPEED;																}
	@Override	public float getHeight() {					return HEIGHT;																}
	@Override	public float getWidth() {						return WIDTH;																}
	@Override	public float getSpeed() {					return SPEED;																}
	@Override	public int getColor() {						return BLUE;																}
	@Override	protected int getMaxHp() {					return HP;																	}
	@Override	public int getXp() {						return XP;																	}
	@Override	public float getBulletSpeedMod() {			return 1;																	}
	protected int getPallierPv() {							return HALF_HP;																}
}
