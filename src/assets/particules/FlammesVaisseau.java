package assets.particules;

import menu.CSG;
import jeu.Endless;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import vaisseaux.joueur.VaisseauType1;

public class FlammesVaisseau extends ParticuleRGB implements Poolable {
	
	public static final float LARGEUR = CSG.LARGEUR_ECRAN / 60;
	public static Pool<FlammesVaisseau> pool = Pools.get(FlammesVaisseau.class);
	private float vitesseX, vitesseY;
	
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
		return LARGEUR;
	}
	
	@Override
	public boolean mouvementEtVerif() {
		posX += vitesseX * Endless.delta;
		posY += vitesseY * Endless.delta;
		angle += vitesseAngle  * Endless.delta;
		if (Endless.maintenant > temps) return false;
		// Je pense qu'on peut se permettre de ne pas verifier si il est tjrs à l'écran vu son court temps de vie
		return true;
	}
	
	@Override
	protected float getHauteur() {
		return LARGEUR;
	}

	public void init(VaisseauType1 v) {
		posX = (v.position.x + v.DEMI_LARGEUR);
		posY = v.position.y;
		temps = (float) (Math.random()/8) + Endless.maintenant;
		
		angle = r.nextFloat() * 360f;
	}

	@Override
	public void reset() {
	}
	@Override
	public void free() {
		pool.free(this);
	}
}
