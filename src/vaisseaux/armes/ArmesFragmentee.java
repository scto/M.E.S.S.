package vaisseaux.armes;

import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.Anim;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

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
	public void afficherSansParticules(SpriteBatch batch) {
		batch.draw(Anim.meteor.getTexture(tpsAnim), position.x, position.y, DEMI_LARGEUR, DEMI_LARGEUR, LARGEUR, LARGEUR, 1, 1, angle, false);
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
		
		pool.free(this);
	}
}
