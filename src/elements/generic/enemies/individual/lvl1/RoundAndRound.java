package elements.generic.enemies.individual.lvl1;

import jeu.Physic;
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
import elements.generic.weapons.enemies.BlueBulletFast;

public class RoundAndRound extends Enemy {
	
	public static final int PK = 13, BASE_XP = Enemy.initXp(21, PK), LVL = 1, HP = initHp(Stats.ROUND_AND_ROUND_HP, PK), XP = getXp(BASE_XP, LVL), EXPLOSION_MIN_PARTICLES = initExplosion(30, PK), SHOTS_GAP = 10;
	protected static final float WIDTH = Stats.ROUND_AND_ROUND_WIDTH, HALF_WIDTH = WIDTH/2, HEIGHT = Stats.ROUND_AND_ROUND_HEIGHT, HALF_HEIGHT = HEIGHT / 2, FIRERATE = initFirerate(.45f, PK), INIT_NEXT_SHOT = initNextShot(0, PK), SPEED = initSpeed(20, PK);
	private static final float OFFSET_TIR = HALF_WIDTH - BlueBulletFast.HALF_WIDTH;
	public static final Pool<RoundAndRound> POOL = Pools.get(RoundAndRound.class);
	private static final Pos positionning = initPositionnement(UpWide.PK, PK);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.U_TURN,				Gatling.BLUE_BULLET_FAST,				Shot.SWEEP,				Animations.ROUND_N_ROUND				)		};
	private boolean shootRight = true, leftOfTheScreen;
	private int shotNumber = 0;
	
	public void init() {
		dir.x = 0;
		dir.y = -getSpeed();
		positionning.set(this);
		nextShot = INIT_NEXT_SHOT;
		now = 1;
	}

	@Override
	public Vector2 getShotPosition(int numeroTir) {
		if (now < 1.5f)
			leftOfTheScreen = Physic.isLeft(pos, HALF_WIDTH);
		TMP_POS.x = (pos.x + OFFSET_TIR);
		TMP_POS.y = (pos.y + OFFSET_TIR);
		return TMP_POS;
	}

	@Override
	protected void isMoving() {
		if (!EndlessMode.alternate)
			angle = dir.angle() + 90;
	}
	
	@Override	public int getNumberOfShotBeforeDirChange() {	return 5;							}
	@Override	protected Sound getExplosionSound() {			return SoundMan.explosion6;			}
	@Override	public void setShotDir(boolean b) {				shootRight = b;						}
	@Override	public float getBulletSpeedMod() {				return 1;						}
	@Override	public float getShootingAngle() {				return 0;							}
	@Override	public Vector2 getShootingDir() {				return dir;							}
	@Override	public int getExplosionCount() {				return EXPLOSION_MIN_PARTICLES;		}
	@Override	public float getHalfHeight() {					return HALF_HEIGHT;					}
	@Override	public float getDirectionY() {					return dir.y;						}
	@Override	public float getDirectionX() {					return dir.x;						}
	@Override	public float getHalfWidth() {					return HALF_WIDTH;					}
	@Override	public void addShots(int i) {					shotNumber += i;					}
	@Override	public boolean getShotDir() {					return shootRight;					}
	@Override	protected String getLabel() {					return getClass().toString();		}
	@Override 	public float getFirerate() {					return FIRERATE;					}
	@Override	public int getBonusValue() {					return BASE_XP;						}
	@Override	public int getShotNumber() {					return shotNumber;					}
	@Override	public float getShotsGap() {					return SHOTS_GAP;					}
	@Override	public Phase[] getPhases() {					return PHASES;						}
	@Override	protected int getMaxHp() {						return HP;							}
	@Override	public float getHeight() {						return HEIGHT;						}
	@Override	public float getSpeed() {						return SPEED;						}
	@Override	public boolean getWay() {						return leftOfTheScreen;				}
	@Override	public float getWidth() {						return WIDTH;						}
	@Override	public boolean toLeft() {						return leftOfTheScreen;				}
	@Override	public int getColor() {							return GREEN;						}
	@Override	public int getXp() {							return XP;							}
	@Override	public void free() {							POOL.free(this);					}
}
