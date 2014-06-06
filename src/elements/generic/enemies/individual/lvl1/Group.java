package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
import jeu.Stats;
import assets.AssetMan;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.Tournante;

public class Group extends Enemy {
	
	protected static final int
		WIDTH = Stats.GROUP_WIDTH,
		HALF_WIDTH = WIDTH / 2;
	public static final Pool<Group> POOL = new Pool<Group>() {
		@Override
		protected Group newObject() {
			return new Group();
		}
	};
	public static final int PK = 4;
	protected static final float
		FIRERATE = initFirerate(0.6f, PK),
		INIT_NEXT_SHOT = initNextShot(.5f, PK),
		SPEED = initSpeed(8, PK);
	private static final float COLOR = AssetMan.convertARGB(1, 1f, 0.5f, 0.2f);
	protected static final int BASE_XP = Enemy.initXp(12, PK);
	private static final int
		HP = initHp(Stats.HP_CYLON, PK),
		EXPLOSION = initExplosion(35, PK),
		XP = getXp(BASE_XP, 1);
	private static final Phase[] PHASES = {
		new Phase(				Behavior.COMMA,				Gatling.TOURNANTE,				Shot.SHOT_EN_RAFALE,				Animations.ENNEMI_TOURNE				)		};
	private int shotNumber = 0;
	
	@Override
	protected void isMoving() {
		angle = dir.angle() + 90;
	}

	public static Group initAll() {
		Group e = POOL.obtain();
		Group f = POOL.obtain();
		Group g = POOL.obtain();
		e.init(CSG.gameZoneWidth - WIDTH);
		f.init(CSG.gameZoneWidth - WIDTH * 2.1f);
		g.init(CSG.gameZoneWidth - WIDTH * 3.2f);
		return e;
	}
	
	protected void init(float x) {
		now = 5;
		LIST.add(this);
		pos.x = x;
		pos.y = CSG.SCREEN_HEIGHT;
		dir.x = 0;
		dir.y = -getSpeed();
	}

	@Override
	public void reset() {
		super.reset();
		pos.y = CSG.SCREEN_HEIGHT;
		nextShot = INIT_NEXT_SHOT;
		shotNumber = 0;
	}
	@Override
	public Vector2 getShootingDir() {
		TMP_DIR.y = Tournante.SPEED * 2;
		TMP_DIR.x = dir.x + Tournante.SPEED;//(now * 15);
		return TMP_DIR;
	}
	@Override	public int getNumberOfShots() {							return 3;	}
	@Override	public float getFirerate() {							return FIRERATE;							}
	@Override	protected Sound getExplosionSound() {					return SoundMan.explosion6;	}
	@Override	public int getXp() {									return XP;	}
	@Override	public int getBonusValue() {							return BASE_XP;	}
	@Override	public float getHeight() {								return WIDTH;	}
	@Override	public float getWidth() {								return WIDTH;	}
	@Override	public float getHalfHeight() {							return HALF_WIDTH;	}
	@Override	public float getHalfWidth() {							return HALF_WIDTH;	}
	@Override	protected int getMaxHp() {								return HP;	}
	@Override	public void free() {									POOL.free(this);	}
	@Override	public float getDirectionY() {							return -SPEED;	}
	@Override	protected String getLabel() {							return getClass().toString();	}
	@Override	public float getSpeed() {								return SPEED;	}
	@Override	public float getShootingAngle() {						return 0;			}
	@Override	public Vector2 getShotPosition(int numeroTir) {
		TMP_POS.x = pos.x + HALF_WIDTH - Tournante.HALF_WIDTH;
		TMP_POS.y = pos.y + HALF_WIDTH - Tournante.HALF_WIDTH;
		return TMP_POS;	
	}
	@Override	public float getBulletSpeedMod() {						return -0.012f;				}
	@Override	public int getShotNumber() {							return shotNumber;		}
	@Override	public void addShots(int i) {							shotNumber += i;			}
	@Override	public int getExplosionCount() {						return EXPLOSION;									}
	@Override	public Phase[] getPhases() {							return PHASES;		}
}
