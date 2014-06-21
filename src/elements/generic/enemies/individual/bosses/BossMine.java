package elements.generic.enemies.individual.bosses;

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
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.Enemy;
import elements.generic.enemies.Progression;
import elements.generic.weapons.enemies.Raindow;
import elements.generic.weapons.enemies.Mine;
import elements.generic.weapons.player.PlayerWeapon;

public class BossMine extends Enemy {

	private static final int WIDTH = Stats.WIDTH_BOOS_MINE, HALF_WIDTH = WIDTH / 2, HEIGHT = Stats.HEIGHT_BOOS_MINE, HALF_HEIGHT = HEIGHT / 2;
	private static final Vector2 tmpDirectionTir = new Vector2();
	public static final Pool<BossMine> POOL = Pools.get(BossMine.class);
	private static final float SPEED = initSpeed(6, 3), FIRERATE = .2f, FIRERATE2 = 1;
	private static int pvPhase2;
	private boolean shotWay = false;
	private float shootingAngle = 0;
	public static final int PK = 101;
	private static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,			null,								null,										Animations.BOSS_MINE_GOOD				),
		new Phase(				Behavior.STAY_TOP,				Gatling.BOSS_MINE,					Shot.SHOT_SWEEP_WITH_BREAK,					Animations.BOSS_MINE_GOOD				),
		new Phase(				Behavior.ROTATE_STAY_TOP,		Gatling.MINE,						Shot.SHOT_EVENTAIL,							Animations.BOSS_MINE_BAD				)};	

	public BossMine() {
		pos.x = CSG.gameZoneHalfWidth - HALF_WIDTH;
		pos.y = CSG.SCREEN_HEIGHT;
	}
	
	public void reset() {
		init();
		super.reset();
	}

	public void init() {
		nextShot = 3f;
		pos.x = CSG.gameZoneHalfWidth - HALF_WIDTH;
		pos.y = CSG.SCREEN_HEIGHT;
		dir.x = 0;
		dir.y = -getSpeed();
	}
	
	@Override
	protected void isMoving() {
		switch (index) {
		case 0:
			if (pos.x < CSG.HEIGHT_ECRAN_PALLIER_3)
				changePhase();
			break;
		}
	}
	
	@Override
	public int getNumberOfShots() {
		if (index != 2)
			return 5;
		return 3 + CSG.R.nextInt(4);
	}
	@Override
	public float getFloatFactor() {
		if (index == 2)
			return 125;
		return 15;
	}
	
	@Override
	public float getFirerate() {
		if (index != 2)
			return FIRERATE;
		return FIRERATE2;
	}
	
	@Override
	public float getSpeed() {
		return SPEED;
	}
	
	@Override
	public boolean stillAlive(PlayerWeapon p) {
		if (hp <= pvPhase2)
			changePhase(2, 1);
		return super.stillAlive(p);
	}

	@Override
	public void setNextShot(float f) {
		SoundMan.playBruitage(SoundMan.shotRocket);
		nextShot = f;
	}

	@Override
	public Vector2 getShootingDir() {
		if (index == 1) {
			tmpDirectionTir.x = 0;
			tmpDirectionTir.y = -1;
		} else {
			tmpDirectionTir.x = -dir.x;
			tmpDirectionTir.y = -dir.y;
			tmpDirectionTir.nor();
		}
		return tmpDirectionTir;
	}

	
	@Override
	public Vector2 getShotPosition(int numeroTir) {
		if (index == 1) {
			TMP_POS.x = (pos.x + HALF_WIDTH - Raindow.HALF_WIDTH);
			TMP_POS.y = pos.y;
		} else {
			TMP_DIR.x = 0;
			TMP_DIR.y = HALF_HEIGHT;
			TMP_DIR.rotate(angle);
			TMP_POS.y = (pos.y + HALF_HEIGHT - Mine.HALF_WIDTH) + TMP_DIR.y;
			TMP_POS.x = (pos.x + HALF_WIDTH - Mine.HALF_WIDTH) + TMP_DIR.x;
			shootingAngle = angle;
		}
		return TMP_POS;
	}
	
	@Override	public void setShootingAngle(float shootingAngle) {	this.shootingAngle = shootingAngle;	}
	@Override	public float getDirectionY() {						return -Stats.V_ENN_BOSS_MINE;		}
	@Override	protected String getLabel() {						return getClass().toString();		}
	@Override	protected Sound getExplosionSound() {				return SoundMan.bigExplosion;		}
	@Override	public float getShootingAngle() {					return shootingAngle;				}
	@Override	public void setShotWay(boolean way) {				this.shotWay = way;					}
	@Override	public float getHalfHeight() {						return HALF_HEIGHT;					}
	@Override	public float getHalfWidth() {						return HALF_WIDTH;					}
	@Override	public void free() {								POOL.free(this);					}
	@Override	public boolean getShotWay() {						return shotWay;						}
	@Override	public Phase[] getPhases() {						return PHASES;						}
	@Override	public float getHeight() {							return HEIGHT;						}
	@Override	public float getWidth() {							return WIDTH;						}
	@Override	public int getColor() {								return BLUE;						}
	@Override	public int getXp() {								return 200;							}
	@Override	public int getBonusValue() {						return 200;							}
	@Override	public int getExplosionCount() {					return 180;							}
	@Override	public float getShotsGap() {						return 5;							}
	@Override	public float getBulletSpeedMod() {
		System.out.println(100);
		return 100;							
	}
	
	@Override
	protected int getMaxHp() {
		pvPhase2 = getPvBoss(Stats.HP_BOSS_MINE) / 2;
		return super.getPvBoss(Stats.HP_BOSS_MINE);
	}
	
	
	@Override
	public void die() {
		Progression.bossDied();
		super.die();
	}

}
