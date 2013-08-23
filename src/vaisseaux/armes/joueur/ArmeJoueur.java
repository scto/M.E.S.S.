package vaisseaux.armes.joueur;

import java.util.Random;

import vaisseaux.armes.Armes;

public abstract class ArmeJoueur extends Armes{
	
	protected static int numeroCouleur = 1;
	protected static Random r = new Random();
	
	public static void roueCouleurs() {
		numeroCouleur++;
		if (numeroCouleur > 1) numeroCouleur = 0;
	}
	
	public abstract float getColor();
}

