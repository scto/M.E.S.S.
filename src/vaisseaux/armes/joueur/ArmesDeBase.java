package vaisseaux.armes.joueur;

import vaisseaux.armes.Armes;
import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationTirFeu;
import assets.particules.ParticulesArmeDeBase;

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
public class ArmesDeBase extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static int LARGEUR;
	public static int DEMI_LARGEUR;
	public static int HAUTEUR;
	public static int DEMI_HAUTEUR; 
	public static final float CADENCETIR = .11f;
	private final int FORCE = 8;
	public static Pool<ArmesDeBase> pool = Pools.get(ArmesDeBase.class);
	private float tpsAnimation = 0;
	// ** ** particules
	public ParticulesArmeDeBase particleEffect;
//	private float dirY = 1;
	private Vector2 direction = new Vector2(0,1);
	
	public static void updateDimensions() {
		LARGEUR = CSG.LARGEUR_ECRAN / 30 + (CSG.LARGEUR_ECRAN/100 * CSG.profil.NvArmeDeBase);
		DEMI_LARGEUR = LARGEUR/2;
		HAUTEUR = (int) (LARGEUR * 1.5);
		DEMI_HAUTEUR = HAUTEUR / 2;
	}
	
	/**
	 * ATTENTION ici le init s'occupe d'ajouter � la bonne liste
	 */
	public void init(float posX, float posY) {
		position.x = posX;
		position.y = posY;
		liste.add(this);
		initGraphismes();
	}

	@Override
	public void reset() {	}

	@Override
	public void afficher(SpriteBatch batch){
		particleEffect.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
		particleEffect.draw(batch, Endless.delta);
		tpsAnimation += Endless.delta;
		batch.draw(AnimationTirFeu.getTexture(tpsAnimation) , position.x, position.y, LARGEUR, HAUTEUR);
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnimation += Endless.delta;
		batch.draw(AnimationTirFeu.getTexture(tpsAnimation) , position.x, position.y, LARGEUR, HAUTEUR);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_DE_BASE, HAUTEUR, LARGEUR)) return true;
		free();
		return false;
	}

	@Override
	public int getForce() {		return FORCE + CSG.profil.NvArmeDeBase;	}

	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return HAUTEUR;	}

	@Override
	public void initGraphismes() {
		if(CSG.profil.particules){
			particleEffect = ParticulesArmeDeBase.pool.obtain();
			particleEffect.start();
		}
	}
	@Override
	public void free() {
		if (particleEffect != null) particleEffect.free();
		pool.free(this);
	}

	@Override
	public boolean testCollsionAdds() {	return false;	}
	
	@Override
	public Vector2 getDirection(){		return direction;	}


}
