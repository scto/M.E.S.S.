package objets.armes.ennemi;

import jeu.CSG;
import jeu.Stats;
import assets.animation.AnimationBouleBleu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmeBossMine extends ArmeEnnemi implements Poolable{
	
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 30, DEMI_LARGEUR = LARGEUR / 2, HAUTEUR = (int) (LARGEUR * 1.5), DEMI_HAUTEUR = HAUTEUR / 2; 
	public static Pool<ArmeBossMine> pool = Pools.get(ArmeBossMine.class);

	public ArmeBossMine() {					angle = 90;	}

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
	public TextureRegion getTexture() {		return AnimationBouleBleu.getTexture(1);	}
}
