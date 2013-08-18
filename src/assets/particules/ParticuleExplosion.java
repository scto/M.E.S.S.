package assets.particules;

import jeu.Endless;
import menu.CSG;
import vaisseaux.ennemis.Ennemis;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ParticuleExplosion extends Particule implements Poolable {
	
	public static final float LARGEUR = CSG.LARGEUR_ECRAN / 100;
	public static Pool<ParticuleExplosion> pool = Pools.get(ParticuleExplosion.class);
	private float largeur;
	
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
		vitesseX /= 1.01f;
		vitesseY /= 1.01f;
		return super.mouvementEtVerif();
	}

	public void init(Ennemis e) {
		posX = (float) (e.position.x + (e.getLargeur() * Math.random()));
		posY = (float) (e.position.y + (e.getHauteur() * Math.random()));

		vitesseY = (float) ((Math.random()-.5) * 250) + e.getDirectionY();
		vitesseX = (float) ((Math.random()-.5) * 250) + e.getDirectionX();
		
		temps = (float) ((Math.random() / 2) + Endless.maintenant + .2f);
	}

	@Override
	public void reset() {
	}
	@Override
	public void free() {
		pool.free(this);
	}
}
