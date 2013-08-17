package vaisseaux.armes.joueur;

import vaisseaux.armes.Armes;

public abstract class ArmeJoueur extends Armes{
	
	protected static int numeroCouleur = 1;
	
	public static void roueCouleurs() {
		numeroCouleur++;
		if (numeroCouleur > 1) numeroCouleur = 0;
	}
	
	public abstract float getR();
	public abstract float getG();
	public abstract float getB();

}

