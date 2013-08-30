package vaisseaux.armes.ennemi;

import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationMeteorite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmesFragmentee extends ArmeEnnemi implements Poolable{
	
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 25;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static Pool<ArmesFragmentee> pool = Pools.get(ArmesFragmentee.class);

	
	public void init(float posX, float posY, float dirX, float dirY) {
		direction.x = dirX;
		direction.y = dirY;
		if (Math.abs(dirX) < .1f) dirX *= 15;
		if (Math.abs(dirY) < .1f) dirY *= 15;
		direction.mul(getVitesse());
		position.x = posX;
		position.y = posY;
		// Car elle est générée par une arme
		listeTirsDesEnnemis.add(this); 
	}
	
	@Override
	public TextureRegion getTexture() {		return AnimationMeteorite.getTexture(maintenant);	}
	@Override
	protected float getVitesse() {		return  Stats.V_ARME_FRAGMENTEE;	}
	@Override
	public int getLargeur() {		return LARGEUR;	}
	@Override
	public int getHauteur() {		return LARGEUR;	}
	@Override
	public void free() {		pool.free(this);	}
	@Override
	public float getDemiHauteur() {		return DEMI_LARGEUR;	}
	@Override
	public float getDemiLargeur() {		return DEMI_LARGEUR;	}
}
