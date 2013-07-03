package assets.particules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ParticulesArmeBossMine extends ParticleEffect implements Poolable{

	public static Pool<ParticulesArmeBossMine> pool = Pools.get(ParticulesArmeBossMine.class);
	
	
	public ParticulesArmeBossMine() {
		load(Gdx.files.internal("particules/test.p"), Gdx.files.internal("particules"));
	}
	
	@Override
	public void reset() {
		setPosition(9999, 9999);
		update(10); // Faut lui mettre un bon update pour qu'il reprenne pas a l'endroit ou il s'etait fait virer
	}

	public void free() {
		pool.free(this);
	}

}
