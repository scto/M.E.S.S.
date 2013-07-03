package vaisseaux.armes;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationArmeFusee;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ArmeFusee extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 30;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = .2f;
	private static final int FORCE = 2;
	public static Pool<ArmeFusee> pool = Pools.get(ArmeFusee.class);
	private float tpsAnim = 0;
	
	@Override
	public boolean testCollisionVaisseau() {
		return Physique.pointDansVaisseau(position, LARGEUR);
	}
	
	@Override
	public boolean testCollsionAdds() {
		return Physique.testCollisionAdds(position, LARGEUR);
	}
	
	@Override
	public void reset() {
		position.x = 0;
		position.y = 0;
		direction.x = 0;
		direction.y = 0;
		tpsAnim = 0;
	}
	
	public ArmeFusee() {
	}

	@Override
	public void afficher(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.draw(AnimationArmeFusee.getTexture(tpsAnim), position.x, position.y,LARGEUR, LARGEUR);
	}
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.draw(AnimationArmeFusee.getTexture(tpsAnim), position.x, position.y,LARGEUR, LARGEUR);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (AnimationArmeFusee.TPS_ANIM_TOTAL < tpsAnim) {
			ArmeFusee a = ArmeFusee.pool.obtain();
			a.position.x = position.x;
			a.position.y = position.y;
			a.direction.x = direction.x + .09f;
			a.direction.y = direction.y;
			listeTirsDesEnnemis.add(a);
			
			ArmeFusee b = ArmeFusee.pool.obtain();
			b.position.x = position.x;
			b.position.y = position.y;
			b.direction.x = direction.x - .09f;
			b.direction.y = direction.y;
			listeTirsDesEnnemis.add(b);

			pool.free(this);
			return false;
		} else {
			if(Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_FRAG, LARGEUR))	return true;
		}
		pool.free(this);
		return false;
	}

	@Override
	public int getForce() {		return FORCE;	}
	
	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return LARGEUR;	}

	@Override
	public void initGraphismes() {	}
	@Override
	public void free() {		pool.free(this);	}
}
