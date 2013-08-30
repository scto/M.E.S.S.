package vaisseaux.armes.typeTir;

import vaisseaux.armes.ennemi.ArmeEnnemi;

import com.badlogic.gdx.math.Vector2;

public interface Tireur {
	
	public ArmeEnnemi getArme();
	public Vector2 getPositionDuTir(int numeroTir);
	public float getModifVitesse();
	public void setProchainTir(float f);
	
}
