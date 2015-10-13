package elements.generic;

import jeu.CSG;
import assets.sprites.AnimUser;
import assets.sprites.Animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import elements.generic.components.Dimensions;
import elements.generic.components.behavior.Mover;

public abstract class Element implements AnimUser {
	
	public final Vector2 pos = new Vector2(CSG.halfWidth, CSG.height), dir = new Vector2(0,-1);
	public float now = 0, angle = 0;
	
	public void reset() {
		now = 0;
		angle = 0;
	}
	public abstract Dimensions getDimensions();
	public abstract Animations getAnimation();
	public void setAngle(float angle) {								this.angle = angle;				}
	public float getAngle() {										return angle;					}
	public float getSlowFactor() {									return 0.94f;					}
	public float getNow() {											return now;						}
	public Vector2 getPosition() {									return pos;						}
	public Vector2 getDirection() {									return dir;						}
	public boolean getWay() {										return true;					}
	@Override	public boolean isInGoodShape() {					return false;					}
	@Override	public int getAnimIndex() {							return 0;						}
	@Override	public float getPvPercentage() {					return 0;						}
	
	public Rectangle getRectangleCollision(Rectangle r) {
		return r.set(pos.x, pos.y, getDimensions().width, getDimensions().height);
	}
	
	protected void removeColor(SpriteBatch batch) {	}
	protected void setColor(SpriteBatch batch) {	}

	protected void displayOnScreen(SpriteBatch batch) {
		setColor(batch);
		batch.draw(getAnimation().anim.getTexture(this), pos.x, pos.y, getDimensions().halfWidth, getDimensions().halfHeight, getDimensions().width, getDimensions().height, 1, 1, angle);
		removeColor(batch);
	}
	
	protected void move() {
		Mover.straight(this);
	}
	
}
