package assets.particules;

import objets.armes.joueur.ArmesTrois;
import jeu.CSG;
import jeu.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ParticuleArmeTrois extends Particule implements Poolable {
	
	public static Pool<ParticuleArmeTrois> pool = Pools.get(ParticuleArmeTrois.class);
//	private float hauteur;
	private static float LARGEUR = CSG.LARGEUR_ECRAN / 220;
	private float vitesseX, vitesseY, hauteur, vitesseAngle, angle;
	
	public ParticuleArmeTrois() {
		hauteur = LARGEUR * (r.nextInt(3)+3);
		vitesseAngle = 360 + 360*r.nextFloat();
	}
	
	public void display(SpriteBatch batch) {
		batch.draw(getTexture(), posX, posY, 0, 0, getLargeur(), getHauteur(), 1, 1, angle);
	}

	
	@Override
	protected TextureRegion getTexture() {
		return AssetMan.laser;
	}

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
		return LARGEUR;
	}
	
	@Override
	protected float getHauteur() {
		return hauteur;
	}

	public void init(ArmesTrois a) {
		posX = (a.position.x + a.DEMI_LARGEUR);
		posY = a.position.y + a.DEMI_LARGEUR;
		angle = a.angle +90;
		temps = (r.nextFloat() / 10) + .1f + EndlessMode.maintenant;
		vitesseX = ((r.nextFloat() / 4f) - .125f) * 1000;
		vitesseY = (((r.nextFloat() / 4f) - .125f)) * 1000;
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
