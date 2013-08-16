package vaisseaux.ennemis.particuliers.nv3;

import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeBossQuad;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.QuiTir;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class EnnemiQuiTirNv3 extends QuiTir {
	
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 13;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 

	public static Pool<EnnemiQuiTirNv3> pool = Pools.get(EnnemiQuiTirNv3.class);
	
	@Override
	protected void free() {
		pool.free(this);
	}
	
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
	
	@Override
	protected int getPvMax() {
		return Stats.PVMAX_DE_BASE_QUI_TIR3;
	}

	@Override
	protected float getVitesse() {
		return Stats.VITESSE_MAX_DE_BASE_QUI_TIR3;
	} 
	
	@Override
	protected int getDemiPv() {
		return Stats.DEMI_PV_BASE_QUI_TIR3;
	}
	
	@Override
	protected float getDerive() {
		return Stats.DERIVE_DE_BASE_QUI_TIR;
	}
	
	@Override
	protected void tir() {
		tir.doubleTirVersBas(this, mort, maintenant, prochainTir);
	}

	@Override
	public int getXp() {			return CoutsEnnemis.EnnemiDeBaseQuiTirNv3.COUT;	}
	@Override
	public int getHauteur() {		return HAUTEUR;	}
	@Override
	public int getLargeur() {		return LARGEUR;	}
	@Override
	public int getDemiHauteur() {	return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {	return DEMI_LARGEUR;	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		if (numeroTir == 1) tmpPos.x = position.x + DEMI_LARGEUR - ArmeBossQuad.LARGEUR;
		else tmpPos.x = position.x + DEMI_LARGEUR;
		
		tmpPos.y = position.y - ArmeBossQuad.HAUTEUR + ArmeBossQuad.DEMI_HAUTEUR;
		return tmpPos;
	}
}
