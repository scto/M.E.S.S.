package elements.generic.enemies.individual.lvl3;

import jeu.CSG;
import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.individual.lvl1.Shooter;

public class Shooter3 extends Shooter {
	
	public static final int WIDTH = Stats.WIDTH_QUI_TIR3, HALF_WIDTH = WIDTH/2, HEIGHT = Stats.HEIGHT_QUI_TIR3, HALF_HEIGHT = HEIGHT / 2, LVL = 3, HP = getModulatedPv(Stats.HP_QUI_TIR, LVL), DEMI_HP = HP / 2, XP = getXp(BASE_XP, LVL);
	public static final Pool<Shooter3> POOL = Pools.get(Shooter3.class);
	private static final float SPEED = getModulatedSpeed(Shooter.SPEED, LVL), FIRERATE = Shooter.FIRERATE * 0.5f;
	private int shotNumber = 0;
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.FIREBALL,				Shot.SHOT_DOWN_RAND_RAFALE,				Animations.SHOOTER_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.FIREBALL,				Shot.SHOT_DOWN_RAND_RAFALE,				Animations.SHOOTER_BAD				)		};
	
	@Override	public float getBulletSpeedMod() {
		return super.getBulletSpeedMod()*100;	
	}
	@Override	protected String getLabel() {		return getClass().toString();	}
	@Override	public int getNumberOfShots() {		return CSG.R.nextInt(4);		}
	@Override	public float getHalfHeight() {		return HALF_HEIGHT;				}
	@Override	public float getHalfWidth() {		return HALF_WIDTH;				}
	@Override	public int getShotNumber() {		return shotNumber;				}
	@Override	public void free() {				POOL.free(this);				}
	@Override	public void addShots(int i) {		shotNumber += i;				}
	@Override	public float getFirerate() {		return FIRERATE;				}
	@Override	protected int getDemiPv() {			return DEMI_HP;					}
	@Override	public int getBonusValue() {		return BASE_XP;					}
	@Override	public Phase[] getPhases() {		return PHASES;					}
	@Override	public float getHeight() {			return HEIGHT;					}
	@Override	public float getSpeed() {			return SPEED;					}		 
	@Override	public float getWidth() {			return WIDTH;					}
	@Override	public int getXp() {				return XP;						}
	@Override	protected int getMaxHp() {			return HP;						}
}
