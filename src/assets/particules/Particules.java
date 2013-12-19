package assets.particules;

import objets.armes.Armes;
import objets.armes.joueur.ArmeAdd;
import objets.armes.joueur.ArmeJoueur;
import objets.armes.joueur.ArmesBalayage;
import objets.armes.joueur.ArmesDeBase;
import objets.armes.joueur.ArmesTrois;
import objets.ennemis.Ennemis;
import objets.joueur.VaisseauJoueur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Particules {
	 	
	public static final int MAX = 150;
	public static final int MAX_ARMES_JOUEUR = 450;
	public static int nbDebris = 0;
	public static int nbArmeJoueur = 0;
	public static int nbFlammes = 0;
	// CHECK LA TAILLE
	public static Array<Particule> particules = new Array<Particule>(false, MAX*4);
	public static Array<Particule> background = new Array<Particule>(false, 300);
	public static Array<ParticlePanneau> boutons = new Array<ParticlePanneau>(false, 300);
	public static Array<ParticleUiElement> uiElement = new Array<ParticleUiElement>();
	private static float yVaisseau = 0;
	private static int fps = 0;
	private static int frame = 0;
	
	/**
	 * Ajoute des étoiles
	 */
	public static void initBackground() {
		ParticuleStars.initBackground();
	}
	
	/**
	 * Display the background and add a star if required
	 * @param batch
	 */
	public static void background(SpriteBatch batch) {
		ParticuleStars.mightAdd();
		drawUi(batch);
		for (Particule p : background) {
			p.display(batch);
			if (p.mouvementEtVerif() == false) {
				background.removeValue(p, true);
			}
		}
		fps += Gdx.graphics.getFramesPerSecond();
		frame++;
//		Gdx.app.log(String.valueOf(fps / frame), String.valueOf(EndlessMode.maintenant));
	}

	public static void drawUi(SpriteBatch batch) {
		for (ParticlePanneau p : boutons) {
			p.display(batch);
			if (p.mouvementEtVerif() == false) {
				boutons.removeValue(p, true);
				ParticlePanneau.pool.free(p);
			}
		}
		for (ParticleUiElement p : uiElement){
			p.display(batch);
			if (p.mouvementEtVerif() == false) {
				uiElement.removeValue(p, true);
				ParticleUiElement.pool.free(p);
			}
		}
	}
	
	public static void render(SpriteBatch batch) {
		for (Particule p : particules){
			p.display(batch);
			if (p.mouvementEtVerif() == false) {
				particules.removeValue(p, true);
				p.free();
			}
		}
	}
	
	public static void ajoutDebris(ArmeJoueur a) {
		if (nbDebris < MAX) {
			ArmeJoueur.roueCouleurs();
			Debris r = Debris.pool.obtain();
			r.init(a);
			particules.add(r);
			
			Debris r2 = Debris.pool.obtain();
			r2.init(a);
			particules.add(r2);
			
			Debris r3 = Debris.pool.obtain();
			r3.init(a);
			particules.add(r3);
			nbDebris += 3;
		}
	}

	/**
	 * Il tient compte de si on monte ou on descend pour ajouter plus ou moins de flammes
	 * @param v
	 */
	public static void ajoutFlammes(VaisseauJoueur v) {
		if (nbFlammes < Particules.MAX) {
			FlammesVaisseau r = FlammesVaisseau.pool.obtain();
			r.init(v);
			particules.add(r);
			
			if (yVaisseau-1 < v.position.y) {
				FlammesVaisseau r1 = FlammesVaisseau.pool.obtain();
				r1.init(v);
				particules.add(r1);
				FlammesVaisseau r2 = FlammesVaisseau.pool.obtain();
				r2.init(v);
				particules.add(r2);
				if (yVaisseau+1 < v.position.y) {
					FlammesVaisseau r3 = FlammesVaisseau.pool.obtain();
					r3.init(v);
					particules.add(r3);
					
				}
			}
		}
		yVaisseau = v.position.y;
	}

	public static void clear() {
		for(Particule p : particules) p.free();
		particules.clear();
		ParticuleStars.nextEtoile = 0;
	}

	public static void explosion(Ennemis ennemis) {
		int max = ennemis.getDemiHauteur() + ennemis.getDemiLargeur();
		for (int i = 0; i < max; i++) {
			ParticuleExplosion p = ParticuleExplosion.pool.obtain();
			p.init(ennemis);
			particules.add(p);
		}
	}

	public static void armeHantee(Armes a) {
		if (nbArmeJoueur < MAX_ARMES_JOUEUR) {
			ParticuleArmeHantee p = ParticuleArmeHantee.pool.obtain();
			p.init(a);
			particules.add(p);
		}
	}

	public static void ajoutAdd(ArmeAdd a) {
		ParticuleArmeAddTrainee p = ParticuleArmeAddTrainee.pool.obtain();
		p.init(a);
		particules.add(p);
	}

	public static void ajoutArmeDeBase(ArmesDeBase a) {
		if (nbArmeJoueur < MAX_ARMES_JOUEUR) {
			ParticuleArmeDeBase p = ParticuleArmeDeBase.pool.obtain();
			p.init(a);
			particules.add(p);
		}
	}

	public static void ajoutArmeBalayage(ArmesBalayage a) {
		if (nbArmeJoueur < MAX_ARMES_JOUEUR) {
			ParticuleArmeBalayage p = ParticuleArmeBalayage.pool.obtain();
			p.init(a);
			particules.add(p);
		}
	}

	public static void armeTrois(ArmesTrois a) {
		if (nbArmeJoueur < MAX_ARMES_JOUEUR) {
			ParticuleArmeTrois p = ParticuleArmeTrois.pool.obtain();
			p.init(a);
			particules.add(p);
		}
	}

	public static void ajoutPanneau(Vector2 posParticule, Vector2 baseDirection) {
//		FlammesVaisseau p = FlammesVaisseau.pool.obtain();
//		p.init(posParticule, baseDirection);
//		boutons.add(p);
//		FlammesVaisseau p2 = FlammesVaisseau.pool.obtain();
//		p2.init(posParticule, baseDirection);
//		boutons.add(p2);
//		FlammesVaisseau p3 = FlammesVaisseau.pool.obtain();
//		p3.init(posParticule, baseDirection);
//		boutons.add(p3);
//		FlammesVaisseau p4 = FlammesVaisseau.pool.obtain();
//		p4.init(posParticule, baseDirection);
//		boutons.add(p4);
//		FlammesVaisseau p5 = FlammesVaisseau.pool.obtain();
//		p5.init(posParticule, baseDirection);
//		boutons.add(p5);
//		FlammesVaisseau p6 = FlammesVaisseau.pool.obtain();
//		p6.init(posParticule, baseDirection);
//		boutons.add(p6);
		ParticlePanneau p = ParticlePanneau.pool.obtain();
		p.init(posParticule, baseDirection);
		boutons.add(p);
	}
	
	public static void ajoutUiElement(float x, float y, boolean selected) {
		ParticleUiElement p = ParticleUiElement.pool.obtain();
		p.init(x, y, selected);
		uiElement.add(p);
		ParticleUiElement p2 = ParticleUiElement.pool.obtain();
		p2.init(x, y, selected);
		uiElement.add(p2);
//		ParticleUiElement p3 = ParticleUiElement.pool.obtain();
//		p3.init(x, y, selected);
//		uiElement.add(p3);
	}
}
