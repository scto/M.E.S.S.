package elements.generic.weapons.player;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;
import assets.sprites.Animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.components.Dimensions;
import elements.particular.particles.Particles;

public class TWeapon extends PlayerWeapon implements Poolable{
	
	public static final Dimensions DIMENSIONS = Dimensions.T_WEAPON;
	public static final float FIRERATETIR = .099f, FIRERATETIRLVL8 = .03f;
	public static final String LABEL = "armeHantee";
	public static final Pool<TWeapon> POOL = new Pool<TWeapon>(30) {		protected TWeapon newObject() {			return new TWeapon();		}	};
	private static boolean alterner = false;
	public float angle;

	private static final float LIMITE = (CSG.height / 4) / Stats.V_ARME_HANTEE;

	public void init(float posX, float posY) {
		pos.set(posX, posY);
		PLAYER_LIST.add(this);
		dir.set(0, 1).scl(Stats.V_ARME_HANTEE);
		Particles.shot(pos.x - DIMENSIONS.quartWidth, pos.y + DIMENSIONS.halfHeight, dir.angle());
	}

	@Override
	public void reset() {
		dir.set(0, 1).scl(Stats.V_ARME_HANTEE);
		super.reset();
	}

	@Override
	public void displayOnScreen(SpriteBatch batch) {
		if (alterner && EndlessMode.triggerStop == false)
			Particles.armeHantee(this);
		batch.draw(AssetMan.tWeapon, pos.x, pos.y, DIMENSIONS.halfWidth, DIMENSIONS.halfHeight, DIMENSIONS.width, DIMENSIONS.height, 1f, 1f, angle);
	}

	@Override
	public boolean mouvementEtVerif() {
		angle += 5000 * EndlessMode.delta;
		if (now > LIMITE && dir.y != 0) {
			if (alterner)
				dir.x = -dir.y;
			else
				dir.x = dir.y;
			dir.y = 0;
			alterner = !alterner;
		}
		return Physic.mvt(dir, pos, DIMENSIONS.width);
	}

	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	@Override	public void free() {					POOL.free(this);									}
	@Override	public boolean isInGoodShape() {		return false;	}
	@Override	public Animations getAnimation() {		return null;	}
}
