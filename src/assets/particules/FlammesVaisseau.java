package assets.particules;

import objets.joueur.VaisseauJoueur;
import jeu.CSG;
import jeu.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class FlammesVaisseau extends Particule implements Poolable {
	
	public static final float LARGEUR = CSG.LARGEUR_ECRAN / 60, DEMI_LARGEUR = LARGEUR / 2;
	public static Pool<FlammesVaisseau> pool = Pools.get(FlammesVaisseau.class);
	private float vitesseX, vitesseY, red, green, alpha;
	
	// temps sert de vitesse de reduction de l'alpha
	
	public FlammesVaisseau() {
		vitesseY =  ((r.nextFloat()+.5f) * -150) - 200;
		vitesseX = (r.nextFloat()-.5f) * 150;
	}
	
	@Override
	protected TextureRegion getTexture() {
		return AssetMan.poussiere;
	}
	
	@Override
	protected float getLargeur() {
		return LARGEUR;
	}
	
	@Override
	public boolean mouvementEtVerif() {
		alpha -= temps * EndlessMode.delta;
		if (alpha < 0) return false;
		posX += vitesseX * EndlessMode.delta;
		posY += vitesseY * EndlessMode.delta;
		red /= (1 + EndlessMode.delta + EndlessMode.delta);
		green /= (1 + EndlessMode.delta + EndlessMode.delta);
		
		// Je pense qu'on peut se permettre de ne pas verifier si il est tjrs � l'�cran vu son court temps de vie
		return true;
	}
	
	@Override
	public void display(SpriteBatch batch) {
		batch.setColor(red, green, 1, alpha);
		batch.draw(getTexture(), posX, posY, LARGEUR, LARGEUR);
		batch.setColor(AssetMan.WHITE);
	}

	
	@Override
	protected float getHauteur() {
		return LARGEUR;
	}

	public void init(VaisseauJoueur v) {
		posX = (v.position.x + v.DEMI_LARGEUR - LARGEUR) + (r.nextFloat() * LARGEUR);
		posY = v.position.y - (DEMI_LARGEUR + (DEMI_LARGEUR * r.nextFloat()));
		temps = 2f + (r.nextFloat() * 2);
		alpha = 1;
		red = r.nextFloat();
		green = r.nextFloat();
		Particules.nbFlammes++;
	}

	@Override
	public void reset() {
	}
	@Override
	public void free() {
		Particules.nbFlammes--;
		pool.free(this);
	}

	public void init(Vector2 posParticule, Vector2 baseDirection) {
		posX = (posParticule.x) + (r.nextFloat() * LARGEUR);
		posY = posParticule.y - (DEMI_LARGEUR + (DEMI_LARGEUR * r.nextFloat()));
		baseDirection.rotate((-50 + r.nextFloat()*100));
		vitesseX = baseDirection.x * 40;
		vitesseY = baseDirection.y * 40;
		temps = 2f + (r.nextFloat() * 2);
		alpha = 1;
		red = r.nextFloat();
		green = r.nextFloat();
		Particules.nbFlammes++;
	}
}
