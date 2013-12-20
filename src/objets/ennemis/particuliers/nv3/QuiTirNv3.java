package objets.ennemis.particuliers.nv3;

import objets.armes.ennemi.BouleFeu;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.QuiTir;
import jeu.Stats;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class QuiTirNv3 extends QuiTir {
	
	public static final int LARGEUR = Stats.LARGEUR_QUI_TIR3, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = Stats.HAUTEUR_QUI_TIR3, DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Pool<QuiTirNv3> POOL = Pools.get(QuiTirNv3.class);
	
	@Override
	protected void free() {				POOL.free(this);	}
	@Override
	public void invoquer() {			LISTE.add(POOL.obtain());	}
	@Override
	protected float getVitesse() {		return Stats.V_ENN_MAX_QUI_TIR3;	} 
	@Override
	protected void tir() {				tir.doubleTirVersBas(this, mort, maintenant, prochainTir);	}
	@Override
	public int getXp() {				return CoutsEnnemis.DE_BASE_QUI_TIR_3.COUT;	}
	@Override
	public int getHauteur() {			return HAUTEUR;	}
	@Override
	public int getLargeur() {			return LARGEUR;	}
	@Override
	public int getDemiHauteur() {		return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}
	@Override
	protected String getLabel() {		return getClass().toString();	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		if (numeroTir == 1) TMP_POS.x = position.x + DEMI_LARGEUR - BouleFeu.LARGEUR;
		else TMP_POS.x = position.x + DEMI_LARGEUR;
		
		TMP_POS.y = position.y - BouleFeu.HAUTEUR + BouleFeu.DEMI_HAUTEUR;
		return TMP_POS;
	}
}
