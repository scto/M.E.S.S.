package vaisseaux.armes.typeTir;

public interface TireurBalayage extends TireurPlusieurFois {
	
	public int getNombreTirsAvantChangement();
	public float getEcartTirs();
	public void addNombresTirs(int i);
	public boolean getSens();
	public void setSens(boolean b);
}
