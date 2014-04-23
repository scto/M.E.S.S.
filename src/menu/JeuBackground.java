package menu;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import elements.generic.Player;
import elements.generic.weapons.Weapons;
import elements.particular.particles.Particles;

public class JeuBackground {
	
	private Player vaisseau = new Player();
	private boolean alterner = true;

	public void reset() {
		CSG.reset();
	}

	public void render(SpriteBatch batch, float delta) {
		vaisseau.draw(batch);
		Weapons.drawAndMove(batch);
		Particles.draw(batch);		
		Player.xCenter = Player.POS.x + Player.DEMI_LARGEUR;
		Player.yCenter = Player.POS.y + Player.DEMI_HAUTEUR;

		// ** ** ** ** ** UPDATE ** ** ** ** **
		if (alterner) {
			Physic.collisionsTest();
			vaisseau.tir();
		}
		if (Player.POS.y > Player.HEIGHT) 	vaisseau.mvtLimiteVitesse(0, -Stats.V_ARME_BOSS_QUAD);
		else			Player.POS.y = Player.HEIGHT;
		
		alterner = !alterner;
	}

	public Player getVaisseau() {
		return vaisseau;
	}
}

