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
	
	public static final float LARGEUR = CSG.LARGEUR_ECRAN / 100, DISPERSION = 450;
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
		vitesseX /= 1.02f;
		vitesseY /= 1.02f;
		posX += vitesseX * Endless.delta;
		posY += vitesseY * Endless.delta;
		return super.mouvementEtVerif();
	}

	public void init(Ennemis e) {
		posX = (e.position.x + (e.getLargeur() * r.nextFloat()));
		posY = (e.position.y + (e.getHauteur() * r.nextFloat()));

		vitesseY = ((r.nextFloat()-.5f) * DISPERSION) + e.getDirectionY();
		vitesseX = ((r.nextFloat()-.5f) * DISPERSION) + e.getDirectionX();
		
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
