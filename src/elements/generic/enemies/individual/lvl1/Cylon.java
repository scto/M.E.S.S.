package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
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
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;

public class Cylon extends Enemy {
	
	private static final int 
		WIDTH = Stats.CYLON_WIDTH,
		HALF_WIDTH = WIDTH/2,
		QUART_WIDTH = WIDTH / 4,
		THRUSTER_OFFSET = (int) (WIDTH * 0.45f);
	public static final Pool<Cylon> POOL = Pools.get(Cylon.class);
	public static final int PK = 2;
	protected static final float
		FIRERATE = initFirerate(3, PK),
		INIT_NEXT_SHOT = initNextShot(2.6f, PK),
		SPEED = initSpeed(17, PK);
	protected static final int BASE_XP = Enemy.initXp(12, PK);
	private static final int 
		HP = initHp(Stats.HP_CYLON, PK),
		HP_BAD = (int) (HP * 0.66f),
		HP_WORST = (int) (HP * 0.33f),
		EXPLOSION = initExplosion(35, PK),
		XP = getXp(BASE_XP, 1);
	private static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.METEORITE,				Shot.TIR_TOUT_DROIT,			Animations.CYLON_RED_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.METEORITE,				Shot.TIR_TOUT_DROIT,			Animations.CYLON_RED_BAD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.METEORITE,				Shot.TIR_TOUT_DROIT,			Animations.CYLON_RED_WORST				)		};
	private static final Pos positionning = initPositionnement(UpWide.PK, PK);

	public void init() {
		positionning.set(this);
		if (pos.x + HALF_WIDTH < CSG.screenHalfWidth)
			dir.x = 0.26f * Stats.CYLON_SPEED;
		else
			dir.x = -0.26f * Stats.CYLON_SPEED;
		dir.y = -0.83f * Stats.CYLON_SPEED;
		nextShot = 2.6f;
		angle = dir.angle() + 90;
		index = 0;
	}
	
	@Override
	protected void isMoving() {
		TMP_POS.x = 0;
		TMP_POS.y = THRUSTER_OFFSET;
		TMP_POS.rotate(angle);
		if (EndlessMode.oneToFour > index + 1)
			Particles.smokeMoving(pos.x + HALF_WIDTH + TMP_POS.x, pos.y + HALF_WIDTH + TMP_POS.y, TMP_POS.nor().scl(Stats.uSur2), getColor());
	}
	
	@Override
	public Vector2 getShotPosition(int numeroTir) {
		TMP_POS.x = (pos.x) + dir.x*0.8f;
		TMP_POS.y = (pos.y) + (dir.y*0.8f);
		return TMP_POS;
	}
	@Override
	public boolean stillAlive(PlayerWeapon p) {
		switch (index) {
		case 0:
			if (hp - p.getPower() < getPvBad()) {
				changeState(1);
			}
			break;
		case 1:
			if (hp - p.getPower() < getPvWorst()) {
				changeState(2);
			}
			break;
		}
		return super.stillAlive(p);
	}
	

	private void changeState(int nextState) {
		index = nextState;
		dir.rotate(CSG.R.nextInt(20)-10);
		dir.scl(0.7f);
		angle = dir.angle() + 90;
		for (int i = 0; i < 10; i++) 
			Particles.smoke(pos.x + QUART_WIDTH + (CSG.R.nextFloat() * HALF_WIDTH), pos.y + CSG.R.nextFloat() * WIDTH, false);
	}

	@Override	protected String getLabel() {			return getClass().toString();				}
	@Override	protected Sound getExplosionSound() {	return SoundMan.explosion4;					}
	@Override	public float getShootingAngle() {		return dir.angle();							}
	@Override	public float getHalfHeight() {			return HALF_WIDTH;							}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;							}
	@Override	public int getExplosionCount() {		return EXPLOSION;							}
	@Override	public float getFirerate() {			return FIRERATE;							}
	@Override	public void free() {					POOL.free(this);							}
	@Override	public int getBonusValue() {			return BASE_XP;								}
	@Override	public Phase[] getPhases() {			return PHASES;								}
	@Override	public float getHeight() {				return WIDTH;								}
	@Override	public float getWidth() {				return WIDTH;								}
	@Override	public float getBulletSpeedMod() {		return 2;								}
	@Override	public void setNextShot(float f) {		nextShot = f;								}
	@Override	public float getDirectionY() {			return dir.y;								}
	@Override	public float getDirectionX() {			return dir.x;								}
	@Override	public Vector2 getShootingDir() {		return dir;									}
	@Override	public int getXp() {					return XP;									}
	@Override	protected int getMaxHp() {				return HP;									}
	protected int getPvBad() {							return HP_BAD;								}
	protected int getPvWorst() {						return HP_WORST;							}
}
