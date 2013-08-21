package assets.particules;

import vaisseaux.armes.joueur.ArmeJoueur;
import jeu.Endless;
import menu.CSG;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class Debris extends Particule implements Poolable{
	
	public static Pool<Debris> pool = Pools.get(Debris.class);
	private float largeur;
	
	public Debris() {
		vitesseAngle = (float) ((Math.random() -.5) * 5000);
		largeur = (float) (Math.random() * CSG.LARGEUR_ECRAN / 55);
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
		return super.mouvementEtVerif();
	}

	public void init(ArmeJoueur a) {
		posX = a.position.x + a.getLargeur()/2;
		posY = a.position.y + a.getHauteur()/2;
		vitesseX = (float) (a.direction.x + (Math.random()-.5));
		vitesseY = (float) (a.direction.y + (Math.random()-.5));
		
		angle = a.direction.angle();
		
		vitesseX *= 512;
		vitesseY *= 512;
		
		r = a.getR();
		g = a.getG();
		b = a.getB();
		
		temps = Endless.maintenant + (float) Math.random();
	}

	@Override
	public void free() {
		pool.free(this);
	}
}

