package elements.generic.components;

import com.badlogic.gdx.math.Vector2;

public interface PhaseUser {
	
	Vector2 getPosition();
	Vector2 getDirection();
	void setWay(boolean b);
	boolean getWay();
	float getNow();
	float getSpeed();
	float getNextShot();
	float getPhaseTime();
	float getHalfWidth();
	float getBulletSpeedMod();
	float getFloatFactor();
	Phase getPhase();
	void setNextShot(float f);
	int getNumberOfShots();
	int getShotNumber();
	float getShootingAngle();
	boolean isBoss();
	Vector2 getShootingDir();
	Vector2 getShotPosition(int i);
	void addShots(int i);
	int getNumberOfShotBeforeDirChange();
	void setShotDir(boolean b);
	boolean getShotDir();
	float getShotsGap();
	int getIntFactor();
	float getFirerate();
	float getHalfHeight();
	float getWidth();
	float getHeight();
	float getAngle();
	void setPhaseTime(float f);
	void setAngle(float shootingAngle);
	void setShootingAngle(float shootingAngle);
	boolean getShotWay();
	float getMaxShotAngle();
	void setShotWay(boolean way);
	float getShotTotalAngle();
	float getXMeter();
	void setXMeter(float f);
	float getYMeter();
	void setYMeter(float f);

}
