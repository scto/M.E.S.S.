package jeu;
import java.text.DecimalFormat;

import menu.CSG;
import menu.Menu;
import vaisseaux.armes.Armes;
import vaisseaux.bonus.Bonus;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.particuliers.nv1.DeBase;
import vaisseaux.ennemis.particuliers.nv1.Insecte;
import vaisseaux.ennemis.particuliers.nv1.Kinder;
import vaisseaux.ennemis.particuliers.nv1.Laser;
import vaisseaux.ennemis.particuliers.nv1.PorteRaisin;
import vaisseaux.ennemis.particuliers.nv1.QuiTir;
import vaisseaux.ennemis.particuliers.nv1.QuiTir2;
import vaisseaux.ennemis.particuliers.nv1.QuiTourne;
import vaisseaux.ennemis.particuliers.nv1.Toupie;
import vaisseaux.ennemis.particuliers.nv1.ZigZag;
import vaisseaux.ennemis.particuliers.nv2.BouleTirCote;
import vaisseaux.ennemis.particuliers.nv2.BouleTirCoteRotation;
import vaisseaux.joueur.VaisseauType1;
import assets.AssetMan;
import assets.SoundMan;
import assets.particules.Particules;
import bloom.Bloom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.swarmconnect.SwarmLeaderboard;

/**
 * Classe principale gerant le mode infini du jeu
 * @author Julien
 */
public class Endless implements Screen {
	
	private Game game;
	// A F F I C H A G E
	private static SpriteBatch batch = CSG.batch;
	private Bloom bloom;
	private GL20 gl;
	// A L T E R N E R
	private boolean alterner = true, alternerUpdateScore = true;
	// E T A T S
	private static boolean activerRalentissement = false;
	private static boolean pause = false;
	private boolean scoreEnvoye = false;
	private static boolean activerStop = false;
	private static boolean perdu = false;
	
	public static final int MAX_STOP_BONUS = 2; 
	private static VaisseauType1 vaisseau;
	public static String strScore = "0";
	private static float chronoRalentir = 0;
	public static float maintenant = 0, score = 0;
	private float vientDEtreTouche = 0;
	public static final int X_CHRONO = CSG.DIXIEME_LARGEUR/2 - CSG.DEMI_LARGEUR_ECRAN;
	private static final int X_SLOW = (CSG.DEMI_LARGEUR_ECRAN/6);
	public static final int HAUTEUR_POLICE = CSG.DIXIEME_HAUTEUR/3;
	// *************************  J  A  U  G  E  *************************
	private TextureRegion rougefonce;
	private static final int MAX_LARGEUR_JAUGE = CSG.LARGEUR_ECRAN/6, DIXIEME_LARGEUR_JAUGE = MAX_LARGEUR_JAUGE/10, TIER_LARGEUR_JAUGE = MAX_LARGEUR_JAUGE/3, HAUTEUR_JAUGE = CSG.HAUTEUR_ECRAN/75;
	// *************************  D  P  A  D  ****************************
	private static final TextureRegion fleche = AssetMan.fleche;
	private static final float HAUTEUR_FLECHE = CSG.HAUTEUR_ECRAN/30, DEMI_HAUTEUR_FLECHE = HAUTEUR_FLECHE/2, LARGEUR_FLECHE = HAUTEUR_FLECHE, DEMI_LARGEUR_FLECHE = LARGEUR_FLECHE/2;

	DecimalFormat df = new DecimalFormat();
	public static OrthographicCamera cam = new OrthographicCamera(CSG.LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN);;
	public static int modeDifficulte, nbBonusStop = 0, nbBombes = 0;
	public static float color = 0, colorRapide, intensiteBloomOrigin = 1, camXmoinsDemiEcran, delta = 0, tempsBonusStop = 0, delta15 = 0;
	public static boolean sensCouleurGlobale = false, sensCouleurRapide = false, effetBloom = false, xpAjout = false;
	private static boolean onAchoisis = false;
	private static boolean onVaRalentir = false;
	private static boolean onVaStopper = false;
	public static boolean afficherMenuRadial = false;
	private static int menuX = 0;
	private static int menuY = 0;
	private int conseil = 0;
	private static final String conseil1 = "YOU SHOULD UPGRADE YOUR WEAPON.    GO TO THE XP MENU";
	private static final String conseil2 = "GO TO THE OPTION MENU IF YOU WANT TO CHANGE THE CONTROL";
	private static final String conseil3 = "KEEP TRYING";
	private static final String conseil4 = "USE THE BOMBE TO CLEAR THE PATH";
	private static final String conseil5 = "USE THIS BONUS TO STOP THE TIME";
	private static final String conseil6 = "USE THIS BONUS SLOW THE TIME";
	private static final String tryAgain = "TRY AGAIN NOW AND YOU CAN KEEP YOUR SHIELD AND ADDS";
	public static boolean konamiCode = false;

	public Endless(Game game, SpriteBatch batch, int level) {
		super();
		Endless.batch = batch;
		this.game = game;
		vaisseau = new VaisseauType1();
		Endless.modeDifficulte = level;
		Ennemis.initLevelBoss(level);
		init();
		vaisseau.initialiser();
		
		Client c = new Client("127.0.0.1", this);
		c.send();
	}

	private void init() {
		vaisseau.reInit(); // Pour remettre les positions mais garder shield et adds
		if (Gdx.app.getVersion() != 0) CSG.myRequestHandler.showAds(false); // desactiver adds. A VIRER POUR LA RELEASE
		// ** DEPLACEMENT ZONE DE JEU
		xpAjout = false;
		cam.position.set(CSG.LARGEUR_ZONE_JEU/2, CSG.HAUTEUR_ECRAN / 2, 0);
        gl = Gdx.graphics.getGL20();
		gl.glViewport(0, 0, CSG.LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN);
		
		df.setMaximumFractionDigits(1);
		df.setMinimumFractionDigits(1);	
		df.setDecimalSeparatorAlwaysShown(true);
		
		CSG.reset();
		chronoRalentir = 0;
        scoreEnvoye = false;
        perdu = false;
        maintenant = 0;
        Ennemis.clear();
		if (CSG.profil.bloom) {
			bloom = new Bloom();
			bloom.setBloomIntesity(CSG.profil.intensiteBloom);
		}
//        Gdx.graphics.setVSync(false);
        SoundMan.playMusic();
		rougefonce = CSG.getAssetMan().getAtlas().findRegion("rougefonce");
		pause = false;
//		EnnemiPorteNef.dejaPresent = false;
		score = 0;
		strScore = String.valueOf(score);
		tempsBonusStop = 0;
		nbBonusStop = 0;
		activerStop = false;
		nbBombes = 0;
		
//		Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//					System.out.println((int)maintenant + ";" + Gdx.graphics.getFramesPerSecond());
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		});
//		t.start();
	}

	@Override
	public void render(float delta) {
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			Ennemis.LISTE.add(DeBase.pool.obtain());
//			Ennemis.LISTE.add(AvionNv3.pool.obtain());
		}
		if (Gdx.input.isKeyPressed(Keys.Z)) 	Ennemis.LISTE.add(Insecte.pool.obtain());
		if (Gdx.input.isKeyPressed(Keys.E))		Ennemis.LISTE.add(Kinder.pool.obtain());
		if (Gdx.input.isKeyPressed(Keys.R))		Ennemis.LISTE.add(Laser.pool.obtain());
		if (Gdx.input.isKeyPressed(Keys.T))		Ennemis.LISTE.add(PorteRaisin.pool.obtain());
		if (Gdx.input.isKeyPressed(Keys.Y))		Ennemis.LISTE.add(QuiTir.pool.obtain());
		if (Gdx.input.isKeyPressed(Keys.U))		Ennemis.LISTE.add(QuiTir2.pool.obtain());
		if (Gdx.input.isKeyPressed(Keys.I))		Ennemis.LISTE.add(QuiTourne.pool.obtain());
		if (Gdx.input.isKeyPressed(Keys.O))		Ennemis.LISTE.add(Toupie.pool.obtain());
		if (Gdx.input.isKeyPressed(Keys.P))		Ennemis.LISTE.add(ZigZag.pool.obtain());
		
		if (Gdx.input.isKeyPressed(Keys.Q))		Ennemis.LISTE.add(BouleTirCote.pool.obtain());
		if (Gdx.input.isKeyPressed(Keys.S))		Ennemis.LISTE.add(BouleTirCoteRotation.pool.obtain());
//		if (Gdx.input.isKeyPressed(Keys.R))
//			Ennemis.liste.add(new EnnemiCylon());
//		if (Gdx.input.isKeyPressed(Keys.T))
//			Ennemis.liste.add(new EnnemiBossMine());
//		if (Gdx.input.justTouched()) 
		{
//			Client c = new Client("80.201.86.80", this);	
//			c.send("Coucou 80.201.86.80	");
//			Client d = new Client("192.168.1.2", this);	
//			d.send("Coucou 192.168.1.2");
//			Ennemis.liste.add(ZigZag.pool.obtain());
//			Ennemis.liste.add(ZigZagNv3.pool.obtain());
//			Ennemis.liste.add(ZigZagNv3.pool.obtain());
//			Ennemis.liste.add(ZigZagNv3.pool.obtain());
//			Ennemis.liste.add(ZigZagNv3.pool.obtain());
//			Ennemis.LISTE.add(Ombrelle.pool.obtain());
		}
//		if (Gdx.input.justTouched()) 
//			Ennemis.liste.add(EnnemiBossMine.pool.obtain());
//			Ennemis.liste.add(Insecte.pool.obtain());
//		}
//			Ennemis.liste.add(new EnnemiBouleQuiSArrete());
//			Ennemis.liste.add(new EnnemiInsecte());
//			Ennemis.liste.add(ZigZag.pool.obtain());
//		System.out.println(Gdx.graphics.getFramesPerSecond());
		preRendu();
		CSG.renderBackground(batch);
		if (!pause) {
			if (delta < 1) { 
				Endless.delta = delta;
				if ((afficherMenuRadial || onAchoisis) && CSG.profil.typeControle != CSG.CONTROLE_ACCELEROMETRE) Endless.delta = delta/7;
				score += (Endless.delta+Endless.delta+Endless.delta);
				delta15 = Endless.delta * 15;
			}
//			Endless.delta /= 3;
			// S I   O N   A   P A S   E N C O R E   P E R D U
			if (!perdu && !activerStop) {
				if (activerRalentissement) activerRalentissement(delta);
				affichageNonPerdu();
			} else {
				if (activerStop) {
					affichageEtUpdateStop(delta);
				} else {
				// A   V I R E R   P O U R   L A   R E L E A S E
					if (perdu && Gdx.app.getVersion() != 0 && !scoreEnvoye && !konamiCode){
						switch (modeDifficulte) {
						case 1:			SwarmLeaderboard.submitScore(6475, score);			break;
						case 2:			SwarmLeaderboard.submitScore(7317, score);			break; 
						case 3:			SwarmLeaderboard.submitScore(9101, (int)score);			break;
						}
						scoreEnvoye = true;
					}
				}
				affichagePerdu();
				if (!activerStop && perdu) scoreEtConseils();
			}					 
			update(); // ** ** UPDATE inline. Gain en moyenne : + 2.5fps sur 450 (test sur 8 fois sur 5 min), en gros rien de mesurable
		} else { // D O N C   E N   P A U S E
			if ((Gdx.input.isKeyPressed(Keys.BACK) && (maintenant > vientDEtreTouche + .1)) || Gdx.input.isKeyPressed(Keys.ESCAPE)) {
				Menu menu = new Menu(game);
				game.setScreen(menu);
			}
			affichagePerdu();
			afficherResume();
			if (Gdx.input.justTouched()) {
				if (maintenant - vientDEtreTouche < .4) {
					pause = false;
					SoundMan.playMusic();
				}
				vientDEtreTouche = maintenant;
			}
		}
		batch.end();
		if (CSG.profil.bloom) bloom.render();
		maintenant += Endless.delta;
	}

	private void affichageEtUpdateStop(float delta) {
		tempsBonusStop -= delta;
		batch.draw(rougefonce, (VaisseauType1.position.x + VaisseauType1.DEMI_LARGEUR) - (TIER_LARGEUR_JAUGE * tempsBonusStop)/2, VaisseauType1.position.y - HAUTEUR_JAUGE * 3, TIER_LARGEUR_JAUGE * tempsBonusStop, HAUTEUR_JAUGE);
		if (tempsBonusStop < 0) {
			activerStop = false;
			if (--nbBonusStop > 0)	tempsBonusStop += 3;
		}
	}

	private void preRendu() {		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		if (CSG.profil.bloom) bloomActive();
		else gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  //+10% de perfs !!. Si pas de bloom il faut le mettre
	
		batch.begin();
	}

	private void activerRalentissement(float delta) {
		chronoRalentir -= delta/3;
		Endless.delta /= 3;
		Endless.delta15 = Endless.delta * 15;
		if (chronoRalentir < 0) {
			activerRalentissement = false;
			chronoRalentir = 0;
		}
	}

	private void bloomActive() {
		if (activerStop) bloom.setBloomIntesity(CSG.profil.intensiteBloom + (tempsBonusStop*2));
		if (effetBloom) {
			intensiteBloomOrigin -= 500 * Endless.delta;
			bloom.setBloomIntesity(intensiteBloomOrigin);
			if (intensiteBloomOrigin < CSG.profil.intensiteBloom) {
				effetBloom = false;
				intensiteBloomOrigin = CSG.profil.intensiteBloom;
				bloom.setBloomIntesity(1);
			}
		}
		bloom.capture();
	}

	private void afficherResume() {
		CSG.menuFont.draw(batch, "Double tap : resume",((cam.position.x-CSG.DEMI_LARGEUR_ECRAN)) + (CSG.DEMI_LARGEUR_ECRAN - CSG.menuFont.getBounds("Double tap : resume").width / 2), CSG.DEMI_HAUTEUR_ECRAN);
		CSG.menuFont.draw(batch, "Back : menu",((cam.position.x-CSG.DEMI_LARGEUR_ECRAN)) + (CSG.DEMI_LARGEUR_ECRAN - CSG.menuFont.getBounds("Back : menu").width / 2), CSG.DEMI_HAUTEUR_ECRAN / 2);
	}

	private static void mettrePause() {
		pause = true;
		CSG.profilManager.persist();
	}

	private void scoreEtConseils() {
		if (!xpAjout && !konamiCode) {
			CSG.profil.addXp((int) score);
			xpAjout = true;
		} 
		
		if (score < 200) {
			if (conseil == 0) conseil = (int) (1 + Math.random() * 6);
			switch (conseil) {
			case 1:		afficherConseil(conseil1);								break;
			case 2:		afficherConseil(conseil2);								break;
			case 3:		afficherConseil(conseil3);								break;
			case 4:		afficherConseil(conseil4, AssetMan.bombe, batch);		break;
			case 5:		afficherConseil(conseil5, AssetMan.bonusetoile, batch);	break;
			case 6:		afficherConseil(conseil6, AssetMan.temps, batch);		break;
			}
		}
		
		if (VaisseauType1.addDroite || VaisseauType1.addDroite2 || VaisseauType1.addGauche || VaisseauType1.bouclier || VaisseauType1.addGauche2)
			CSG.menuFontPetite.draw(batch, tryAgain, ((cam.position.x-CSG.DEMI_LARGEUR_ECRAN)) + ((CSG.DEMI_LARGEUR_ECRAN - (CSG.menuFontPetite.getBounds(tryAgain).width)/2)),
					(CSG.DEMI_HAUTEUR_ECRAN/2) - CSG.menuFontPetite.getBounds(tryAgain).height);
		
		CSG.menuFont.setColor(.99f, .85f, 0f, 1);
		CSG.menuFont.draw(batch, strScore, ((cam.position.x-CSG.DEMI_LARGEUR_ECRAN)) + ((CSG.DEMI_LARGEUR_ECRAN - (CSG.menuFont.getBounds(strScore).width)/2)),
				CSG.DEMI_HAUTEUR_ECRAN + CSG.menuFontPetite.getBounds(strScore).height);
	}

	private float prevDelta;
	private void affichagePerdu() {
		prevDelta = delta;
		delta = 0;
		vaisseau.draw(batch);
		Ennemis.affichage(batch);
		delta = prevDelta;
		Particules.render(batch);
		Armes.affichage(batch);
		ui();
	}
	
	public static void effetBloom(){
		effetBloom = true;
		intensiteBloomOrigin = 50;
	}

	private void ui() {
		// ***************                   P   O   L   I   C   E                  ***************
		CSG.menuFontPetite.draw(batch, strScore, cam.position.x + X_CHRONO, HAUTEUR_POLICE);
		// ******** J  A  U  G  E  *************
		batch.draw(rougefonce, cam.position.x - X_SLOW, HAUTEUR_POLICE - HAUTEUR_JAUGE, DIXIEME_LARGEUR_JAUGE * chronoRalentir, HAUTEUR_JAUGE);
		// ****  A F F I C H E R   S T O P  ****
		switch(nbBonusStop) {
		default :
		case 3:	batch.draw(AssetMan.bonusetoile, cam.position.x + X_CHRONO + Bonus.LARGEUR *2 + Bonus.DEMI_LARGEUR *2, HAUTEUR_POLICE*2, Bonus.LARGEUR, Bonus.LARGEUR);
		case 2:	batch.draw(AssetMan.bonusetoile, cam.position.x + X_CHRONO + Bonus.LARGEUR + Bonus.DEMI_LARGEUR, HAUTEUR_POLICE*2, Bonus.LARGEUR, Bonus.LARGEUR);
		case 1:	batch.draw(AssetMan.bonusetoile, cam.position.x + X_CHRONO, HAUTEUR_POLICE*2, Bonus.LARGEUR, Bonus.LARGEUR);
		case 0:
		}
		switch(nbBombes) {
		default :
		case 3:	batch.draw(AssetMan.bombe, CSG.DEMI_LARGEUR_ECRAN + cam.position.x + X_CHRONO + Bonus.LARGEUR *3 + Bonus.DEMI_LARGEUR *3, Bonus.DEMI_LARGEUR, Bonus.LARGEUR, Bonus.LARGEUR);
		case 2:	batch.draw(AssetMan.bombe, CSG.DEMI_LARGEUR_ECRAN + cam.position.x + X_CHRONO + Bonus.LARGEUR *2 + Bonus.DEMI_LARGEUR *2, Bonus.DEMI_LARGEUR, Bonus.LARGEUR, Bonus.LARGEUR);
		case 1:	batch.draw(AssetMan.bombe, CSG.DEMI_LARGEUR_ECRAN + cam.position.x + X_CHRONO + Bonus.LARGEUR *1 + Bonus.DEMI_LARGEUR *1, Bonus.DEMI_LARGEUR, Bonus.LARGEUR, Bonus.LARGEUR);
		case 0:
		}
	}

	private void affichageNonPerdu() {
		Bonus.affichageEtMouvement(batch);
		vaisseau.draw(batch);
		Ennemis.affichageEtMouvement(batch);
		Particules.render(batch);
		Armes.affichageEtMouvement(batch);
		ui();
	}

	private void update() {
		if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.POWER) || Gdx.input.isKeyPressed(Keys.HOME)) {
			vientDEtreTouche = maintenant;
			mettrePause();
		}
		if (!perdu) {
			// **************************************  M O U V E M E N T   E T   M E N U  **************************************  
			mouvement();

			if (alterner) {	
				Ennemis.possibleApparitionEtUpdateScore();
				if (!activerStop) 			Physique.testCollisions();
				if (!afficherMenuRadial)	vaisseau.tir();
				if (alternerUpdateScore) {
					strScore = df.format(score);
					// Roue des couleurs
					if (color > .99f) {
						sensCouleurGlobale = false;
					}
					else if (color < .01f) sensCouleurGlobale = true;
					if (sensCouleurGlobale) color += .02f;
					else color -= 0.02f;
					
					if (colorRapide > .91f) sensCouleurRapide = false;
					else if (colorRapide < .09f) sensCouleurRapide = true;
					if (sensCouleurRapide) colorRapide += .08f;
					else colorRapide -= 0.08f;
				}
				alternerUpdateScore = !alternerUpdateScore;
			}
			alterner = !alterner;
		} else { // Donc si on a perdu
			if (Gdx.input.justTouched())		init();
		}
	}

	public static void mouvement() {
		if (Gdx.input.justTouched()) 		justeTouche();
		else {
			if (Gdx.input.isTouched()) 		touche();
			else  							pasTouche();
		}	
		if (CSG.profil.typeControle == CSG.CONTROLE_ACCELEROMETRE & !afficherMenuRadial) vaisseau.accelerometre();
	}

	private static void pasTouche() {
		if (afficherMenuRadial)	{
			if (nbBonusStop > 0) batch.draw(AssetMan.bonusetoile,(menuX - Bonus.AFFICHAGE) + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauType1.DEMI_LARGEUR, menuY, Bonus.AFFICHAGE, Bonus.AFFICHAGE);
			else batch.draw(AssetMan.bonusetoileGris,(menuX - Bonus.AFFICHAGE) + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauType1.DEMI_LARGEUR, menuY, Bonus.AFFICHAGE, Bonus.AFFICHAGE);
			
			if (chronoRalentir > .01f) batch.draw(AssetMan.temps, menuX + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauType1.DEMI_LARGEUR, menuY + Bonus.AFFICHAGE, Bonus.AFFICHAGE,Bonus.AFFICHAGE);
			else batch.draw(AssetMan.tempsGris, menuX + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauType1.DEMI_LARGEUR, menuY + Bonus.AFFICHAGE, Bonus.AFFICHAGE,Bonus.AFFICHAGE);
			
			if (nbBombes > 0) batch.draw(AssetMan.bombe, (menuX + Bonus.AFFICHAGE) + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauType1.DEMI_LARGEUR, menuY, Bonus.AFFICHAGE,Bonus.AFFICHAGE);
			else batch.draw(AssetMan.bombeGris, (menuX + Bonus.AFFICHAGE) + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauType1.DEMI_LARGEUR, menuY, Bonus.AFFICHAGE,Bonus.AFFICHAGE);
		}
		else if (!onAchoisis) {
			afficherMenuRadial = true;
			menuX = Gdx.input.getX();
			menuY = CSG.HAUTEUR_ECRAN - Gdx.input.getY();
		}
	}

	private static void touche() {
		if (!onAchoisis) {
			if (Gdx.app.getVersion() == 0) clavier();
			if (CSG.profil.typeControle == CSG.CONTROLE_DPAD) afficherDPAD();  
			vaisseau.mouvements();
			if (onVaRalentir) {
				activerRalentissement = !activerRalentissement;
				onVaRalentir = false;
				}
				if (onVaStopper) {
					activerStop = true;
					onVaStopper = false;
				}
		}
	}

	private static void justeTouche() {
		if (CSG.profil.typeControle == CSG.CONTROLE_DPAD) {
			VaisseauType1.prevX = Gdx.input.getX();
			VaisseauType1.prevY = Gdx.input.getY();
		}
		if (afficherMenuRadial) { 		// ---- SELECTION
			if (nbBonusStop > 0 && Physique.pointDansRectangle(Gdx.input.getX(), CSG.HAUTEUR_ECRAN - Gdx.input.getY(), (menuX - Bonus.AFFICHAGE) - VaisseauType1.DEMI_LARGEUR, menuY, Bonus.AFFICHAGE, Bonus.AFFICHAGE)) {
				onVaStopper = true;
				onAchoisis = true;
			} else if (chronoRalentir > 0.01 && Physique.pointDansRectangle(Gdx.input.getX(), CSG.HAUTEUR_ECRAN - Gdx.input.getY(), (menuX) - VaisseauType1.DEMI_LARGEUR, menuY + Bonus.AFFICHAGE, Bonus.AFFICHAGE, Bonus.AFFICHAGE)) {
				onVaRalentir = true;
				onAchoisis = true;
			} else if (nbBombes > 0 && Physique.pointDansRectangle(Gdx.input.getX(), CSG.HAUTEUR_ECRAN - Gdx.input.getY(), (menuX + Bonus.AFFICHAGE) - VaisseauType1.DEMI_LARGEUR, menuY, Bonus.AFFICHAGE,Bonus.AFFICHAGE)) {
				Ennemis.bombe();
				nbBombes--;
				onAchoisis = true;
			}
			afficherMenuRadial = false;
		} else { 						// ---- REPRISE JEU
			onAchoisis = false;
		}
	}

	private static void afficherDPAD() {
		if (CSG.profil.typeControle == CSG.CONTROLE_DPAD && Gdx.input.isTouched()){
			//			F L E C H E   D R O I T E
			batch.draw(fleche,(cam.position.x-CSG.DEMI_LARGEUR_ECRAN) + VaisseauType1.prevX + DEMI_LARGEUR_FLECHE, CSG.HAUTEUR_ECRAN - (VaisseauType1.prevY + DEMI_HAUTEUR_FLECHE), DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 0);
			//			F L E C H E   G A U C H E
			batch.draw(fleche,(cam.position.x-CSG.DEMI_LARGEUR_ECRAN) + VaisseauType1.prevX - (LARGEUR_FLECHE+DEMI_LARGEUR_FLECHE), CSG.HAUTEUR_ECRAN - (VaisseauType1.prevY + DEMI_HAUTEUR_FLECHE), DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 180);
			//			F L E C H E   H A U T
			batch.draw(fleche,(cam.position.x-CSG.DEMI_LARGEUR_ECRAN) + VaisseauType1.prevX - DEMI_LARGEUR_FLECHE, CSG.HAUTEUR_ECRAN - (VaisseauType1.prevY - DEMI_HAUTEUR_FLECHE), DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 90);
			//			F L E C H E   B A S
			batch.draw(fleche,(cam.position.x-CSG.DEMI_LARGEUR_ECRAN) + VaisseauType1.prevX - DEMI_LARGEUR_FLECHE, CSG.HAUTEUR_ECRAN - (VaisseauType1.prevY + DEMI_HAUTEUR_FLECHE + HAUTEUR_FLECHE), DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 270);
		}
	}

	private static void clavier() {
		if (!afficherMenuRadial & !onAchoisis) 	vaisseau.mouvements();
		if (Gdx.input.isKeyPressed(Keys.LEFT)) 	vaisseau.mvtLimiteVitesse(-500, 0);
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) vaisseau.mvtLimiteVitesse(500, 0);
		if (Gdx.input.isKeyPressed(Keys.UP)) 	vaisseau.mvtLimiteVitesse(0, 500);
		if (Gdx.input.isKeyPressed(Keys.DOWN)) 	vaisseau.mvtLimiteVitesse(0, -500);
		if (Gdx.input.isKeyPressed(Keys.S) && chronoRalentir > 0.1f) activerRalentissement = true;
		if (Gdx.input.isKeyPressed(Keys.D) && tempsBonusStop > 0) activerStop = true;
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) mettrePause();
	}

	@Override
	public void resize(int width, int height) {		CSG.profilManager.persist();	}

	@Override
	public void show() {	}

	@Override
	public void hide() { CSG.profilManager.persist(); }

	@Override
	public void pause() {	}

	@Override
	public void resume() { 		CSG.assetMan.reload();	}

	@Override
	public void dispose() {	}

	public static void addBonusStop() {
		if (nbBonusStop < 2) {
			nbBonusStop++;
			tempsBonusStop = 3;
		}
	}

	public static void ralentir(int i) {		if (chronoRalentir < 10) chronoRalentir += i;	}

	public static void ajoutBombe() {		if (nbBombes < 3) nbBombes++;	}
	
	private void afficherConseil(String s, TextureRegion tr, SpriteBatch batch) {
		CSG.menuFontPetite.draw(batch, s, ((cam.position.x-CSG.DEMI_LARGEUR_ECRAN)) + ((CSG.DEMI_LARGEUR_ECRAN - CSG.menuFontPetite.getBounds(s).width/2)),	CSG.DEMI_HAUTEUR_ECRAN - CSG.menuFontPetite.getBounds(s).height * 4);
		batch.draw(tr, ((cam.position.x-CSG.DEMI_LARGEUR_ECRAN) + CSG.DEMI_LARGEUR_ECRAN) - Bonus.AFFICHAGE/2 , CSG.DEMI_HAUTEUR_ECRAN - CSG.menuFontPetite.getBounds(s).height * 12, Bonus.AFFICHAGE, Bonus.AFFICHAGE);
	}

	private void afficherConseil(String s) {
		CSG.menuFontPetite.draw(batch, s, ((cam.position.x-CSG.DEMI_LARGEUR_ECRAN)) + ((CSG.DEMI_LARGEUR_ECRAN - CSG.menuFontPetite.getBounds(s).width/2)),	CSG.DEMI_HAUTEUR_ECRAN - CSG.menuFontPetite.getBounds(s).height * 4);
	}

	public static void perdu() {
//		 perdu = true;
	}

	public static boolean aPerdu() {
		return perdu;
	}
	
	public static void reset() {
		afficherMenuRadial = false;
		onAchoisis = false;
		vaisseau = new VaisseauType1();
		onVaRalentir = false;
		onVaStopper = false;
		menuX = (int) VaisseauType1.position.x;
		activerStop = false;
		activerRalentissement = false;
		pause = false;
		menuY = (int) VaisseauType1.position.y;
	}
}
