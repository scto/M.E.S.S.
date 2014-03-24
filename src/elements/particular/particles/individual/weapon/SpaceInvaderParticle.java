package elements.particular.particles.individual.weapon;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.weapons.player.SpaceInvaderWeapon;
import elements.particular.particles.Particles;

public class SpaceInvaderParticle implements Poolable {
	
	private static final Pool<SpaceInvaderParticle> POOL = new Pool<SpaceInvaderParticle>(100) {
		@Override
		protected SpaceInvaderParticle newObject() {
			return new SpaceInvaderParticle();
		}
	};
	private float x, y;
	private float a, g, b;
	private static float sb = 3;
	private static boolean upB = true;
	private final Vector2 dir = new Vector2();
	private boolean clockwise = false;
	
	@Override
	public void reset() {	}

	public static void init(SpaceInvaderWeapon e) {
		final SpaceInvaderParticle p = POOL.obtain();
		p.x = e.pos.x + SpaceInvaderWeapon.halfWidth;
		p.y = e.pos.y + SpaceInvaderWeapon.halfWidth;
		Particles.SPACE_INVADER.add(p);
		p.dir.y = Stats.U270;
		p.dir.x = 0;
		p.dir.rotate(CSG.R.nextFloat()*360);
		p.a = 1;
		p.g = 1;
		p.b = sb / (((CSG.R.nextFloat()+1)/2) + 3);
		p.clockwise = CSG.R.nextBoolean();
	}

	private static void colors() {
		if (upB) 				sb += 0.001f;
		else 					sb -= 0.001f;
		if (sb >= 3.99f)		upB = false;
		else if (sb <= 3.01f)	upB = true;
	}
	
	public static void act(Array<SpaceInvaderParticle> explosions, SpriteBatch batch) {
		colors();
		for (final SpaceInvaderParticle p : explosions) {
			batch.setColor(0, p.g, p.b, p.a);
			p.a -= 0.04f;
			p.g *= 0.9f; 
			batch.draw(AssetMan.dust, p.x, p.y, SpaceInvaderWeapon.particle, SpaceInvaderWeapon.particle);
			
			if (EndlessMode.triggerStop)
				continue;
			
			p.x += p.dir.x * EndlessMode.deltaDiv3;
			p.y += p.dir.y * EndlessMode.deltaDiv3;
			
   			if (p.clockwise)	p.dir.rotate(40);
			else				p.dir.rotate(-40);
   			p.dir.scl(0.90f);
   			
			if (p.a < 0.4f) {
				POOL.free(p);
				explosions.removeValue(p, true);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<SpaceInvaderParticle> explosions) {
		POOL.freeAll(explosions);
		explosions.clear();
	}
}
