package vaisseaux.armes;

import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme de base qui fait une boule de feu
 * @author Julien
 *
 */
public class ArmeLaser extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 23;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = .72f;
	private final int FORCE = 4;
	public static Pool<ArmeLaser> pool = Pools.get(ArmeLaser.class);
	
	@Override
	public void reset() {	}
	
	@Override
	public boolean testCollisionVaisseau() {
		return Physique.pointDansVaisseau(position, LARGEUR);
	}
	
	@Override
	public boolean testCollsionAdds() {
		return Physique.testCollisionAdds(position, LARGEUR);
	}

	@Override
	public void afficher(SpriteBatch batch){
		batch.draw(AssetMan.laserVert , position.x, position.y,	DEMI_LARGEUR,DEMI_LARGEUR, LARGEUR, LARGEUR, 1,1, angle, false);
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		batch.draw(AssetMan.laserVert , position.x, position.y,	DEMI_LARGEUR,DEMI_LARGEUR, LARGEUR, LARGEUR, 1,1, angle, false);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_LASER, LARGEUR)) return true;
		free();
		return false;
	}

	@Override
	public int getForce() {		return FORCE;	}

	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return LARGEUR;	}

	@Override
	public void initGraphismes() {	}
	
	@Override
	public void free() {		pool.free(this);	}
}
