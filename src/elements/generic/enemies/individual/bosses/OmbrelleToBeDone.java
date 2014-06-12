package elements.generic.enemies.individual.bosses;

import elements.generic.components.Phase;
import elements.generic.enemies.Enemy;

public class OmbrelleToBeDone extends Enemy {

	@Override
	public int getXp() {
		return 0;
	}

	@Override
	public void free() {
	}

	@Override
	protected int getMaxHp() {
		return 0;
	}

	@Override
	protected String getLabel() {
		return null;
	}

	@Override
	public int getBonusValue() {
		return 0;
	}

	@Override
	public float getDirectionY() {
		return 0;
	}

	@Override
	public int getExplosionCount() {
		return 0;
	}

	@Override
	public Phase[] getPhases() {
		return null;
	}

	@Override
	public float getWidth() {
		return 0;
	}

	@Override
	public float getHeight() {
		return 0;
	}

	@Override
	public float getHalfWidth() {
		return 0;
	}

	@Override
	public float getHalfHeight() {
		return 0;
	}
//	
//	public static final int WIDTH = (int) (CSG.screenWidth / 2.1f), HALF_WIDTH = WIDTH/2;
//	public static final int HEIGHT = (int) (WIDTH - (HALF_WIDTH/1.5f)), HALF_HEIGHT = HEIGHT / 2;
//	public static final float FIRERATE = 0.5f;
//	public static final Shot shot = new Shot(FIRERATE);
//	protected float nextShot = .1f;
//	public static Pool<Ombrelle> pool = Pools.get(Ombrelle.class);
//	private static final Pos positionnement = new Middle();
//	public static final Invocable ref = new Ombrelle();
//	private static final Behavior behavior = initBehaviors(99, Behavior.STRAIGHT_ON);
//	
//	public Ombrelle() {
//		super();
//		init();
//	}
//	
//	public void init() {
//		dir.x = 0;
//		dir.y = -CSG.halfHeight;
//		nextShot = .1f;
//		positionnement.set(this);
//	}
//	
//	@Override
//	public void reset() {
//		super.reset();
//		init();
//	}
//	
//	@Override
//	protected void shoot() {
//		shot.shotOmbrelle(this, 11, 10.8f, now, nextShot, 7);
//	}
//	@Override
//	protected float getAngle() {		return angle + 90;	}
//	@Override
//	protected TextureRegion getTexture() {
//		return AnimationOmbrelle.getTexture(hp);
//	}
//	@Override
//	protected int getMaxHp() {					return Stats.HP_OMBRELLE;	}
//	@Override
//	public int getXp() {						return 0;	}
//	@Override
//	protected Sound getExplosionSound() {		return SoundMan.bigExplosion;	}
//	@Override
//	public float getHeight() {			return HEIGHT;	}
//	@Override
//	public float getWidth() {			return WIDTH;	}
//	@Override
//	public float getHalfHeight() {		return HALF_HEIGHT;	}
//	@Override
//	public float getHalfWidth() {		return HALF_WIDTH;	}
//	@Override
//	public Invocable invoke() {
//		Ombrelle l = pool.obtain();
//		l.init();
//		LIST.add(l);
//		return l;
//	}
//	@Override
//	public void free() { pool.free(this);	}
//	@Override
//	public float getDirectionY() {		return dir.y;	}
//	@Override
//	public EnemyWeapon getWeapon() {		return SmallFireball.POOL.obtain();	}
//	@Override
//	public Vector2 getShotPosition(int numeroTir) {		
//		if (numeroTir != 6) {
//			TMP_POS.x = (pos.x + HALF_WIDTH) - SmallFireball.HALF_WIDTH;
//			TMP_POS.y = (pos.y + HALF_HEIGHT) - SmallFireball.HALF_WIDTH;
//			TMP_DIR.y = HALF_HEIGHT - SmallFireball.HALF_WIDTH;
//		} else {
//			TMP_POS.x = (pos.x + HALF_WIDTH) - Fireball.HALF_WIDTH;
//			TMP_POS.y = (pos.y + HALF_HEIGHT) - Fireball.HALF_HEIGHT;
//			TMP_DIR.y = HALF_HEIGHT ;
//		}
//		TMP_DIR.x = 0;
//		// On additionne pour parshot du haut de l'ennemi
//		TMP_DIR.rotate(angle+90);
//		TMP_POS.x += (TMP_DIR.x * (Stats.u/4f));
//		TMP_POS.y += (TMP_DIR.y * (Stats.u/4f));
//		return TMP_POS;
//	}
//	@Override
//	public float getBulletSpeedMod() {		return 200f;	}
//	@Override
//	public void setNextShot(float f) { nextShot = f;	}
//	@Override
//	public float getShootingAngle() {		return angle - 90;	}
//	@Override
//	public Vector2 getShootingDir() {		
//		TMP_DIR.x = dir.x;
//		TMP_DIR.y = dir.y;
//		TMP_DIR.nor();
//		return TMP_DIR;
//	}
//	
//	@Override
//	protected String getLabel() {
//		return getClass().toString();
//	}
//
//	@Override
//	public int getBonusValue() {
//		return 0;
//	}
//
//	@Override
//	public int getExplosionCount() {
//		return 100;
//	}
//
//	@Override
//	public Phase[] getPhases() {
//		return behavior;
//	}
}
