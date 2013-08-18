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
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 9;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (int) (LARGEUR*1.2);
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final float AMPLITUDE_HORIZONTALE = 160f, VITESSE = Stats.VITESSE_MAX_ZIGZAG;
	private Vector2 direction;
	private boolean sens = true;
	public static Pool<ZigZag> pool = Pools.get(ZigZag.class);

	public ZigZag() {
		super();
		direction = new Vector2(0, -VITESSE);
		position.x = getRandX();
	}
	
	@Override
	protected Sound getSonExplosion() {
		return SoundMan.explosiontoupie;
	}
	
	@Override
	public void reset() {
		super.reset();
		position.x = getRandX();
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		direction.x = 0;
		direction.y = -VITESSE;
		sens = true;
	}
	
	@Override
	protected void free() {
		pool.free(this);
	}
	
	@Override
	protected int getPvMax() {
		return Stats.PVMAX_ZIGZAG;
	}
	
	private static float getRandX() {
		if (Math.random() > .5f) {
			return CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - DEMI_LARGEUR;
		}
		return DEMI_LARGEUR + CSG.LARGEUR_BORD;
	}

	@Override
	protected TextureRegion getTexture() {
		return AnimationRouli.getTexture(position.x + DEMI_LARGEUR);
	}

	@Override
	public boolean mouvementEtVerif() {	
		if (!mort)	sens = Physique.goToZigZagCentre(position, direction, DEMI_LARGEUR, sens, AMPLITUDE_HORIZONTALE, HAUTEUR, LARGEUR);
		Physique.mvtSansVerif(position, direction);
		return super.mouvementEtVerif();
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
	
	@Override
	public float getDirectionY() {		return direction.y;	}
	@Override
	public float getDirectionX() {		return direction.x;	}
}
