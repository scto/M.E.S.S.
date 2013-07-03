package vaisseaux.armes.joueur;

import vaisseaux.armes.Armes;
import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationTirFeu;
import assets.particules.ParticulesArmeTraitVert;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme tirant en balyant l'�cran de gauche � droite, d'o� le nom
 * C'est celle qui est verte
 * @author Julien
 *
 */

public class ArmeAdd extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 27, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = LARGEUR * 2, DEMI_HAUTEUR = HAUTEUR / 2; 
	public static float CADENCETIR;
	private static final int FORCE = 5;
	public static Pool<ArmeAdd> pool = Pools.get(ArmeAdd.class);
	// ** ** animation
	private float tpsAnim = 0;
	// ** ** variable utilitaire
	private ParticulesArmeTraitVert particleEffect;
	private Vector2 direction = new Vector2();
	private float angle;
	
	public static void determinerCadenceTir() {	CADENCETIR = 0.4f / CSG.profil.cadenceAdd;	}

	public void init(float x, float y, float angle) {
		position.x = x;
		position.y = y;
		direction.x = 0;
		direction.y = 1;
		direction.rotate(angle + 90);
		position.x += direction.x * 25;
		position.y += direction.y * 25;
		initGraphismes();
		this.angle = angle;
		Armes.liste.add(this);
	}

	@Override
	public void reset() {		tpsAnim = 0;	}

	@Override
	public void afficher(SpriteBatch batch) {
		particleEffect.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
		particleEffect.draw(batch, Endless.delta);
		tpsAnim += Endless.delta;
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.setColor(Color.GREEN);
		batch.draw(AnimationTirFeu.getTexture(tpsAnim), position.x, position.y,	DEMI_LARGEUR,DEMI_HAUTEUR, LARGEUR, HAUTEUR, 1.5f,0.5f, angle, false);
		batch.setColor(Color.WHITE);
	}

	@Override
	public boolean mouvementEtVerif() {
		// 0 pour que l'effet ne disparaisse pas trop vite au lieu de HAUTEUR
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_ADD, HAUTEUR, LARGEUR))		return true;
		free();
		return false;
	}
	
	@Override
	public int getForce() {		return FORCE;	}
	
	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return HAUTEUR;	}

	@Override
	public void initGraphismes() {
		if(CSG.profil.particules){
			particleEffect = ParticulesArmeTraitVert.pool.obtain();
			particleEffect.start();
		}
	}
	
	@Override
	public Vector2 getDirection(){
		return direction;
	}

	@Override
	public void free() {
		if (particleEffect != null) particleEffect.free();
		pool.free(this);
	}

	@Override
	public boolean testCollsionAdds() {		return false;	}
}

