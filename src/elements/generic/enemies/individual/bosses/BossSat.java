package elements.generic.enemies.individual.bosses;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.Weapon;


public class BossSat extends Enemy {
	
	public static final int WIDTH = Stats.WIDTH_BOSS_SAT, HALF_WIDTH = WIDTH/2;
	private static final int Y_1 = (WIDTH / 40) * 17, Y_2 = (WIDTH / 40) * 29, Y_3 = (WIDTH) - (int)AddBossStat.WIDTH, Y_4 = Y_2, Y_5 = Y_1;
	private static final int X_2 = (WIDTH / 13), X_3 = (WIDTH / 2) - (int)AddBossStat.HALF_WIDTH, X_4 = (int) (WIDTH - X_2 - AddBossStat.WIDTH), X_5 = (int) (WIDTH - AddBossStat.WIDTH);
	private static final int ANGLE_2 = 140, ANGLE_3 = 90, ANGLE_4 = 40, ANGLE_5 = 0;
	public static Pool<BossSat> POOL = Pools.get(BossSat.class);
	private int nbLance = 0;
	private static final int PK = 17;
	protected static final float SPEED = initSpeed(8, PK);
	private static final int HP = initHp(195, PK), HALF_HP = HP / 2;
	private static final int EXPLOSION = initExplosion(60, PK);
	protected static final int BASE_XP = Enemy.initXp(75, PK);
	private static final int XP = BASE_XP;
	private static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				null,				null,				Animations.PORTE_NEF_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON,				null,				null,				Animations.PORTE_NEF_BAD				)		};	

	public BossSat() {
		init();
	}

	public void init() {
		pos.x = Stats.GAME_ZONE_W_PLUS_WIDTH_DIV_10;
		pos.y = CSG.HEIGHT_ECRAN_PALLIER_3 - HALF_WIDTH;
		dir.x = -getSpeed();
		nbLance = 0;
	}
	
	@Override
	protected void isMoving() {
		switch (nbLance) {
		case 0:
			if (pos.x < CSG.WIDTH_ZONE_MOINS_WIDTH_BORD - HALF_WIDTH) {
				AddBossStat.pool.obtain().lancer(-1, 0, pos.x, pos.y + Y_1, 180);
				AddBossStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, pos.x + X_2, pos.y + Y_2, ANGLE_2);
				if (EndlessMode.difficulty > 1) {
					AddBossStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
				}
				nbLance++;
			}
			break;
		case 1:
			if (pos.x < CSG.WIDTH_ZONE_MOINS_WIDTH_BORD - WIDTH) {
				AddBossStat.pool.obtain().lancer(-1, 0, pos.x, pos.y + Y_1, 180);
				AddBossStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, pos.x + X_2, pos.y + Y_2, ANGLE_2);
				if (EndlessMode.difficulty > 1) {
					AddBossStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
					AddBossStat.pool.obtain().lancer(0.76604444f, 0.6427876f, pos.x + X_4, pos.y + Y_4, ANGLE_4);
				}
				nbLance++;
			}
			break;
		case 2:
			if (pos.x < CSG.WIDTH_ZONE_MOINS_WIDTH_BORD - (WIDTH + HALF_WIDTH)) {
				AddBossStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
				AddBossStat.pool.obtain().lancer(0.76604444f, 0.6427876f, pos.x + X_4, pos.y + Y_4, ANGLE_4);
				if (EndlessMode.difficulty > 1) {
					AddBossStat.pool.obtain().lancer(-1, 0, pos.x, pos.y + Y_1, 180);
					AddBossStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, pos.x + X_2, pos.y + Y_2, ANGLE_2);
				}
				nbLance++;
			}
			break;
		case 3:
			if (pos.x < CSG.WIDTH_ZONE_MOINS_WIDTH_BORD - HALF_WIDTH) {
				AddBossStat.pool.obtain().lancer(0.76604444f, 0.6427876f, pos.x + X_4, pos.y + Y_4, ANGLE_4);
				AddBossStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
				if (EndlessMode.difficulty > 1) {
					AddBossStat.pool.obtain().lancer(-1, 0, pos.x, pos.y + Y_1, 180);
					AddBossStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, pos.x + X_2, pos.y + Y_2, ANGLE_2);
				}
				nbLance++;
			}
		case 4:
			if (pos.x < CSG.WIDTH_ZONE_MOINS_WIDTH_BORD - WIDTH) {
				AddBossStat.pool.obtain().lancer(1, 0, pos.x + X_5, pos.y + Y_5, ANGLE_5);
				if (EndlessMode.difficulty > 1) {
					AddBossStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
					AddBossStat.pool.obtain().lancer(0.76604444f, 0.6427876f, pos.x + X_4, pos.y + Y_4, ANGLE_4);
				}
				nbLance++;
			}
			break;
		}
	}
	
	@Override
	public boolean isTouched(Weapon a) {
		if (hp < HALF_HP)
			index = 1;
		return super.isTouched(a);
	}

	@Override	public float getDirectionY() {			return 0;										}
	@Override	public float getDirectionX() {			return dir.x;									}
	@Override	protected String getLabel() {			return getClass().toString();					}
	@Override	protected int getMaxHp() {				return super.getPvBoss(HP);		}
	@Override	public int getXp() {					return XP;			}
	@Override	public int getBonusValue() {			return BASE_XP;	}
	@Override	protected Sound getExplosionSound() {		return SoundMan.bigExplosion;					}
	@Override	public float getHalfHeight() {			return HALF_WIDTH;							}
	@Override	public float getHalfWidth() {				return HALF_WIDTH;							}
	@Override	public void free() {					POOL.free(this);								}
	@Override	public float getHeight() {				return WIDTH;									}
	@Override	public float getWidth() {					return WIDTH;									}
	@Override	public int getExplosionCount() {		return EXPLOSION;	}
	@Override	public Phase[] getPhases() {			return PHASES;	}
	@Override	public float getSpeed() {				return SPEED;	}

}
