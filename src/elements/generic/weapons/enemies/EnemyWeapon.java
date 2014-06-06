package elements.generic.weapons.enemies;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import jeu.db.Requests;
import jeu.mode.EndlessMode;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Invocable;
import elements.generic.Player;
import elements.generic.components.Phase;
import elements.generic.weapons.Weapon;
import elements.particular.bonuses.XP;

public abstract class EnemyWeapon extends Weapon implements Poolable, Invocable {
	
	private static float tmpFloat;
	public static float nextGraze = 0;
	private static final Vector2 tmpV = new Vector2();
	
	public boolean testCollisionVaisseau() {
		tmpV.x = Player.xCenter;
		tmpV.y = Player.yCenter;
		tmpFloat = tmpV.dst(pos.x + getHalfWidth(), pos.y + getHalfHeight());
		
//		if (Player.bouclier)
		for (int i = 0; i < Player.bouclier; i++)
			tmpFloat -= Stats.uSur2;
		
		if (tmpFloat < getWidth() + Stats.UU || tmpFloat < getHeight() + Stats.UU) {
			if (nextGraze < EndlessMode.now) {
				final XP xp = XP.POOL.obtain();
				xp.init(pos.x + getHalfWidth(), pos.y + getHalfHeight(), 10);
//				xp.direction.x = -dir.x * EndlessMode.delta;
//				xp.direction.y = -dir.y * EndlessMode.delta;
				nextGraze = EndlessMode.now + .1f;
			}
			if (tmpFloat < getHalfWidth() || tmpFloat < getHalfHeight()) {
				Player.touched();
				return true;
			}
		}
		return false;
	}

	public boolean testCollsionAdds() {
		return Physic.isAddTouched(pos, getWidth(), getHeight());
	}
	
	protected static float initSpeed(float def, int pk) {
		if (CSG.updateNeeded)
			return Requests.getSpeedEnemyWeapon(pk, def) * Stats.u;
		return def * Stats.u;
	}
	
	@Override	public Vector2 getPosition() {						return pos;					}
	@Override	public Vector2 getDirection() {						return dir;					}
	@Override	public void setWay(boolean b) {													}
	@Override	public boolean getWay() {							return false;				}
	@Override	public float getNow() {								return now;					}
	@Override	public float getNextShot() {						return 0;					}
	@Override	public float getPhaseTime() {						return phaseTime;			}
	@Override	public float getBulletSpeedMod() {					return 0;					}
	@Override	public float getFloatFactor() {						return 0;					}
	@Override	public Phase getPhase() {							return getPhases()[index];	}
	@Override	public void setNextShot(float f) {												}
	@Override	public int getNumberOfShots() {						return 0;					}
	@Override	public float getShootingAngle() {					return 0;					}
	@Override	public boolean isBoss() {							return false;				}
	@Override	public Vector2 getShootingDir() {					return null;				}
	@Override	public Vector2 getShotPosition(int i) {				return null;				}
	@Override	public int getShotNumber() {						return 0;					}
	@Override	public void addShots(int i) {													}
	@Override	public int getNumberOfShotBeforeDirChange() {		return 0;					}
	@Override	public void setShotDir(boolean b) {												}
	@Override	public boolean getShotDir() {						return false;				}
	@Override	public float getShotsGap() {						return 0;					}
	@Override	public int getIntFactor() {				return 0;					}
	@Override	public int getXp() {								return 0;					}
	@Override	public void setPosition(Vector2 pos) {											}
	
	public void init(Vector2 position, float dEMI_WIDTH, float demiHauteur, float modifVitesse) {
		position.x = position.x + dEMI_WIDTH - getHalfWidth();
		position.y = position.y + demiHauteur - getHalfHeight();
		ENEMIES_LIST.add(this);
	}

	/**
	 * L'envoie vers le bas
	 * @param position
	 * @param modifVitesse
	 * @param boss TODO
	 */
	public void init(Vector2 position, float modifVitesse, boolean boss) {
		this.pos.x = position.x;
		this.pos.y = position.y;
		dir.x = 0;
		dir.y = -1 * modifVitesse * getSpeed();
		if (boss) {
			BOSSES_LIST.add(this);
		} else {
			ENEMIES_LIST.add(this);
		}
	}

	public void init(Vector2 position, float modifVitesse, Vector2 direction, boolean boss) { 
		this.pos.x = position.x;
		this.pos.y = position.y;
		this.dir.x = direction.x * modifVitesse;
		this.dir.y = direction.y * modifVitesse;
		this.dir.scl(getSpeed());
		if (boss) {
			BOSSES_LIST.add(this);
		} else {
			ENEMIES_LIST.add(this);
		}
	}
	
	public void init(Vector2 position, float modifVitesse, float angle) {
		this.pos.x = position.x;
		this.pos.y = position.y;
		this.dir.x = 0;
		this.dir.y = 1 * modifVitesse;
		dir.rotate(angle);
		this.pos.x += dir.x / 1.35f;
		this.pos.y += dir.y / 1.35f;
		ENEMIES_LIST.add(this);
	}

	public void init(Vector2 position, Vector2 direction) {
		this.pos.x = position.x;
		this.pos.y = position.y;
		this.dir.x = direction.x;
		this.dir.y = direction.y;
		ENEMIES_LIST.add(this);
	}
	
	public void init(Vector2 position, Vector2 direction, float modifVitesse) {
		this.pos.x = position.x;
		this.pos.y = position.y;
		this.dir.x = direction.x * modifVitesse;
		this.dir.y = direction.y * modifVitesse;
		ENEMIES_LIST.add(this);
	}

	public static void clear() {
		for (Weapon a : PLAYER_LIST)
			a.free();
		for (Weapon a : ENEMIES_LIST)
			a.free();
		PLAYER_LIST.clear();
		ENEMIES_LIST.clear();
	}

}
