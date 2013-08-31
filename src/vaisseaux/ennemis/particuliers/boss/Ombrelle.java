package vaisseaux.ennemis.particuliers.boss;

import menu.CSG;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.Ennemis;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Ombrelle extends Ennemis {
	
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 3, DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR, DEMI_HAUTEUR = HAUTEUR / 2;
	public static final float CADENCE = 0.2f;
	public static final Tirs tir = new Tirs(CADENCE);
	private Vector2 direction = new Vector2();
	protected float prochainTir = .1f;
	public static Pool<Ombrelle> pool = Pools.get(Ombrelle.class);

	@Override
	protected int getPvMax() {
		return 500;
	}
	@Override
	public int getXp() {
		return 0;
	}
	@Override
	public int getHauteur() {			return HAUTEUR;	}
	@Override
	public int getLargeur() {			return LARGEUR;	}
	@Override
	public int getDemiHauteur() {		return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}
	@Override
	public void invoquer() { liste.add(pool.obtain());	}
	@Override
	protected void free() { pool.free(this);	}
	@Override
	public float getDirectionY() {		return direction.y;	}
}
