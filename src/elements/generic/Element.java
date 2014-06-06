package elements.generic;

import jeu.CSG;
import jeu.db.Requests;

import com.badlogic.gdx.math.Vector2;

import elements.generic.components.Phase;
import elements.generic.components.PhaseUser;
import elements.generic.components.positionning.LeftRight;
import elements.generic.components.positionning.Middle;
import elements.generic.components.positionning.Pos;
import elements.generic.components.positionning.Sides;
import elements.generic.components.positionning.Up;
import elements.generic.components.positionning.UpWide;

public abstract class Element implements PhaseUser {
	
	public final Vector2 pos = new Vector2(CSG.gameZoneHalfWidth, CSG.SCREEN_HEIGHT), dir = new Vector2(0,-1);
	protected int index = 0;
	public abstract float getWidth();
	public abstract float getHeight();
	public abstract float getHalfWidth();
	public abstract float getHalfHeight();
	public float now = 0, phaseTime = 0;
	
	public Vector2 getDirection() {
		return dir;
	}
	
	public float getNow() {
		return now;
	}
	
	public Vector2 getPosition() {
		return pos;
	}
	
	public void reset() {
		phaseTime = 0;
		now = 0;
	}
	
	@Override
	public Phase getPhase() {
		return getPhases()[index];
	}

	private static Pos detectPos(int b) {
		switch (b) {
		case Up.PK: 				return new Up();
		case UpWide.PK: 			return new UpWide();
		case Middle.PK: 			return new Middle();
		case LeftRight.PK: 			return new LeftRight();
		case Sides.PK: 			return new Sides();
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
	@Override	public void setWay(boolean b) {	}
	@Override	public boolean getWay() {		return false;	}
	@Override	public float getPhaseTime() {		return phaseTime;	}
	@Override	public float getNextShot() {		return 0;	}
	@Override	public float getFloatFactor() {		return 0;	}
	@Override	public int getNumberOfShots() {		return 0;	}
	@Override	public float getShootingAngle() {		return 0;	}
	@Override	public boolean isBoss() {		return false;	}
	@Override	public Vector2 getShootingDir() {		return null;	}
	@Override	public int getShotNumber() {		return 0;	}
	@Override	public void addShots(int i) {	}
	@Override	public int getNumberOfShotBeforeDirChange() {		return 0;	}
	@Override	public void setShotDir(boolean b) {	}
	@Override	public boolean getShotDir() {		return false;	}
	@Override	public float getShotsGap() {		return 0;	}
	@Override	public int getIntFactor() {		return 0;	}
	@Override	public float getBulletSpeedMod() {		return 0;	}
	@Override	public float getFirerate() {				return 100;	}
	@Override	public void setNextShot(float f) {							}
	@Override	public Vector2 getShotPosition(int i) {		return null;	}
	@Override	public float getAngle() {		return 0;	}
	@Override	public boolean getShotWay() {		return false;	}
	@Override	public void setShotWay(boolean way) {	}
	@Override	public float getMaxShotAngle() {		return 10;	}
	@Override	public float getShotTotalAngle() {		return 90;	}
	protected void changePhase() {
		index++;
		phaseTime = 0;
	}
}
