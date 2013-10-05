package objets.armes.ennemi;

import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationArmeFusee;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ArmeFusee extends ArmeEnnemi implements Poolable{
	
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 30;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = .2f;
	public static Pool<ArmeFusee> pool = Pools.get(ArmeFusee.class);

	@Override
	public boolean mouvementEtVerif() {
		if (AnimationArmeFusee.TPS_ANIM_TOTAL < maintenant) {
			ArmeFusee a = ArmeFusee.pool.obtain();
			a.position.x = position.x;
			a.position.y = position.y;
			a.direction.x = direction.x;
			a.direction.y = direction.y;
			a.direction.rotate(5);
			listeTirsDesEnnemis.add(a);
			
			ArmeFusee b = ArmeFusee.pool.obtain();
			b.position.x = position.x;
			b.position.y = position.y;
			b.direction.x = direction.x;
			b.direction.y = direction.y;
			b.direction.rotate(-5);
			listeTirsDesEnnemis.add(b);

			return false;
		} 
		return super.mouvementEtVerif();
	}

	@Override
	public TextureRegion getTexture() {	return AnimationArmeFusee.getTexture(maintenant);	}
	@Override
	protected float getVitesse() {		return Stats.V_ARME_FRAG;	}
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
