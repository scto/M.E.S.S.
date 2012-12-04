package jeu;

import com.badlogic.gdx.Gdx;

public class Chrono implements Runnable {
	private Thread deroulement;
	private long tempsEcoule = 0; // exprime en millisecondes
	private long momentDebut = 0;
	private long momentSuspension;
	private boolean continuer;
	private boolean finir;
	private long nbSecondes = 0;
	private Endless parent;
	
	/**
	 * - duree donne le temps en secondes mis pour que le chronometre fasse un tour complet,
	 * apres ce temps, le chronometre s'arrete.
*/
	public Chrono(int duree) {
		//this.duree = duree * 1000;
	}

	/* Demarre le chronometre */
	public void demarrer(Endless endless)  {   
		if (enFonctionnement()) {
			arreter();
			try {
				deroulement.join();
			}
			catch(InterruptedException exc) {
				exc.printStackTrace();
			}
		}
		deroulement = new Thread(this);
		deroulement.start();
		parent = endless;
	}

	/* Suspend le deroulement du temps ; ce deroulement pourra etre repris 
	 * dans l'etat ou il se trouvait par la methode reprendre */
	public void suspendre() {     
		if (enFonctionnement()  && continuer) {
			momentSuspension = System.currentTimeMillis();
			continuer = false;
		}
	}

		/* Si le chronometre est en fonctionnment mais a ete suspendu, 
	 * il recommence a tourne r*/ 
	public synchronized  void reprendre() { 
		if (enFonctionnement() & !continuer) {  
			momentDebut +=  System.currentTimeMillis() - momentSuspension;
			continuer = true;
			notifyAll();
		}
	}

	/* Arrete le chronometre. Une fois arrete, le chronometre ne peut
    repartir qu'avec la methode demarrer, au debut du décompte du temps*/
	public synchronized void arreter() {
		if (enFonctionnement()) {
			finir = true;
			notifyAll();
		}
	}

	/* Fait tourner le chronometre */
	public void run() {
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		continuer = true;
		finir = false;
		momentDebut = System.currentTimeMillis();
		while(!finir) {
			tempsEcoule = System.currentTimeMillis() - momentDebut;
			nbSecondes = tempsEcoule/1000;
			//  Si le joueur a perdu on arrete le chrono. C'est mis ici pour le checker le moins souvent possible
			if(parent.getPerdu()) arreter();
			parent.updateTemps(nbSecondes);
			//Gdx.app.log(nbSecondes+"," + Gdx.graphics.getFramesPerSecond(), " test");//"" + (Armes.liste.size() + Armes.listeTirsDesEnnemis.size() + Ennemis.liste.size() + XP.liste.size()));
			try {
				Thread.sleep(1000);
				synchronized(this) {
					while (!continuer & !finir) wait();
				}
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	/* Retourne true si le chronometre est en fonctionnement,  eventuellement suspendu 
	 * et false si le chronometre n'est pas demarre, ou bien a ete arrete, ou bien a fini de tourner*/
	public boolean enFonctionnement() {
		return (deroulement!=null) && (deroulement.isAlive());
	}

	public long getTempsEcoule() {
		return nbSecondes;
	}
	
	
}