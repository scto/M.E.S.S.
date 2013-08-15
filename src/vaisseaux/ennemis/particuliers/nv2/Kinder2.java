package vaisseaux.ennemis.particuliers.nv2;

import jeu.Stats;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.Kinder;

import com.badlogic.gdx.math.Rectangle;

public class Kinder2 extends Kinder {

	private static Tirs tir = new Tirs(.4f); 

	public Kinder2() {
		super();
		pv = Stats.PV_KINDER2;
	}
	
	@Override
	public void reset() {
		super.reset();
		pv = Stats.PV_KINDER2;
	}
	
	@Override
	protected void tir() {
		tir.tirToutDroit(this, mort, maintenant, prochainTir);
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.KINDER2.COUT;
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}
}

