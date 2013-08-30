package vaisseaux.armes.ennemi;

import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationMine;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmeMine extends ArmeEnnemi implements Poolable{
	
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 10;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = 2f;
	public static Pool<ArmeMine> pool = Pools.get(ArmeMine.class);

	@Override
	public TextureRegion getTexture() {		return AnimationMine.getTexture(maintenant);	}
	@Override
	protected float getVitesse() {			return Stats.V_ARME_FRAGMENTEE;	}
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
