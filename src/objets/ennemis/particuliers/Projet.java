package objets.ennemis.particuliers;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import objets.ennemis.Phases;
import assets.animation.AnimationEnnemiDeBase;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Projet extends Ennemis {
	
	public static Pool<Projet> pool = Pools.get(Projet.class);
	private static final int HAUTEUR = 40, LARGEUR = 40, DEMI_HAUTEUR = HAUTEUR / 2, DEMI_LARGEUR = LARGEUR / 2;
	private static AnimationEnnemiDeBase anim;
	private static Phases[] phases = {new Phases(anim)};
	
	
	
	@Override
	public void invoquer() {				LISTE.add(pool.obtain());	}
	@Override
	protected void free() {					pool.free(this);	}
	@Override
	protected String getLabel() {			return "Projet";	}
	@Override
	protected int getPvMax() {				return 100;	}
	@Override
	public int getXp() {					return CoutsEnnemis.DeBase.COUT;	}
	@Override
	public int getHauteur() {				return HAUTEUR;	}
	@Override
	public int getLargeur() {				return LARGEUR;	}
	@Override
	public int getDemiHauteur() {			return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {			return DEMI_LARGEUR;	}
	@Override
	public float getDirectionY() {			return 0;	}
	
	public void act(SpriteBatch batch) {
		
	}
	
}
