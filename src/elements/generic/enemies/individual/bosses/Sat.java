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


public class Sat extends Enemy {
	
	public static final int WIDTH = Stats.SAT_WIDTH, HALF_WIDTH = WIDTH/2, Y_1 = (WIDTH / 40) * 17, Y_2 = (WIDTH / 40) * 29, Y_3 = (WIDTH) - (int)AddStat.WIDTH, Y_4 = Y_2, Y_5 = Y_1, X_2 = (WIDTH / 13),
			X_3 = (WIDTH / 2) - (int)AddStat.HALF_WIDTH, X_4 = (int) (WIDTH - X_2 - AddStat.WIDTH), X_5 = (int) (WIDTH - AddStat.WIDTH), ANGLE_2 = 140, ANGLE_3 = 90, ANGLE_4 = 40, ANGLE_5 = 0, PK = 17, HP = initHp(195, PK),
			HALF_HP = HP / 2, EXPLOSION = initExplosion(60, PK), BASE_XP = Enemy.initXp(75, PK), XP = BASE_XP;
	public static Pool<Sat> POOL = Pools.get(Sat.class);
	protected static final float SPEED = initSpeed(8, PK);
	private int launched = 0;
	private static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				null,				null,				Animations.SAT_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON,				null,				null,				Animations.SAT_BAD				)		};	

	public Sat() {
		init();
	}

	public void init() {
		pos.x = Stats.GAME_ZONE_W_PLUS_WIDTH_DIV_10;
		pos.y = CSG.HEIGHT_ECRAN_PALLIER_3 - HALF_WIDTH;
		dir.x = -getSpeed();
		launched = 0;
	}
	
	@Override
	protected void isMoving() {
		switch (launched) {
		case 0:
			if (pos.x < CSG.WIDTH_ZONE_MOINS_WIDTH_BORD - HALF_WIDTH) {
				AddStat.pool.obtain().lancer(-1, 0, pos.x, pos.y + Y_1, 180);
				AddStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, pos.x + X_2, pos.y + Y_2, ANGLE_2);
				if (EndlessMode.difficulty > 1) {
					AddStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
				}
				launched++;
			}
			break;
		case 1:
			if (pos.x < CSG.WIDTH_ZONE_MOINS_WIDTH_BORD - WIDTH) {
				AddStat.pool.obtain().lancer(-1, 0, pos.x, pos.y + Y_1, 180);
				AddStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, pos.x + X_2, pos.y + Y_2, ANGLE_2);
				if (EndlessMode.difficulty > 1) {
					AddStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
					AddStat.pool.obtain().lancer(0.76604444f, 0.6427876f, pos.x + X_4, pos.y + Y_4, ANGLE_4);
				}
				launched++;
			}
			break;
		case 2:
			if (pos.x < CSG.WIDTH_ZONE_MOINS_WIDTH_BORD - (WIDTH + HALF_WIDTH)) {
				AddStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
				AddStat.pool.obtain().lancer(0.76604444f, 0.6427876f, pos.x + X_4, pos.y + Y_4, ANGLE_4);
				if (EndlessMode.difficulty > 1) {
					AddStat.pool.obtain().lancer(-1, 0, pos.x, pos.y + Y_1, 180);
					AddStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, pos.x + X_2, pos.y + Y_2, ANGLE_2);
				}
				launched++;
			}
			break;
		case 3:
			if (pos.x < CSG.WIDTH_ZONE_MOINS_WIDTH_BORD - HALF_WIDTH) {
				AddStat.pool.obtain().lancer(0.76604444f, 0.6427876f, pos.x + X_4, pos.y + Y_4, ANGLE_4);
				AddStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
				if (EndlessMode.difficulty > 1) {
					AddStat.pool.obtain().lancer(-1, 0, pos.x, pos.y + Y_1, 180);
					AddStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, pos.x + X_2, pos.y + Y_2, ANGLE_2);
				}
				launched++;
			}
		case 4:
			if (pos.x < CSG.WIDTH_ZONE_MOINS_WIDTH_BORD - WIDTH) {
				AddStat.pool.obtain().lancer(1, 0, pos.x + X_5, pos.y + Y_5, ANGLE_5);
				if (EndlessMode.difficulty > 1) {
					AddStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
					AddStat.pool.obtain().lancer(0.76604444f, 0.6427876f, pos.x + X_4, pos.y + Y_4, ANGLE_4);
				}
				launched++;
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

	@Override	protected Sound getExplosionSound() {	return SoundMan.bigExplosion;		}
	@Override	protected String getLabel() {			return getClass().toString();		}
	@Override	protected int getMaxHp() {				return super.getPvBoss(HP);			}
	@Override	public float getHalfHeight() {			return HALF_WIDTH;					}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;					}
	@Override	public int getExplosionCount() {		return EXPLOSION;					}
	@Override	public void free() {					POOL.free(this);					}
	@Override	public int getBonusValue() {			return BASE_XP;						}
	@Override	public Phase[] getPhases() {			return PHASES;						}
	@Override	public float getHeight() {				return WIDTH;						}
	@Override	public float getWidth() {				return WIDTH;						}
	@Override	public float getSpeed() {				return SPEED;						}
	@Override	public float getDirectionX() {			return dir.x;						}
	@Override	public int getXp() {					return XP;							}
	@Override	public float getDirectionY() {			return 0;							}

}
