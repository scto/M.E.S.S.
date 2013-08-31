package menu;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import assets.particules.Particules;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import vaisseaux.armes.Armes;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.joueur.VaisseauType1;

public class JeuBackground {
	
	private VaisseauType1 vaisseau = new VaisseauType1();
	private boolean alterner = true;

	public void reset() {
		CSG.resetLists();
	}

	public void render(SpriteBatch batch, float delta) {
		Endless.maintenant += delta;

		vaisseau.draw(batch);
		Armes.affichageEtMouvement(batch);
		Particules.render(batch);		
		VaisseauType1.centreX = VaisseauType1.position.x + VaisseauType1.DEMI_LARGEUR;
		VaisseauType1.centreY = VaisseauType1.position.y + VaisseauType1.DEMI_HAUTEUR;

		// ** ** ** ** ** UPDATE ** ** ** ** **
		if (alterner) {
			Physique.testCollisions();
			vaisseau.tir();
		}
		if (VaisseauType1.position.y > VaisseauType1.HAUTEUR) 	vaisseau.mvtLimiteVitesse(0, -Stats.V_ARME_BOSS_QUAD);
		else			VaisseauType1.position.y = VaisseauType1.HAUTEUR;
		
		alterner = !alterner;
	}

	public VaisseauType1 getVaisseau() {
		return vaisseau;
	}
}

