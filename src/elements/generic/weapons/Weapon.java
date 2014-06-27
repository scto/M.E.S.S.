package elements.generic.weapons;

import jeu.Physic;
import jeu.mode.EndlessMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Element;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.bonuses.Bonus;
import elements.particular.particles.Particles;

public abstract class Weapon extends Element implements Poolable {
	
	public static final Array<PlayerWeapon> PLAYER_LIST = new Array<PlayerWeapon>(false, 50), ADDS = new Array<PlayerWeapon>(false, 10);
	public static final Array<EnemyWeapon> ENEMIES_LIST = new Array<EnemyWeapon>(false, 50), BOSSES_LIST = new Array<EnemyWeapon>(false, 50);
	protected static final Rectangle TMP_RECT = new Rectangle();
	private static boolean testCollision = false, shieldHs = false;
	public static int color = 0;
	private static final int XP_WITH_SHIELD = 7;

	public static void drawAndMove(SpriteBatch batch) {
		playerWeapons(batch);
		testCollision = !testCollision;
		// Attention que si y'a une collision on skip le reste de la liste. D'où l'effet bizarre quand on est invicible
		act(batch, ENEMIES_LIST);
		act(batch, BOSSES_LIST);
    }

	private static void playerWeapons(SpriteBatch batch) {
		for (final PlayerWeapon pWeapon : PLAYER_LIST) {
			pWeapon.displayOnScreen(batch);
			pWeapon.now += EndlessMode.delta;
			if (pWeapon.mouvementEtVerif() == false) {
				PLAYER_LIST.removeValue(pWeapon, true);
				pWeapon.free();
			}
		}
		for (final PlayerWeapon pWeapon : ADDS) {
			pWeapon.displayOnScreen(batch);
			pWeapon.now += EndlessMode.delta;
			if (pWeapon.mouvementEtVerif() == false) {
				ADDS.removeValue(pWeapon, true);
				pWeapon.free();
			}
		}
	}

	private static void act(SpriteBatch batch, Array<EnemyWeapon> LIST) {
		for (final EnemyWeapon eWeapon : LIST) {
			
			eWeapon.now += EndlessMode.delta;
			eWeapon.displayOnScreen(batch);
			if (testCollision) {
				if (eWeapon.testCollisionVaisseau() && !EndlessMode.aPerdu()) {
					removeEnemyWeapon(eWeapon, LIST);
					continue;
				}
			} else if (eWeapon.testCollsionAdds()) {
				removeEnemyWeapon(eWeapon, LIST);
				continue;
			}
			eWeapon.move();
			if (!Physic.isOnScreen(eWeapon.pos, eWeapon.getDimensions().height, eWeapon.getDimensions().width))
				removeEnemyWeapon(eWeapon, LIST);
		}
		if (shieldHs) {
			for (final EnemyWeapon w : LIST) {
				Particles.popOutWeapon(w);
				Bonus.addXp(XP_WITH_SHIELD, w.pos.x + w.getDimensions().halfWidth, w.pos.y + w.getDimensions().halfHeight);
				w.free();
			}
			shieldHs = false;
			LIST.clear();
		}
	}
	
	private static void removeEnemyWeapon(EnemyWeapon a, Array<EnemyWeapon> lIST) {
		lIST.removeValue(a, true);
		a.free();
	}
	
	public static void affichage(SpriteBatch batch) {
		for (final PlayerWeapon a : PLAYER_LIST)	a.displayOnScreen(batch);
		for (final Weapon a : ENEMIES_LIST)			a.displayOnScreen(batch);
		for (final Weapon a : BOSSES_LIST)			a.displayOnScreen(batch);
		for (final PlayerWeapon a : ADDS)			a.displayOnScreen(batch);
	}

	public static void clear() {
		for (final Weapon a : ADDS)
			a.free();
		ADDS.clear();
		for (final Weapon a : PLAYER_LIST)
			a.free();
		PLAYER_LIST.clear();
		enemiesClear();
	}

	public static void enemiesClear() {
		for (int i = 0; i < ENEMIES_LIST.size; i++) 
			ENEMIES_LIST.get(i).free();
		ENEMIES_LIST.clear();
		for (int i = 0; i < BOSSES_LIST.size; i++) 
			BOSSES_LIST.get(i).free();
		BOSSES_LIST.clear();
	}

	public Rectangle getRectangleCollision() {
		TMP_RECT.x = pos.x;
		TMP_RECT.y = pos.y;
		TMP_RECT.height = getDimensions().height;
		TMP_RECT.width = getDimensions().width;
		return TMP_RECT;
	}
	
	/**
	 * Fait bouger les objets et les enlï¿½ves si ils ne sont plus ï¿½ l'ï¿½cran
	 * @param batch
	 * return false si on doit le virer de la liste et appeler free()
	 */
	public boolean mouvementEtVerif() {								return Physic.mvt(getDimensions().width, getDimensions().height, dir, pos);	}
	public static void resetAll() {									EnemyWeapon.nextGraze = 0;								}
	public static void shieldHs() {									shieldHs = true;										}
	public abstract void free();
	@Override	public void setAngle(float mvtToPlayerWithAngle) {															}
}
