package assets.particules;

import vaisseaux.armes.joueur.ArmeJoueur;
import jeu.Endless;
import menu.CSG;
import assets.AssetMan;

<<<<<<< HEAD
=======
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
>>>>>>> parent of a593e8e... refact animation
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class Debris extends ParticuleRGB implements Poolable{
	
	public static Pool<Debris> pool = Pools.get(Debris.class);
	private float largeur, vitesseX, vitesseY;
	private static final float LARGEUR = CSG.LARGEUR_ECRAN / 55;
	
	public Debris() {
		vitesseAngle = (r.nextFloat() -.5f) * 5000f;
		largeur = (r.nextFloat() * LARGEUR) + 1;
	}
	
	@Override
	public void reset() {	
	}
	
	@Override
	protected float getHauteur() {
		return HAUTEUR;
	}
	
	@Override
	protected float getLargeur() {
		return largeur;
	}
	
	@Override
	public boolean mouvementEtVerif() {
		vitesseX /= 1.01f;
		vitesseY /= 1.01f;
		posX += vitesseX * Endless.delta;
		posY += vitesseY * Endless.delta;
		return super.mouvementEtVerif();
	}

	public void init(ArmeJoueur a) {
		posX = a.position.x + a.getLargeur()/2;
		posY = a.position.y + a.getHauteur()/2;
		
		vitesseX = (a.direction.x + (r.nextFloat()-.5f));
		vitesseY = (a.direction.y + (r.nextFloat()-.5f));
		
		angle = a.direction.angle();
		
		vitesseX *= 512;
		vitesseY *= 512;
		
		color = a.getColor();

		temps = (Endless.maintenant + r.nextFloat() / 6) + 1.5f;
	}

	@Override
	public void free() {
		pool.free(this);
	}
}

