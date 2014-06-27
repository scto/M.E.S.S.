package elements.generic.weapons.player;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import assets.AssetMan;
import assets.sprites.Animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;
import elements.generic.weapons.Weapon;
import elements.particular.Player;
import elements.particular.particles.individual.PrecalculatedParticles;
import elements.particular.particles.individual.weapon.GreenAddParticle;

/**
 * Arme shotant en balyant l'�cran de gauche � droite, d'o� le nom
 * C'est celle qui est verte
 * @author Julien
 *
 */

public class ArmeAdd extends PlayerWeapon {
	
	public static final Dimensions DIMENSIONS = Dimensions.DRONE_WEAPON;
	public static float FIRERATETIR;
	public static final Pool<ArmeAdd> POOL = Pools.get(ArmeAdd.class);
	public static final float[] COLORS = {
		AssetMan.convertARGB(1, 27f/255f, 124f/255f, 0),
		AssetMan.convertARGB(1, 38f/255f, 178f/255f, 0),
		AssetMan.convertARGB(1, 32f/255f, 151f/255f, 0),
		AssetMan.convertARGB(1, 45f/255f, 210f/255f, 0),
		AssetMan.convertARGB(1, 0, 255f/255f, 103f/255f),
		
		AssetMan.convertARGB(1, 40f/255f, 138f/255f, 0),
		AssetMan.convertARGB(1, 44f/255f, 180f/255f, 0),
		AssetMan.convertARGB(1, 42f/255f, 171f/255f, 0),
		AssetMan.convertARGB(1, 45f/255f, 210f/255f, 0),
		AssetMan.convertARGB(1, 0, 255f/255f, 103f/255f),
		
		AssetMan.convertARGB(1, 24f/255f, 120f/255f, 0),
		AssetMan.convertARGB(1, 34f/255f, 174f/255f, 0),
		AssetMan.convertARGB(1, 28f/255f, 146f/255f, 0),
		AssetMan.convertARGB(1, 40f/255f, 205f/255f, 0),
		AssetMan.convertARGB(1, 0, 245f/255f, 113f/255f),
		
		AssetMan.convertARGB(1, 57f/255f, 134f/255f, 0),
		AssetMan.convertARGB(1, 58f/255f, 188f/255f, 0),
		AssetMan.convertARGB(1, 52f/255f, 171f/255f, 0),
		AssetMan.convertARGB(1, 65f/255f, 220f/255f, 0),
		AssetMan.convertARGB(1, 0, 235f/255f, 103f/255f),
		
		AssetMan.convertARGB(1, 0, 254f/255f, 191f/255f)};
	
	public static void determinerCadenceTir() {	FIRERATETIR = 1.6f / CSG.profile.cadenceAdd;	}
	private float angle;
	private static final Vector2 decalage = new Vector2();
	public void init(float x, float y, float angle, float decalage) {
		pos.x = (x + Player.HALF_WIDTH_ADD) - DIMENSIONS.halfWidth;
		pos.y = (y + Player.HEIGHT_DIV8) - DIMENSIONS.halfHeight;
		dir.x = 0;
		dir.y = 1;
		dir.rotate(angle);
		ArmeAdd.decalage.x = Player.WIDTH_ADD;
		ArmeAdd.decalage.y = Player.HEIGHT_DIV4;
		dir.rotate(decalage);
		dir.scl(Stats.V_ARME_ADD);
		Weapon.ADDS.add(this);
		this.angle = (angle-180) + decalage;
		ArmeAdd.decalage.rotate(angle+65);
		pos.add(ArmeAdd.decalage);
	}

	@Override
	protected void displayOnScreen(SpriteBatch batch) {
		batch.setColor(GreenAddParticle.COLOR);
		batch.draw(AssetMan.addBullet, pos.x, pos.y, DIMENSIONS.halfWidth, DIMENSIONS.halfHeight, DIMENSIONS.width, DIMENSIONS.height, 1, 1, angle);
		batch.setColor(AssetMan.WHITE);
	}
	
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	@Override	public void free() {				POOL.free(this);												}
	@Override	public float getColor() {			return COLORS[R.nextInt(COLORS.length)];						}	
	@Override	public boolean mouvementEtVerif() {	return Physic.mvt(DIMENSIONS.height, DIMENSIONS.width, dir, pos);						}
	@Override	public float[] getColors() {		return PrecalculatedParticles.colorsOverTimeYellowToGreen;		}

	public static void add(float x, float y, float angle, float decalage) {
		POOL.obtain().init(x, y, angle + 90, decalage);
	}

	@Override
	public Animations getAnimation() {
		return null;
	}
	

}

