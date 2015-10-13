package elements.generic.weapons.enemies;

import jeu.mode.EndlessMode;
import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class Tournante extends EnemyWeapon {
	
	public static final Pool<Tournante> POOL = Pools.get(Tournante.class);
	public static final Dimensions DIMENSIONS = Dimensions.TOURNANTE;
	
	@Override
	protected void move() {
		dir.rotate(EndlessMode.delta15);
		super.move();
	}
	@Override	public void free() {						POOL.free(this);				}
	@Override	public Dimensions getDimensions() {			return DIMENSIONS;				}
	@Override	public Animations getAnimation() {			return Animations.BLUE_BALL;	}
	
}
