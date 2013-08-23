package vaisseaux.armes;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.Anim;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmeMine extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 10;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = 2f;
	private static final int FORCE = 2;
	public static Pool<ArmeMine> pool = Pools.get(ArmeMine.class);
	private float tpsAnim = 3;

	
	@Override
	public void reset() {
		tpsAnim = 4;
	}
	
	@Override
	public void afficher(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.draw(Anim.mine.getTexture(tpsAnim), position.x, position.y, DEMI_LARGEUR,DEMI_LARGEUR, LARGEUR, LARGEUR, 1,1,	angle, false);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (direction.x > 0) direction.x -= Endless.delta / 10;
		else if (direction.x < 0) direction.x += Endless.delta / 10;
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_FRAGMENTEE, LARGEUR, LARGEUR))	return true;
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
	public void free() {		pool.free(this);	}
}
