package assets.background;

import menu.CSG;
import jeu.Endless;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class ParallaxBackground {

	private ParallaxLayer[] layers;
	private Camera camera;
	private float currentX, currentY;
	private static final int SPEED = 25;
	private static final int MAX_POUSSIERES = 35;
	private static Array<Poussiere> poussieres = new Array<Poussiere>(false, MAX_POUSSIERES);
	private static float prochaineEtoile = 0;

	public ParallaxBackground(ParallaxLayer[] layers) {
		super();
		this.layers = layers;
		camera = new OrthographicCamera(CSG.LARGEUR_ZONE_JEU, CSG.HAUTEUR_ECRAN);
		camera.position.set(CSG.DEMI_LARGEUR_ZONE_JEU, CSG.DEMI_HAUTEUR_ECRAN, 0);
		for (ParallaxLayer layer : layers)
			layer.originalX = -camera.position.x % layer.largeurRegion;
	}

	public void render(SpriteBatch batch) {
		// **********************  P A R T I E   R E G I O N S  **********************  
		camera.position.y += SPEED * Endless.delta;
		for (ParallaxLayer layer : layers) {
			currentX = layer.originalX;
			do {
				currentY = -camera.position.y * layer.ratioVitesseY;
				do {
					batch.draw(layer.region, currentX, currentY);
					currentY += (layer.hauteurRegion);
				} while (currentY < camera.viewportHeight);
				currentX += (layer.largeurRegion);
			} while (currentX < camera.viewportWidth);
		}
		// **********************  P O U S S I E R E S  **********************
		if (poussieres.size < MAX_POUSSIERES && prochaineEtoile < Endless.maintenant) {
			Poussiere p = Poussiere.pool.obtain();
			p.init((float) (Math.random() * CSG.LARGEUR_ZONE_JEU));
			poussieres.add(p);
			prochaineEtoile += .3;
		}

		for (Poussiere p : poussieres) {
			p.afficher(batch);
			if (p.mouvementEtVerif()) poussieres.removeValue(p, true);
		}
	}

	public static void resetEtoiles() {
		prochaineEtoile = 0;
	}
}