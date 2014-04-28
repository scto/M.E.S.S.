package elements.generic.weapons.enemies;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Physic;
import jeu.Stats;
import jeu.db.Requests;

import com.badlogic.gdx.math.Vector2;

import elements.generic.Player;
import elements.generic.weapons.Weapons;
import elements.generic.weapons.patterns.Tireur;
import elements.particular.bonuses.XP;

public abstract class EnemyWeapon extends Weapons {
	
	private static float tmpFloat;
	private static float next = 0;
	private static final Vector2 tmpV = new Vector2();
	
	public boolean testCollisionVaisseau() {
		tmpFloat = Player.POS.dst(pos.x + getHalfWidth() - Player.DEMI_LARGEUR, pos.y + getHalfHeight() - Player.DEMI_HAUTEUR);
		if (tmpFloat < getWidth() + Stats.U || tmpFloat < getHalfHeight() + Stats.U) {
			if (next < EndlessMode.now) {
				final XP xp = XP.POOL.obtain();
				xp.init(pos.x + getHalfWidth(), pos.y + getHalfHeight(), 10);
				xp.direction.x = -dir.x * EndlessMode.delta;
				xp.direction.y = -dir.y * EndlessMode.delta;
				next = EndlessMode.now + .1f;
			}
			if (tmpFloat < getHalfWidth() || tmpFloat < getHalfHeight()) {
				Player.touched();
				return true;
			}
		}
		return false;
	}

	public boolean testCollsionAdds() {
		return Physic.isAddTouched(pos, getWidth(), getHeight());
	}
	
	public void init(Vector2 position, float dEMI_LARGEUR, float demiHauteur, float modifVitesse) {
		position.x = position.x + dEMI_LARGEUR - getHalfWidth();
		position.y = position.y + demiHauteur - getHalfHeight();
		ENEMIES_LIST.add(this);
	}

	/**
	 * L'envoie vers le bas
	 * @param position
	 * @param modifVitesse
	 * @param boss TODO
	 */
	public void init(Vector2 position, float modifVitesse, boolean boss) {
		this.pos.x = position.x;
		this.pos.y = position.y;
		dir.x = 0;
		dir.y = -1 * modifVitesse * getSpeed();
		if (boss) {
			BOSSES_LIST.add(this);
		} else {
			ENEMIES_LIST.add(this);
		}
	}

	protected abstract float getSpeed();

	public void init(Vector2 position, float modifVitesse, Vector2 direction, boolean boss) { 
		this.pos.x = position.x;
		this.pos.y = position.y;
		this.dir.x = direction.x * modifVitesse;
		this.dir.y = direction.y * modifVitesse;
		this.dir.scl(getSpeed());
		if (boss) {
			BOSSES_LIST.add(this);
		} else {
			ENEMIES_LIST.add(this);
		}
	}
	
	public void init(Vector2 position, float modifVitesse, float angle) {
		this.pos.x = position.x;
		this.pos.y = position.y;
		this.dir.x = 0;
		this.dir.y = 1 * modifVitesse;
		dir.rotate(angle);
		this.pos.x += dir.x / 1.35f;
		this.pos.y += dir.y / 1.35f;
		ENEMIES_LIST.add(this);
	}

	public void init(Vector2 position, Vector2 direction) {
		this.pos.x = position.x;
		this.pos.y = position.y;
		this.dir.x = direction.x;
		this.dir.y = direction.y;
		ENEMIES_LIST.add(this);
	}
	
	public void init(Vector2 position, Vector2 direction, float modifVitesse) {
		this.pos.x = position.x;
		this.pos.y = position.y;
		this.dir.x = direction.x * modifVitesse;
		this.dir.y = direction.y * modifVitesse;
		ENEMIES_LIST.add(this);
	}

	public static void clear() {
		for (Weapons a : PLAYER_LIST)
			a.free();
		for (Weapons a : ENEMIES_LIST)
			a.free();
		PLAYER_LIST.clear();
		ENEMIES_LIST.clear();
	}

	public void initDispersion(Vector2 position, float modifVitesse, Vector2 direction, int nbTirs, float dispersion, Tireur t) {
		for (int i = 1; i < nbTirs; i++) {
			tmpV.x = direction.x;
			tmpV.y = direction.y;
			tmpV.rotate( (EndlessMode.R.nextFloat()-.5f) * dispersion);
			t.getArme().init(position, modifVitesse, direction, false);
		}
	}

	protected static float initSpeed(float def, int pk) {
		if (CSG.updateNeeded)
			return Requests.getSpeedEnemyWeapon(pk, def) * Stats.u;
		return def * Stats.u;
	}
}
