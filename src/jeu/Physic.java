package jeu;

import jeu.mode.EndlessMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import elements.generic.enemies.Enemy;
import elements.generic.weapons.Weapon;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.Player;

public class Physic {
	
	public static boolean pointIn(final Sprite s) {
		final int x = Gdx.input.getX();
		final int y = CSG.SCREEN_HEIGHT - Gdx.input.getY();
        return s.getX() <= x && s.getX() + s.getWidth() >= x && s.getY() <= y && s.getY() + s.getHeight() >= y;
	}
	
	public static boolean isOnScreen(final Vector2 position, final float hauteur, final float largeur) {
		if (position.y + hauteur < -CSG.HEIGHT_DIV10 || position.x + largeur < 0 ||
				position.x > CSG.gameZoneWidth  || position.y > CSG.HEIGHT_PLUS_4 + hauteur)
			return false;
		return true;
	}
	
	public static boolean isOnScreenWithTolerance(final Vector2 position, final float hauteur, final float largeur) {
		if (position.y + hauteur < -CSG.HEIGHT_DIV10 || position.x + largeur < -Stats.WIDTH_DIV_10 ||
				position.x > Stats.GAME_ZONE_W_PLUS_WIDTH_DIV_10  || position.y > CSG.HEIGHT_PLUS_4 + hauteur)
			return false;
		return true;
	}
	
	public static boolean isOnScreen(final float x, final float y, final float width) {
		if (y + width < 0 || x + width < 0 || x > CSG.gameZoneWidth || y > CSG.SCREEN_HEIGHT + width)
			return false;
		return true;
	}
	
	/**
	 * @return way
	 */
	public static boolean goToZigZagCentre(final Vector2 pos, final Vector2 dir, final int halfWidth, boolean way, final float amplitude, final float height, final int width){
		if (pos.x + halfWidth < CSG.gameZoneHalfWidth)
			way = false;
		else
			way = true;
		if (way)
			dir.x -= amplitude * EndlessMode.delta;
		else
			dir.x += amplitude * EndlessMode.delta;
		mvtNoCheck(pos, dir);
		return way;
	}

	public static void collisionsTest() {
		for (final Enemy enemy : Enemy.LIST) {
			if (enemy.pos.y + CSG.CENTIEME_HEIGHT > CSG.SCREEN_HEIGHT)		continue;
			if (enemy.isOnPlayer())	{
				Player.touchedEnnemy(enemy);
			}

			collisionPlayerWeaponToEnemy(enemy, Weapon.PLAYER_LIST);
			collisionPlayerWeaponToEnemy(enemy, Weapon.ADDS);
//			collisionEnemyWeaponToEnemy(enemy, Weapons.BOSSES_LIST);
		}
	}
	
	private static void collisionEnemyWeaponToEnemy(final Enemy enemy, Array<EnemyWeapon> playerList) {
		for (final EnemyWeapon a : playerList) {
			if (!enemy.dead && enemy.isTouched(a)) {
				if (enemy.stillAliveEnemyWeapon(a)) {
					a.free();
					playerList.removeValue(a, true);
				}
			}
		}
	}

	private static void collisionPlayerWeaponToEnemy(final Enemy enemy, Array<PlayerWeapon> playerList) {
		for (final PlayerWeapon a : playerList) {
			if (!enemy.dead && enemy.isTouched(a)) {
				if (enemy.stillAlive(a)) {
					a.free();
					playerList.removeValue(a, true);
				}
			}
		}
	}

	public static boolean isPointInRect(final float x, final float y, final float rectX, final float rectY, final float rectWidth, final float rectHeight) {
		 return rectX <= x && rectX + rectWidth >= x && rectY <= y && rectY + rectHeight >= y;
	}
	public static boolean isPointInRect(final float x, final float y, final Vector2 pos, final float rectWidth, final float rectHeight) {
		return pos.x <= x && pos.x + rectWidth >= x && pos.y <= y && pos.y + rectHeight >= y;
	}
	
	public static boolean isShipInRect(final float rectX, final float rectY, final float rectLarg, final float rectHaut) {
		return rectX <= Player.xCenter && rectX + rectLarg >= Player.xCenter && rectY <= Player.yCenter && rectY + rectHaut >= Player.yCenter;
	}
	
	public static boolean isPointInSquare(final float x, final float y, final float rectX, final float rectY, final float rectWidth) {
		 return rectX <= x && rectX + rectWidth >= x && rectY <= y && rectY + rectWidth >= y;
	}

	public static boolean isPlayerInRect(final Vector2 pos, final int rectWidth, final int rectHeight) {
		if (pos.x <= Player.xCenter && pos.x + rectWidth >= Player.xCenter && pos.y <= Player.yCenter && pos.y + rectHeight >= Player.yCenter) {
			Player.touched();
			return true;
		}
		return false;
	}
	
	private static final Vector2 DESIRED = new Vector2();
	private static final Vector2 STEER = new Vector2();

	public static float setDirToPlayer(final Vector2 dir, final Vector2 pos, final float maxSpeed, final float width, final float halfWidth, float rotation) {
		mvtToPlayer(dir, pos, maxSpeed, width, halfWidth, rotation);
		return dir.angle();
	}
	
	public static float setDirTo(final Vector2 dir, final Vector2 pos, final float maxSpeed, final float width, final float halfWidth, float rotation, Vector2 target) {
		mvtToTarget(dir, pos, maxSpeed, width, halfWidth, rotation, target);
		return dir.angle();
	}
	
	private static float tmp = 0;
	public static void mvtToPlayer(final Vector2 dir, final Vector2 pos, final float maxSpeed, final float width, final float halfWidth, float rotation) {
		DESIRED.x = pos.x - Player.xCenter;
		DESIRED.y = pos.y - Player.yCenter;
		DESIRED.nor();
		dir.nor();
		STEER.x = dir.x - DESIRED.x;
		STEER.y = dir.y - DESIRED.y;
		STEER.nor();
		STEER.scl(rotation);
		dir.add(STEER);
		dir.scl(maxSpeed);
	}
	
	public static void mvtToTarget(final Vector2 dir, final Vector2 pos, final float maxSpeed, final float width, final float halfWidth, float rotation, Vector2 target) {
		DESIRED.x = pos.x - target.x;
		DESIRED.y = pos.y - target.y;
		DESIRED.nor();
		dir.nor();
		STEER.x = dir.x - DESIRED.x;
		STEER.y = dir.y - DESIRED.y;
		STEER.nor();
		STEER.scl(rotation);
		dir.add(STEER);
		dir.scl(maxSpeed);
	}
	
	public static void dirToPlayer(final Vector2 dir, final Vector2 pos, final float width, final float halfWidth) {
		DESIRED.x = (pos.x + halfWidth) - Player.xCenter;
		DESIRED.y = (pos.y + halfWidth) - Player.yCenter;
		DESIRED.nor();
		
		dir.x = -DESIRED.x;
		dir.y = -DESIRED.y;
	}
	
	private static boolean collision = false;
	
	public static boolean isAddTouched(final Vector2 pos, final float width, final float height) {
		collision = false;
		if (Player.leftDrone && Physic.isPointInRect(Player.centerLeft1AddX, Player.centerAdd1Y, pos, width, height)) {
			Player.removeLeftAdd1();
			collision = true;
		}
		if (Player.rightDrone && Physic.isPointInRect(Player.centerRight1AddX, Player.centerAdd1Y, pos, width, height))	{
			Player.enleverAddDroite1();
			collision = true;
		}
		if (Player.leftDrone2 && Physic.isPointInRect(Player.centerLeft2AddX, Player.centerAdd2Y, pos, width, height)) {
			Player.removeLeftAdd2();
			collision = true;
		}
		if (Player.rightDrone2 && Physic.isPointInRect(Player.centerRight2AddX, Player.centerAdd2Y, pos, width, height)) {
			Player.enleverAddDroite2();
			collision = true;
		}
		return collision;
	}
	
	public static void mvtNoCheck(final Vector2 pos, final Vector2 dir) {
		pos.x += dir.x * EndlessMode.delta;
		pos.y += dir.y * EndlessMode.delta;
	}
	
	public static boolean mvt(final Vector2 dir, final Vector2 pos, final float width) {
		pos.x += dir.x * EndlessMode.delta;
		pos.y += dir.y * EndlessMode.delta;
		return isOnScreen(pos, width, width);
	}
	
	public static boolean mvt(final float height, final float width, final Vector2 dir, final Vector2 pos) {
		pos.x += dir.x * EndlessMode.delta;
		pos.y += dir.y * EndlessMode.delta;
		return isOnScreen(pos, width, height);
	}
	
	public static float mvtOmbrelle(final Enemy e, final Vector2 dir) {
		dir.x = -((CSG.gameZoneHalfWidth - Player.xCenter) - (CSG.gameZoneHalfWidth - e.pos.x - e.getDimensions().halfWidth));
		dir.y /= 1 + EndlessMode.delta;
		Physic.mvtNoCheck(e.pos, dir);
		return getAngleWithPlayer(e.pos, e.getDimensions().halfWidth, e.getDimensions().halfHeight);
	}
	
	private static final Vector2 tmpPos = new Vector2();
	
	public static float getAngleWithPlayer(final Vector2 pos, final float halfWidth, final float halfHeight) {
		tmpPos.x = Player.xCenter;
		tmpPos.y = Player.yCenter;
		return tmpPos.sub(pos.x + halfWidth, pos.y + halfHeight).angle();
	}

	public static float distanceFromPlayer(final Vector2 pos) {
		tmpPos.x = pos.x - Player.xCenter;
		tmpPos.y = pos.y - Player.yCenter;
		return tmpPos.len();
	}

	public static void stayOnScreen(Vector2 pos, float width) {
		if (pos.x < 0)
			pos.x += EndlessMode.delta15;
		else if (pos.x + width > CSG.gameZoneWidth)
			pos.x -= EndlessMode.delta15;
	}

	public static boolean isNotDisplayed(Enemy enemy) {
		if (enemy.pos.x + enemy.getDimensions().width < 0 || enemy.pos.x > CSG.screenWidth
				|| enemy.pos.y > CSG.SCREEN_HEIGHT || enemy.pos.y + enemy.getDimensions().height < 0)
			return true;
		return false;
	}

	public static boolean isLeft(Vector2 pos, float halfWidth) {
		return (pos.x + halfWidth < CSG.gameZoneHalfWidth); 
	}
}