package elements.generic.weapons.patterns;

import com.badlogic.gdx.math.Vector2;

import elements.generic.weapons.enemies.EnemyWeapon;

public interface Tireur {
	
	EnemyWeapon getArme();
	Vector2 getPositionDuTir(int numeroTir);
	float getModifVitesse();
	void setProchainTir(float f);
	
}
