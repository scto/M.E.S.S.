package vaisseaux.ennemis.particuliers;

import com.badlogic.gdx.math.Vector2;

import vaisseaux.armes.Armes;

public interface Tireur {
	
	public Armes getArme();
//	public float getMaintenant();
//	public float getProchainTir(); 
	public Vector2 getPositionDuTir(int numeroTir);
	public float getModifVitesse();
//	public boolean isMort();
	public void setProchainTir(float f);
	
}
