package elements.generic.behavior;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import elements.generic.enemies.Enemy;

public class MineBehavior extends Behavior {

	@Override
	public void act(Enemy e) {
		if (e.pos.x < CSG.gameZoneHalfWidth - e.getHalfWidth())
			e.pos.x += EndlessMode.delta * Stats.V_ENN_BOSS_MINE;
		if (e.pos.x > CSG.gameZoneHalfWidth - e.getHalfWidth())
			e.pos.x -= EndlessMode.delta * Stats.V_ENN_BOSS_MINE;
		if (e.pos.y > CSG.HAUTEUR_ECRAN_PALLIER_3 )
			e.pos.y -= (Stats.V_ENN_BOSS_MINE * EndlessMode.delta);
		if (e.getPhase() == 2 && e.angle < 180)
				e.angle += EndlessMode.delta * 100;
	}

}
