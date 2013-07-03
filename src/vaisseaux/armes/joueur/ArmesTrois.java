package vaisseaux.armes.joueur;

import vaisseaux.armes.Armes;
import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.AssetMan;
import assets.particules.ParticulesArmeTrois;

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
public class ArmesTrois extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static int LARGEUR;
	public static int DEMI_LARGEUR;
	public static final float CADENCETIR = .12f;
	private final int FORCE = 4;
	public static Pool<ArmesTrois> pool = Pools.get(ArmesTrois.class);
//	private float tpsAnimation = 0;
	private float angle = 0;
	// ** ** particules
	private ParticulesArmeTrois particleEffect;
	private Vector2 direction = new Vector2();

	public static void updateDimensions() {
		LARGEUR = CSG.LARGEUR_ECRAN / 30 + (CSG.LARGEUR_ECRAN/100 * CSG.profil.NvArmeTrois);
		DEMI_LARGEUR = LARGEUR/2;
	}
	
	public void init(float posX, float posY, float d, float e, float angle) {
		position.x = posX;
		position.y = posY;
		direction.x = d;
		direction.y = e;
		this.angle = angle;
		liste.add(this);
		initGraphismes();
	}

	@Override
	public void reset() {	}

	@Override
	public void afficher(SpriteBatch batch){
		particleEffect.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR);
		particleEffect.draw(batch, Endless.delta);
		batch.draw(AssetMan.laser , position.x, position.y, DEMI_LARGEUR, DEMI_LARGEUR,	LARGEUR, LARGEUR, 1, 1,	angle, false);
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		batch.draw(AssetMan.laser , position.x, position.y, DEMI_LARGEUR, DEMI_LARGEUR,	LARGEUR, LARGEUR, 1, 1,	angle, false);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_DE_BASE, LARGEUR)) return true;
		free();
		return false;
	}

	@Override
	public int getForce() {		return FORCE + CSG.profil.NvArmeTrois;	}

	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return LARGEUR;	}

	@Override
	public void initGraphismes() {
		if (CSG.profil.particules){
			particleEffect = ParticulesArmeTrois.pool.obtain();
			particleEffect.start();
		}
	}
	
	@Override
	public void free() {
		if (particleEffect != null) particleEffect.free();
		pool.free(this);
	}

	@Override
	public boolean testCollsionAdds() {		return false;	}
	
	@Override
	public Vector2 getDirection(){
		return direction;
	}
}
