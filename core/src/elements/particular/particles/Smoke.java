package elements.particular.particles;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Smoke implements Poolable {
	
	private float x, y, angle, width, height, angleSpeead, widthSpeed, heightSpeed, alpha;
	public static final Pool<Smoke> POOL = new Pool<Smoke>() {		protected Smoke newObject() {			return new Smoke();		}	};
	private static final Array<Smoke> SMOKE = new Array<Smoke>();
	private static final float SPEED = 14 * Stats.U;
	
	@Override
	public void reset() {	}
	
	public static void add(float x, float y, float width) {
		SMOKE.add(POOL.obtain().init(x, y, width));
	}
	
	static float modulatedSpeed; 
	public static void draw(SpriteBatch batch) {
//		System.out.println(SMOKE.size);
		modulatedSpeed = (EndlessMode.delta + Gdx.graphics.getDeltaTime()) * SPEED; 
		for (Smoke s : SMOKE) {
			batch.setColor(1, 1, 1, s.alpha);
			s.y -= modulatedSpeed;
			s.angle += s.angleSpeead * Gdx.graphics.getDeltaTime();
			s.width += s.widthSpeed * Gdx.graphics.getDeltaTime();
			s.height += s.heightSpeed * Gdx.graphics.getDeltaTime();
			
			s.x -= s.widthSpeed * (Gdx.graphics.getDeltaTime() / 2);
			s.y -= s.heightSpeed * (Gdx.graphics.getDeltaTime() / 2);
			
			batch.draw(AssetMan.smoke, s.x, s.y, s.width / 2, s.height / 2, s.width, s.height, 1f, 1f, s.angle);
			s.alpha *= 0.965f;
			if (s.alpha < 0.01f) {
				SMOKE.removeValue(s, true);
				POOL.free(s);
			}
		}
	}

	public Smoke init(float x, float y, float width) {
		height = width + (CSG.R.nextFloat() * width);
		this.width = width + (CSG.R.nextFloat() * width);
		this.x = x - width / 2;
		this.y = y - height / 2;
		angle = CSG.R.nextFloat() * 360;
		angleSpeead = (float) CSG.R.nextGaussian() * Stats.U;
		widthSpeed = (float) Math.abs(CSG.R.nextGaussian()) * Stats.U6;
		heightSpeed = (float) Math.abs(CSG.R.nextGaussian()) * Stats.U6;
		alpha = 0.1f + CSG.R.nextFloat() / 10;
		return this;
	}

	public static void clear() {
		POOL.freeAll(SMOKE);
		SMOKE.clear();
	}

}
