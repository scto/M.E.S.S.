package objets.ennemis.particuliers;

import objets.ennemis.Ennemis;
import jeu.EndlessMode;
import menu.CSG;
import assets.AssetMan;
import assets.SoundMan;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * Ennemi de base, g�re son pool, va tout droit et ni tire pas.
 * @author Julien
 *
 */
public class Rocher extends Ennemis{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR_DE_BASE = CSG.LARGEUR_ECRAN / 10;
	public int largeur;
	public int demiLargeur; 
	public static Pool<Rocher> pool = Pools.get(Rocher.class);
	// ** ** animations
	private float angle;
	private float vitesse;

	@Override
	protected int getPvMax() {
		return pv = largeur * 15;
	}
	
	@Override
	protected void free() {
		pool.free(this);
	}
	
	private void initDimensionsEtPosition() {
		largeur = (int) ((Math.random() + .5) * LARGEUR_DE_BASE);
		demiLargeur = largeur / 2;
		angle = largeur;
		position.x = getRandX();
		position.y = CSG.HAUTEUR_ECRAN + largeur;
		vitesse = (CSG.DIXIEME_HAUTEUR - demiLargeur);
	}

	@Override
	protected Sound getSonExplosion() {
		return SoundMan.explosiontoupie;
	}

	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public Rocher() {
		super();
		initDimensionsEtPosition();
	}
	
	private float getRandX() {
		if (Math.random() > .5f)			return CSG.LARGEUR_ZONE_JEU - largeur;
		return 0;
	}

	@Override
	public void reset() {
		initDimensionsEtPosition();
		super.reset();
	}
	
	@Override
	protected TextureRegion getTexture() {
		return AssetMan.rocher;
	}
	
	@Override
	protected float getAngle() {
		return angle;
	}
	
	@Override
	public boolean mouvementEtVerif() {
		angle += vitesse * EndlessMode.delta;
		position.y -= (vitesse * EndlessMode.delta);
		return super.mouvementEtVerif();
	}

	@Override
	public int getXp() {
		return ((largeur / CSG.DIXIEME_LARGEUR)*3) + 1;
	}

	@Override
	public int getHauteur() {
		return largeur;
	}

	@Override
	public int getLargeur() {
		return largeur;
	}
	
	@Override
	public int getDemiHauteur() {
		return demiLargeur;
	}

	@Override
	public int getDemiLargeur() {
		return demiLargeur;
	}
	
	@Override
	public void invoquer() {
		LISTE.add(pool.obtain());
	}
	
	@Override
	public float getDirectionY() {		return -vitesse;	}
}
