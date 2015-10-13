package menu;

import jeu.CSG;
import jeu.Physic;
import jeu.mode.EndlessMode;
import assets.sprites.AnimPlayer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import elements.generic.weapons.Weapon;
import elements.particular.Player;
import elements.particular.particles.Particles;

public class JeuBackground {
	
	private Player vaisseau = new Player();
	private boolean alterner = true;

	public void reset() {
		CSG.reset();
	}

	public void render(SpriteBatch batch, float delta) {
		vaisseau.draw(batch);
		Weapon.drawAndMove(batch);
		Particles.draw(batch);		
		Player.updateCenters();

		if (alterner) {
			Physic.collisionsTest();
			vaisseau.shot();
		}
		if (Player.POS.y > Player.HEIGHT) {
			Player.POS.y -= EndlessMode.delta25 * 6;
			AnimPlayer.state = 2;
			vaisseau.routineAdds();
		} else
			Player.POS.y = Player.HEIGHT;
		
		alterner = !alterner;
	}

	public Player getVaisseau() {
		return vaisseau;
	}
}

