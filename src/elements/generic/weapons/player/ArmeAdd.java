package elements.generic.weapons.player;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Player;
import elements.generic.weapons.Weapons;
import elements.particular.particles.individual.PrecalculatedParticles;
import elements.particular.particles.individual.weapon.GreenAddParticle;

/**
 * Arme tirant en balyant l'�cran de gauche � droite, d'o� le nom
 * C'est celle qui est verte
 * @author Julien
 *
 */

public class ArmeAdd extends PlayerWeapon implements Poolable{
	
	public static final int WIDTH = CSG.screenWidth / 26, DEMI_LARGEUR = WIDTH/2, HAUTEUR = WIDTH * 2, DEMI_HAUTEUR = HAUTEUR / 2; 
	public static float CADENCETIR;
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
	
	public static void determinerCadenceTir() {	CADENCETIR = 1.6f / CSG.profile.cadenceAdd;	}
	private float angle;
	private static final Vector2 decalage = new Vector2();
	public void init(float x, float y, float angle, float decalage) {
		pos.x = (x + Player.DEMI_LARGEUR_ADD) - DEMI_LARGEUR;
		pos.y = (y + Player.HAUTEUR_DIV8) - DEMI_HAUTEUR;
		dir.x = 0;
		dir.y = 1;
		dir.rotate(angle);
		ArmeAdd.decalage.x = Player.LARGEUR_ADD;
		ArmeAdd.decalage.y = Player.HAUTEUR_DIV4;
		dir.rotate(decalage);
		dir.scl(Stats.V_ARME_ADD);
		Weapons.ADDS.add(this);
		this.angle = (angle-180) + decalage;
		ArmeAdd.decalage.rotate(angle+65);
		pos.add(ArmeAdd.decalage);
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.setColor(GreenAddParticle.COLOR);
		batch.draw(AssetMan.addBullet, pos.x, pos.y, DEMI_LARGEUR, DEMI_HAUTEUR, WIDTH, HAUTEUR, 1, 1, angle);
		batch.setColor(AssetMan.WHITE);
	}

	@Override
	public boolean mouvementEtVerif() {
//		Particles.ajoutAdd(this);
		return Physic.mvt(HAUTEUR, WIDTH, dir, pos);
	}

	@Override	public int getWidth() {	return WIDTH;	}
	@Override	public int getHeight() {	return HAUTEUR;	}
	@Override	public void free() {		POOL.free(this);	}
	@Override	public float getColor() {	return COLORS[R.nextInt(COLORS.length)];	}
	@Override	public int getHalfWidth() {		return DEMI_LARGEUR;	}
	@Override	public int getHalfHeight() {		return DEMI_HAUTEUR;	}

	public static void add(float x, float y, float angle, float decalage) {
		POOL.obtain().init(x, y, angle + 90, decalage);
	}
	
	@Override		public float[] getColors() {			return PrecalculatedParticles.colorsOverTimeGreen;		}
}

