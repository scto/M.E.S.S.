package assets.particules;

import objets.armes.joueur.ArmesDeBase;
import jeu.CSG;
import jeu.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ParticuleArmeDeBase extends ParticuleRGB implements Poolable {
	
	public static Pool<ParticuleArmeDeBase> pool = Pools.get(ParticuleArmeDeBase.class);
//	private float hauteur;
	private static float HAUTEUR = CSG.LARGEUR_ECRAN / 130;
	protected static float diminutionH;
	private float vitesseX, vitesseY;


	public ParticuleArmeDeBase() {
		vitesseAngle = r.nextFloat() * 400;
	}
	
	@Override
	protected TextureRegion getTexture() {
		return AssetMan.poussiere;
	}
	@Override
	public boolean mouvementEtVerif() {
		posX += vitesseX * EndlessMode.delta;
		posY += vitesseY * EndlessMode.delta;
		angle += vitesseAngle  * EndlessMode.delta;
		if (EndlessMode.maintenant > temps) return false;
		// Je pense qu'on peut se permettre de ne pas verifier si il est tjrs � l'�cran vu son court temps de vie
		return true;
	}
	
	@Override
	protected float getLargeur() {
		return HAUTEUR;
	}
	
	@Override
	protected float getHauteur() {
		return HAUTEUR;
	}

	public void init(ArmesDeBase a) {
		posX = (a.position.x + a.DEMI_LARGEUR);
		posY = a.position.y + a.DEMI_HAUTEUR;
		
		temps = (r.nextFloat() / 10) + .1f + EndlessMode.maintenant;
		vitesseX = ((r.nextFloat() / 4f) - .125f) * 500f;
		vitesseY = ((r.nextFloat() / 4f) - .125f) * 400f;
		
		color = a.getColor();
	}

	@Override
	public void reset() {
	}
	@Override
	public void free() {
		pool.free(this);
		Particules.nbArmeJoueur--;
	}
}
