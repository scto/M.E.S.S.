package vaisseaux.armes;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.SoundMan;
import assets.animation.AnimationMeteorite;
import assets.particules.ParticulesMeteorite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ArmesFragmentation extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 10;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = .1f;
	private static final int FORCE = 2;
	public static Pool<ArmesFragmentation> pool = Pools.get(ArmesFragmentation.class);
	private float tpsAnim = 0;
	// ** ** variable utilitaire
	private float angle;
	private boolean gauche = true;
	private boolean bas = true;
	private ParticulesMeteorite particleEffect;
	
	@Override
	public boolean testCollisionVaisseau() {
		return Physique.pointDansVaisseau(position, LARGEUR);
	}
	
	@Override
	public boolean testCollsionAdds() {
		return Physique.testCollisionAdds(position, LARGEUR);
	}
	
	@Override
	public void reset() {
		tpsAnim = 0;
	}
	
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.draw(AnimationMeteorite.getTexture(tpsAnim), position.x, position.y, DEMI_LARGEUR, DEMI_LARGEUR, LARGEUR, LARGEUR, 1, 1, angle, false);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (AnimationMeteorite.TPS_ANIM_TOTAL < tpsAnim) {
			SoundMan.playBruitage(SoundMan.explosionPetite);
			// E X P L O S I O N
			for(int i = (int) (Math.random() * 7); i < 10; i++){
				ArmesFragmentee a = ArmesFragmentee.pool.obtain();
				if (gauche){
					if(bas)	a.init(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR, (float)Math.random(), (float)-Math.random());
					else	a.init(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR, (float)Math.random(), (float)Math.random());
				} else {
					if(bas)	a.init(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR, (float)-Math.random(), (float)-Math.random());
					else	a.init(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR, (float)-Math.random(), (float)Math.random());
					bas = !bas;
				}
				gauche = !gauche;
			}
			free();
			return false;
		} else {
			if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_FRAG, LARGEUR))	return true;
		}
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
	public void free() {
		if (particleEffect != null) particleEffect.free();
		pool.free(this);
	}

}
