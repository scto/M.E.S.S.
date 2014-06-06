package elements.generic.weapons.player;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.particles.individual.PrecalculatedParticles;
import elements.particular.particles.individual.weapon.SpaceInvaderParticle;


public class SpaceInvaderWeapon extends PlayerWeapon implements Poolable {

	public static final int width = CSG.screenWidth/6, halfWidth = width / 2, halfWidth3 = halfWidth*3, width2 = width*2, halfWidth5 = halfWidth*5, width3 = width * 3, halfWidth7 = halfWidth * 7, width4 = width * 4, particle = width / 10;
	public static final int height = width * 3;
	public final float color;
	public static final float FIRERATETIR = initCadence(0.04f, 4);
	public static final String LABEL = "SpaceInvaderWeapon";
	public static final Pool<SpaceInvaderWeapon> POOL = new Pool<SpaceInvaderWeapon>(30) {
		@Override
		protected SpaceInvaderWeapon newObject() {
			return new SpaceInvaderWeapon();
		}
	};
	public static final float[] COLORS = {
		AssetMan.convertARGB(1, 0, 194f/255f, 97f/255f),
		AssetMan.convertARGB(1, 0, 178f/255f, 88f/255f),
		AssetMan.convertARGB(1, 0, 151f/255f, 72f/255f),
		AssetMan.convertARGB(1, 0, 210f/255f, 95f/255f),
		AssetMan.convertARGB(1, 0, 255f/255f, 253f/255f),
		
		AssetMan.convertARGB(1, 0, 198f/255f, 160f/255f),
		AssetMan.convertARGB(1, 0, 180f/255f, 144f/255f),
		AssetMan.convertARGB(1, 0, 171f/255f, 152f/255f),
		AssetMan.convertARGB(1, 0, 210f/255f, 145f/255f),
		AssetMan.convertARGB(1, 0, 255f/255f, 123f/255f),
		
		AssetMan.convertARGB(1, 0, 190f/255f, 174f/255f),
		AssetMan.convertARGB(1, 0, 194f/255f, 224f/255f),
		AssetMan.convertARGB(1, 0, 166f/255f, 218f/255f),
		AssetMan.convertARGB(1, 0, 145f/255f, 200f/255f),
		AssetMan.convertARGB(1, 0, 145f/255f, 223f/255f),
		
		AssetMan.convertARGB(1, 0, 194f/255f, 237f/255f),
		AssetMan.convertARGB(1, 0, 208f/255f, 238f/255f),
		AssetMan.convertARGB(1, 0, 201f/255f, 252f/255f),
		AssetMan.convertARGB(1, 0, 220f/255f, 225f/255f),
		AssetMan.convertARGB(1, 0, 255f/255f, 203f/255f),
		
		AssetMan.convertARGB(1, 0, 254f/255f, 201f/255f)};
	public SpaceInvaderWeapon() {
		color = COLORS[CSG.R.nextInt(COLORS.length)];
	}

	@Override
	public void draw(SpriteBatch batch) {
		for (int i = 0; i <= EndlessMode.fps; i++)
			SpaceInvaderParticle.init(this);
	}
	@Override	public float getWidth() {						return width;								}
	@Override	public float getHeight() {					return width;								}
	@Override	public void free() {						POOL.free(this);							}
	@Override	public float getColor() {					return COLORS[R.nextInt(COLORS.length)];	}
	@Override	public float getHalfWidth() {					return halfWidth;	}
	@Override	public float getHalfHeight() {				return halfWidth;	}
	@Override		public float[] getColors() {			return PrecalculatedParticles.colorsOverTimeBlue;		}

	public static Object getLabel() {			return LABEL;	}

	public void init(float x, float y) {
		pos.x = x;
		pos.y = y;
		PLAYER_LIST.add(this);
		dir.x = 0;
		dir.y = Stats.V_ARME_SPACE_INVADER + (CSG.profile.NvSpaceInvadersWeapon * CSG.HEIGHT_DIV20);
	}
	@Override
	public int getPower() {
		return (int) ((50f * (CSG.profile.NvSpaceInvadersWeapon/2f)) + 50f);
	}
}
