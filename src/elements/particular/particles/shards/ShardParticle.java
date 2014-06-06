package elements.particular.particles.shards;

import jeu.CSG;
import jeu.Stats;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.enemies.Enemy;

public class ShardParticle implements Poolable {
	
	private float x, y, width, color, dirX, dirY, height, blue;
	private static final Vector2 tmpVector = new Vector2();
	private boolean xp;
	private int ttl;
	
	public static final Pool<ShardParticle> POOL = new Pool<ShardParticle>(500) {
		@Override
		protected ShardParticle newObject() {
			return new ShardParticle();
		}
	};
	
	public static void add(Array<ShardParticle> shards, ShardMatrix matrix, Enemy e) {
		int cpt = 0;
//		if (Math.abs(90 - e.angle) < 5) {
//			for (int i = 0; i < matrix.shards.length; i++) {
//				shards.add(POOL.obtain().init(matrix.shards[i], e, matrix.pixelWidth, matrix.pixelHeight));
//				cpt++;
//			}
//		} else {
			for (int i = 0; i < matrix.shards.length; i++) {
				shards.add(POOL.obtain().initRotation(matrix.shards[i], e, matrix.pixelWidth, matrix.pixelHeight, CSG.R.nextBoolean()));
				cpt++;
			}
//		}
	}
	
	
	private ShardParticle initRotation(ShardModel shard, Enemy e, float pixelWidth, float pixelHeight, boolean xp) {
//		System.out.println("INIT ROTATION SHARD PARTICLES");
		// put in center
		tmpVector.set(shard.initX - e.getHalfWidth(), shard.initY - e.getHalfHeight());
		tmpVector.rotate(90 + e.angle);
		
		x = e.pos.x + tmpVector.x + e.getHalfWidth();
		y = e.pos.y + tmpVector.y + e.getHalfHeight();
		color = shard.initColor;
//		tmpVector.nor();
//		dirX = shard.initDirX * tmpVector.x;
//		dirY = shard.initDirY * tmpVector.y;
		dirY = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICLE_EXPLOSION_SLOW) + e.getDirectionY() * CSG.R.nextFloat());
		dirX = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICLE_EXPLOSION_SLOW) + e.getDirectionX() * CSG.R.nextFloat());
		
		dirX += shard.initDirX;
		dirY += shard.initDirY ;
				
		width = pixelWidth*3;
		height = pixelHeight*3;
		blue = 0;
		ttl = 30 + CSG.R.nextInt(20);
		this.xp = xp;
		return this;
	}

	private ShardParticle init(ShardModel shard, Enemy e, float pixelWidth, float pixelHeight) {
		x = e.pos.x + shard.initX;
		y = e.pos.y + shard.initY;
		color = shard.initColor;
		dirX = shard.initDirX;
		dirY = shard.initDirY;
		
		dirY = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICLE_EXPLOSION_SLOW) + e.getDirectionY() * CSG.R.nextFloat());
		dirX = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICLE_EXPLOSION_SLOW) + e.getDirectionX() * CSG.R.nextFloat());
		
		dirX += shard.initDirX * Stats.U;
		dirY += shard.initDirY * Stats.U;
		
		width = pixelWidth*2;
		height = pixelHeight*2;
		blue = 0;
		ttl = 80 + CSG.R.nextInt(20);
		return this;
	}

	public static void act(Array<ShardParticle> shards, SpriteBatch batch) {
//		if (EndlessMode.alternate && !EndlessMode.triggerStop) {
//			for (ShardParticle s : shards) {
//				batch.setColor(AssetMan.setAlpha(s.color, s.blue));
//				batch.draw(AssetMan.debris, s.x, s.y, s.width, s.height);
//				
//				if (s.ttl < 15) {
//					batch.setColor(1,1,1,s.blue);
//					batch.draw(AssetMan.xp, s.x, s.y, s.width, s.height);
//					s.blue += 0.05f;
//					if (s.blue > 1)
//						s.blue = 1;
//				}
//				
//				s.x += s.dirX * EndlessMode.delta;
//				s.y += s.dirY * EndlessMode.delta;
////				
//				s.dirX /= EndlessMode.unPlusDelta3;
//				s.dirY /= EndlessMode.unPlusDelta3;
////				
//				if (--s.ttl <= 0) {
//					shards.removeValue(s, true);
//					POOL.free(s);
//					if (s.xp)
//						XP.POOL.obtain().init(s.x, s.y, 1, s.width, s.height);
//				}
//			}
//		} else {
//			for (ShardParticle s : shards) {
//				batch.setColor(s.color);
//				batch.draw(AssetMan.debris, s.x, s.y, s.width, s.width);
//				if (s.ttl < 15) {
//					batch.setColor(1,1,1,s.blue);
//					batch.draw(AssetMan.xp, s.x, s.y, s.width, s.height);
//				}
//			}
//		}
//		batch.setColor(AssetMan.WHITE);
	}


	@Override
	public void reset() {
	}
	
	public static void clear(Array<ShardParticle> explosions) {
		POOL.freeAll(explosions);
		explosions.clear();
	}

}
