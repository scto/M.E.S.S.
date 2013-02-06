package vaisseaux.armes;

import java.util.Vector;

import menu.CSG;
import physique.Physique;
import affichage.animation.AnimationBouleBleu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ArmesBouleVerte extends Armes implements Poolable{
	
	// ** ** caracteristiques générales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 50;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final int VITESSE_MAX = 140;
	public static final float CADENCETIR = .1f;
	private static final int FORCE = 2;
	public static Pool<ArmesBouleVerte> pool = Pools.get(ArmesBouleVerte.class);
	private static AnimationBouleBleu anim = new AnimationBouleBleu();
	private float tpsAnim = 0;
	// ** ** variable utilitaire
	private float angle;
	private Vector2 translation = new Vector2(0, 0);
	private static Sound son = Gdx.audio.newSound(Gdx.files.internal("sons/144887__willfitch1__energy-weapon.wav"));
	
	/**
	 * Ca tir dans le bon angle
	 * @param centreX
	 * @param translationX
	 * @param centreY
	 * @param translationY
	 * @param angle
	 */
	public void init(float centreX, float translationX, float centreY, float translationY, float angle) {
		this.angle = angle + 90;
		direction.x = 1;
		direction.y = 0;
		direction.rotate(angle);
		translation.x = translationX;
		translation.y = translationY;
		translation.rotate(this.angle);
		position.x = centreX + translation.x;
		position.y = centreY + translation.y;
		Armes.listeTirsDesEnnemis.add(this);
		son.play(CSG.profil.volumeArme);
	}
	
	@Override
	public void reset() {
		tpsAnim = 0;
	}
	
	public ArmesBouleVerte() {
	}

	@Override
	public void afficher(SpriteBatch batch, float delta) {
		tpsAnim += delta;
		batch.setColor(.7f, 1, .7f, 1);
		batch.draw(anim.getTexture(tpsAnim), position.x, position.y,
		// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
		DEMI_LARGEUR,DEMI_HAUTEUR,
		// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
		LARGEUR, HAUTEUR,
		//scaleX the scale of the rectangle around originX/originY in x ET Y
		1,1,
		// L'ANGLE DE ROTATION
		angle,
		//FLIP OU PAS
		false);
		batch.setColor(Color.WHITE);
	}

	@Override
	public boolean mouvementEtVerif(float delta) {
		return Physique.mouvementDeBase(direction, position, VITESSE_MAX, HAUTEUR, LARGEUR, delta);
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}
	
	@Override
	public int getForce() {
		return FORCE + CSG.profil.NvArmeBalayage;
	}
	
	@Override
	public int getLargeur() {
		return LARGEUR;
	}

	@Override
	public int getHauteur() {
		return HAUTEUR;
	}

	/**
	 * A voir si il faut pas la virer
	 * @param posX
	 * @param posY
	 * @param dirX
	 * @param dirY
	 * @param ennemi
	 */
	@Override
	public void init(float posX, float posY, int dirX, int dirY, boolean ennemi) {
		
	}

	@Override
	public void free() {
		pool.free(this);
	}
}
