package elements.generic.components;

import jeu.Physic;
import jeu.mode.EndlessMode;
import assets.sprites.Animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;

public class Phase {
	
	public final Behavior behavior;
	public final Gatling bullet;
	public final Shot shot;
	public final Animations anim;
	
	public Phase(Behavior behavior, Gatling bullet, Shot shot, Animations anim) {
		if (behavior == null)
			behavior = Behavior.NOTHING;
		this.behavior = behavior;
		this.bullet = bullet;
		if (shot == null)
			shot = Shot.NOTHING;
		this.shot = shot;
		this.anim = anim;
	}
	
	public void shoot(PhaseUser u) {
		if (u.getPhaseTime() > u.getNextShot()) {
			u.setNextShot(u.getPhaseTime() + u.getFirerate()); 
			shot.implementation.shoot(u);
		}
	}
	
	/**
	 * Return true if the user is out of the screen
	 * @param u
	 * @param batch
	 * @return
	 */
	public boolean move(PhaseUser u) {
		if (Physic.isOnScreenWithTolerance(u.getPosition(), u.getHeight(), u.getWidth()) == false) {
			return true;
		} else {
			u.setPhaseTime(u.getPhaseTime() + EndlessMode.delta);
			behavior.act(u);
			return false;
		}
	}
	
	public void draw(PhaseUser u, SpriteBatch batch) {
		batch.draw(anim.anim.getTexture(u.getPhaseTime()), u.getPosition().x, u.getPosition().y, u.getHalfWidth(), u.getHalfHeight(), u.getWidth(), u.getHeight(), 1, 1, u.getAngle());
	}
	
}
