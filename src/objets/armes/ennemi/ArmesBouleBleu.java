package objets.armes.ennemi;

import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationBouleBleu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmesBouleBleu extends ArmeEnnemi implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 30;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR;
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static Pool<ArmesBouleBleu> pool = Pools.get(ArmesBouleBleu.class);
	
	@Override
	public TextureRegion getTexture() {		return AnimationBouleBleu.getTexture(maintenant);	}
	@Override
	protected float getVitesse() {			return Stats.V_ARME_BOULE_BLEU;	}
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
}
