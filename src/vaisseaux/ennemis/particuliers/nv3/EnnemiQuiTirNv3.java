package vaisseaux.ennemis.particuliers.nv3;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeBossQuad;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.QuiTir;
import assets.animation.AnimationExplosion1;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EnnemiQuiTirNv3 extends QuiTir {
	
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 13;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 

	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR3)
			return QuiTir.mauvaisEtat;
		return QuiTir.bonEtat;
	}
	
	@Override
	public void reset() {
		pv = Stats.PVMAX_DE_BASE_QUI_TIR;
		super.reset();
	}

	public EnnemiQuiTirNv3() {
		super();
		pv = Stats.PVMAX_DE_BASE_QUI_TIR3;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) || Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.y += (Stats.VITESSE_MAX_DE_BASE_QUI_TIR3 * Endless.delta);
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR3) position.x += (Stats.DERIVE_DE_BASE_QUI_TIR * Endless.delta);
		return true;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficher(SpriteBatch batch) {
		maintenant += Endless.delta;
		if(mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		}
		else	batch.draw(getTexture(pv), position.x, position.y, LARGEUR, HAUTEUR);
	}
	
	@Override
	protected void tir() {
		tir.doubleTirVersBas(this, mort, maintenant, prochainTir);
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
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
