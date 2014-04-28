package elements.generic.weapons.patterns;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Physic;
import jeu.Stats;

import com.badlogic.gdx.math.Vector2;

import elements.generic.enemies.individual.lvl1.Insecte;
import elements.generic.weapons.enemies.Fireball;
import elements.generic.weapons.enemies.EnemyWeapon;

public class Tirs {
	
	public float cadence;
	private static final Vector2 vecteurPosition = new Vector2();
	private static Vector2 vecteurCible = new Vector2();
	public Tirs(float CADENCE) {
		super();
		this.cadence = CADENCE;
	}

	public void tirVersBas(Tireur t, float maintenant, float prochainTir) {
		if (maintenant > prochainTir) {
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), false);
			t.setProchainTir(maintenant + cadence);
		}
	}
	
	public void doubleTirVersBas(Tireur t, float maintenant, float prochainTir) {
		if (maintenant > prochainTir) {
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), false);
			t.getArme().init(t.getPositionDuTir(2), t.getModifVitesse(), false);
			t.setProchainTir(maintenant + cadence);
		}
	}

	public void tirMultiplesVersBas(Tireur t, int tirs, float maintenant, float prochainTir, boolean boss) {
		if (maintenant > prochainTir) {
			for (int i = 1; i <= tirs; i++) {
				t.getArme().init(t.getPositionDuTir(i), t.getModifVitesse(), boss);
			}
			t.setProchainTir(maintenant + cadence);
		}
	}
	
	public void tirMultiplesVersBasRandomize(Tireur t, int tirs, float maintenant, float prochainTir, boolean boss) {
		if (maintenant > prochainTir) {
			for (int i = 1; i <= tirs; i++) {
				tmpDir.x = 0;
				tmpDir.y = -1;
				t.getArme().init(t.getPositionDuTir(i), t.getModifVitesse(), tmpDir.rotate((float) CSG.R.nextGaussian()*2f), boss);
			}
			t.setProchainTir(maintenant + cadence);
		}
	}
	
	public void tirToutDroit(TireurAngle t, float maintenant, float prochainTir) {
		if (maintenant > prochainTir) {
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), t.getDirectionTir(), false); //, t.getAngleTir());
			t.setProchainTir(prochainTir + cadence);
		}
	}
	
	public void tirSurCote(TireurAngle t, float maintenant, float prochainTir) {
		if (maintenant > prochainTir) {
			t.getArme().init(t.getPositionDuTir(2), t.getDirectionTir().rotate(-90), t.getModifVitesse());
			t.getArme().init(t.getPositionDuTir(2), t.getDirectionTir().rotate(90), t.getModifVitesse());
			t.setProchainTir(prochainTir + cadence);
		}
	}
	
	/**
	 * Tire en �ventail autour de l'angle renvoy� par getAngle()
	 * @param t
	 * @param nbTirs
	 * @param dispersion 
	 * @param boss TODO
	 */
	public void tirEventail(TireurAngle t, int nbTirs, int dispersion, float now, float prochainTir, boolean boss) {
		if (now > prochainTir) {
			for (int i = - (nbTirs/2) ; i <= nbTirs/2 ; i++) 
				t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), t.getDirectionTir().rotate(t.getAngleTir() + (i * dispersion)), boss);
			t.setProchainTir(now + cadence);
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
	public void tirOmbrelle(TireurAngle t, int nbTirs, float dispersion, float maintenant, float prochainTir, float ecartCentre) {
		if (maintenant > prochainTir) {
			vecteurCible.x = 0;
			vecteurCible.y = -1;
			vecteurCible.rotate(t.getAngleTir());
			final int numeroTrou = nbTirs/2;
			float angleDepart = t.getAngleTir() - (( (dispersion * (nbTirs-1)) + ecartCentre * 2) / 2f);
			for (int i = 0; i < nbTirs; i++) {
				if (i == numeroTrou) {
					angleDepart += ecartCentre;
					Fireball.POOL.obtain().init(t.getPositionDuTir(6), t.getModifVitesse(), angleDepart + (i * dispersion));
				} else {
					if (i == numeroTrou+1) 
						angleDepart += ecartCentre;
					t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), angleDepart + (i * dispersion));
				}
			}
			t.setProchainTir(maintenant + cadence);
		}
	}

	public void tirVersJoueur(Tireur t, float maintenant, float prochainTir) {
		if (maintenant > prochainTir) {
			final EnemyWeapon a = t.getArme();
			vecteurPosition.x = t.getPositionDuTir(0).x;
			vecteurPosition.y = t.getPositionDuTir(0).y;
			vecteurCible.x = 1;
			vecteurCible.y = 0;
			vecteurCible.rotate(Physic.getAngleWithPlayer(vecteurPosition, a.getWidth()/2, a.getHeight()/2));
			a.init(vecteurPosition, t.getModifVitesse(), vecteurCible, false);//, angle);
			t.setProchainTir(maintenant + cadence);
		}
	}
	
	public void tirVersJoueur(Tireur t, float maintenant, float prochainTir, int nbTirs, float dispersionTotale) {
		if (maintenant > prochainTir) {
			final EnemyWeapon a = t.getArme();
			vecteurPosition.x = t.getPositionDuTir(0).x;
			vecteurPosition.y = t.getPositionDuTir(0).y;
			vecteurCible.x = 1;
			vecteurCible.y = 0;
			vecteurCible.rotate(Physic.getAngleWithPlayer(vecteurPosition, a.getWidth()/2, a.getHeight()/2));
			a.initDispersion(vecteurPosition, t.getModifVitesse(), vecteurCible, nbTirs, dispersionTotale, t);
			t.setProchainTir(maintenant + cadence);
		}
	}

	/**
	 * Attention a la direction passee
	 * @param t
	 * @param nombreTirs
	 * @param mort
	 * @param maintenant
	 * @param prochainTir
	 */
	public void tirEnRafaleGaucheEtDroite(TireurPlusieurFois t, int nombreTirs, float maintenant, float prochainTir) {
		if (maintenant > prochainTir) {
			vecteurCible = t.getDirectionTir();
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), vecteurCible, false);
			vecteurCible.x = -vecteurCible.x;
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), vecteurCible, false);
			if (t.getNumeroTir() >= nombreTirs) {
				t.addNombresTirs(-t.getNumeroTir());
				t.setProchainTir(maintenant + (cadence * nombreTirs));
			} else {
				t.setProchainTir(maintenant + cadence);
			}
			t.addNombresTirs(1);
		}
	}

	public void tirEnRafale(TireurPlusieurFois t, int nombreTirs, float maintenant, float prochainTir) {
		if (maintenant > prochainTir) {
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), t.getDirectionTir(), false);
			if (t.getNumeroTir() >= nombreTirs) {
				t.addNombresTirs(-t.getNumeroTir());
				t.setProchainTir(maintenant + (cadence * nombreTirs * 2));
			} else {
				t.setProchainTir(maintenant + cadence);
			}
			t.addNombresTirs(1);
		}
	}

	public void tirBalayage(TireurBalayage t, float maintenant, float prochainTir) {
		if (maintenant > prochainTir) {
			if (t.getNombreTirsAvantChangement() < t.getNumeroTir()) {
				t.setSens(false);
			} else if (-t.getNombreTirsAvantChangement() > t.getNumeroTir()) {
				t.setSens(true);
			}
			vecteurCible.y = t.getDirectionTir().y;
			vecteurCible.x = t.getDirectionTir().x;
			vecteurCible.rotate(t.getAngleTir() + (t.getEcartTirs() * t.getNumeroTir()));
			
			if (t.getSens()) 	t.addNombresTirs(1);
			else 				t.addNombresTirs(-1);
			
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), vecteurCible, false);
			t.setProchainTir(maintenant + cadence);
		}
	}

	private static Vector2 tmpPos, tmpDir = new Vector2(), tmp2 = new Vector2();
	private static int tmpInt;
	public void tirShotgun(Tireur t, float now, float prochainTir, int rnd, int min, float angle) {
		if (now > prochainTir) {
			tmpInt = min + EndlessMode.R.nextInt(rnd);
			for (int i =0; i < tmpInt; i++) {
				tmpDir.x = (float) (EndlessMode.R.nextGaussian() * Stats.UU);
				tmpDir.y = ((float) -Math.abs(EndlessMode.R.nextGaussian() * Stats.UUU)) - Stats.UUU;
				tmpDir.rotate(angle);
				tmpPos = t.getPositionDuTir(0);
				tmp2.x = 0;
				tmp2.y = -Insecte.DEMI_LARGEUR;
				tmp2.rotate(tmpDir.angle() + 90);
				tmpPos.add(tmp2);
				t.getArme().init(tmpPos, tmpDir);
			}
			t.setProchainTir(now + cadence);
		}
	}

	public void doubleTirVersBasRandomize(Tireur t, float now, float prochainTir, float dispersion) {
		if (now > prochainTir) {
			tmpDir.x = 0;
			tmpDir.y = -1;
			t.getArme().init(t.getPositionDuTir(1), t.getModifVitesse(), tmpDir.rotate(
					(EndlessMode.R.nextFloat() - 0.5f) * dispersion
					), false);
			tmpDir.x = 0;
			tmpDir.y = -1;
			t.getArme().init(t.getPositionDuTir(2), t.getModifVitesse(), tmpDir.rotate(
					(EndlessMode.R.nextFloat() - 0.5f) * dispersion
					), false);
			t.setProchainTir(now + cadence);
		}
	}


}
