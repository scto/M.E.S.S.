package vaisseaux.tirs;

import vaisseaux.armes.Armes;
import vaisseaux.armes.ArmesBouleBleu;
import vaisseaux.ennemis.particuliers.DoubleTireur;
import vaisseaux.ennemis.particuliers.Tireur;
import vaisseaux.ennemis.particuliers.TireurAngle;
import vaisseaux.ennemis.particuliers.TireurBalayage;
import vaisseaux.ennemis.particuliers.TireurPlusieurFois;
import vaisseaux.joueur.VaisseauType1;

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
	
	public void doubleTirVersBas(DoubleTireur t, boolean mort, float maintenant, float prochainTir) {
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
	
	/**
	 * Tire en éventail autour de l'angle renvoyé par getAngle()
	 * @param t
	 * @param nbTirs
	 * @param dispersion 
	 */
	public void tirEventail(TireurAngle t, int nbTirs, int dispersion, boolean mort, float maintenant, float prochainTir) {
		if (!mort && maintenant > prochainTir) {
			for (int i = - (nbTirs/2) ; i <= nbTirs/2 ; i++) {
				float rotationSupplementaire = t.getAngleTir() + (i * dispersion);
				t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), rotationSupplementaire, t.getDirectionTir().rotate(rotationSupplementaire));
				t.setProchainTir(maintenant + CADENCE);
			}
		}
	}

	public void tirVersJoueur(Tireur t, boolean mort, float maintenant, float prochainTir) {
		if (!mort && maintenant > prochainTir) {
			Armes a = t.getArme();
			vecteurCiblePosition.x = (VaisseauType1.position.x + VaisseauType1.DEMI_LARGEUR) - a.getLargeur()/2;
			vecteurCiblePosition.y = (VaisseauType1.position.y + VaisseauType1.DEMI_HAUTEUR) - a.getHauteur()/2;
			vecteurPosition.x = t.getPositionDuTir(0).x;
			vecteurPosition.y = t.getPositionDuTir(0).y;
			vecteurCible.x = 1;
			vecteurCible.y = 0;
			float angle = vecteurCiblePosition.sub(vecteurPosition).angle();
			vecteurCible.rotate(angle);
			a.init(vecteurPosition, t.getModifVitesse(), angle, vecteurCible);
			t.setProchainTir(maintenant + CADENCE);
		}
	}

	/**
	 * Attention à la direction passée
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
				t.setProchainTir(maintenant + (CADENCE * nombreTirs));
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
