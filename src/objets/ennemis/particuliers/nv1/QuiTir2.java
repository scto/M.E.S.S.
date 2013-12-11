package objets.ennemis.particuliers.nv1;

import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.ennemi.ArmeFusee;
import objets.armes.typeTir.Tireur;
import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import jeu.CSG;
import jeu.Stats;
import assets.animation.AnimationQuiTir;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class QuiTir2 extends QuiTir implements Tireur {
	
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 9, DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR, DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Tirs TIR = new Tirs(0.7f);
	public static Pool<QuiTir2> pool = Pools.get(QuiTir2.class);

	@Override
	protected void free() {				pool.free(this);	}
	@Override
	protected int getPvMax() {			return Stats.PV_DE_BASE_QUI_TIR2;	}
	@Override
	protected int getDemiPv() {			return Stats.DEMI_PV_BASE_QUI_TIR2;}
	@Override
	protected float getVitesse() {		return Stats.V_ENN_DE_BASE_QUI_TIR2;	}
	@Override
	protected float getDerive() {		return Stats.DERIVE_DE_BASE_QUI_TIR2;	};
	@Override
	protected void tir() {				TIR.tirVersBas(this, mort, maintenant, prochainTir);	}
	@Override
	public int getXp() {				return CoutsEnnemis.EnnemiQuiTir2.COUT;	}
	@Override
	public int getHauteur() {			return HAUTEUR;	}
	@Override
	public int getLargeur() {			return LARGEUR;	}
	@Override
	public int getDemiHauteur() {		return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}
	@Override
	public ArmeEnnemi getArme() {		return ArmeFusee.pool.obtain();	}
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}
	@Override
	public float getModifVitesse() {	return 1;	}
	@Override
	public void invoquer() {			LISTE.add(pool.obtain());	}
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = position.x + DEMI_LARGEUR - ArmeFusee.DEMI_LARGEUR;
		TMP_POS.y = position.y - ArmeFusee.LARGEUR;
		return TMP_POS;
	}
	@Override
	public TextureRegion getTexture() {		return AnimationQuiTir.getTexture(pv);	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
