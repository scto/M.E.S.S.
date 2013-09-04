package vaisseaux.armes.ennemi;

import jeu.Endless;
import jeu.Stats;
import menu.CSG;
import assets.AssetMan;
import assets.animation.AnimationTirTrois;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class BouleFeuPetite extends BouleFeu implements Poolable {
	
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 30;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (int) (LARGEUR * 1.5);
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static Pool<BouleFeuPetite> pool = Pools.get(BouleFeuPetite.class);

	@Override
	public void free() {					pool.free(this);	}
	@Override
	public int getLargeur() {
		return LARGEUR;
	}
	@Override
	public int getHauteur() {
		return HAUTEUR;
	}
	@Override
	public float getDemiHauteur() {
		return DEMI_HAUTEUR;
	}
	@Override
	public float getDemiLargeur() {
		return DEMI_LARGEUR;
	}
}
