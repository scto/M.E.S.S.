package elements.generic.enemies.individual.lvl1;

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
import elements.generic.enemies.Enemy;
import elements.particular.particles.Particles;

public class ZigZag extends Enemy {
	
	public static final int PK = 15, HP = initHp(Stats.HP_ZIGZAG, PK), EXPLOSION = initExplosion(40, PK), BASE_XP = Enemy.initXp(3, PK), XP = getXp(BASE_XP, 1);
	protected static final float SPEED = initSpeed(1, PK), WIDTH = Stats.WIDTH_ZIG_ZAG, HALF_WIDTH = WIDTH/2, HEIGHT = Stats.HEIGHT_ZIG_ZAG, OFFSET_SMOKE = (int) (HEIGHT * 0.8f), HALF_HEIGHT = HEIGHT / 2;
	public static final Pool<ZigZag> POOL = Pools.get(ZigZag.class);
	private static final Pos positionning = initPositionnement(UpWide.PK, PK);
	private static final Vector2 smokeVector = new Vector2(0, Stats.uDiv4);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.ZIG_ZAG,				null,				null,				Animations.ZIG_ZAG_RED				)		};
	private boolean way = true;

	public void init() {
		positionning.set(this);
		dir.x = 0;
		dir.y = -getSpeed() * 24;
		way = true;
	}
	
	@Override
	public void isMoving() {
		if (EndlessMode.alternate)
			Particles.smokeMoving(pos.x + HALF_WIDTH, pos.y + OFFSET_SMOKE, true, getColor(), getSmokeVector());
	}

	protected Vector2 getSmokeVector() {				return smokeVector;		}

//	@Override
//	public float getBulletSpeedMod() {
//		return super.getBulletSpeedMod();
//	}
	@Override	public float getPhaseTime() {			return pos.x + HALF_WIDTH;		}
	@Override	public void free() {					POOL.free(this);				}
	@Override	protected int getMaxHp() {				return HP;						}
	@Override	public float getHeight() {				return HEIGHT;					}
	@Override	public float getWidth() {				return WIDTH;					}
	@Override	public float getHalfHeight() {			return HALF_HEIGHT;				}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;				}
	@Override	public float getDirectionY() {			return dir.y;					}
	@Override	public float getDirectionX() {			return dir.x;					}
	@Override	public float getSpeed() {				return SPEED;					}
	@Override	protected Sound getExplosionSound() {	return SoundMan.explosion5;		}
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	public int getXp() {					return XP;						}
	@Override	public int getBonusValue() {			return BASE_XP;					}
	@Override	public int getExplosionCount() {		return EXPLOSION;				}
	@Override	public Phase[] getPhases() {			return PHASES;					}
	@Override	public boolean getWay() {				return way;						}
	@Override	public void setWay(boolean b) {			way = b;						}
}
