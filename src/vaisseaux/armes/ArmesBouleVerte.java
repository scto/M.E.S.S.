package vaisseaux.armes;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationBouleBleu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * VOIR POUR LA TRANSLATION
 * @author Julien
 *
 */
public class ArmesBouleVerte extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 50;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = .15f;
	private static final int FORCE = 2;
	public static Pool<ArmesBouleVerte> pool = Pools.get(ArmesBouleVerte.class);
	private float tpsAnim = 0;
	// ** ** variable utilitaire
	
	@Override
	public void reset() {	}

	@Override
	public boolean testCollisionVaisseau() {
		return Physique.pointDansVaisseau(position, LARGEUR);
	}
	
	@Override
	public boolean testCollsionAdds() {
		return Physique.testCollisionAdds(position, LARGEUR);
	}

	@Override
	public void afficher(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.setColor(.7f, 1, .7f, 1);
		batch.draw(AnimationBouleBleu.getTexture(tpsAnim), position.x, position.y, DEMI_LARGEUR,DEMI_LARGEUR, LARGEUR, LARGEUR,	1, 1, angle, false);
		batch.setColor(Color.WHITE);
	}
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.setColor(.7f, 1, .7f, 1);
		batch.draw(AnimationBouleBleu.getTexture(tpsAnim), position.x, position.y, DEMI_LARGEUR, DEMI_LARGEUR, LARGEUR, LARGEUR, 1, 1, angle, false);
		batch.setColor(Color.WHITE);
	}
	@Override
	public boolean mouvementEtVerif() {
		if(Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_BOULE_VERTE, LARGEUR))	return true;
		pool.free(this);
		return false;
	}

	@Override
	public int getForce() {		return FORCE + CSG.profil.NvArmeBalayage;	}
	
	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return LARGEUR;	}

	@Override
	public void initGraphismes() {	}
	
	@Override
	public void free() {		pool.free(this);	}
}
