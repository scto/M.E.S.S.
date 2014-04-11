package elements.particular.particles.individual;

import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Player;
import elements.particular.particles.Particles;

public class ShieldParticle implements Poolable {
	
	private float x, y, time;
	private final static float WIDTH = Stats.u;
	public static final float HALF_WIDTH = WIDTH / 2;
	private static final Pool<ShieldParticle> POOL = Pools.get(ShieldParticle.class);
	private static final ShapeRenderer renderer = new ShapeRenderer();
	private static float angle = 0;

	public static void add(float x, float y) {
		ShieldParticle p = POOL.obtain();
		p.x = x;
		p.y = y;
		p.time = EndlessMode.now + .1f;
		Particles.SHIELD.add(p);
	}
	public static void addLong(float x, float y) {
		ShieldParticle p = POOL.obtain();
		p.x = x;
		p.y = y;
		p.time = EndlessMode.now + .3f;
		Particles.SHIELD.add(p);
	}

	@Override
	public void reset() {}

	public static void act(SpriteBatch batch, Array<ShieldParticle> particles) {
		batch.setColor(.9f, .9f - Player.alphaShield, 1, 1);
		for (final ShieldParticle p : particles) {
			batch.draw(AssetMan.dust, p.x, p.y, WIDTH, WIDTH);
			if (p.time < EndlessMode.now) {
				particles.removeValue(p, true);
				POOL.free(p);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}
	
//	public static void act(SpriteBatch batch, Array<ShieldParticle> particles) {
//		angle += EndlessMode.delta15 * 4;
//		batch.end();
//		renderer.begin(ShapeType.Line);
////		if (Player.bouclier) {
////			renderer.cone(Player.xCenter, Player.yCenter, 0, Player.LARGEUR, Player.HAUTEUR, CSG.R.nextInt(10) + 6);
////			renderer.setColor(Color.BLUE);
////			renderer.cone(Player.xCenter, Player.yCenter, 0, Player.LARGEUR, Player.HAUTEUR, CSG.R.nextInt(10) + 6);
////			renderer.setColor(Color.CLEAR);
////			renderer.cone(Player.xCenter, Player.yCenter, 0, Player.LARGEUR, Player.HAUTEUR, CSG.R.nextInt(10) + 6);
//			renderer.setColor(Color.CYAN);
//			for (int i = 0; i < 180; i = i+20)
//				renderer.rect(Player.POS.x - Player.WIDTH_DIV_10, Player.POS.y - Player.WIDTH_DIV_10, Player.HAUTEUR, Player.HAUTEUR, Player.DEMI_HAUTEUR, Player.DEMI_HAUTEUR, angle + i);
////		}
////		for (final ShieldParticle p : particles) {
//////			renderer.rect(p.x, p.y, WIDTH, WIDTH, HALF_WIDTH, HALF_WIDTH, 90, Color.BLUE, Color.CYAN, Color.BLUE, Color.CYAN);
////			renderer.cone(p.x, p.y + HALF_WIDTH, 0, WIDTH, WIDTH, 6);
////			if (p.time < EndlessMode.now) {
////				particles.removeValue(p, true);
////				POOL.free(p);
////			}
////		}
//		renderer.end();
//		batch.begin();
//	}

	public static void clear(Array<ShieldParticle> particles) {
		POOL.freeAll(particles);
		particles.clear();
	}

}
