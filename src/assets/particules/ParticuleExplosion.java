package assets.particules;

import jeu.Endless;
import menu.CSG;
import vaisseaux.ennemis.Ennemis;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ParticuleExplosion extends ParticuleRGB implements Poolable {
	
	public static final float LARGEUR = CSG.LARGEUR_ECRAN / 100;
	public static Pool<ParticuleExplosion> pool = Pools.get(ParticuleExplosion.class);
	private float largeur, vitesseX, vitesseY;
	
	public ParticuleExplosion() {
		flammes();
	
		largeur = (float) ((Math.random() * LARGEUR) + LARGEUR);
	}

	@Override
	protected TextureRegion getTexture() {
		return AssetMan.poussiere;
	}
	
	@Override
	protected float getLargeur() {
		return largeur;
	}
	
	@Override
	protected float getHauteur() {
		return largeur;
	}
	
	@Override
	public boolean mouvementEtVerif() {
<<<<<<< HEAD
		vitesseX /= 1.02f;
		vitesseY /= 1.02f;
		posX += vitesseX * Endless.delta;
		posY += vitesseY * Endless.delta;
=======
		vitesseX /= 1.01f;
		vitesseY /= 1.01f;
>>>>>>> parent of a593e8e... refact animation
		return super.mouvementEtVerif();
	}

	public void init(Ennemis e) {
		posX = (e.position.x + (e.getLargeur() * r.nextFloat()));
		posY = (e.position.y + (e.getHauteur() * r.nextFloat()));

<<<<<<< HEAD
		vitesseY = ((r.nextFloat()-.5f) * DISPERSION) + e.getDirectionY();
		vitesseX = ((r.nextFloat()-.5f) * DISPERSION) + e.getDirectionX();
=======
		vitesseY = (float) ((Math.random()-.5) * 250) + e.getDirectionY();
		vitesseX = (float) ((Math.random()-.5) * 250) + e.getDirectionX();
>>>>>>> parent of a593e8e... refact animation
		
		temps = ((r.nextFloat()/2) + Endless.maintenant + .2f);
	}

	@Override
	public void reset() {
	}
	@Override
	public void free() {
		pool.free(this);
	}
}
