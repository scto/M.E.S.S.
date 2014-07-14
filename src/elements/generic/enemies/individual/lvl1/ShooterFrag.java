package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;
import elements.generic.components.HPandSpeed;
import elements.generic.components.shots.AbstractShot;
import elements.generic.components.shots.Gatling;
import elements.generic.weapons.enemies.FragWeapon;


public class ShooterFrag extends Shooter {
	
	protected static final Dimensions DIMENSIONS = Dimensions.SHOOTER_FRAG;
	public static final int BASE_XP = 39, EXPLOSION = 50, XP = BASE_XP;
	protected static final float COLOR = AssetMan.convertARGB(1, 1, 1, .7f), xOffset = DIMENSIONS.halfWidth - FragWeapon.DIMENSIONS.halfWidth, FIRERATE = 1.9f * MOD_FIRERATE;
	public static final Pool<ShooterFrag> POOL = Pools.get(ShooterFrag.class);

	@Override
	protected void setColor(SpriteBatch batch) {
		batch.setColor(Color.CYAN);
	}
	
	@Override
	protected void removeColor(SpriteBatch batch) {
		batch.setColor(AssetMan.WHITE);
	}
	
	@Override
	protected void shoot() {
		TMP_POS.set(pos.x + xOffset,  pos.y - FragWeapon.DIMENSIONS.width);
		AbstractShot.shootDown(Gatling.FRAG, TMP_POS, Stats.U10);
	}
	
	@Override	public HPandSpeed getEnemyStats() {			return HPandSpeed.SHOOTER_FRAG;					}
	@Override	public Dimensions getDimensions() {			return DIMENSIONS;								}
	@Override	public int getExplosionCount() {			return EXPLOSION;								}
	@Override 	public float getFirerate() {				return FIRERATE;								}
	@Override	public void free() {						POOL.free(this);								}
	@Override	public int getBonusValue() {				return BASE_XP;									}
	@Override	public int getXp() {						return XP;										}
}
