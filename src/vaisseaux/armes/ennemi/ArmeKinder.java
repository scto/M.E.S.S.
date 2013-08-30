package vaisseaux.armes.ennemi;

import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationTirTrois;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmeKinder extends ArmeEnnemi implements Poolable {
	
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 25;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static Pool<ArmeKinder> pool = Pools.get(ArmeKinder.class);

	@Override
	public TextureRegion getTexture() {		return AnimationTirTrois.getTexture(maintenant);	}
	@Override
	protected float getVitesse() {			return -Stats.V_ARME_KINDER;	}
	@Override
	public int getLargeur() {				return LARGEUR;	}
	@Override
	public int getHauteur() {				return LARGEUR;	}
	@Override
	public float getDemiHauteur() {			return DEMI_LARGEUR;	}
	@Override
	public float getDemiLargeur() {			return DEMI_LARGEUR;	}
	@Override
	public void free() {					pool.free(this);	}
}
