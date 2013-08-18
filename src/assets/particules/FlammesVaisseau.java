package assets.particules;

import menu.CSG;
import jeu.Endless;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import vaisseaux.joueur.VaisseauType1;

public class FlammesVaisseau extends Particule implements Poolable {
	
	public static final float LARGEUR = CSG.LARGEUR_ECRAN / 80;
	public static Pool<FlammesVaisseau> pool = Pools.get(FlammesVaisseau.class);
	
	public FlammesVaisseau() {
		flammes();
		vitesseY = (float) ((Math.random()+2) * -80);
		vitesseX = (float) ((Math.random()-.5) * 120);
		
	}
	
	@Override
	protected TextureRegion getTexture() {
		return AssetMan.poussiere;
	}
	
	@Override
	protected float getLargeur() {
		return CSG.LARGEUR_ECRAN / 60;
	}
	
	@Override
	protected float getHauteur() {
		return CSG.LARGEUR_ECRAN / 60;
	}

	public void init(VaisseauType1 v) {
		posX = v.position.x + v.DEMI_LARGEUR;
		posY = v.position.y;
		temps = (float) (Math.random()/8) + Endless.maintenant;
	}

	@Override
	public void reset() {
	}
	@Override
	public void free() {
		pool.free(this);
	}
}
