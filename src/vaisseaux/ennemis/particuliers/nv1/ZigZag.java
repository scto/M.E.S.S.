package vaisseaux.ennemis.particuliers.nv1;

import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.SoundMan;
import assets.animation.AnimationRouli;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class ZigZag extends Ennemis {
	
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 9, DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (int) (LARGEUR*1.2), DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final float AMPLITUDE_HORIZONTALE = 160f, VITESSE = Stats.V_ENN_ZIGZAG;
	private Vector2 direction;
	private boolean sens = true;
	public static Pool<ZigZag> pool = Pools.get(ZigZag.class);

	public ZigZag() {
		super();
		position.x = getRandX();
		direction = new Vector2(0, -getVitesse());
	}
	
	@Override
	public void reset() {
		super.reset();
		position.x = getRandX();
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		direction.x = 0;
		direction.y = -getVitesse();
		sens = true;
	}
	
	private static float getRandX() {
		if (Math.random() > .5f) {
			return CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - DEMI_LARGEUR;
		}
		return DEMI_LARGEUR + CSG.LARGEUR_BORD;
	}

	@Override
	public boolean mouvementEtVerif() {	
		if (!mort)	sens = Physique.goToZigZagCentre(position, direction, DEMI_LARGEUR, sens, AMPLITUDE_HORIZONTALE, HAUTEUR, LARGEUR);
		Physique.mvtSansVerif(position, direction);
		return super.mouvementEtVerif();
	}

	@Override
	protected void free() {					pool.free(this);	}
	@Override
	protected int getPvMax() {				return Stats.PV_ZIGZAG;	}
	@Override
	protected TextureRegion getTexture() {	return AnimationRouli.getTexture(position.x + DEMI_LARGEUR);	}
	@Override
	public int getXp() {					return CoutsEnnemis.EnnemiZigZag.COUT;	}
	@Override
	public int getHauteur() {				return HAUTEUR;	}
	@Override
	public int getLargeur() {				return LARGEUR;	}
	@Override
	public int getDemiHauteur() {			return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {			return DEMI_LARGEUR;	}
	@Override
	public void invoquer() {				LISTE.add(pool.obtain());	}
	@Override
	public float getDirectionY() {			return direction.y;	}
	@Override
	public float getDirectionX() {			return direction.x;	}
	@Override
	protected float getVitesse() {			return VITESSE;	}
	@Override
	protected Sound getSonExplosion() {		return SoundMan.explosiontoupie;	}
}
