package vaisseaux.armes.ennemi;

import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationBouleBleu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmeInsecte extends ArmeEnnemi implements Poolable{
	
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 23;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR * 2;
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static Pool<ArmeInsecte> pool = Pools.get(ArmeInsecte.class);

	@Override
	public TextureRegion getTexture() {	return AnimationBouleBleu.getTexture(maintenant);	}
	@Override
	protected float getVitesse() {		return Stats.V_INSECTE;	}
	@Override
	public int getLargeur() {			return LARGEUR;	}
	@Override
	public int getHauteur() {			return HAUTEUR;	}
	@Override
	public void free() {				pool.free(this);	}
	@Override
	public float getDemiHauteur() {		return DEMI_HAUTEUR;	}
	@Override
	public float getDemiLargeur() {		return DEMI_LARGEUR;	}
}

