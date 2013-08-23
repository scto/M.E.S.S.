package assets.particules;

import jeu.Endless;
import menu.CSG;
import vaisseaux.armes.joueur.ArmesBalayage;
import vaisseaux.armes.joueur.ArmesDeBase;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ParticuleArmeBalayage extends ParticuleRGB implements Poolable {
	
	public static Pool<ParticuleArmeBalayage> pool = Pools.get(ParticuleArmeBalayage.class);
//	private float hauteur;
	private static float LARGEUR = CSG.LARGEUR_ECRAN / 300;
	private static float DEMI_HAUTEUR = CSG.LARGEUR_ECRAN / 130;
	protected static float diminutionH;
	private static float VITESSE = 1000;
	private float vitesseX, vitesseY, hauteur;
	
	public ParticuleArmeBalayage() {
		hauteur = LARGEUR * (r.nextInt(3)+3);
	}
	
	@Override
	protected TextureRegion getTexture() {
		return AssetMan.poussiere;
	}
	@Override
	public boolean mouvementEtVerif() {
		posX += vitesseX * Endless.delta;
		posY += vitesseY * Endless.delta;
		return super.mouvementEtVerif();
	}
	
	@Override
	protected float getLargeur() {
		return LARGEUR;
	}
	
	@Override
	protected float getHauteur() {
		return hauteur;
	}

	public void init(ArmesBalayage a) {
		posX = (a.position.x + a.DEMI_LARGEUR) + HAUTEUR;
		posY = a.position.y + a.DEMI_LARGEUR;
		angle = a.angle +90;
		temps = (r.nextFloat() / 10) + .1f + Endless.maintenant;
		vitesseX = ((r.nextFloat() / 4f) - .125f) * 500f;
		vitesseY = ((a.direction.y) * ((r.nextFloat() / 4f) - .125f)) * 400f;
		
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
