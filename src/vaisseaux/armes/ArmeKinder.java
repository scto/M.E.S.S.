package vaisseaux.armes;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.Anim;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme tirant en balyant l'�cran de gauche � droite, d'o� le nom
 * C'est celle qui est verte
 * @author Julien
 *
 */

public class ArmeKinder extends Armes implements Poolable {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 25;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = .15f;
	private static final int FORCE = 2;
	public static Pool<ArmeKinder> pool = Pools.get(ArmeKinder.class);
	// ** ** animation
	private float tpsAnim = 0;

	@Override
	public void reset() {
		tpsAnim = 0;
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.setColor(Color.CYAN);
		batch.draw(Anim.tirTrois.getTexture(tpsAnim), position.x, position.y, DEMI_LARGEUR,DEMI_LARGEUR, LARGEUR, LARGEUR, 1,1,	angle, false);
		batch.setColor(Color.WHITE);
	}

	@Override
	public boolean mouvementEtVerif() {
		if (Physique.mouvementDeBase(direction, position, -Stats.VITESSE_MAX_ARME_KINDER, LARGEUR)) return true;
		free();
		return false;
	}


	

	
	@Override
	public int getForce() {		return FORCE + CSG.profil.NvArmeBalayage;	}
	
	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return LARGEUR;	}

	@Override
	public void free() {
		
		pool.free(this);
	}
}
