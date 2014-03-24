package elements.generic;

import jeu.CSG;
import jeu.db.Requests;
import assets.animation.AnimTWeapon;
import assets.animation.Animated;
import assets.animation.AnimationBouleBleu;
import assets.animation.AnimationMeteorite;
import assets.animation.AnimationMine;
import assets.animation.AnimationTirFeu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import elements.positionning.Kinder;
import elements.positionning.LeftRight;
import elements.positionning.Middle;
import elements.positionning.Pos;
import elements.positionning.Up;
import elements.positionning.UpWide;

public abstract class Element {
	
	public final Vector2 pos = new Vector2(CSG.gameZoneHalfWidth, CSG.SCREEN_HEIGHT);

	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getHalfWidth();
	public abstract int getHalfHeight();
	protected abstract TextureRegion getTexture();
	public float now = 0;
	
	public void reset() {
		now = 0;
	}

	protected static Animated initAnimation(int def, int pk) {
		if (CSG.updateNeeded)
			return detectAnimation(Requests.getAnimation(pk, def));
		return detectAnimation(def);
	}

	private static Animated detectAnimation(int b) {
		switch (b) {
		case Animated.BLUE_BULLET: 			return new AnimationBouleBleu();
		case Animated.FIREBALL: 			return new AnimationTirFeu();
		case Animated.METEORITE: 			return new AnimationMeteorite();
		case Animated.T_WEAPON: 			return new AnimTWeapon();
		case Animated.MINE: 				return new AnimationMine();
		}
		return null;
	}
	
	private static Pos detectPos(int b) {
		switch (b) {
		case Up.PK: 				return new Up();
		case UpWide.PK: 			return new UpWide();
		case Middle.PK: 			return new Middle();
		case LeftRight.PK: 			return new LeftRight();
		case Kinder.PK: 			return new Kinder();
		}
		return null;
	}
	
	protected static Pos initPositionnement(int def, int pk) {
		if (CSG.updateNeeded)
			return detectPos(Requests.getPositionnement(pk, def));
		return detectPos(def);
	}
}
