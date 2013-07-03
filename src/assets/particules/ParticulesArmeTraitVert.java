package assets.particules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ParticulesArmeTraitVert extends ParticleEffect implements Poolable{

	public static Pool<ParticulesArmeTraitVert> pool = Pools.get(ParticulesArmeTraitVert.class);
	
	public ParticulesArmeTraitVert() {
		load(Gdx.files.internal("particules/traitvert.p"), Gdx.files.internal("particules"));
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
