package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import menu.CSG;
import physique.Physique;
import vaisseaux.armes.ArmesDeBase;
import vaisseaux.ennemis.TypesEnnemis;
import affichage.animation.AnimationExplosion1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiDeBaseQuiTir extends EnnemiDeBase{
	
	// ** ** caracteristiques générales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 8;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR/2;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final int VITESSE_MAX = -100;
	public static final float CADENCETIR = 1.2f;
	public static final int PVMAX = 12;
	// ** ** caracteristiques variables.
	private float dernierTir = .1f;
	private float maintenant = 0;
	public static Pool<EnnemiDeBaseQuiTir> pool = Pools.get(EnnemiDeBaseQuiTir.class);
//	private ParticleEffect particleEffect = new ParticleEffect();
	
	@Override
	public void reset() {
		position.x = getRandX();
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		mort = false;
		tpsAnimationExplosion = 0;
		pv = PVMAX;
		dernierTir = .2f;
	}

	public EnnemiDeBaseQuiTir() {
		super(getRandX(), CSG.HAUTEUR_ECRAN + HAUTEUR, PVMAX);
//		particleEffect.load(Gdx.files.internal("particules/b.p"), Gdx.files.internal("particules"));
	}

	private static float getRandX() {
		return (float) (Math.random() * CSG.LARGEUR_ECRAN - DEMI_LARGEUR);
	}


	/**
	 * Exactement la même que dans la super classe mais ça évite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		if(mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1 
				| Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.y += (VITESSE_MAX * Endless.delta);
		return true;
	}
	
//	float a = MathUtils.atan2(-v.y, -v.x) * MathUtils.radiansToDegrees;
//	Array<ParticleEmitter> emitters = effect.getEmitters();        
//	for (int i = 0; i < emitters.size; i++) {                          
//	   ScaledNumericValue val = emitters.get(i).getAngle();           
//	   float h1 = a + 90f;                                            
//	   float h2 = a - 90f;                                            
//	   val.setHigh(h1, h2);                                           
//	   val.setLow(a);       
//	} 

	/**
	 * Exactement la même que dans la super classe mais ça évite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficher(SpriteBatch batch) {
		maintenant += Endless.delta;
		if(mort){
			batch.draw(animationExplosion.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		}
		else{
			batch.draw(animation.getTexture(tpsAnimation), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimation += Endless.delta;
		}
	}
	
	
	@Override
	protected void tir() {
		if (!mort & maintenant > dernierTir	+ ArmesDeBase.CADENCETIR + CADENCETIR) {
			ArmesDeBase e = ArmesDeBase.pool.obtain();
			e.init(position.x + DEMI_LARGEUR - ArmesDeBase.DEMI_LARGEUR, position.y - ArmesDeBase.HAUTEUR, 0, -1, true);
			dernierTir = maintenant;
		}
	}


	@Override
	public int getXp() {
		return TypesEnnemis.EnnemiDeBaseQuiTir.COUT;
	}
	
	@Override
	public int getHauteur() {
		return HAUTEUR;
	}

	@Override
	public int getLargeur() {
		return LARGEUR;
	}

	@Override
	public int getVitesse() {
		return VITESSE_MAX;
	}
	
	@Override
	public int getDemiHauteur() {
		return DEMI_HAUTEUR;
	}

	@Override
	public int getDemiLargeur() {
		return DEMI_LARGEUR;
	}
}
