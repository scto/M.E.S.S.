package vaisseaux.armes;

import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationMeteorite;
import assets.particules.ParticulesMeteorite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ArmesFragmentee extends Armes implements Poolable{
	
	// ** ** caracteristiques gï¿½nï¿½rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 25;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = .1f;
	private static final int FORCE = 2;
	public static Pool<ArmesFragmentee> pool = Pools.get(ArmesFragmentee.class);
	private float tpsAnim = 3;
	// ** ** variable utilitaire
	private float angle;
	private ParticulesMeteorite particleEffect;
	
	public void init(float posX, float posY, float dirX, float dirY) {
		direction.x = dirX;
		direction.y = dirY;
		if (Math.abs(dirX) < .1f) dirX *= 15;
		if (Math.abs(dirY) < .1f) dirY *= 15;
		position.x = posX;
		position.y = posY;
		// Car elle est générée par une arme
		listeTirsDesEnnemis.add(this); 
	}
	
	@Override
	public void reset() {
		tpsAnim = 4;
	}

	@Override
	public boolean testCollisionVaisseau() {
		return Physique.pointDansVaisseau(position, LARGEUR);
	}

	@Override
	public boolean testCollsionAdds() {
		return Physique.testCollisionAdds(position, LARGEUR);
	}
	
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		batch.draw(AnimationMeteorite.getTexture(tpsAnim), position.x, position.y, DEMI_LARGEUR, DEMI_LARGEUR, LARGEUR, LARGEUR, 1, 1, angle, false);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if(Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_FRAGMENTEE, LARGEUR))	return true;
		free();
		return false;
	}

	@Override
	public int getForce() {		return FORCE;	}
	
	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return LARGEUR;	}

	@Override
	public void free() {
		if (particleEffect != null) particleEffect.free();
		pool.free(this);
	}
}
