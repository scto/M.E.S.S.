package elements.generic.weapons.player;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.components.Dimensions;
import elements.particular.particles.individual.weapon.SpaceInvaderParticle;


public class SpaceInvaderWeapon extends PlayerWeapon implements Poolable {

	public static final Dimensions DIMENSIONS = Dimensions.SPACE_INVADER_WEAPON;
	public static final int particle = (int) (DIMENSIONS.width / 6);
	public static final float FIRERATETIR = 0.04f;
	public static final String LABEL = "SpaceInvaderWeapon";
	public static final Pool<SpaceInvaderWeapon> POOL = new Pool<SpaceInvaderWeapon>(30) {		protected SpaceInvaderWeapon newObject() {			return new SpaceInvaderWeapon();		}	};

	@Override
	public void displayOnScreen(SpriteBatch batch) {
		for (int i = 0; i <= EndlessMode.fps; i++)
			SpaceInvaderParticle.init(this);
	}
	public Rectangle getRectangleCollision() {
		TMP_RECT.x = pos.x;
		TMP_RECT.y = pos.y - DIMENSIONS.halfHeight;
		TMP_RECT.height = DIMENSIONS.height;
		TMP_RECT.width = DIMENSIONS.width;
		return TMP_RECT;
	}
	@Override	public void free() {					POOL.free(this);														}
	@Override	public int getPower() {					return (int) ((50f * (CSG.profile.lvlSpaceInvader/3f)) + 50f);	}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	public static Object getLabel() {					return LABEL;															}

	public void init(float x, float y) {
		pos.set(x, y);
		PLAYER_LIST.add(this);
		dir.set(0, Stats.V_ARME_SPACE_INVADER + (CSG.profile.lvlSpaceInvader * CSG.HEIGHT_DIV20));
	}
}
