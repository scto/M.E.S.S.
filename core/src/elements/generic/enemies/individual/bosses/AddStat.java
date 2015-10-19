package elements.generic.enemies.individual.bosses;

import behind.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;
import elements.generic.components.EnemyStats;
import elements.generic.components.behavior.Mover;
import elements.generic.components.shots.AbstractShot;
import elements.generic.components.shots.Gatling;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.CyanBullet;

public class AddStat extends Enemy {
	
	protected static final Dimensions DIMENSIONS = Dimensions.ADD_SAT;
	static final float OFFSET_TIR = DIMENSIONS.halfWidth - CyanBullet.DIMENSIONS.halfWidth, INIT_NEXT_SHOT = 0;
	public static Pool<AddStat> pool = Pools.get(AddStat.class);

	@Override
	public void reset() {
		super.reset();
		nextShot = INIT_NEXT_SHOT;
		pos.set(200, 200);
		dir.set(0, getEnemyStats().getSpeed());
	}

	public void lancer(float dirX, float dirY, float x, float y, float angle) {
		dir.set(dirX, dirY).scl(getEnemyStats().getSpeed());
		pos.set(x, y);
		this.angle = angle + 90;
		LIST.add(this);
	}
	
	@Override
	protected void move() {
		if (now > Animations.ailesDeployees.getAnimationDuration())
			Mover.goToPlayer(this, 0.1f);
		else
			super.move();
	}
	
	@Override
	protected void shoot() {
		TMP_POS.set(pos.x + OFFSET_TIR, pos.y + OFFSET_TIR);
		TMP_DIR.set(dir.x, dir.y).nor();
		AbstractShot.straight(Gatling.CYAN_BULLET, TMP_POS, dir, 3);
	}

	@Override	public Animations getAnimation() {			return Animations.AILE_DEPLOYEES;	}
	@Override	protected Sound getExplosionSound() {		return SoundMan.explosion2;			}
	@Override	public EnemyStats getEnemyStats() {			return EnemyStats.ADD_SAT;			}
	@Override	public Dimensions getDimensions() {			return DIMENSIONS;					}
	@Override	public void free() {						pool.free(this);					}
	
}
