package objets.armes.ennemi;

import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationBouleBleu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmeBouleTir extends ArmeEnnemi implements Poolable{
	
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 26, DEMI_LARGEUR = LARGEUR/2; 
	public static Pool<ArmeBouleTir> pool = Pools.get(ArmeBouleTir.class);
	
	@Override
	protected float getVitesse() {			return Stats.V_BOULE_BLEU_RAPIDE;	}
	@Override
	public int getLargeur() {				return LARGEUR;	}
	@Override
	public int getHauteur() {				return LARGEUR;	}
	@Override
	public void free() {					pool.free(this);	}
	@Override
	public float getDemiHauteur() {			return DEMI_LARGEUR;	}
	@Override
	public float getDemiLargeur() {			return DEMI_LARGEUR;	}
	@Override
	public TextureRegion getTexture() {		return AnimationBouleBleu.getTexture(maintenant);	}
}
