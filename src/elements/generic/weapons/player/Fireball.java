package elements.generic.weapons.player;

import jeu.CSG;
import jeu.Stats;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.components.Dimensions;
import elements.particular.particles.Particles;

/**
 * Arme de base qui fait une boule de feu
 * @author Julien
 *
 */
public class Fireball extends PlayerWeapon implements Poolable{

	public static final Dimensions DIMENSIONS = Dimensions.FIREBALL_PLAYER;
	public static final int width033 = (int) (DIMENSIONS.width* .33f), WIDTH066 = (int) (DIMENSIONS.width* .66f), WIDTH05 = (int) (DIMENSIONS.width *.03f), WIDTH10 = (int) (DIMENSIONS.width *.06f),
			WIDTH15 = (int) (DIMENSIONS.width *.09f), WIDTH20 = (int) (DIMENSIONS.width *.12f), WIDTH25 = (int) (DIMENSIONS.width *.15f), WIDTH30 = (int) (DIMENSIONS.width *.18f), WIDTH35 = (int) (DIMENSIONS.width *.21f), WIDTH40 = (int) (DIMENSIONS.width *.24f);
	public static final float FIRERATETIR = .12f;
	public static final String LABEL = "ArmeDeBase";
	public static final Pool<Fireball> POOL = new Pool<Fireball>(10) {		protected Fireball newObject() {			return new Fireball();		}	};
	
	public Fireball() {
		dir.set(0, Stats.V_ARME_DE_BASE).rotate((float) (CSG.R.nextFloat()-0.5f) * 10);
	}
	
	/**
	 * ATTENTION ici le init s'occupe d'ajouter a la bonne liste
	 */
	public void init(float posX, float posY) {
		pos.set(posX, posY);
		PLAYER_LIST.add(this);
		Particles.shot(pos.x - Stats.u, pos.y + DIMENSIONS.halfHeight, 90);
	}

	@Override	public void displayOnScreen(SpriteBatch batch) {
		Particles.ajoutArmeDeBase(this);	
		Particles.ajoutArmeDeBase(this);	
		Particles.ajoutArmeDeBase(this);	
	}
	@Override	public void free() {				POOL.free(this);									}
	@Override	public Dimensions getDimensions() {	return DIMENSIONS;									}
	public static String getLabel() {				return "ArmeDeBase";								}
}
