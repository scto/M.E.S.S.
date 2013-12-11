package objets.armes.ennemi;

import jeu.CSG;
import jeu.Stats;
import assets.animation.AnimationMeteorite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmesFragmentee extends ArmeEnnemi implements Poolable{
	
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 25;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static Pool<ArmesFragmentee> pool = Pools.get(ArmesFragmentee.class);
	private static final float MIN = .6f;
	
	public void init(float posX, float posY, float dirX, float dirY) {		
		// pas trop lent
		
		if (dirX > 0 && dirX < MIN) dirX += MIN;
		if (dirX < 0 && dirX > -MIN) dirX -= MIN;
		
		if (dirY > 0 && dirY < MIN) dirY += MIN;
		if (dirY < 0 && dirY > -MIN) dirY -= MIN;
		
		direction.x = dirX;
		direction.y = dirY;
		
		direction.mul(getVitesse());
		position.x = posX;
		position.y = posY;
		// Car elle est g�n�r�e par une arme
		listeTirsDesEnnemis.add(this); 
	}
	
	@Override
	public TextureRegion getTexture() {	return AnimationMeteorite.getTexture(1);	}
	@Override
	protected float getVitesse() {		return Stats.V_ARME_FRAGMENTEE;	}
	@Override
	public int getLargeur() {			return LARGEUR;	}
	@Override
	public int getHauteur() {			return LARGEUR;	}
	@Override
	public void free() {				pool.free(this);	}
	@Override
	public float getDemiHauteur() {		return DEMI_LARGEUR;	}
	@Override
	public float getDemiLargeur() {		return DEMI_LARGEUR;	}
}
