package vaisseaux.ennemis.particuliers.nv1;

import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeFusee;
import vaisseaux.armes.Armes;
import vaisseaux.armes.typeTir.Tireur;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class QuiTir2 extends QuiTir implements Tireur {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 9;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Tirs TIR = new Tirs(0.7f);
	// ** ** caracteristiques variables.
	private float prochainTir = .1f;
	public static Pool<QuiTir2> pool = Pools.get(QuiTir2.class);
	// Animation
	public static AtlasRegion bonEtat;
	public static AtlasRegion mauvaisEtat;

	@Override
	protected void free() {
		pool.free(this);
	}
	
	@Override
	protected int getPvMax() {		return Stats.PVMAX_DE_BASE_QUI_TIR2;	}

	protected int getDemiPv() {return Stats.DEMI_PV_BASE_QUI_TIR2;}
	
	
	@Override
	protected float getVitesse() {		return Stats.VITESSE_MAX_DE_BASE_QUI_TIR2;	}
	
	@Override
	protected float getDerive() {		return Stats.DERIVE_DE_BASE_QUI_TIR2;	};
	
	@Override
	protected void tir() {
		TIR.tirVersBas(this, mort, maintenant, prochainTir);
	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiQuiTir2.COUT;	}
	
	@Override
	public int getHauteur() {		return HAUTEUR;	}

	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getDemiHauteur() {		return DEMI_HAUTEUR;	}

	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}
	
	@Override
	public Armes getArme() {			return ArmeFusee.pool.obtain();	}
	
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}

	@Override
	public float getModifVitesse() {	return 1;	}

	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = position.x + DEMI_LARGEUR - ArmeFusee.DEMI_LARGEUR;
		tmpPos.y = position.y - ArmeFusee.LARGEUR;
		return tmpPos;
	}

	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
}
