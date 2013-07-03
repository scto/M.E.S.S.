package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import assets.SoundMan;
import assets.animation.AnimationExplosion1;
import assets.animation.AnimationRouli;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * A voir pour virer le vecteur direction vu qu'il utilise juste le x
 * @author Julien
 */
public class EnnemiZigZag extends Ennemis{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 9;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (int) (LARGEUR*1.2);
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final float AMPLITUDE_HORIZONTALE = 8f;
	private Vector2 direction;
	protected float tpsAnimationExplosion;
	private boolean sens = true;
	public static Pool<EnnemiZigZag> pool = Pools.get(EnnemiZigZag.class);
	// ** ** particules
	private ParticulesExplosionPetite explosion;

	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosiontoupie);
		if (CSG.profil.particules) {
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
			explosion.start();
		} else {
			tpsAnimationExplosion = 0;
		}
	}
	
	@Override
	public void init() {
		if (CSG.profil.particules && explosion == null) explosion = ParticulesExplosionPetite.pool.obtain();
		else tpsAnimationExplosion = 0;
	}
	
	@Override
	public void reset() {
		position.x = getRandX();
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		direction.x = 0;
		direction.y = -1;
		mort = false;
		pv = Stats.PVMAX_ZIGZAG;
		sens = true;
	}
	
	public EnnemiZigZag() {
		super(getRandX(), CSG.HAUTEUR_ECRAN + EnnemiZigZag.HAUTEUR, Stats.PVMAX_ZIGZAG);
		direction = new Vector2(0, -1);
	}

	
	private static float getRandX() {
		if(Math.random() > .5f)			return CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - DEMI_LARGEUR;
		return DEMI_LARGEUR + CSG.LARGEUR_BORD;
	}


	@Override
	public void afficher(SpriteBatch batch) {
		if(mort){
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
			explosion.draw(batch, Endless.delta);
		}
		else
			batch.draw(AnimationRouli.getTexture(position.x + DEMI_LARGEUR), position.x, position.y, LARGEUR, HAUTEUR);
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if(mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		}
		else
			batch.draw(AnimationRouli.getTexture(position.x + DEMI_LARGEUR), position.x, position.y, LARGEUR, HAUTEUR);
	}
	@Override
	public boolean mouvementEtVerif() {	
		if( (mort && explosion.isComplete()) | !Physique.toujoursAfficher(position, HAUTEUR, LARGEUR)){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		if(!mort)	sens = Physique.goToZigZagCentre(position, direction, DEMI_LARGEUR, sens, AMPLITUDE_HORIZONTALE, Stats.VITESSE_MAX_ZIGZAG, HAUTEUR, LARGEUR, mort);
		else 		Physique.goToZigZagCentre(position, direction, DEMI_LARGEUR, sens, AMPLITUDE_HORIZONTALE, Stats.VITESSE_MAX_ZIGZAG, HAUTEUR, LARGEUR, mort);
		return true;
	}
	@Override
	public boolean mouvementEtVerifSansParticules() {	
		if( (mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) | !Physique.toujoursAfficher(position, HAUTEUR, LARGEUR)){
			pool.free(this);
			return false;
		}
		if(!mort)	sens = Physique.goToZigZagCentre(position, direction, DEMI_LARGEUR, sens, AMPLITUDE_HORIZONTALE, Stats.VITESSE_MAX_ZIGZAG, HAUTEUR, LARGEUR, mort);
		else 		Physique.goToZigZagCentre(position, direction, DEMI_LARGEUR, sens, AMPLITUDE_HORIZONTALE, Stats.VITESSE_MAX_ZIGZAG, HAUTEUR, LARGEUR, mort);
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
	public int getDemiHauteur() {
		return DEMI_HAUTEUR;
	}

	@Override
	public int getDemiLargeur() {
		return DEMI_LARGEUR;
	}
}
