package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
import jeu.Stats;
import assets.AssetMan;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;

import elements.generic.components.Dimensions;
import elements.generic.components.EnemyStats;
import elements.generic.components.behavior.Mover;
import elements.generic.components.shots.AbstractShot;
import elements.generic.components.shots.Gatling;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.Tournante;

public class Group extends Enemy {
	
	protected static final Dimensions DIMENSIONS = Dimensions.GROUP;
	public static final Pool<Group> POOL = new Pool<Group>() {		protected Group newObject() {			return new Group();		}	};
	protected static final float FIRERATE = 0.6f * MOD_FIRERATE, INIT_NEXT_SHOT = .5f;
	private static final float COLOR = AssetMan.convertARGB(1, 1f, 0.5f, 0.2f);
	private int shotNumber = 0;
	
	@Override
	protected void move() {
		Mover.coma(this, 1);
		angle = dir.angle() + 90;
	}
	
	@Override
	protected void setColor(SpriteBatch batch) {
		batch.setColor(COLOR);
	}
	
	@Override
	protected void removeColor(SpriteBatch batch) {
		batch.setColor(AssetMan.WHITE);
	}

	public static Group initAll() {
		Group e = POOL.obtain();
		Group f = POOL.obtain();
		Group g = POOL.obtain();
		e.init(CSG.width - DIMENSIONS.width);
		f.init(CSG.width - DIMENSIONS.width * 2.1f);
		g.init(CSG.width - DIMENSIONS.width * 3.2f);
		return e;
	}
	
	protected void init(float x) {
		now = 5;
		LIST.add(this);
		pos.x = x;
		pos.y = CSG.height;
		dir.x = 0;
		dir.y = -getEnemyStats().getSpeed();
	}

	@Override
	public void reset() {
		super.reset();
		pos.y = CSG.height;
		nextShot = INIT_NEXT_SHOT;
		shotNumber = 0;
	}
	
	@Override
	protected void shoot() {
		TMP_POS.set(pos.x + DIMENSIONS.halfWidth - Tournante.DIMENSIONS.halfWidth, pos.y + DIMENSIONS.halfWidth - Tournante.DIMENSIONS.halfWidth);
		TMP_DIR.set(Stats.U12, dir.x + Stats.U12);
		
		AbstractShot.straight(Gatling.TOURNANTE, TMP_POS, TMP_DIR, -1);
		AbstractShot.rafale(this);
	}
	@Override	protected Sound getExplosionSound() {					return SoundMan.explosion6;		}
	@Override	public Animations getAnimation() {						return Animations.DIABOLO;		}
	@Override	public EnemyStats getEnemyStats() {						return EnemyStats.GROUP;		}
	@Override	public Dimensions getDimensions() {						return DIMENSIONS;				}
	@Override	public int getShotNumber() {							return shotNumber;				}
	@Override	public void free() {									POOL.free(this);				}
	@Override	public void addShots(int i) {							shotNumber += i;				}
	@Override	public float getFirerate() {							return FIRERATE;				}
	@Override	public int getNumberOfShots() {							return 3;						}
}
