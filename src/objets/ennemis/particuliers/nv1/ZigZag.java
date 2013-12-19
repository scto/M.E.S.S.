package objets.ennemis.particuliers.nv1;

import objets.Positionnement;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import jeu.CSG;
import jeu.Physique;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationRouli;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class ZigZag extends Ennemis {
	
	private static final int LARGEUR = Stats.LARGEUR_ZIG_ZAG, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = Stats.HAUTEUR_ZIG_ZAG, DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final float AMPLITUDE_HORIZONTALE = 160f, VITESSE = Stats.V_ENN_ZIGZAG;
	private Vector2 direction = new Vector2(0, -getVitesse());
	private boolean sens = true;
	public static final Pool<ZigZag> POOL = Pools.get(ZigZag.class);

	public ZigZag() {
		super();
		position.x = Positionnement.getGaucheDroite(DEMI_LARGEUR);
	}
	
	@Override
	public void reset() {
		super.reset();
		position.x = Positionnement.getGaucheDroite(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		direction.x = 0;
		direction.y = -getVitesse();
		sens = true;
	}
	
	@Override
	public boolean mouvementEtVerif() {	
		if (!mort)	sens = Physique.goToZigZagCentre(position, direction, DEMI_LARGEUR, sens, AMPLITUDE_HORIZONTALE, HAUTEUR, LARGEUR);
		Physique.mvtSansVerif(position, direction);
		return super.mouvementEtVerif();
	}

	@Override
	protected void free() {					POOL.free(this);	}
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
	public void invoquer() {				LISTE.add(POOL.obtain());	}
	@Override
	public float getDirectionY() {			return direction.y;	}
	@Override
	public float getDirectionX() {			return direction.x;	}
	@Override
	protected float getVitesse() {			return VITESSE;	}
	@Override
	protected Sound getSonExplosion() {		return SoundMan.explosiontoupie;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
