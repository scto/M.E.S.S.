package elements.generic.vectorial;

import jeu.mode.EndlessMode;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import elements.generic.Player;

public class Shield {

	private static final ShapeRenderer renderer = new ShapeRenderer();
	private static float angle = 0, antiAngle = 0, expantion = Player.HEIGHT;

	public static void drawShield(SpriteBatch batch) {
		angle += EndlessMode.delta15 * 2;
		antiAngle -= EndlessMode.delta15 * 2;
		batch.end();
		renderer.begin(ShapeType.Line);
		draw(Player.POS.x - Player.WIDTH_DIV_10, Player.POS.y - Player.WIDTH_DIV_10, Player.HEIGHT, Player.HALF_HEIGHT);
		batch.begin();
	}

	private static void draw(float tmpX, float tmpY, float width, float halfWidth) {
		renderer.setColor(Color.CYAN);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, antiAngle);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, angle);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, antiAngle + 50);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, antiAngle + 150);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, angle + 25);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, antiAngle + 25);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, angle + 50);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, angle + 75);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, antiAngle + 75);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, angle + 100);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, antiAngle + 100);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, angle + 125);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, antiAngle + 125);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, angle + 150);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, antiAngle + 175);
		renderer.rect(tmpX, tmpY, width, width, halfWidth, halfWidth, angle + 175);
		renderer.end();
	}

	public static void drawAndExpand(SpriteBatch batch) {
//		expantion += EndlessMode.delta15 * 10;
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
//		renderer.rotate(0, -0.001f, 0, 1f);
		angle += EndlessMode.delta15 * 2;
		antiAngle -= EndlessMode.delta15 * 2;
		batch.end();
		renderer.begin(ShapeType.Line);
//		draw((Player.POS.x, Player.POS.y - (expantion/10), expantion, expantion/2);
		draw(Player.POS.x - Player.WIDTH_DIV_10, Player.POS.y - Player.WIDTH_DIV_10, Player.HEIGHT, Player.HALF_HEIGHT);
		batch.begin();
	}

}
