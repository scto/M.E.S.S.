package elements.particular.particles;

import jeu.CSG;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public class StaticSmoke implements Poolable {
	
	private float x, y, angle, width, height, widthSpeed, heightSpeed, alpha;
	public static final Pool<StaticSmoke> POOL = new Pool<StaticSmoke>() {		protected StaticSmoke newObject() {			return new StaticSmoke();		}	};
	private static final Array<StaticSmoke> SMOKE = new Array<StaticSmoke>();
	
	@Override
	public void reset() {	}
	
	public static void add(float x, float y, float width) {
		SMOKE.add(POOL.obtain().init(x, y, width));
	}
	
	public static void draw(SpriteBatch batch) {
		System.out.println(SMOKE.size);
		for (StaticSmoke s : SMOKE) {
			batch.setColor(1, 1, 1, s.alpha);
			s.width += s.widthSpeed * Gdx.graphics.getDeltaTime();
			s.height += s.heightSpeed * Gdx.graphics.getDeltaTime();
			
			s.x -= s.widthSpeed * (Gdx.graphics.getDeltaTime() / 2);
			s.y -= s.heightSpeed * (Gdx.graphics.getDeltaTime() / 2);
			s.alpha *= 0.935f;
			batch.draw(AssetMan.smoke, s.x, s.y, s.width / 2, s.height / 2, s.width, s.height, 1f, 1f, s.angle);
			if (s.alpha < 0.045f) {
				SMOKE.removeValue(s, true);
				POOL.free(s);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public StaticSmoke init(float x, float y, float width) {
		height = width + (CSG.R.nextFloat() * width);
		this.y = y - height / 2;
		this.width = width + (CSG.R.nextFloat() * width);
		this.x = x - this.width / 2;
		angle = CSG.R.nextFloat() * 360;
		widthSpeed = (float) Math.abs(CSG.R.nextGaussian()) * Stats.U12;
		heightSpeed = (float) Math.abs(CSG.R.nextGaussian()) * Stats.U12;
		alpha = 0.1f + (CSG.R.nextFloat() / 20f);
		return this;
	}

	public static void clear() {
		POOL.freeAll(SMOKE);
		SMOKE.clear();
	}

}
