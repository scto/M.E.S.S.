package vaisseaux.ennemis.particuliers.nv1;

import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.SoundMan;
import assets.animation.AnimationExplosion1;
import assets.animation.AnimationRouli;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class ZigZag extends Ennemis {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 9;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (int) (LARGEUR*1.2);
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final float AMPLITUDE_HORIZONTALE = 8f;
	private Vector2 direction;
	private boolean sens = true;
	public static Pool<ZigZag> pool = Pools.get(ZigZag.class);
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
	public void reset() {
		position.x = getRandX();
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		direction.x = 0;
		direction.y = -1;
		mort = false;
		pv = Stats.PVMAX_ZIGZAG;
		sens = true;
	}
	
	public ZigZag() {
		super(getRandX(), CSG.HAUTEUR_ECRAN + ZigZag.HAUTEUR, Stats.PVMAX_ZIGZAG);
		direction = new Vector2(0, -1);
	}
	
	private static float getRandX() {
		if(Math.random() > .5f)			return CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - DEMI_LARGEUR;
		return DEMI_LARGEUR + CSG.LARGEUR_BORD;
	}

	@Override
	protected TextureRegion getTexture() {
		return AnimationRouli.getTexture(position.x + DEMI_LARGEUR);
	}

	@Override
	public boolean mouvementEtVerifSansParticules() {	
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) || !Physique.toujoursAfficher(position, HAUTEUR, LARGEUR)){
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
	public int getXp() {			return CoutsEnnemis.EnnemiZigZag.COUT;	}
	@Override
	public int getHauteur() {		return HAUTEUR;	}
	@Override
	public int getLargeur() {		return LARGEUR;	}
	@Override
	public int getDemiHauteur() {	return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {	return DEMI_LARGEUR;	}
	@Override
	public void invoquer() {		liste.add(pool.obtain());	}
}
