package elements.generic.enemies.individual.lvl2;

import jeu.CSG;
import jeu.Stats;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;
import elements.generic.components.shots.AbstractShot;
import elements.generic.components.shots.Gatling;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.OrangeBullet;

public class BouleTirCote extends Enemy {
	
	protected static final Dimensions DIMENSIONS = Dimensions.BALL_SIDE_SHOOT;
	private static final float FIRERATE = .62f, OFFSET = DIMENSIONS.halfWidth - OrangeBullet.DIMENSIONS.halfWidth;
	public static final Pool<BouleTirCote> POOL = Pools.get(BouleTirCote.class);
	protected int numeroTir;
	
	public void init() {
		dir.set(0, -getSpeed());
		nextShot = 1;
		numeroTir = 1;
		pos.set(CSG.gameZoneHalfWidth, CSG.SCREEN_HEIGHT);
		angle = CSG.gameZoneHalfWidth - (pos.x + DIMENSIONS.width*2);
		angle /= 4;
		dir.rotate(angle);
		angle += 180;
	}
	
	@Override
	protected void shoot() {
		shoot(0);
	}
	
	protected void shoot(float angle) {
		TMP_POS.set(pos.x + OFFSET, pos.y + OFFSET);
		TMP_DIR.set(dir.y, -dir.x).rotate(angle);
		AbstractShot.leftRight(Gatling.ORANGE_BULLET, TMP_POS, TMP_DIR, 1.5f);
		numeroTir = AbstractShot.interval(this, 6, 1, numeroTir);
	}

	@Override	public Dimensions getDimensions() {			return DIMENSIONS;					}
	@Override	public Animations getAnimation() {			return Animations.BALL;	}
	@Override	public float getFirerate() {				return FIRERATE;	}
	@Override	public float getSpeed() {					return Stats.V_BOULE_TIR_COTE;	}
	@Override	public int getXp() {						return 73;	}
	@Override	public int getBonusValue() {				return 80;	}
	@Override	protected int getMaxHp() {					return Stats.HP_BOULE_COTE_PETIT;	}
	@Override	protected Sound getExplosionSound() {		return SoundMan.explosion4;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	public int getExplosionCount() {			return 40;											}
}
