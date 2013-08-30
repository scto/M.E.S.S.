package vaisseaux.armes.ennemi;

import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationTirFeu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme de base qui fait une boule de feu
 * @author Julien
 */
public class BouleFeu extends ArmeEnnemi implements Poolable {
	
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 15;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (int) (LARGEUR * 1.5);
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static Pool<BouleFeu> pool = Pools.get(BouleFeu.class);
	
	public BouleFeu() { angle = 180; }
	
	@Override
	protected float getVitesse() {			return Stats.V_ARME_BOSS_QUAD;	}
	@Override
	public int getLargeur() {				return LARGEUR;	}
	@Override
	public int getHauteur() {				return HAUTEUR;	}
	@Override
	public void free() {					pool.free(this);	}
	@Override
	public float getDemiHauteur() {			return DEMI_HAUTEUR;	}
	@Override
	public float getDemiLargeur() {			return DEMI_LARGEUR;	}
	@Override
	public TextureRegion getTexture() {		return AnimationTirFeu.getTexture(maintenant);	}
}
