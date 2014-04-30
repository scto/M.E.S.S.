package elements.particular.particles;

import jeu.mode.EndlessMode;

import com.badlogic.gdx.math.Vector2;

import elements.particular.particles.individual.PrecalculatedParticles;
import elements.particular.particles.individual.explosions.SparklesColorOverTime;

public class OvaleParticuleGenerator {
	
	private Vector2 positionEmiter = new Vector2();
	private final float SHORT = .1f;
	private float angle;
	
	public void grow(float f) {
		positionEmiter.scl(f);
	}
	
	public OvaleParticuleGenerator(float hauteur) {
		init(hauteur);
	}

	public void init(float hauteur) {
		positionEmiter.x = hauteur / 1.6f;
		positionEmiter.y = 0;
	}

	public void add(float centreX, float centreY) {
		positionEmiter.rotate(EndlessMode.delta15 * 40);
		angle = positionEmiter.angle();
		SparklesColorOverTime.add(
				(centreX - SparklesColorOverTime.HALF_HEIGHT) + positionEmiter.x,
				centreY + positionEmiter.y,
				angle,
				PrecalculatedParticles.colorsOverTimeBlue);
		SparklesColorOverTime.add(
				(centreX - SparklesColorOverTime.HALF_HEIGHT) - positionEmiter.x,
				centreY - positionEmiter.y,
				angle,
				PrecalculatedParticles.colorsOverTimeGreen);
		if (!EndlessMode.triggerStop) {
			SparklesColorOverTime.add(
					(centreX - SparklesColorOverTime.HALF_HEIGHT) - positionEmiter.x,
					centreY + positionEmiter.y,
					angle,
					PrecalculatedParticles.colorsOverTimeBlue);
			SparklesColorOverTime.add(
					(centreX - SparklesColorOverTime.HALF_HEIGHT) + positionEmiter.x,
					centreY - positionEmiter.y,
					angle,
					PrecalculatedParticles.colorsOverTimeGreen);
		}
	}
	
}
