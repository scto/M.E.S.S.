package elements.generic;

import jeu.CSG;
import jeu.db.Requests;
import assets.sprites.Animations;

import com.badlogic.gdx.math.Vector2;

import elements.generic.components.Phase;
import elements.generic.components.PhaseUser;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.positionning.LeftRight;
import elements.generic.components.positionning.Middle;
import elements.generic.components.positionning.Pos;
import elements.generic.components.positionning.Sides;
import elements.generic.components.positionning.Up;
import elements.generic.components.positionning.UpWide;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;

public abstract class Element implements PhaseUser {
	
	public final Vector2 pos = new Vector2(CSG.gameZoneHalfWidth, CSG.SCREEN_HEIGHT), dir = new Vector2(0,-1);
	protected int index = 0;
	public float now = 0, phaseTime = 0;
	
	public void reset() {
		phaseTime = 0;
		now = 0;
	}

	private static Pos detectPos(int b) {
		switch (b) {
		case Up.PK: 				return new Up();
		case UpWide.PK: 			return new UpWide();
		case Middle.PK: 			return new Middle();
		case LeftRight.PK: 			return new LeftRight();
		case Sides.PK: 				return new Sides();
		}
		return null;
	}
	
	protected static Pos initPositionnement(int def, int pk) {
		if (CSG.updateNeeded)
			return detectPos(Requests.getPositionnement(pk, def));
		return detectPos(def);
	}
	@Override
	public void setPhaseTime(float f) {
		phaseTime = f;
	}
	public abstract Phase[] getPhases();
	public abstract float getWidth();
	public abstract float getHeight();
	public abstract float getHalfWidth();
	public abstract float getHalfHeight();
	public float getNow() {											return now;				}
	public Vector2 getPosition() {									return pos;				}
	public Vector2 getDirection() {									return dir;				}
	@Override	public void addShots(int i) {												}
	@Override	public void setWay(boolean b) {												}
	@Override	public void setShotDir(boolean b) {											}
	@Override	public Phase getPhase() {							return getPhases()[index];	}
	@Override	public float getBulletSpeedMod() {					return getSpeed() * 2;	}
	@Override	public float getPhaseTime() {						return phaseTime;		}
	@Override	public boolean getShotDir() {						return false;			}
	@Override	public boolean getShotWay() {						return false;			}
	@Override	public boolean getWay() {							return false;			}
	@Override	public boolean isBoss() {							return false;			}
	@Override	public Vector2 getShotPosition(int i) {				return pos;				}
	@Override	public float getFirerate() {						return 100;				}
	@Override	public float getMaxShotAngle() {					return 10;				}
	@Override	public float getShotTotalAngle() {					return 90;				}
	@Override	public float getAngle() {							return 0;				}
	@Override	public float getXMeter() {							return 0;				}
	@Override	public float getYMeter() {							return 0;				}
	@Override	public int getIntFactor() {							return 0;				}
	@Override	public int getShotNumber() {						return 0;				}
	@Override	public float getNextShot() {						return 0;				}
	@Override	public float getShotsGap() {						return 0;				}
	@Override	public float getFloatFactor() {						return 0;				}
	@Override	public int getNumberOfShots() {						return 0;				}
	@Override	public float getShootingAngle() {					return 0;				}
	@Override	public int getNumberOfShotBeforeDirChange() {		return 0;				}
	@Override	public void setShotWay(boolean way) {										}
	@Override	public void setNextShot(float f) {											}
	@Override	public void setXMeter(float f) {											}
	@Override	public void setYMeter(float f) {											}
	@Override	public Vector2 getShootingDir() {
		CSG.tmpDir.x = dir.x;
		CSG.tmpDir.y = dir.y;
		return CSG.tmpDir.nor();	
	}
	
	protected static Phase initPhases(int pk, int def) {
		int pkPhases = Requests.getPhases(pk, def);
		int pkBehavior = Requests.getBehaviorFromPhase(pkPhases);
		int pkAnimation = Requests.getAnimationFromPhase(pkPhases);
		int pkShot = Requests.getShotFromPhase(pkPhases);
		int pkWeapon = Requests.getWeaponFromPhase(pkPhases);
		return new Phase(detectBehavior(pkBehavior), detectWeapon(pkWeapon), detectShot(pkShot), detectAnimation(pkAnimation));
	}
	
	protected void changePhase() {
		index++;
		phaseTime = 0;
	}
	
	protected static Behavior initBehavior(int def, int pk) {
		if (CSG.updateNeeded) 
			return detectBehavior(Requests.getBehavior(pk, def));
		return detectBehavior(def);
	}
	
	protected static Gatling initWeapon(int def, int pk) {
		if (CSG.updateNeeded) 
			return detectWeapon(Requests.getWeapon(pk, def));
		return detectWeapon(def);
	}
	
	protected static Shot initShot(int def, int pk) {
		if (CSG.updateNeeded) 
			return detectShot(Requests.getShot(pk, def));
		return detectShot(def);
	}
	
	protected static Animations initAnimation(int def, int pk) {
		if (CSG.updateNeeded) 
			return detectAnimation(Requests.getAnimation(pk, def));
		return detectAnimation(def);
	}
	
	private static Shot detectShot(int i) {
		if (i == Shot.SHOT_DOWN_MULTIPLE_WITH_BREAK.pk)				return Shot.SHOT_DOWN_MULTIPLE_WITH_BREAK;
		if (i == Shot.DOUBLE_SHOT_DOWN_RAND_RAFALE.pk)				return Shot.DOUBLE_SHOT_DOWN_RAND_RAFALE;
		if (i == Shot.TIR_TOUT_DROIT_DOUBLE_RAND.pk)				return Shot.TIR_TOUT_DROIT_DOUBLE_RAND;
		if (i == Shot.SHOT_EN_RAFALE_LEFT_RIGHT.pk)					return Shot.SHOT_EN_RAFALE_LEFT_RIGHT;
		if (i == Shot.DOUBLE_SHOT_DOWN_RAND.pk)						return Shot.DOUBLE_SHOT_DOWN_RAND;
		if (i == Shot.SHOT_SWEEP_WITH_BREAK.pk)						return Shot.SHOT_SWEEP_WITH_BREAK;
		if (i == Shot.SHOT_DOWN_RAND_RAFALE.pk)						return Shot.SHOT_DOWN_RAND_RAFALE;
		if (i == Shot.SHOT_VERS_JOUEUR_RAND.pk)						return Shot.SHOT_VERS_JOUEUR_RAND;
		if (i == Shot.SHOT_DOWN_MULTIPLE.pk)						return Shot.SHOT_DOWN_MULTIPLE;
		if (i == Shot.TIRS_VERS_BAS_RAND.pk)						return Shot.TIRS_VERS_BAS_RAND;
		if (i == Shot.DOUBLE_SHOT_DOWN.pk)							return Shot.DOUBLE_SHOT_DOWN;
		if (i == Shot.SHOT_EN_RAFALE.pk)							return Shot.SHOT_EN_RAFALE;
		if (i == Shot.SHOT_ON_PLAYER.pk)							return Shot.SHOT_ON_PLAYER;
		if (i == Shot.TIR_TOUT_DROIT.pk)							return Shot.TIR_TOUT_DROIT;
		if (i == Shot.SHOT_EVENTAIL.pk)								return Shot.SHOT_EVENTAIL;
		if (i == Shot.TIR_SUR_COTES.pk)								return Shot.TIR_SUR_COTES;
		if (i == Shot.SHOT_DOWN.pk)									return Shot.SHOT_DOWN;
		if (i == Shot.SHOTGUN.pk)									return Shot.SHOTGUN;
		if (i == Shot.SWEEP.pk)										return Shot.SWEEP;
		if (i == Shot.V.pk)											return Shot.V;
		return Shot.NOTHING;
	}

	private static Animations detectAnimation(int i) {
		if (i == Animations.BASIC_ENEMY_RED.pk)				return Animations.BASIC_ENEMY_RED;
		if (i == Animations.BASIC_ENEMY_BLUE.pk)			return Animations.BASIC_ENEMY_BLUE;
		if (i == Animations.BASIC_ENEMY_GREEN.pk)			return Animations.BASIC_ENEMY_GREEN;
		if (i == Animations.BALL.pk)						return Animations.BALL;
		return Animations.BASIC_ENEMY_RED;
	}

	private static Behavior detectBehavior(int b) {
		if (b == Behavior.STRAIGHT_ON_DETECT.pk)	return Behavior.STRAIGHT_ON_DETECT;
		if (b == Behavior.COMMA_RIGHT_MUL2.pk)		return Behavior.COMMA_RIGHT_MUL2;
		if (b == Behavior.ROTATE_STAY_TOP.pk)		return Behavior.ROTATE_STAY_TOP;
		if (b == Behavior.ROUND_N_ROUND.pk)			return Behavior.ROUND_N_ROUND;
		if (b == Behavior.GO_ON_PLAYER.pk)			return Behavior.GO_ON_PLAYER;
		if (b == Behavior.TURN_AROUND.pk)			return Behavior.TURN_AROUND;
		if (b == Behavior.TURN_AROUND.pk)			return Behavior.TURN_AROUND;
		if (b == Behavior.OSCILLATE_X.pk)			return Behavior.OSCILLATE_X;
		if (b == Behavior.UMBRELLA.pk)				return Behavior.UMBRELLA;
		if (b == Behavior.CENTER_X.pk)				return Behavior.CENTER_X;
		if (b == Behavior.STAY_TOP.pk)				return Behavior.STAY_TOP;
		if (b == Behavior.ZIG_ZAG.pk)				return Behavior.ZIG_ZAG;
		if (b == Behavior.GO_AWAY.pk)				return Behavior.GO_AWAY;
		if (b == Behavior.NOTHING.pk)				return Behavior.NOTHING;
		if (b == Behavior.U_TURN.pk)				return Behavior.U_TURN;
		if (b == Behavior.ON_TOP.pk)				return Behavior.ON_TOP;
		if (b == Behavior.ROTATE.pk)				return Behavior.ROTATE;
		if (b == Behavior.COMMA.pk)					return Behavior.COMMA;
		if (b == Behavior.OVALE.pk)					return Behavior.OVALE;
		if (b == Behavior.SINK.pk) 					return Behavior.SINK;
		if (b == Behavior.SLOW.pk)					return Behavior.SLOW;
		
		return Behavior.STRAIGHT_ON;
	}
	
	private static Gatling detectWeapon(int b) {
		if (b == Gatling.BLUE_BULLET_SLOW.pk) 	return Gatling.BLUE_BULLET_SLOW;
		if (b == Gatling.BLUE_BULLET_FAST.pk) 	return Gatling.BLUE_BULLET_FAST;
		if (b == Gatling.SMALL_FIREBALL.pk) 	return Gatling.SMALL_FIREBALL;
		if (b == Gatling.KINDER_WEAPON.pk) 		return Gatling.KINDER_WEAPON;
		if (b == Gatling.BLUE_BULLET.pk) 		return Gatling.BLUE_BULLET;
		if (b == Gatling.METEORITE.pk) 			return Gatling.METEORITE;
		if (b == Gatling.BOSS_MINE.pk) 			return Gatling.BOSS_MINE;
		if (b == Gatling.TOURNANTE.pk) 			return Gatling.TOURNANTE;
		if (b == Gatling.FIREBALL.pk) 			return Gatling.FIREBALL;
		if (b == Gatling.INSECT.pk) 			return Gatling.INSECT;
		if (b == Gatling.VICIOUS.pk) 			return Gatling.VICIOUS;
		if (b == Gatling.LASER.pk) 				return Gatling.LASER;
		if (b == Gatling.FRAG.pk) 				return Gatling.FRAG;
		if (b == Gatling.MINE.pk) 				return Gatling.MINE;
		return null;
	}
}
