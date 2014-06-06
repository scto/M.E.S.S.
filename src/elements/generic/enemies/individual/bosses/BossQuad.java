package elements.generic.enemies.individual.bosses;

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
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.Enemy;
import elements.generic.enemies.Progression;
import elements.generic.weapons.enemies.Fireball;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;

public class BossQuad extends Enemy {

	private static final int WIDTH = Stats.WIDTH_BOSS_QUAD, HALF_WIDTH = WIDTH/2, HEIGHT = Stats.HEIGHT_BOSS_QUAD, HALF_HEIGHT = HEIGHT / 2,
			DECALAGE_ARME_2 = (WIDTH /6), DECALAGE_ARME_3 = (WIDTH /3)*2, DECALAGE_ARME_EXTERIEUR_Y = HEIGHT / 3;
	private static int pvMaxPhase2, pvMinPhase2;
	public static Pool<BossQuad> POOL = Pools.get(BossQuad.class);
	private static final float FIRERATE = .2f, FIRERATE2 = .12f, FIRERATE3 = 0.07f, SPEED_LOW = Stats.V_ENN_BOSS_QUAD / 40;
	private int shotNumber = 1;
	public static final int PK = 100;
	private static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				null,								null,										Animations.BOSS_QUAD_GOOD				),
		new Phase(				Behavior.OSCILLATE_X,				Gatling.FIREBALL,					Shot.SHOT_DOWN_MULTIPLE_WITH_BREAK,			Animations.BOSS_QUAD_GOOD				),
		new Phase(				Behavior.OSCILLATE_X,				Gatling.FIREBALL,					Shot.SHOT_DOWN_MULTIPLE_WITH_BREAK,			Animations.BOSS_QUAD_BAD				),
		new Phase(				Behavior.OSCILLATE_X,				Gatling.FIREBALL,					Shot.SHOT_DOWN_MULTIPLE_WITH_BREAK,			Animations.BOSS_QUAD_WORST				)};
	
	public BossQuad() {
		super();
		init();
	}

	public void init() {
		pos.x = CSG.screenHalfWidth - HALF_WIDTH;
		pos.y = CSG.SCREEN_HEIGHT;
		dir.y = -getSpeed();
		dir.x = -getSpeed() * 0.75f;
	}

	public void reset() {
		super.reset();
		nextShot = .2f;
		init();
	}
	
	@Override
	protected void isMoving() {
		if (pos.x <= Stats.U && index == 0)
			index = 1;
		if (pos.y - HEIGHT > CSG.HEIGHT_ECRAN_PALLIER_3) {
			pos.y -= EndlessMode.delta25 * 8;
		}
	}

	private static boolean tmp;

	@Override
	public boolean stillAlive(PlayerWeapon p) {
		tmp = super.stillAlive(p);

		switch (index) {
		case 1:
			if (hp < pvMaxPhase2)
				changePhase();
			break;
		case 2:
			if (hp < pvMinPhase2)
				changePhase();
			break;
		}
		return tmp;
	}

	@Override
	protected void changePhase() {
		for (int i = -EndlessMode.fps; i < 15 * index; i++)
			Particles.smoke(pos.x + (CSG.R.nextFloat() * WIDTH), pos.y + (CSG.R.nextFloat() * HEIGHT), false);
		super.changePhase();
	}

	public Vector2 getShotPosition(int numeroTir) {
		switch (numeroTir) {
		// Attention on donne en premier les exterieurs
		case 0:
			SoundMan.playBruitage(SoundMan.shotRocket);
			pos.y++;
			TMP_POS.x = pos.x;
			TMP_POS.y = pos.y - DECALAGE_ARME_EXTERIEUR_Y;
			break;
		case 1:
			pos.y++;
			TMP_POS.x = pos.x + WIDTH - Fireball.WIDTH;
			TMP_POS.y = pos.y - DECALAGE_ARME_EXTERIEUR_Y;
			break;
		case 2:
			pos.y++;
			TMP_POS.x = pos.x + DECALAGE_ARME_3;
			TMP_POS.y = pos.y - Fireball.HALF_HEIGHT;
			break;
		case 3:
			pos.y++;
			TMP_POS.x = pos.x + DECALAGE_ARME_2;
			TMP_POS.y = pos.y - Fireball.HALF_HEIGHT;
			break;
		default:
			TMP_POS.x = pos.x + HALF_WIDTH - Fireball.HALF_WIDTH;
			TMP_POS.y = pos.y - Fireball.HALF_HEIGHT;
			break;
		}
		return TMP_POS;
	}

	@Override
	public void free() {
		POOL.free(this);
	}

	@Override
	protected int getMaxHp() {
		pvMaxPhase2 = getPvBoss(Stats.HP_BOSS_QUAD) / 3 * 2;
		pvMinPhase2 = getPvBoss(Stats.HP_BOSS_QUAD) / 3;
		return super.getPvBoss(Stats.HP_BOSS_QUAD);
	}

	public void die() {
		Progression.bossDied();
		super.die();
	}

	@Override	public int getShotNumber() {			return shotNumber;	}
	@Override	public void addShots(int i) {			this.shotNumber += i;	}
	@Override	public int getNumberOfShots() {
		if (index == 1)
			return 4;
		return 2;
	}
	@Override	public float getFirerate() {
		switch (index) {
		case 1:			return FIRERATE;
		case 2:			return FIRERATE2;
		}
		return FIRERATE3;	
	}
	@Override	public float getDirectionY() {			return -dir.y;					}
	@Override	public float getDirectionX() {			return dir.x;					}
	@Override	public int getXp() {					return 200;						}
	@Override	public float getHeight() {				return HEIGHT;					}
	@Override	public float getWidth() {					return WIDTH;					}
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	public float getHalfHeight() {			return HALF_HEIGHT;			}
	@Override	public float getHalfWidth() {				return HALF_WIDTH;			}
	@Override	public float getBulletSpeedMod() {		return 2;						}
	@Override	protected Sound getExplosionSound() {		return SoundMan.bigExplosion;	}
	@Override	public int getBonusValue() {			return 200;						}
	@Override	public int getExplosionCount() {		return 180;						}
	@Override	public Phase[] getPhases() {			return PHASES;				}
	@Override	public float getSpeed() {
		if (index == 0)
			return Stats.V_ENN_BOSS_QUAD;
		return SPEED_LOW;
	}
}

