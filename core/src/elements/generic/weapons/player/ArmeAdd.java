package elements.generic.weapons.player;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;
import elements.generic.weapons.Weapon;
import elements.particular.Player;
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
	
	public static void determinerCadenceTir() {	FIRERATETIR = 1.6f / CSG.profile.dronesFirerate;	}
	private float angle;
	private static final Vector2 OFFSET = new Vector2();
	
	public void init(float x, float y, float angle, float decalage) {
		pos.set((x + Player.HALF_WIDTH_ADD) - DIMENSIONS.halfWidth, (y + Player.HEIGHT_DIV8) - DIMENSIONS.halfHeight);
		dir.set(0, 1).rotate(angle + decalage).scl(Stats.V_ARME_ADD);
		OFFSET.set(Player.WIDTH_ADD, Player.HEIGHT_DIV4).rotate(angle+65);
		Weapon.ADDS.add(this);
		this.angle = (angle-180) + decalage;
		pos.add(OFFSET);
	}

	@Override
	protected void displayOnScreen(SpriteBatch batch) {
		batch.setColor(GreenAddParticle.COLOR);
		batch.draw(AssetMan.addBullet, pos.x, pos.y, DIMENSIONS.halfWidth, DIMENSIONS.halfHeight, DIMENSIONS.width, DIMENSIONS.height, 1, 1, angle);
		batch.setColor(CSG.gm.palette().white);
	}
	
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	@Override	public void free() {				POOL.free(this);												}
	@Override	public boolean mouvementEtVerif() {	return Physic.mvt(DIMENSIONS.height, DIMENSIONS.width, dir, pos);						}

	public static void add(float x, float y, float angle, float decalage) {
		POOL.obtain().init(x, y, angle + 90, decalage);
	}

}

