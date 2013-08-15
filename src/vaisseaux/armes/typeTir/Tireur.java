package vaisseaux.armes.typeTir;

import com.badlogic.gdx.math.Vector2;

import vaisseaux.armes.Armes;

public interface Tireur {
	
	public Armes getArme();
	public Vector2 getPositionDuTir(int numeroTir);
	public float getModifVitesse();
	public void setProchainTir(float f);
	
}
