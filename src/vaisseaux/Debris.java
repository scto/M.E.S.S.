package vaisseaux;

import vaisseaux.armes.joueur.ArmeJoueur;
import jeu.Endless;
import menu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class Debris implements Poolable{
	
	public static final float HAUTEUR = CSG.LARGEUR_ECRAN / 280;
	public static Pool<Debris> pool = Pools.get(Debris.class);
	public float posX, posY, vitesseX, vitesseY, temps, r, g, b, angle, largeur, vitesseAngle;

	
	@Override
	public void reset() {	}

	public void afficher(SpriteBatch batch) {
		batch.setColor(r, g, b, 1); // Jaune - rouge
		batch.draw(AssetMan.debris, posX, posY, 0, 0, largeur, HAUTEUR, 1, 1, angle);
		batch.setColor(Color.WHITE);
	}

	/**
	 * @return true si il faut le virer
	 */
	public boolean mouvementEtVerif() {
		angle += vitesseAngle * Endless.delta;
		
		if (Endless.maintenant > temps) return false;
		posX += vitesseX * Endless.delta;
		posY += vitesseY * Endless.delta;
		// Je pense qu'on peut se permettre de ne pas verifier si il est tjrs à l'écran vu son court temps de vie
		return true;
	}

	public void init(ArmeJoueur a) {
		posX = a.position.x + a.getLargeur()/2;
		posY = a.position.y + a.getHauteur()/2;
		vitesseX = (float) (a.direction.x + (Math.random()-.5));
		vitesseY = (float) (a.direction.y + (Math.random()-.5));
		
		angle = a.direction.angle();
		
		vitesseX *= 512;
		vitesseY *= 512;
		vitesseAngle = (float) ((Math.random() -.5) * 5000);
		
		r = a.getR();
		g = a.getG();
		b = a.getB();
		
		largeur = (float) (Math.random() * CSG.LARGEUR_ECRAN / 55);
		temps = Endless.maintenant + (float) Math.random();
	}
}

