package elements.generic.enemies.individual.lvl4;

import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.behavior.Mover;
import elements.generic.enemies.individual.lvl1.Basic;
import elements.generic.enemies.individual.lvl3.Basic3;
import elements.particular.particles.Particles;
import elements.particular.particles.individual.PrecalculatedParticles;

public class Basic4 extends Basic3 {
	
	public static final Pool<Basic4> POOL = Pools.get(Basic4.class);
	private static final int XP = getXp(BASE_XP, 4);
	
	@Override	
	public void move() {
		TMP_POS.x = 0;
		TMP_POS.y = DIMENSIONS.halfWidth;
		TMP_POS.rotate(angle);
		if (EndlessMode.alternate) 
			Particles.smoke((pos.x + DIMENSIONS.halfWidth) + TMP_POS.x, (pos.y + DIMENSIONS.halfHeight) + TMP_POS.y, true, PrecalculatedParticles.colorsYellowToGreen);
		Mover.goToPlayer(this, 0.07f);			
	}
	
	@Override	public Animations getAnimation() {			return Animations.BASIC_ENEMY_GREEN;	}
	@Override	public void free() {						POOL.free(this);						}
	@Override	public int getBonusValue() {				return BASE_XP;							}
	@Override	public int getColor() {						return GREEN;							}
	@Override	public int getXp() {						return XP;								}
}
 