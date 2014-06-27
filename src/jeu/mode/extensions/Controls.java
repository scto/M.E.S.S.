package jeu.mode.extensions;

import jeu.CSG;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import elements.particular.Player;

public class Controls {
	
	private static final float HALF_WIDTH_FLECHE = 0, HALF_HEIGHT_FLECHE = 0, WIDTH_FLECHE = 0, HEIGHT_FLECHE = 0;
	
	public static void keyboard(Player ship) {
		ship.mouvements();
		if (Gdx.input.isKeyPressed(Keys.LEFT)) 	ship.mvtLimiteVitesse(-500, 0);
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) ship.mvtLimiteVitesse(500, 0);
		if (Gdx.input.isKeyPressed(Keys.UP)) 	ship.mvtLimiteVitesse(0, 500);
		if (Gdx.input.isKeyPressed(Keys.DOWN)) 	ship.mvtLimiteVitesse(0, -500);
	}
	
	private static void afficherDPAD(SpriteBatch batch, TextureRegion arrow, Camera cam) {
		if (CSG.profile.typeControle == CSG.CONTROLE_DPAD && Gdx.input.isTouched()) {
			// F L E C H E D R O I T E
			batch.draw(arrow, (cam.position.x - CSG.screenHalfWidth) + Player.prevX + HALF_WIDTH_FLECHE, CSG.SCREEN_HEIGHT - (Player.prevY + HALF_HEIGHT_FLECHE), HALF_WIDTH_FLECHE, HALF_HEIGHT_FLECHE, WIDTH_FLECHE, HEIGHT_FLECHE, 1, 1, 0);
			// F L E C H E G A U C H E
			batch.draw(arrow, (cam.position.x - CSG.screenHalfWidth) + Player.prevX - (WIDTH_FLECHE + HALF_WIDTH_FLECHE), CSG.SCREEN_HEIGHT - (Player.prevY + HALF_HEIGHT_FLECHE), HALF_WIDTH_FLECHE, HALF_HEIGHT_FLECHE, WIDTH_FLECHE, HEIGHT_FLECHE, 1, 1, 180);
			// F L E C H E H A U T
			batch.draw(arrow, (cam.position.x - CSG.screenHalfWidth) + Player.prevX - HALF_WIDTH_FLECHE, CSG.SCREEN_HEIGHT - (Player.prevY - HALF_HEIGHT_FLECHE), HALF_WIDTH_FLECHE, HALF_HEIGHT_FLECHE, WIDTH_FLECHE, HEIGHT_FLECHE, 1, 1, 90);
			// F L E C H E B A S
			batch.draw(arrow, (cam.position.x - CSG.screenHalfWidth) + Player.prevX - HALF_WIDTH_FLECHE, CSG.SCREEN_HEIGHT - (Player.prevY + HALF_HEIGHT_FLECHE + HEIGHT_FLECHE), HALF_WIDTH_FLECHE, HALF_HEIGHT_FLECHE, WIDTH_FLECHE, HEIGHT_FLECHE, 1, 1, 270);
		}
	}

	private static void justTouched() {
//		if (CSG.profile.typeControle == CSG.CONTROLE_DPAD) {
//			Player.prevX = Gdx.input.getX();
//			Player.prevY = Gdx.input.getY();
//		}
	}

	private static void touched() {
//		if (CSG.profile.typeControle == CSG.CONTROLE_DPAD) afficherDPAD();
	}

	public static void act(Player ship) {
//		if (CSG.profile.typeControle == CSG.CONTROLE_ACCELEROMETRE && !drawMenu)
//			ship.accelerometre();
		if (Gdx.input.justTouched())
			justTouched();
		else if (Gdx.input.isTouched())
			touched();
	}

}
