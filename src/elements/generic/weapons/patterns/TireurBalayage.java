package elements.generic.weapons.patterns;

public interface TireurBalayage extends TireurPlusieurFois {
	
	int getNombreTirsAvantChangement();
	float getEcartTirs();
	void addNombresTirs(int i);
	boolean getSens();
	void setSens(boolean b);
}
