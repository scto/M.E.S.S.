package vaisseaux.armes.ennemi;

import jeu.Stats;
import menu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmeBouleEnergie extends ArmeEnnemi implements Poolable{
	
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 23, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = LARGEUR + DEMI_LARGEUR, DEMI_HAUTEUR = HAUTEUR / 2; 
	public static Pool<ArmeBouleEnergie> pool = Pools.get(ArmeBouleEnergie.class);

	@Override
	protected float getVitesse() {			return Stats.V_ARME_RAISIN;	}
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
	public TextureRegion getTexture() {		return AssetMan.boulenergiebleu;	}
}
