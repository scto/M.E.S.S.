package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;
import elements.generic.components.shots.AbstractShot;
import elements.generic.components.shots.Gatling;
import elements.generic.weapons.enemies.FragWeapon;


public class ShooterFrag extends Shooter {
	
	protected static final Dimensions DIMENSIONS = Dimensions.SHOOTER_FRAG;
	public static final int BASE_XP = 39, HP = Stats.HP_SHOOTER_FRAG, HALF_HP = HP/2, EXPLOSION = 50, XP = BASE_XP;
	protected static final float COLOR = AssetMan.convertARGB(1, 1, 1, .7f), xOffset = DIMENSIONS.halfWidth - FragWeapon.DIMENSIONS.halfWidth, FIRERATE = 1.9f * MOD_FIRERATE, SPEED12 = getModulatedSpeed(12, 1);
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
	
	@Override	protected float getDerive() {				return Stats.DERIVE_DE_BASE_QUI_TIR2;			}
	@Override	public Dimensions getDimensions() {			return DIMENSIONS;								}
	@Override	public int getExplosionCount() {			return EXPLOSION;								}
	@Override 	public float getFirerate() {				return FIRERATE;								}
	@Override	public void free() {						POOL.free(this);								}
	@Override	protected int getDemiPv() {					return HALF_HP;									}
	@Override	public int getBonusValue() {				return BASE_XP;									}
	@Override	public float getSpeed() {					return SPEED12;									}
	@Override	protected int getMaxHp() {					return HP;										}
	@Override	public int getXp() {						return XP;										}
}
