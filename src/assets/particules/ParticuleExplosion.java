package assets.particules;

import objets.ennemis.Ennemis;
import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
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
		vitesseX /= 1.02f;
		vitesseY /= 1.02f;
		posX += vitesseX * EndlessMode.delta;
		posY += vitesseY * EndlessMode.delta;
		angle += vitesseAngle  * EndlessMode.delta;
		if (EndlessMode.maintenant > temps) return false;
		// Je pense qu'on peut se permettre de ne pas verifier si il est tjrs � l'�cran vu son court temps de vie
		return true;
	}

	public void init(Ennemis e) {
		posX = (e.position.x + (e.getLargeur() * r.nextFloat()));
		posY = (e.position.y + (e.getHauteur() * r.nextFloat()));

		vitesseY = (float) ((Math.random()-.5) * Stats.V_PARTICULE_EXPLOSION) + e.getDirectionY();
		vitesseX = (float) ((Math.random()-.5) * Stats.V_PARTICULE_EXPLOSION) + e.getDirectionX();
		
		temps = ((r.nextFloat()/2) + EndlessMode.maintenant + .2f);
	}

	@Override
	public void reset() {
	}
	@Override
	public void free() {
		pool.free(this);
	}
}
