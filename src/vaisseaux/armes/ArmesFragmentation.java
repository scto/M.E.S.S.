package vaisseaux.armes;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.SoundMan;
import assets.animation.Anim;
import assets.animation.AnimationMeteorite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

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

	

	

	
	@Override
	public void reset() {
		tpsAnim = 0;
	}
	
	@Override
	public void afficher(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.draw(Anim.meteor.getTexture(tpsAnim), position.x, position.y, DEMI_LARGEUR, DEMI_LARGEUR, LARGEUR, LARGEUR, 1, 1, angle, false);
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
		
		pool.free(this);
	}

}
