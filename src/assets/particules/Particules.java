package assets.particules;

import vaisseaux.armes.Armes;
import vaisseaux.armes.joueur.ArmeAdd;
import vaisseaux.armes.joueur.ArmeJoueur;
import vaisseaux.armes.joueur.ArmesBalayage;
import vaisseaux.armes.joueur.ArmesDeBase;
import vaisseaux.armes.joueur.ArmesTrois;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.joueur.VaisseauType1;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Particules {
	
	public static final int MAX = 150;
	public static final int MAX_ARMES_JOUEUR = 450;
	public static Array<Particule> restes = new Array<Particule>(false, MAX);
	public static Array<Particule> flammes = new Array<Particule>(false, MAX);
	public static Array<Particule> explosions = new Array<Particule>(false, MAX);
	public static Array<Particule> armesJoueur = new Array<Particule>(false, MAX_ARMES_JOUEUR);
	private static float yVaisseau = 0;
	
	public static void render(SpriteBatch batch) {
		for (Particule p : restes){
			p.afficher(batch);
			if (p.mouvementEtVerif() == false) {
				restes.removeValue(p, true);
				p.free();
			}
		}
		for (Particule p : flammes){
			p.afficher(batch);
			if (p.mouvementEtVerif() == false) {
				flammes.removeValue(p, true);
				p.free();
			}
		}
		for (Particule p : explosions){
			p.afficher(batch);
			if (p.mouvementEtVerif() == false) {
				explosions.removeValue(p, true);
				p.free();
			}
		}
		
		for (Particule p : armesJoueur){
			p.afficher(batch);
			if (p.mouvementEtVerif() == false) {
				armesJoueur.removeValue(p, true);
				p.free();
			}
		}
	}
	
	public static void ajoutDebris(ArmeJoueur a) {
		if (restes.size < MAX) {
			ArmeJoueur.roueCouleurs();
			Debris r = Debris.pool.obtain();
			r.init(a);
			restes.add(r);
			
			Debris r2 = Debris.pool.obtain();
			r2.init(a);
			restes.add(r2);
			
			Debris r3 = Debris.pool.obtain();
			r3.init(a);
			restes.add(r3);
		}
	}

	/**
	 * Il tient compte de si on monte ou on descend pour ajouter plus ou moins de flammes
	 * @param v
	 */
	public static void ajoutFlammes(VaisseauType1 v) {
		if (Particules.flammes.size < Particules.MAX) {
			FlammesVaisseau r = FlammesVaisseau.pool.obtain();
			r.init(v);
			flammes.add(r);
			
			if (yVaisseau-1 < v.position.y) {
				FlammesVaisseau r1 = FlammesVaisseau.pool.obtain();
				r1.init(v);
				flammes.add(r1);
		
				FlammesVaisseau r2 = FlammesVaisseau.pool.obtain();
				r2.init(v);
				flammes.add(r2);
				if (yVaisseau+1 < v.position.y) {
					FlammesVaisseau r3 = FlammesVaisseau.pool.obtain();
					r3.init(v);
					flammes.add(r3);
					
				}
			}
		}
		yVaisseau = v.position.y;
	}

	public static void clear() {
		for(Particule p : flammes) p.free();
		for(Particule p : restes) p.free();
		for(Particule p : explosions) p.free();
		for(Particule p : armesJoueur) p.free();
		restes.clear();
		flammes.clear();
		explosions.clear();
		armesJoueur.clear();
	}

	public static void explosion(Ennemis ennemis) {
		int max = ennemis.getDemiHauteur() + ennemis.getDemiLargeur();
		for (int i = 0; i < max; i++) {
			ParticuleExplosion p = ParticuleExplosion.pool.obtain();
			p.init(ennemis);
			explosions.add(p);
		}
	}

	public static void armeHantee(Armes a) {
		if (armesJoueur.size < MAX_ARMES_JOUEUR) {
			ParticuleArmeHantee p = ParticuleArmeHantee.pool.obtain();
			p.init(a);
			armesJoueur.add(p);
		}
	}

	public static void ajoutAdd(ArmeAdd a) {
//		if (armesJoueur.size < MAX_ARMES_JOUEUR) {
			ParticuleArmeAddTrainee p = ParticuleArmeAddTrainee.pool.obtain();
			p.init(a);
			armesJoueur.add(p);
//		}
	}

	public static void ajoutArmeDeBase(ArmesDeBase a) {
		if (armesJoueur.size < MAX_ARMES_JOUEUR) {
			ParticuleArmeDeBase p = ParticuleArmeDeBase.pool.obtain();
			p.init(a);
			armesJoueur.add(p);
		}
	}

	public static void ajoutArmeBalayage(ArmesBalayage a) {
		if (armesJoueur.size < MAX_ARMES_JOUEUR) {
			ParticuleArmeBalayage p = ParticuleArmeBalayage.pool.obtain();
			p.init(a);
			armesJoueur.add(p);
		}
	}

	public static void armeTrois(ArmesTrois a) {
		if (armesJoueur.size < MAX_ARMES_JOUEUR) {
			ParticuleArmeTrois p = ParticuleArmeTrois.pool.obtain();
			p.init(a);
			armesJoueur.add(p);
		}
	}

}
