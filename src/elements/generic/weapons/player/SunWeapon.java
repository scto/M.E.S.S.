package elements.generic.weapons.player;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;
import assets.animation.Animated;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Player;
import elements.particular.particles.individual.weapon.SunParticle;

public class SunWeapon extends PlayerWeapon implements Poolable {

	public static int width, halfWidth;
	public final float color;
	public static final float CADENCETIR = initCadence(.060f, 5);
	public static final String LABEL = "SunWeapon";
	private static final Animated ANIMATED = initAnimation(1, 1);
	public static final Pool<SunWeapon> POOL = new Pool<SunWeapon>(30) {
		@Override
		protected SunWeapon newObject() {
			return new SunWeapon();
		}
	};
	public static final float[] COLORS = {
		AssetMan.convertARGB(1, 87f/255f, 194f/255f, 0),
		AssetMan.convertARGB(1, 78f/255f, 178f/255f, 0),
		AssetMan.convertARGB(1, 62f/255f, 151f/255f, 0),
		AssetMan.convertARGB(1, 85f/255f, 210f/255f, 0),
		AssetMan.convertARGB(1, 0, 255f/255f, 153f/255f),
		
		AssetMan.convertARGB(1, 60f/255f, 198f/255f, 0),
		AssetMan.convertARGB(1, 44f/255f, 180f/255f, 0),
		AssetMan.convertARGB(1, 52f/255f, 171f/255f, 0),
		AssetMan.convertARGB(1, 45f/255f, 210f/255f, 0),
		AssetMan.convertARGB(1, 0, 255f/255f, 123f/255f),
		
		AssetMan.convertARGB(1, 74f/255f, 190f/255f, 0),
		AssetMan.convertARGB(1, 64f/255f, 174f/255f, 0),
		AssetMan.convertARGB(1, 78f/255f, 146f/255f, 0),
		AssetMan.convertARGB(1, 60f/255f, 205f/255f, 0),
		AssetMan.convertARGB(1, 0, 245f/255f, 113f/255f),
		
		AssetMan.convertARGB(1, 57f/255f, 194f/255f, 0),
		AssetMan.convertARGB(1, 58f/255f, 208f/255f, 0),
		AssetMan.convertARGB(1, 52f/255f, 201f/255f, 0),
		AssetMan.convertARGB(1, 65f/255f, 220f/255f, 0),
		AssetMan.convertARGB(1, 0, 255f/255f, 103f/255f),
		
		AssetMan.convertARGB(1, 0, 254f/255f, 191f/255f)};
	public SunWeapon() {
		color = COLORS[CSG.R.nextInt(COLORS.length)];
	}
	public static void updateDimensions() {
		width = (int) (MINWIDTH);
		halfWidth = width/2;
		SunParticle.halfWidh = halfWidth;
		SunParticle.width = width;
	}

	@Override
	public void draw(SpriteBatch batch) {	
		batch.setColor(color);
		batch.draw(ANIMATED.getTexture(EndlessMode.now), pos.x, pos.y, width, width);
		batch.setColor(AssetMan.WHITE);
	}
	@Override	public int getWidth() {						return width;								}
	@Override	public int getHeight() {					return width;								}
	@Override	public void free() {						POOL.free(this);							}
	@Override	public float getColor() {					return COLORS[R.nextInt(COLORS.length)];	}
	@Override	public TextureRegion getTexture() {			return null;	}
	@Override	public int getHalfWidth() {					return halfWidth;	}
	@Override	public int getHalfHeight() {				return halfWidth;	}

	public static Object getLabel() {			return LABEL;	}

	public static void init(Vector2 dir) {
		SunWeapon s = POOL.obtain();
		s.dir.x = dir.x;
		s.dir.y = dir.y;
		
		s.pos.x = (Player.xCenter) - halfWidth;
		s.pos.y = (Player.yCenter) + Player.DEMI_HAUTEUR;
		
		s.dir.scl(Stats.V_ARME_SUN);
		
		PLAYER_LIST.add(s);
	}
	@Override
	public int getPower() {
		return 8;
	}
}
