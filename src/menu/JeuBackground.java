package menu;

import objets.armes.Armes;
import objets.joueur.VaisseauJoueur;
import jeu.EndlessMode;
import jeu.Physique;
import jeu.Stats;
import assets.particules.Particules;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JeuBackground {
	
	private VaisseauJoueur vaisseau = new VaisseauJoueur();
	private boolean alterner = true;

	public void reset() {
		CSG.reset();
	}

	public void render(SpriteBatch batch, float delta) {
		EndlessMode.maintenant += delta;

		vaisseau.draw(batch);
		Armes.affichageEtMouvement(batch);
		Particules.render(batch);		
		VaisseauJoueur.centreX = VaisseauJoueur.position.x + VaisseauJoueur.DEMI_LARGEUR;
		VaisseauJoueur.centreY = VaisseauJoueur.position.y + VaisseauJoueur.DEMI_HAUTEUR;

		// ** ** ** ** ** UPDATE ** ** ** ** **
		if (alterner) {
			Physique.testCollisions();
			vaisseau.tir();
		}
		if (VaisseauJoueur.position.y > VaisseauJoueur.HAUTEUR) 	vaisseau.mvtLimiteVitesse(0, -Stats.V_ARME_BOSS_QUAD);
		else			VaisseauJoueur.position.y = VaisseauJoueur.HAUTEUR;
		
		alterner = !alterner;
	}

	public VaisseauJoueur getVaisseau() {
		return vaisseau;
	}
}

