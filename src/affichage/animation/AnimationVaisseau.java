package affichage.animation;

import vaisseaux.Vaisseaux;
import vaisseaux.joueur.VaisseauType1;
import affichage.TexMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationVaisseau{
	
	private final VaisseauType1 v;
	private static final float TPS_ANIM = .15f;
	private Animation centreVersGauche;
	private Animation centreVersDroite;
	private float tps;
	private static final TextureRegion[][] vaisseaux = TextureRegion.split(TexMan.lesVaisseaux, 24, 28);
	private boolean onAllaitVersDroite = false, remettreDroit = false, onAllaitToutDroit = true;
	
	/**
	 * Initialise l'animation
	 * @param v
	 * @param i Le numero de la ligne de la texture
	 */
	public AnimationVaisseau(VaisseauType1 v, int i) {
		super();
		this.v = v;
		initAnimationVaisseau(i);
	}
	
	/**
	 * initalise les differentes animations
	 * @param LIGNES
	 */
	private void initAnimationVaisseau(int LIGNES) {
		// le nombre de colonnes pour une animation
		int COLONNES = 3;

        TextureRegion[] tr = new TextureRegion[COLONNES];
        int index = 0;
        for(int i = COLONNES-1; i >= 0; i--) tr[index++] = vaisseaux[LIGNES][i];
        centreVersGauche = new Animation(TPS_ANIM, tr);
        index = 0;
        
        // obligé de récreer une texture région sinon il va modifier celle existante
        TextureRegion[] tr2 = new TextureRegion[COLONNES];
        for(int i = COLONNES-1; i+1 < COLONNES*2; i++) tr2[index++] = vaisseaux[LIGNES][i];
        centreVersDroite = new Animation(TPS_ANIM, tr2);
	}

	/**
	 * Affiche le vaisseau
	 * @param batch
	 * @param delta 
	 */
	public void afficher(SpriteBatch batch, float delta) {
		tps += delta;
		batch.draw(getTexture(), v.position.x, v.position.y, v.getLargeur(), v.getHauteur());
	}

	/**
	 * La methode s'occupe de calculer la frame à afficher suivant la position en x si on va vers la gauche, la droite ou si on vient de se remettre droit
	 * @return
	 */
	private TextureRegion getTexture() {
		// Si on va tout droit
		if(v.position.x == v.oldPosition){
			// et qu'avant on allait pas tout droit on remet le temps à 0 une seule fois.
			if(remettreDroit){
				tps = 0;
				remettreDroit = false;
				onAllaitToutDroit = true;
				centreVersGauche.setPlayMode(Animation.REVERSED);
				centreVersDroite.setPlayMode(Animation.REVERSED);
			}
			// si avant on allait vers la droite
			if (onAllaitVersDroite) return centreVersDroite.getKeyFrame(tps, false);
			return centreVersGauche.getKeyFrame(tps, false);
		}
		// si avait on allait tout droit on remet le temps à 0 et tout dans l'ordre
		if(onAllaitToutDroit){
			tps = 0;
			onAllaitToutDroit = false;
			centreVersDroite.setPlayMode(Animation.NORMAL);
			centreVersGauche.setPlayMode(Animation.NORMAL);
		}
		remettreDroit = true;
		// si on va vers la gauche
		if(v.oldPosition > v.position.x){
			//onAllaitVersGauche = true;
			onAllaitVersDroite = false;
			return centreVersGauche.getKeyFrame(tps, false); 
		}// sinon on va vers la droite
		//onAllaitVersGauche = false;
		onAllaitVersDroite = true;
		return centreVersDroite.getKeyFrame(tps, false);
	}
}
