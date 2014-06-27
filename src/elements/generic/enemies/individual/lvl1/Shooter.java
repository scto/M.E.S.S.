package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;
import elements.generic.components.positionning.Positionner;
import elements.generic.components.shots.AbstractShot;
import elements.generic.components.shots.Gatling;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.Fireball;
import elements.generic.weapons.player.PlayerWeapon;


public class Shooter extends Enemy {
	
	protected static final Dimensions DIMENSIONS = Dimensions.SHOOTER;
	public static final int BASE_XP = 10, HP = Stats.HP_QUI_TIR, HALF_HP = HP/2, EXPLOSION = 40, XP = BASE_XP, LVL = 1;
	protected static final float xOffset = DIMENSIONS.halfWidth - Fireball.DIMENSIONS.halfWidth/1.5f, FIRERATE = 1.2f, INIT_NEXT_SHOT = 1.5f, SPEED = getModulatedSpeed(10, LVL);
	public static final Pool<Shooter> POOL = Pools.get(Shooter.class);
	private boolean goodShape = true;
	
	public void init() {
		Positionner.UP_WIDE.set(this);
		nextShot = 2f;
		dir.set(0, -getSpeed());
	}
	
	@Override
	protected void move() {
		if (!isInGoodShape())
			pos.x += getDerive() * EndlessMode.delta;
		super.move();
	}
	
	@Override
	public boolean stillAlive(PlayerWeapon a) {
		if (hp < getDemiPv()) {
			dir.rotate(2);
			goodShape = false;
		} else {
			goodShape = true;
		}
		return super.stillAlive(a);
	}
	
	@Override
	protected void shoot() {
		TMP_POS.set(pos.x + (DIMENSIONS.halfWidth - Fireball.DIMENSIONS.halfWidth),  pos.y - Fireball.DIMENSIONS.height);
		AbstractShot.shootDown(Gatling.FIREBALL, TMP_POS, Stats.U12);
	}
	
	@Override	protected Sound getExplosionSound() {		return SoundMan.explosion5;		}
	@Override	public Animations getAnimation() {			return Animations.SHOOTER;		}
	@Override	public Dimensions getDimensions() {			return DIMENSIONS;				}
	@Override	public int getExplosionCount() {			return EXPLOSION;				}
	@Override	public boolean isInGoodShape() {			return goodShape;				}
	@Override 	public float getFirerate() {				return FIRERATE;				}
	@Override	public void free() {						POOL.free(this);				}
	@Override	public int getBonusValue() {				return BASE_XP;					}
	@Override	public float getSpeed() {					return SPEED;					}
	@Override	public int getColor() {						return BLUE;					}
	@Override	protected int getMaxHp() {					return HP;						}
	@Override	public int getXp() {						return XP;						}
	protected int getDemiPv() {								return HALF_HP;					}
	protected float getDerive() {							return Stats.DERIVE_QUI_TIR;	}

}
