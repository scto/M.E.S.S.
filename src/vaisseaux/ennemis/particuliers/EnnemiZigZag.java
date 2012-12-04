package vaisseaux.ennemis.particuliers;

import physique.Physique;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import menu.CSG;
import affichage.animation.AnimationExplosion1;
import affichage.animation.AnimationRouli;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiZigZag extends Ennemis{
	
	// ** ** caracteristiques générales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 9;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (int) (LARGEUR*1.2);
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final int VITESSE_MAX = 80;
	public static final long CADENCETIR = 800;
	private static final float AMPLITUDE_HORIZONTALE = 8f;
	public static final int PVMAX = 7;
	static final double chancePowerUp = 0.02;
	private Vector2 direction;
	private AnimationRouli animation;
	private AnimationExplosion1 animationExplosion;
	protected float tpsAnimationExplosion;
	//private static final int VALEUR = 1;
	// ** ** caracteristiques variables. 
	// ** ** variables utilitaires.
	private boolean sens = true;
	public static Pool<EnnemiZigZag> pool = Pools.get(EnnemiZigZag.class);

	@Override
	public void reset() {
		position.x = getRandX();
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		direction.x = 0;
		direction.y = -1;
		mort = false;
		tpsAnimationExplosion = 0;
		pv = PVMAX;
		sens = true;
	}
	
	public EnnemiZigZag() {
		super(getRandX(), CSG.HAUTEUR_ECRAN + EnnemiZigZag.HAUTEUR, 0,-1, PVMAX);
		init();
	}

	
	private static float getRandX() {
		if(Math.random() > .5f)
			return CSG.LARGEUR_ECRAN-EnnemiZigZag.LARGEUR;
		return DEMI_LARGEUR;
	}

	public EnnemiZigZag(float posX, float posY, float dirX, float dirY){
		super(posX, posY, dirX, dirY, PVMAX);
		init();
	}

	private void init() {
		direction = new Vector2(0, -1);
		animation = new AnimationRouli();
		animation.setV(this);
		animationExplosion = new AnimationExplosion1();
	}

	@Override
	public void afficher(SpriteBatch batch, float delta) {
		if(mort){
			batch.draw(animationExplosion.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += delta;
		}
		else batch.draw(animation.getTexture(), position.x, position.y, LARGEUR, HAUTEUR);
	}

	@Override
	public boolean mouvementEtVerif(float delta) {	
		if(mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1){
			pool.free(this);
			return false;
		}
		sens = Physique.goToZigZagCentre(position, direction, DEMI_LARGEUR, sens, AMPLITUDE_HORIZONTALE, VITESSE_MAX, HAUTEUR, LARGEUR, mort, delta);
		if(!Physique.toujoursAfficher(position, HAUTEUR, LARGEUR)){
			pool.free(this);
			return false;
		}
		return true;
	}
	
	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}
	

	@Override
	public int getXp() {
		return TypesEnnemis.EnnemiZigZag.COUT;
	}
	
	@Override
	public int getHauteur() {
		return HAUTEUR;
	}

	@Override
	public int getLargeur() {
		return LARGEUR;
	}

	@Override
	public int getVitesse() {
		return VITESSE_MAX;
	}
	
	@Override
	public int getDemiHauteur() {
		return DEMI_HAUTEUR;
	}

	@Override
	public int getDemiLargeur() {
		return DEMI_LARGEUR;
	}
}
