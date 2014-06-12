package elements.generic.weapons.player;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;
import assets.sprites.Animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Player;
import elements.particular.particles.individual.PrecalculatedParticles;
import elements.particular.particles.individual.weapon.SunParticle;

public class SunWeapon extends PlayerWeapon implements Poolable {

	public static int width, halfWidth;
	public static final float FIRERATETIR = initCadence(.060f, 5);
	public static final String LABEL = "SunWeapon";
	public static final Pool<SunWeapon> POOL = new Pool<SunWeapon>(30) {		protected SunWeapon newObject() {			return new SunWeapon();		}	};
	public static final float[] COLORS = {
		AssetMan.convertARGB(1, 87f/255f, 	194f/255f, 	0),
		AssetMan.convertARGB(1, 78f/255f, 	178f/255f, 	0),
		AssetMan.convertARGB(1, 62f/255f, 	151f/255f, 	0),
		AssetMan.convertARGB(1, 85f/255f, 	210f/255f, 	0),
		AssetMan.convertARGB(1, 0, 			255f/255f, 	153f/255f),
		
		AssetMan.convertARGB(1, 60f/255f, 	198f/255f, 	0),
		AssetMan.convertARGB(1, 44f/255f, 	180f/255f, 	0),
		AssetMan.convertARGB(1, 52f/255f, 	171f/255f, 	0),
		AssetMan.convertARGB(1, 45f/255f, 	210f/255f, 	0),
		AssetMan.convertARGB(1, 0, 			255f/255f, 	123f/255f),
		
		AssetMan.convertARGB(1, 74f/255f, 	190f/255f, 	0),
		AssetMan.convertARGB(1, 64f/255f, 	174f/255f, 	0),
		AssetMan.convertARGB(1, 78f/255f, 	146f/255f, 	0),
		AssetMan.convertARGB(1, 60f/255f, 	205f/255f, 	0),
		AssetMan.convertARGB(1, 0, 			245f/255f, 	113f/255f),
		
		AssetMan.convertARGB(1, 57f/255f, 	194f/255f, 	0),
		AssetMan.convertARGB(1, 58f/255f, 	208f/255f, 	0),
		AssetMan.convertARGB(1, 52f/255f, 	201f/255f, 	0),
		AssetMan.convertARGB(1, 65f/255f, 	220f/255f, 	0),
		AssetMan.convertARGB(1, 0, 			255f/255f, 	103f/255f),
		
		AssetMan.convertARGB(1, 0, 			254f/255f, 	191f/255f) };
	
	public final float color;
	
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
		batch.draw(Animations.BLUE_BALL.anim.getTexture(EndlessMode.now), pos.x, pos.y, width, width);
		batch.setColor(AssetMan.WHITE);
	}
	
	@Override	public float[] getColors() {			return PrecalculatedParticles.colorsOverTimeYellowToGreen;		}
	@Override	public float getColor() {				return COLORS[R.nextInt(COLORS.length)];						}
	@Override	public float getHalfWidth() {			return halfWidth;												}
	@Override	public float getHalfHeight() {			return halfWidth;												}
	@Override	public void free() {					POOL.free(this);												}
	@Override	public float getWidth() {				return width;													}
	@Override	public float getHeight() {				return width;													}
	@Override	public int getPower() {					return 8;														}
	public static Object getLabel() {					return LABEL;	}

	public static void init(Vector2 dir) {
		final SunWeapon s = POOL.obtain();
		s.dir.x = dir.x;
		s.dir.y = dir.y;
		
		s.pos.x = (Player.xCenter) - halfWidth;
		s.pos.y = (Player.yCenter) + Player.HALF_HEIGHT;
		
		s.dir.scl(Stats.SUN_SPEED);
		
		PLAYER_LIST.add(s);
	}
}
