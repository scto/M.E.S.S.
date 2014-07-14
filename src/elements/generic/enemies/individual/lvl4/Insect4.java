package elements.generic.enemies.individual.lvl4;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.behavior.Mover;
import elements.generic.enemies.individual.lvl1.Insect;
import jeu.Stats;


public class Insect4 extends Insect {
	
	public static final Pool<Insect4> POOL = Pools.get(Insect4.class);
	private static final int XP = getXp(BASE_XP, 4);
	private static float tmp = 0;
	@Override
	protected void move() {
		if (now > 12f) {
			if (now < 16f) {
				// between 2 | 0 | 2    :    Math.abs(now - 14f)
				// between 0 | 2 | 0    :    Math.abs(Math.abs(now - 14f)-2)
				tmp = Math.abs(Math.abs(now - 14f)-2);
				Mover.rotate(this, tmp * -90);
				angle = dir.angle() + 90;
			} else {
				Mover.straight(this);
			}
		} else
			Mover.insectMove(this, 4, 12, Stats.uDiv8);
	}

	@Override	public int getXp() {				return XP;														}
	@Override	public int getBonusValue() {		return BASE_XP;													}
	@Override	public void free() {				POOL.free(this);												}
}
