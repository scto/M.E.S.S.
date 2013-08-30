package assets.particules;

import jeu.Endless;
import menu.CSG;
import vaisseaux.armes.joueur.ArmeAdd;
import assets.AssetMan;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ParticuleArmeAdd extends ParticuleRGB implements Poolable {
	
	public static Pool<ParticuleArmeAdd> pool = Pools.get(ParticuleArmeAdd.class);
//	private float hauteur;
	private static float HAUTEUR = CSG.LARGEUR_ECRAN / 150;
	private static float DEMI_HAUTEUR = CSG.LARGEUR_ECRAN / 150;
	protected static float diminutionH;
	private static float VITESSE = 2000;
	private float vitesseX, vitesseY;


	public ParticuleArmeAdd() {
//		vitesseAngle = r.nextFloat() * 400;
	}
	
	@Override
	protected TextureRegion getTexture() {
		return AssetMan.poussiere;
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
	protected float getLargeur() {
		return HAUTEUR;
	}
	
	@Override
	protected float getHauteur() {
		return HAUTEUR;
	}

	public void init(ArmeAdd a) {
//		angle = a.angle + 90;
		posX = (a.position.x + a.DEMI_LARGEUR);
		posY = a.position.y + a.DEMI_HAUTEUR;
		
		temps = (r.nextFloat() / 5) + .15f + Endless.maintenant;
		vitesseX = ((a.direction.x) * ((r.nextFloat() / 4f) - .125f)) * 1000f;
		vitesseY = ((a.direction.y) * ((r.nextFloat() / 4f) - .125f)) * 1000f;
		
		color = a.getColor();
	}

	@Override
	public void reset() {
	}
	@Override
	public void free() {
		pool.free(this);
	}
}
