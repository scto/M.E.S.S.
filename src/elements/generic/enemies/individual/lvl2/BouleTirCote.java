package elements.generic.enemies.individual.lvl2;

import jeu.CSG;
import jeu.Stats;
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
import elements.generic.weapons.enemies.BlueBullet;

public class BouleTirCote extends Enemy {
	
	private static final int WIDTH = Stats.WIDTH_BOULE_TIR_COTE, HALF_WIDTH = WIDTH/2;
	private static final float FIRERATE = .22f, OFFSET = HALF_WIDTH - BlueBullet.HALF_WIDTH;
	public static final Pool<BouleTirCote> POOL = Pools.get(BouleTirCote.class);
	protected int numeroTir;
	public static final int PK = 99;
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.BLUE_BULLET,				Shot.SHOT_EN_RAFALE_LEFT_RIGHT,				Animations.BALL				)		};
	
	public void init() {
		dir.x = 0;
		dir.y = -getSpeed();
		nextShot = 1;
		numeroTir = 1;
		pos.x = CSG.gameZoneHalfWidth;
		pos.y = CSG.SCREEN_HEIGHT;
		angle = CSG.gameZoneHalfWidth - (pos.x + WIDTH*2);
		angle /= 4;
		dir.rotate(angle);
		angle += 180;
	}
	
	public Vector2 getShotPosition(int numeroTir) {
		TMP_POS.x = pos.x + OFFSET; 
		TMP_POS.y = pos.y + OFFSET;
		return TMP_POS;
	}
	
	public Vector2 getShootingDir() {
		TMP_DIR.x = dir.y;
		TMP_DIR.y = -dir.x;
		return TMP_DIR;
	}

	@Override	public float getFirerate() {				return FIRERATE;	}
	@Override	public void addShots(int i) {				numeroTir += i;			}
	@Override	public int getShotNumber() {				return numeroTir;	}
	@Override	public int getNumberOfShots() {				return 3;	}
	@Override	public float getSpeed() {					return Stats.V_BOULE_TIR_COTE;	}
	@Override	public int getXp() {						return 73;	}
	@Override	public int getBonusValue() {				return 80;	}
	@Override	public float getHeight() {					return WIDTH;	}
	@Override	public float getWidth() {					return WIDTH;	}
	@Override	public float getHalfHeight() {				return HALF_WIDTH;	}
	@Override	public float getHalfWidth() {				return HALF_WIDTH;	}
	@Override	public float getBulletSpeedMod() {			return 1f;	}
	@Override	protected int getMaxHp() {					return Stats.HP_BOULE_COTE_PETIT;	}
	@Override	protected Sound getExplosionSound() {		return SoundMan.explosion4;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	public float getShootingAngle() {			return angle;	}
	@Override	public float getDirectionY() {				return dir.y;	}
	@Override	public float getDirectionX() {				return dir.x;	}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public int getExplosionCount() {			return 40;											}
	@Override	public Phase[] getPhases() {				return PHASES;	}
}
