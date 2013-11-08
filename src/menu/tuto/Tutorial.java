//package menu.tuto;
//
//import objets.armes.Armes;
//import objets.bonus.Bonus;
//import objets.bonus.BonusAdd;
//import objets.bonus.XP;
//import objets.ennemis.Ennemis;
//import objets.ennemis.particuliers.nv1.DeBase;
//import objets.joueur.VaisseauJoueur;
//import jeu.EndlessMode;
//import jeu.Physique;
//import menu.CSG;
//import assets.particules.Particule;
//import assets.particules.Particules;
//import bloom.Bloom;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//
//public class Tutorial implements Screen {
//	
//	private static final String P1 = "HELLO, HERE IS THE BORING STUFF THAT WILL TEACH YOU HOW TO PLAY... THANKS FOR PLAYING !     PAY ATTENTION TO THE BONUS? THEY ARE VERY USEFUL";
//	private static final String SHIP = "Touch the screen to move your ship";
//	private static final String SHOOTHIM = "Shoot them !";
//	private static final String GG = "ENEMIES WILL GIVE YOU POINTS. USE THEM TO IMPROVE YOUR SHIP !";
//	private SpriteBatch batch = CSG.batch;
//	private float xP1 = CSG.LARGEUR_ECRAN;
//	private Bloom bloom;
//	private float alphaBackground = 0.02f, vitesseMontee = 200f, alphaShip = 0.02f, timerPhase = 0;
//	private VaisseauJoueur vaisseau = new VaisseauJoueur();
//	private int phase = 0;
//	private boolean alterner = false;
//	private DeBase deBase1 = DeBase.pool.obtain(), deBase2 = DeBase.pool.obtain();
//	private Action actionAlphaBackground = new Action() {
//		public void action() {
//			alphaBackground *= 1 + Gdx.graphics.getDeltaTime();
//			if (alphaBackground > 1) alphaBackground = 1;
//			
//			if (VaisseauJoueur.position.y < VaisseauJoueur.HAUTEUR - 2 && phase == 0) {
//				if (VaisseauJoueur.position.y < VaisseauJoueur.HAUTEUR) VaisseauJoueur.position.y += vitesseMontee * Gdx.graphics.getDeltaTime();
//				vitesseMontee /= 1 + (Gdx.graphics.getDeltaTime() * 1.5f);
//			}
//		}
//	};
//	private Action actionShipFont = new Action() {
//		public void action() {
//			batch.begin();
//			CSG.menuFont.setColor(1, 1, .8f, alphaShip);
//			CSG.menuFont.draw(batch, SHIP, CSG.DEMI_LARGEUR_ZONE_JEU - (CSG.menuFont.getBounds(SHIP).width / 2), CSG.DEMI_HAUTEUR_ECRAN);
//			CSG.menuFont.setColor(originalColor);
//			batch.end();
//			alphaShip *= 1 + Gdx.graphics.getDeltaTime();
//			if (alphaShip > 1) alphaShip = 1;
//		}
//	};
//	private Action actionMouvementVaisseau = new Action() {		public void action() {
//		batch.begin();
//		EndlessMode.mouvement();
//		EndlessMode.strScore = String.valueOf(EndlessMode.score);
//		batch.end();
//		}	};
//	private Action actionTirVaisseau = new Action() {		public void action() {
//		if (alterner) vaisseau.tir();
//		alterner = !alterner;
//		}
//	};
//	private Action actionPolice = new Action() {		public void action() {
//		batch.begin();
//		CSG.menuFontPetite.draw(batch, P1, xP1, CSG.menuFontPetite.getBounds(P1).height + 2);
//		batch.end();
//		xP1 -= Gdx.graphics.getDeltaTime() * 40;
//			if (xP1 < (-CSG.menuFontPetite.getBounds(P1).width)) {
//				xP1 = CSG.LARGEUR_ECRAN;
//			}
//		}
//	};
//	private Action actionAjoutEnnemiDeBase = new Action() {
//		public void action() {	
//			if(Ennemis.LISTE.size == 0) {
//				Ennemis.LISTE.add(deBase1);
//				deBase1.position.x = CSG.DEMI_LARGEUR_ZONE_JEU - DeBase.LARGEUR;
//				Ennemis.LISTE.add(deBase2);
//				deBase2.position.x = CSG.DEMI_LARGEUR_ZONE_JEU + DeBase.LARGEUR;
//			}
//	}};
//	private Action actionPoliceTirerSurEnnemi = new Action() {
//		public void action() {	
//			batch.begin();
//			CSG.menuFont.draw(batch, SHOOTHIM, deBase1.position.x - (CSG.menuFont.getBounds(SHOOTHIM).width/2), deBase1.position.y + deBase1.HAUTEUR * 2);
//			batch.end();
//	}};
//	private Action actionAfficherPoints = new Action() {
//		public void action() {	
//			batch.begin();
//			CSG.menuFontPetite.draw(batch, EndlessMode.strScore, EndlessMode.cam.position.x + EndlessMode.X_CHRONO, EndlessMode.HAUTEUR_POLICE);
//			batch.end();
//	}};
//	private Action aAfficherGgEtAddXp = new Action() {
//		public void action() {
//			if (EndlessMode.score == 0) Bonus.liste.add(XP.pool.obtain());
//			batch.begin();
//			CSG.menuFont.draw(batch, GG, EndlessMode.cam.position.x + EndlessMode.X_CHRONO, EndlessMode.HAUTEUR_POLICE * 3);
//			batch.end();
//	}};
//	private Color originalColor = CSG.menuFont.getColor();
//	Phase phase0 = new Phase(actionAlphaBackground);
//	Phase pMouvementDemande = new Phase(actionAlphaBackground, actionShipFont);
//	Phase pMouvementPermis = new Phase(actionAlphaBackground, actionMouvementVaisseau);
//	Phase pTir = new Phase(actionAlphaBackground, actionMouvementVaisseau, actionTirVaisseau);
//	Phase pApparitionEnnemi = new Phase(actionAlphaBackground, actionMouvementVaisseau, actionTirVaisseau, actionAjoutEnnemiDeBase, actionPoliceTirerSurEnnemi);
//	Phase pEnnemisDescendent = new Phase(actionAfficherPoints, actionAlphaBackground, actionMouvementVaisseau, actionTirVaisseau, actionPoliceTirerSurEnnemi);
//	Phase pEnnemisMorts = new Phase(actionAfficherPoints, aAfficherGgEtAddXp, actionAlphaBackground, actionMouvementVaisseau, actionTirVaisseau);
//	private final Phase[] phases = {phase0, pMouvementDemande, pMouvementPermis, pTir, pApparitionEnnemi, pEnnemisDescendent, pEnnemisMorts};
//
//	public Tutorial(Game game) {
//		bloom = new Bloom();
//		bloom.setBloomIntesity(CSG.profil.intensiteBloom);
//		VaisseauJoueur.position.x = CSG.DEMI_LARGEUR_ZONE_JEU - VaisseauJoueur.DEMI_LARGEUR;
//		VaisseauJoueur.position.y = -VaisseauJoueur.HAUTEUR;
//		CSG.reset();
//		EndlessMode.cam.position.set(CSG.LARGEUR_ZONE_JEU/2, CSG.HAUTEUR_ECRAN / 2, 0);
//	}
//
//	@Override
//	public void render(float delta) {
//		EndlessMode.cam.update();
//		batch.setProjectionMatrix(EndlessMode.cam.combined);
//		
//		bloom.capture();
//		batch.begin();
//		
//		background(delta);
//		vaisseau.draw(batch);
//		Ennemis.affichageEtMouvement(batch);
//		Bonus.affichageEtMouvement(batch);
//		Armes.affichageEtMouvement(batch);
//		Particules.render(batch);
//		batch.end();
//		bloom.render();
//		
//		phases[phase].act();
//		timerPhase += delta;
//		triggers(delta);
//		Physique.testCollisions();
//		EndlessMode.delta = delta;
//		EndlessMode.delta15 = delta * 15;
//		EndlessMode.maintenant += delta;
//	}
//
//	private void triggers(float delta) {
//		// Quand le vaisseau est en place
//		switch (phase) {
//		case 0:		if (VaisseauJoueur.position.y > VaisseauJoueur.HAUTEUR - 2 && phase == 0) nextPhase();	break;
//		case 1:		if (Gdx.input.justTouched())											nextPhase();	break;
//		case 2:		if (timerPhase > .8f)													nextPhase();	break;
//		case 3:		if (timerPhase > .8f)													nextPhase();	break;
//		case 4:		if (timerPhase > .8f && Ennemis.LISTE.size < 2)							nextPhase();	break;
//		case 5:		if (Ennemis.LISTE.size == 0)											nextPhase();	break;
//		}
//	}
//
//	private void nextPhase() {
//		timerPhase = 0;
//		phase++;
//		System.out.println(phase);
//	}
//
//	private void background(float delta) {
//		batch.setColor(1, 1, 1, alphaBackground);
//		batch.setColor(1,1,1,1);
//	}
//
//	@Override
//	public void resize(int width, int height) {	}
//	@Override
//	public void show() {	}
//	@Override
//	public void hide()  {	}
//	@Override
//	public void pause()  {	}
//	@Override
//	public void resume()  {	}
//	@Override
//	public void dispose()  {}
//}
