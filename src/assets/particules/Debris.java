package assets.particules;

import objets.armes.joueur.ArmeJoueur;
import jeu.EndlessMode;
import jeu.Stats;
import menu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class Debris extends ParticuleRGB implements Poolable{
	
	public static Pool<Debris> pool = Pools.get(Debris.class);
	private float largeur, vitesseX, vitesseY, angle, vitesseAngle;
	private static final float LARGEUR = CSG.LARGEUR_ECRAN / 55;
	
	public Debris() {
		vitesseAngle = (r.nextFloat() -.5f) * 5000f;
		largeur = (r.nextFloat() * LARGEUR) + 1;
	}
	
	public void afficher(SpriteBatch batch) {
		batch.setColor(color);
		batch.draw(getTexture(), posX, posY, 0, 0, getLargeur(), getHauteur(), 1, 1, angle);
		batch.setColor(AssetMan.WHITE);
	}
	public boolean mouvementEtVerif() {
		vitesseX /= 1.03f;
		vitesseY /= 1.03f;
		posX += vitesseX * EndlessMode.delta;
		posY += vitesseY * EndlessMode.delta;
		angle += vitesseAngle  * EndlessMode.delta;
		if (EndlessMode.maintenant > temps) return false;
		// Je pense qu'on peut se permettre de ne pas verifier si il est tjrs � l'�cran vu son court temps de vie
		return true;
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

	public void init(ArmeJoueur a) {
		posX = a.position.x + a.getLargeur()/2;
		posY = a.position.y + a.getHauteur()/2;
		
		vitesseX = (a.direction.x + ((r.nextFloat()-.5f) * Stats.V_PARTICULE_DEBRIS));
		vitesseY = (a.direction.y + ((r.nextFloat()-.5f) * Stats.V_PARTICULE_DEBRIS));
		
		angle = a.direction.angle();

		color = a.getColor();

		temps = (EndlessMode.maintenant + r.nextFloat() / 6) + .1f;
	}

	@Override
	public void free() {
		pool.free(this);
	}
}

