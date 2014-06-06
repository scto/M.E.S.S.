package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import assets.AssetMan;
import assets.sprites.Animations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.FragWeapon;


public class QuiTirTriangle extends QuiTir {
	
	private static final int 
		WIDTH = Stats.WIDTH_QUI_TIR2, 
		HALF_WIDTH = WIDTH/2, 
		HEIGHT = Stats.HEIGHT_QUI_TIR2, 
		HALF_HEIGHT = HEIGHT / 2; 
	public static final Pool<QuiTirTriangle> POOL = Pools.get(QuiTirTriangle.class);
	private static final float 
		COLOR = AssetMan.convertARGB(1, 1, 1, .7f),
		xOffset = HALF_WIDTH - FragWeapon.HALF_WIDTH;
	
	public static final int PK = 11;
	protected static final int BASE_XP = Enemy.initXp(39, PK);
	protected static final float 
		FIRERATE = initFirerate(0.7f, PK),
		SPEED = initSpeed(12, PK);
	private static final int 
		HP = initHp(Stats.HP_DE_BASE_QUI_TIR_TRIANGLE, PK), 
		HALF_HP = HP/2,
		EXPLOSION = initExplosion(50, PK),
		XP = getXp(BASE_XP, 1);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.FRAG,				Shot.SHOT_DOWN,				Animations.QUI_TIR_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.FRAG,				Shot.SHOT_DOWN,				Animations.QUI_TIR_BAD				),
		};

	@Override
	public Vector2 getShotPosition(int numeroTir) {
		TMP_POS.x = pos.x + xOffset;
		TMP_POS.y = pos.y - FragWeapon.WIDTH;
		return TMP_POS;
	}
	
	@Override	public Phase[] getPhases() {				return PHASES;	}
	@Override 	public float getFirerate() {				return FIRERATE;								}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	protected int getMaxHp() {					return HP;	}
	@Override	protected int getDemiPv() {					return HALF_HP;}
	@Override	public float getSpeed() {					return SPEED;	}
	@Override	protected float getDerive() {				return Stats.DERIVE_DE_BASE_QUI_TIR2;	};
	@Override	public int getXp() {						return XP;	}
	@Override	public int getBonusValue() {				return BASE_XP;	}
	@Override	public float getHeight() {					return HEIGHT;	}
	@Override	public float getWidth() {					return WIDTH;	}
	@Override	public float getHalfHeight() {				return HALF_HEIGHT;	}
	@Override	public float getHalfWidth() {				return HALF_WIDTH;	}
	@Override	public float getBulletSpeedMod() {			return 1;	}
	@Override	public int getExplosionCount() {			return EXPLOSION;									}
}
