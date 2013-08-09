package vaisseaux.armes.joueur;

import vaisseaux.armes.Armes;
import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationTirTrois;
import assets.particules.ParticulesArmeHantee;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme de base qui fait une boule de feu
 * @author Julien
 *
 */
public class ArmeHantee extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static int LARGEUR;
	public static int DEMI_LARGEUR;
	public static final float CADENCETIR = .05f;
	private final int FORCE = 2;
	public static Pool<ArmeHantee> pool = Pools.get(ArmeHantee.class);
	private float tpsAnimation = 0;
	// ** ** particules
	public ParticulesArmeHantee particleEffect;
	private Vector2 direction = new Vector2();
	private static boolean alterner = false;
	
	public static void updateDimensions() {
		LARGEUR = CSG.LARGEUR_ECRAN / 30 + (CSG.LARGEUR_ECRAN/100 * CSG.profil.NvArmeHantee);
		DEMI_LARGEUR = LARGEUR/2;
	}
	
	public void init(float posX, float posY) {
		position.x = posX;
		position.y = posY;
		liste.add(this);
		initGraphismes();
		direction.x = 0;
		direction.y = 1;
		tpsAnimation = 0;
	}

	@Override
	public void reset() {	}

	@Override
	public void afficher(SpriteBatch batch){
		particleEffect.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR);
		particleEffect.draw(batch, Endless.delta);
		batch.draw(AnimationTirTrois.getTexture(tpsAnimation) , position.x, position.y, LARGEUR, LARGEUR);
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		batch.draw(AnimationTirTrois.getTexture(tpsAnimation) , position.x, position.y, LARGEUR, LARGEUR);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		tpsAnimation += Endless.delta;
		if (tpsAnimation > .4f & direction.y != 0) {
			if (alterner) direction.x = -1;
			else direction.x = 1;
			direction.y = 0;
			alterner = !alterner;
		}
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_HANTEE, LARGEUR)) return true;
		free();
		return false;
	}

	@Override
	public int getForce() {		return FORCE + CSG.profil.NvArmeHantee;	}

	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return LARGEUR;	}

	@Override
	public void initGraphismes() {
		if(CSG.profil.particules){
			particleEffect = ParticulesArmeHantee.pool.obtain();
			particleEffect.start();
		}
	}
	@Override
	public void free() {
		if (particleEffect != null) particleEffect.free();
		pool.free(this);
	}

	@Override
	public boolean testCollsionAdds() {
		return false;
	}
	
	@Override
	public Vector2 getDirection(){
		return direction;
	}
}
