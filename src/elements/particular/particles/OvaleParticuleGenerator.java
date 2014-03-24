package elements.particular.particles;

import jeu.EndlessMode;

import com.badlogic.gdx.math.Vector2;

import elements.particular.particles.individual.ShieldParticle;

public class OvaleParticuleGenerator {
	
	private Vector2 positionEmiter = new Vector2();
	
	public void grow(float f) {
		positionEmiter.scl(f);
	}
	
	public OvaleParticuleGenerator(float hauteur) {
		init(hauteur);
	}

	public void init(float hauteur) {
		positionEmiter.x = hauteur/2;
		positionEmiter.y = 0;
	}

	public void add(float centreX, float centreY) {
		positionEmiter.rotate(EndlessMode.delta15 * 80);
		ShieldParticle.add(
				(centreX - ShieldParticle.HALF_WIDTH/2) + positionEmiter.x,
				centreY + positionEmiter.y);
		ShieldParticle.add(
				(centreX - ShieldParticle.HALF_WIDTH/2) - positionEmiter.x,
				centreY - positionEmiter.y);
	}
	
	
	// x + (positionEmiter.x - (hauteur - positionEmiter.y) / 2) 
}
