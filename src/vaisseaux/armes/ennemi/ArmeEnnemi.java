package vaisseaux.armes.ennemi;

import com.badlogic.gdx.math.Vector2;

import jeu.Physique;
import vaisseaux.armes.Armes;

public abstract class ArmeEnnemi extends Armes {
	
	public boolean testCollisionVaisseau() {
		return Physique.pointDansVaisseau(position, getLargeur(), getHauteur());
	}

	public boolean testCollsionAdds() {
		return Physique.testCollisionAdds(position, getLargeur(), getHauteur());
	}
	
	public void init(Vector2 position, float dEMI_LARGEUR, float demiHauteur, float modifVitesse) {
		position.x = position.x + dEMI_LARGEUR - getLargeur() / 2;
		position.y = position.y + demiHauteur - getHauteur() / 2;
		listeTirsDesEnnemis.add(this);
	}

	/**
	 * L'envoie vers le bas
	 * @param position
	 * @param modifVitesse
	 */
	public void init(Vector2 position, float modifVitesse) {
		this.position.x = position.x;
		this.position.y = position.y;
		direction.x = 0;
		direction.y = -1 * modifVitesse * getVitesse();
		listeTirsDesEnnemis.add(this);
	}

	protected abstract float getVitesse();

	public void init(Vector2 position, float modifVitesse, float angle, Vector2 direction) {
		this.position.x = position.x;
		this.position.y = position.y;
		this.direction.x = direction.x * modifVitesse;
		this.direction.y = direction.y * modifVitesse;
		this.direction.mul(getVitesse());
		this.angle = angle;
		listeTirsDesEnnemis.add(this);
	}
	
	public void init(Vector2 position, float modifVitesse, float angle) {
		this.position.x = position.x;
		this.position.y = position.y;
		this.direction.x = 0;
		this.direction.y = 1 * modifVitesse;
		direction.rotate(angle);
		this.angle = angle;
		this.position.x += direction.x / 1.35f;
		this.position.y += direction.y / 1.35f;
		listeTirsDesEnnemis.add(this);
	}

	public void init(Vector2 position, Vector2 direction) {
		this.position.x = position.x;
		this.position.y = position.y;
		this.direction.x = direction.x;
		this.direction.y = direction.y;
		listeTirsDesEnnemis.add(this);
	}
	
	public void init(Vector2 position, Vector2 direction, float modifVitesse) {
		this.position.x = position.x;
		this.position.y = position.y;
		this.direction.x = direction.x * modifVitesse;
		this.direction.y = direction.y * modifVitesse;
		listeTirsDesEnnemis.add(this);
	}

	public static void clear() {
		for(Armes a : liste) a.free();
		for(Armes a : listeTirsDesEnnemis) a.free();
		liste.clear();
		listeTirsDesEnnemis.clear();
	}

}
