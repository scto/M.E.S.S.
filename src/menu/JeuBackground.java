package menu;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import vaisseaux.armes.Armes;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.joueur.VaisseauType1;

public class JeuBackground {
	
	private VaisseauType1 vaisseau = new VaisseauType1();
	private boolean alterner = true;
	private float tps = 0;

	public void reset() {
		CSG.resetLists();
	}

	public void render(SpriteBatch batch, float delta) {
		Endless.maintenant += delta;
		Ennemis.affichageEtMouvement(batch);
		vaisseau.drawSansParticules(batch);
		Armes.affichageEtMouvementSansParticules(batch);
		
		// ** ** ** ** ** UPDATE ** ** ** ** **
		Endless.delta = delta;
		tps += delta;
		if (tps > 8){
//			Ennemis.randomApparition();
			tps = 0;
		}
		if (alterner) {
			Physique.testCollisions();
			vaisseau.tir();
		}
		if (VaisseauType1.position.y > VaisseauType1.HAUTEUR) 	vaisseau.mvtLimiteVitesse(0, -Stats.VITESSE_ARME_BOSS_QUAD);
		else			VaisseauType1.position.y = VaisseauType1.HAUTEUR;
		
		alterner = !alterner;
	}

	public VaisseauType1 getVaisseau() {
		return vaisseau;
	}

	public void add() {
		vaisseau.routineAdds();
	}
}
