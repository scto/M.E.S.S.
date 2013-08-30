package vaisseaux.armes.ennemi;

import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationBouleBleu;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class BouleBleu extends ArmeEnnemi {
	
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 30;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static Pool<BouleBleu> pool = Pools.get(BouleBleu.class);
	
	@Override
	public int getLargeur() {				return LARGEUR;	}
	@Override
	public int getHauteur() {				return LARGEUR;	}
	@Override
	public void free() {					pool.free(this);}
	@Override
	protected float getVitesse() {			return Stats.V_ARME_BOULE_BLEU;	}
	@Override
	public float getDemiHauteur() {			return DEMI_LARGEUR;	}
	@Override
	public float getDemiLargeur() {			return DEMI_LARGEUR;	}
	@Override
	public TextureRegion getTexture() {		return AnimationBouleBleu.getTexture(maintenant);	}
}
