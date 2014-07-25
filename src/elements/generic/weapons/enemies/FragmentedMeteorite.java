package elements.generic.weapons.enemies;

import jeu.CSG;
import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class FragmentedMeteorite extends EnemyWeapon implements Poolable{
	
	protected static final Dimensions DIMENSIONS = Dimensions.FRAG_METEORITE;
	public static final Pool<FragmentedMeteorite> POOL = Pools.get(FragmentedMeteorite.class);
	
	public void init(final float x, final float y, Vector2 dir) {		
		this.dir.x = dir.x;
		this.dir.y = dir.y;
		this.dir.scl(Stats.U4 * ((CSG.R.nextFloat()/2)+.5f) );
		pos.x = x;
		pos.y = y;
		ENEMIES_LIST.add(this);
	}
	
	@Override	public Animations getAnimation() {		return Animations.FRAG_METEORITE;		}
	@Override	public void free() {					POOL.free(this);	}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
}
