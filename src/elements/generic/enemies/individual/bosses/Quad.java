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

public class Quad extends Enemy {

	private static final int WIDTH = Stats.QUAD_WIDTH, HALF_WIDTH = WIDTH/2, HEIGHT = Stats.QUAD_HEIGHT, HALF_HEIGHT = HEIGHT / 2, OFFSET_W_2 = (WIDTH /6), OFFSET_W_3 = (WIDTH /3)*2, OFFSET_OUTSIDE_W_Y = HEIGHT / 3, pvMaxPhase2 = getPvBoss(Stats.QUAD_HP) / 3 * 2, pvMinPhase2 = getPvBoss(Stats.QUAD_HP) / 3;
	private static final float FIRERATE = .2f, FIRERATE2 = .12f, FIRERATE3 = 0.07f, SPEED_LOW = Stats.QUAD_SPEED / 40;
	public static Pool<Quad> POOL = Pools.get(Quad.class);
	private int shotNumber = 1;
	public static final int PK = 100;
	private static boolean tmp;
	private static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				null,								null,										Animations.QUAD_GOOD				),
		new Phase(				Behavior.OSCILLATE_X,				Gatling.FIREBALL,					Shot.SHOT_DOWN_MULTIPLE_WITH_BREAK,			Animations.QUAD_GOOD				),
		new Phase(				Behavior.OSCILLATE_X,				Gatling.FIREBALL,					Shot.SHOT_DOWN_MULTIPLE_WITH_BREAK,			Animations.QUAD_BAD					),
		new Phase(				Behavior.OSCILLATE_X,				Gatling.FIREBALL,					Shot.SHOT_DOWN_MULTIPLE_WITH_BREAK,			Animations.QUAD_WORST				)};
	
	public Quad() {
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
			TMP_POS.y = pos.y - OFFSET_OUTSIDE_W_Y;
			break;
		case 1:
			pos.y++;
			TMP_POS.x = pos.x + WIDTH - Fireball.WIDTH;
			TMP_POS.y = pos.y - OFFSET_OUTSIDE_W_Y;
			break;
		case 2:
			pos.y++;
			TMP_POS.x = pos.x + OFFSET_W_3;
			TMP_POS.y = pos.y - Fireball.HALF_HEIGHT;
			break;
		case 3:
			pos.y++;
			TMP_POS.x = pos.x + OFFSET_W_2;
			TMP_POS.y = pos.y - Fireball.HALF_HEIGHT;
			break;
		default:
			TMP_POS.x = pos.x + HALF_WIDTH - Fireball.HALF_WIDTH;
			TMP_POS.y = pos.y - Fireball.HALF_HEIGHT;
			break;
		}
		return TMP_POS;
	}


	public void die() {
		Progression.bossDied();
		super.die();
	}

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
	@Override	protected int getMaxHp() {				return super.getPvBoss(Stats.QUAD_HP);	}
	@Override	protected String getLabel() {			return getClass().toString();			}
	@Override	protected Sound getExplosionSound() {	return SoundMan.bigExplosion;			}
	@Override	public void addShots(int i) {			this.shotNumber += i;					}
	@Override	public float getHalfHeight() {			return HALF_HEIGHT;						}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;						}
	@Override	public int getShotNumber() {			return shotNumber;						}
	@Override	public void free() {					POOL.free(this);						}
	@Override	public float getHeight() {				return HEIGHT;							}
	@Override	public Phase[] getPhases() {			return PHASES;							}
	@Override	public float getDirectionY() {			return -dir.y;							}
	@Override	public float getDirectionX() {			return dir.x;							}
	@Override	public float getWidth() {				return WIDTH;							}
	@Override	public int getXp() {					return 200;								}
	@Override	public int getBonusValue() {			return 200;								}
	@Override	public int getExplosionCount() {		return 180;								}
	@Override	public float getBulletSpeedMod() {		return 2;								}
	@Override	public float getSpeed() {
		if (index == 0)
			return Stats.QUAD_SPEED;
		return SPEED_LOW;
	}
}

