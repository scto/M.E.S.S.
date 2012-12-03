package affichage;

import menu.CSG;
import affichage.animation.AnimationBouton;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ui {
	
	private static AnimationBouton boutonRouge = new AnimationBouton();
	private static final int boutonRougeLargeur = (CSG.DIXIEME_LARGEUR/3) * 2;
	private static final int boutonRougeX = (int) (CSG.LARGEUR_ECRAN - (boutonRougeLargeur * 1.5));
	private static final int boutonRougeY = (int) (boutonRougeLargeur * .5);
	
	public static void perdu() {
		
	}

	public static void afficher(SpriteBatch batch) {
		batch.draw(boutonRouge.getTexture(), boutonRougeX, boutonRougeY, boutonRougeLargeur, boutonRougeLargeur);
	}

}
