package objets.armes.typeTir;

import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.ennemi.BouleFeu;
import jeu.Physique;

import com.badlogic.gdx.math.Vector2;

public class Tirs {
	
	private float CADENCE;
	private static Vector2 vecteurCiblePosition = new Vector2();
	private static Vector2 vecteurPosition = new Vector2();
	private static Vector2 vecteurCible = new Vector2();
	
	public Tirs(float CADENCE) {
		super();
		this.CADENCE = CADENCE;
	}

	public void tirVersBas(Tireur t, boolean mort, float maintenant, float prochainTir) {
		if (!mort && maintenant > prochainTir) {
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse());
			t.setProchainTir(maintenant + CADENCE);
		}
	}
	
	public void doubleTirVersBas(Tireur t, boolean mort, float maintenant, float prochainTir) {
		if (!mort && maintenant > prochainTir) {
			
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse());
			t.getArme().init(t.getPositionDuTir(2), t.getModifVitesse());
			
			t.setProchainTir(maintenant + CADENCE);
		}
	}

	public void tirMultiplesVersBas(Tireur t, int tirs, boolean mort, float maintenant, float prochainTir) {
		if (!mort && maintenant > prochainTir) {
			for (int i = 1; i <= tirs; i++) {
				t.getArme().init(t.getPositionDuTir(i), t.getModifVitesse());
			}
			t.setProchainTir(maintenant + CADENCE);
		}
	}
	
	public void tirToutDroit(TireurAngle t, boolean mort, float maintenant, float prochainTir) {
		if (!mort && maintenant > prochainTir) {
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), t.getAngleTir(), t.getDirectionTir());
			t.setProchainTir(prochainTir + CADENCE);
		}
	}
	
	public void tirSurCote(TireurAngle t, boolean mort, float maintenant, float prochainTir) {
		if (!mort && maintenant > prochainTir) {
			t.getArme().init(t.getPositionDuTir(2), t.getDirectionTir().rotate(-90), t.getModifVitesse());
			t.getArme().init(t.getPositionDuTir(2), t.getDirectionTir().rotate(90), t.getModifVitesse());
			t.setProchainTir(prochainTir + CADENCE);
		}
	}
	
	/**
	 * Tire en �ventail autour de l'angle renvoy� par getAngle()
	 * @param t
	 * @param nbTirs
	 * @param dispersion 
	 */
	public void tirEventail(TireurAngle t, int nbTirs, int dispersion, boolean mort, float maintenant, float prochainTir) {
		if (!mort && maintenant > prochainTir) {
			for (int i = - (nbTirs/2) ; i <= nbTirs/2 ; i++) {
				float rotationSupplementaire = t.getAngleTir() + (i * dispersion);
				t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), rotationSupplementaire, t.getDirectionTir().rotate(rotationSupplementaire));
			}
			t.setProchainTir(maintenant + CADENCE);
		}
	}
	
	/**
	 * Semblable au tir en eventail mais laisse un angle plus important au centre et ne tient compte que de l'angle pour le tir
	 * @param ombrelle
	 * @param i
	 * @param j
	 * @param mort
	 * @param maintenant
	 * @param prochainTir
	 * @param k
	 */
	public void tirOmbrelle(TireurAngle t, int nbTirs, float dispersion, boolean mort, float maintenant, float prochainTir, float ecartCentre) {
		if (!mort && maintenant > prochainTir) {
			vecteurCible.x = 0;
			vecteurCible.y = -1;
			vecteurCible.rotate(t.getAngleTir());
			int numeroTrou = nbTirs/2;
			float angleTotal = (dispersion * (nbTirs-1)) + ecartCentre * 2;
			float angleDepart = t.getAngleTir() - ( angleTotal / 2f);
			float angleTir = angleDepart;
			for (int i = 0; i < nbTirs; i++) {
				System.out.println(i);
				if (i==numeroTrou) {
					angleDepart += ecartCentre;
					angleTir = angleDepart + (i * dispersion);
					BouleFeu.pool.obtain().init(t.getPositionDuTir(6), t.getModifVitesse(), angleTir);
				} else {
					if(i == numeroTrou+1) angleDepart += ecartCentre;
					angleTir = angleDepart + (i * dispersion);
					t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), angleTir);
				}
			}
			t.setProchainTir(maintenant + CADENCE);
		}
	}
	
//	if (i==numeroTrou) { 
//		// On est � celle du centre
//		angleDepart += ecartCentre;
//		BouleFeu.pool.obtain().init(vecteurPosition, modifVitesse, angleTir);
//	} else {
//		if (i == numeroTrou+1) 	angleTir = angleDepart + (i * dispersion);
//		t.getArme().init(vecteurPosition, modifVitesse, angleTir);
//	}
	
//	public void tirOmbrelleV2(TireurAngle t, int nbTirs, float dispersion, boolean mort, float maintenant, float prochainTir, float ecartCentre) {
//		if (!mort && maintenant > prochainTir) {
//			vecteurCible.x = 0;
//			vecteurCible.y = -1;
//			vecteurCible.rotate(t.getAngleTir());
//			int numeroTrou = nbTirs/2;
//			float angleTotal = (dispersion * (nbTirs-1)) + ecartCentre * 2;
//			float angleDepart = t.getAngleTir() - ( angleTotal / 2f);
//			float angleTir = angleDepart;
//			for (int i = 0; i < nbTirs; i++) {
//				if (i==numeroTrou || i == numeroTrou+1) angleDepart += ecartCentre;
//				angleTir = angleDepart + (i * dispersion);
//				t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), angleTir);
//				t.setProchainTir(maintenant + CADENCE);
//			}
//		}
//	}

	public void tirVersJoueur(Tireur t, boolean mort, float maintenant, float prochainTir) {
		if (!mort && maintenant > prochainTir) {
			ArmeEnnemi a = t.getArme();
			vecteurPosition.x = t.getPositionDuTir(0).x;
			vecteurPosition.y = t.getPositionDuTir(0).y;
			vecteurCible.x = 1;
			vecteurCible.y = 0;
			float angle = Physique.getAngleVersJoueur(vecteurPosition, a.getLargeur()/2, a.getHauteur()/2);
			vecteurCible.rotate(angle);
			a.init(vecteurPosition, t.getModifVitesse(), angle, vecteurCible);
			t.setProchainTir(maintenant + CADENCE);
		}
	}

	/**
	 * Attention � la direction pass�e
	 * @param t
	 * @param nombreTirs
	 * @param mort
	 * @param maintenant
	 * @param prochainTir
	 */
	public void tirEnRafaleGaucheEtDroite(TireurPlusieurFois t, int nombreTirs, boolean mort, float maintenant, float prochainTir) {
		if (!mort && maintenant > prochainTir) {
			vecteurCible = t.getDirectionTir();
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), t.getAngleTir(), vecteurCible);
			vecteurCible.x = -vecteurCible.x;
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), -t.getAngleTir(), vecteurCible);
			if (t.getNumeroTir() >= nombreTirs) {
				t.addNombresTirs(-t.getNumeroTir());
				t.setProchainTir(maintenant + (CADENCE * nombreTirs));
			} else {
				t.setProchainTir(maintenant + CADENCE);
			}
			t.addNombresTirs(1);
		}
	}

	public void tirEnRafale(TireurPlusieurFois t, int nombreTirs, boolean mort, float maintenant, float prochainTir) {
		if (!mort && maintenant > prochainTir) {
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), -t.getAngleTir(), t.getDirectionTir());
			if (t.getNumeroTir() >= nombreTirs) {
				t.addNombresTirs(-t.getNumeroTir());
				t.setProchainTir(maintenant + (CADENCE * nombreTirs * 2));
			} else {
				t.setProchainTir(maintenant + CADENCE);
			}
			t.addNombresTirs(1);
		}
	}

	public void tirBalayage(TireurBalayage t, boolean mort, float maintenant, float prochainTir) {
		if (!mort && maintenant > prochainTir) {
			if (t.getNombreTirsAvantChangement() < t.getNumeroTir()) {
				t.setSens(false);
			} else if (-t.getNombreTirsAvantChangement() > t.getNumeroTir()) {
				t.setSens(true);
			}
			float angleTir = t.getAngleTir() + (t.getEcartTirs() * t.getNumeroTir());
			vecteurCible.y = t.getDirectionTir().y;
			vecteurCible.x = t.getDirectionTir().x;
			vecteurCible.rotate(angleTir);
			
			if (t.getSens()) 	t.addNombresTirs(1);
			else 				t.addNombresTirs(-1);
			
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), angleTir, vecteurCible);
			t.setProchainTir(maintenant + CADENCE);
		}
	}


}
