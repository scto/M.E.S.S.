package affichage.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class ModeleAnimation {
	
	
	
	public ModeleAnimation() {
		super();
	}

	/**
	 * La methode s'occupe de calculer la frame à afficher suivant la position en x si on va vers la gauche, la droite ou si on vient de se remettre droit
	 * @return
	 */
	protected abstract TextureRegion getTexture(float tps);
	
	protected static TextureRegion[] getTextureRegion(int COLONNES, int LIGNES, Texture t, int largeur, int hauteur){
		TextureRegion[][] sheet = TextureRegion.split(t, largeur, hauteur);
		TextureRegion[] tr = new TextureRegion[COLONNES];
	    for(int i = 0; i < COLONNES; i++)
	      	tr[i] = sheet[LIGNES][i];
	    return tr;
	}

	/**
	 * Crée une animation et la retourne
	 * @param COLONNES
	 * @param LIGNES
	 * @param t
	 * @param largeur
	 * @param hauteur
	 * @param TPS_ANIM
	 * @param mode 
	 * @return
	 */
	protected static Animation initAnimation(int COLONNES, int LIGNES, Texture t, int largeur, int hauteur, float TPS_ANIM, int mode) {
		TextureRegion[][] sheet = TextureRegion.split(t, largeur, hauteur);
		TextureRegion[] tr = new TextureRegion[COLONNES];
	    for(int i = 0; i < COLONNES; i++)
	      	tr[i] = sheet[LIGNES][i];
	    Animation animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(mode);
		return animation;
	}	
}
