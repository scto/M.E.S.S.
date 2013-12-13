package jeu;
import java.text.DecimalFormat;
import menu.Menu;
import objets.armes.Armes;
import objets.bonus.Bonus;
import objets.ennemis.Ennemis;
import objets.joueur.VaisseauJoueur;
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

/**
 * Classe principale gerant le mode infini du jeu
 * @author Julien
 */
public class EndlessMode implements Screen {
	
	private Game game;
	// DISPLAY
	private static SpriteBatch batch = CSG.batch;
	private Bloom bloom;
	private GL20 gl;
	// there is an alternateUpdateScore because we only update the score every 4 frame, other things are done every 2 frames (collision detection,...)
	private boolean alternate = true, alternateUpdateScore = true;
	// states
	private static boolean triggerSlowMotion = false, pause = false, scoreSent = false, triggerStop = false, lost = false;
	
	private static VaisseauJoueur ship;
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
	public static OrthographicCamera cam = new OrthographicCamera(CSG.LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN);
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
	public static boolean konamiCode = false;
//	Client c = new Client("beyondpixels.no-ip.biz");
//	Client c = new Client("127.0.0.1");
//	private float fps = 0, minFPS = 200, maxFPS = 0, currentFPS = 0;
//	private int frameCounter = 0;

	public EndlessMode(Game game, SpriteBatch batch, int level) {
		super();
		Gdx.input.setCatchBackKey(true);
		EndlessMode.batch = batch;
		this.game = game;
		ship = new VaisseauJoueur();
		EndlessMode.modeDifficulte = level;
		Ennemis.initLevelBoss(level);
		init();
		ship.initialiser();
	}

	private void init() {
//		fps = 0;
		ship.reInit(); // Pour remettre les positions mais garder shield et adds
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
        scoreSent = false;
        lost = false;
        maintenant = 0;
        Ennemis.clear();
		if (CSG.profil.bloom) {
			bloom = new Bloom();
			bloom.setBloomIntesity(CSG.profil.intensiteBloom);
		}
//        Gdx.graphics.setVSync(true);
        SoundMan.playMusic();
		rougefonce = CSG.getAssetMan().getAtlas().findRegion("rougefonce");
		pause = false;
//		EnnemiPorteNef.dejaPresent = false;
		score = 0;
		strScore = String.valueOf(score);
		tempsBonusStop = 0;
		nbBonusStop = 0;
		triggerStop = false;
		nbBombes = 0;

	}

//	Projet projet = new Projet();
	@Override
	public void render(float delta) {
		
//		if (Gdx.input.isKeyPressed(Keys.A)) {
//			Ennemis.LISTE.add(DeBase.pool.obtain());
//		}
//		if (Gdx.input.isKeyPressed(Keys.Z)) 	Ennemis.LISTE.add(Insecte.pool.obtain());
//		if (Gdx.input.isKeyPressed(Keys.E))		Ennemis.LISTE.add(Kinder.pool.obtain());
//		if (Gdx.input.isKeyPressed(Keys.R))		Ennemis.LISTE.add(Laser.pool.obtain());
//		if (Gdx.input.isKeyPressed(Keys.T))		Ennemis.LISTE.add(PorteRaisin.pool.obtain());
//		if (Gdx.input.isKeyPressed(Keys.Y))		Ennemis.LISTE.add(QuiTir.pool.obtain());
//		if (Gdx.input.isKeyPressed(Keys.U))		Ennemis.LISTE.add(QuiTir2.pool.obtain());
//		if (Gdx.input.isKeyPressed(Keys.I))		Ennemis.LISTE.add(QuiTourne.pool.obtain());
//		if (Gdx.input.isKeyPressed(Keys.O))		Ennemis.LISTE.add(Toupie.pool.obtain());
//		if (Gdx.input.isKeyPressed(Keys.P))		Ennemis.LISTE.add(ZigZag.pool.obtain());
//		
//		if (Gdx.input.isKeyPressed(Keys.Q))		Ennemis.LISTE.add(BouleTirCote.pool.obtain());
//		if (Gdx.input.isKeyPressed(Keys.S))		Ennemis.LISTE.add(BouleTirCoteRotation.pool.obtain());
//		if (Gdx.input.isKeyPressed(Keys.D))		XP.pool.obtain().init(400, 400, 30);
//		if (Gdx.input.isKeyPressed(Keys.F))		Ennemis.LISTE.add(EnnemiQuiTourneNv3.pool.obtain());
//		if (Gdx.input.isKeyPressed(Keys.G))		Ennemis.LISTE.add(Cylon.pool.obtain());
//		if (Gdx.input.justTouched())		Ennemis.LISTE.add(EnnemiBossMine.pool.obtain());
//		if (Gdx.input.isKeyPressed(Keys.H))
//			try {
//				sendInfos();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		preRendu();
		Particules.background(batch);
//		projet.act(batch);
		if (!pause) {
			if (delta < 1) { 
				EndlessMode.delta = delta;
				if ((afficherMenuRadial || onAchoisis) && CSG.profil.typeControle != CSG.CONTROLE_ACCELEROMETRE) EndlessMode.delta = delta/7;
				score += (EndlessMode.delta + EndlessMode.delta + EndlessMode.delta);
				delta15 = EndlessMode.delta * 15;
			}
			// S I   O N   A   P A S   E N C O R E   P E R D U
			if (!lost && !triggerStop) {
				if (triggerSlowMotion) activerRalentissement(delta);
				affichageNonPerdu();
			} else {
				if (triggerStop) {
					affichageEtUpdateStop(delta);
				} else {
				// A   V I R E R   P O U R   L A   R E L E A S E
					if (lost && Gdx.app.getVersion() != 0 && !scoreSent && !konamiCode){
						int monScore = (int) score;
						switch (modeDifficulte) {
						case 1:			CSG.google.submitScore("CgkIrsqv7rIVEAIQAw", monScore );	break;
						case 2:			CSG.google.submitScore("CgkIrsqv7rIVEAIQGA", monScore );	break;
						case 3:			CSG.google.submitScore("CgkIrsqv7rIVEAIQGQ", monScore );	break;
						}
						scoreSent = true;
					}
				}
				affichagePerdu();
				if (!triggerStop && lost) scoreEtConseils();
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
		maintenant += EndlessMode.delta;
		
//		frameCounter++;
//		currentFPS = Gdx.graphics.getFramesPerSecond();
//		fps += currentFPS;
//		if (currentFPS > maxFPS)
//			maxFPS = currentFPS;
//		if (currentFPS < minFPS)
//			minFPS = currentFPS;
//		Gdx.app.log(String.valueOf(frameCounter), String.valueOf(fps/frameCounter));
	}

	private void affichageEtUpdateStop(float delta) {
		tempsBonusStop -= delta;
		batch.draw(rougefonce, (VaisseauJoueur.position.x + VaisseauJoueur.DEMI_LARGEUR) - (TIER_LARGEUR_JAUGE * tempsBonusStop)/2, VaisseauJoueur.position.y - HAUTEUR_JAUGE * 3, TIER_LARGEUR_JAUGE * tempsBonusStop, HAUTEUR_JAUGE);
		if (tempsBonusStop < 0) {
			triggerStop = false;
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
		EndlessMode.delta /= 3;
		EndlessMode.delta15 = EndlessMode.delta * 15;
		if (chronoRalentir < 0) {
			triggerSlowMotion = false;
			chronoRalentir = 0;
		}
	}

	private void bloomActive() {
		if (triggerStop) bloom.setBloomIntesity(CSG.profil.intensiteBloom + (tempsBonusStop*2));
		if (effetBloom) {
			intensiteBloomOrigin -= 500 * EndlessMode.delta;
			bloom.setBloomIntesity(intensiteBloomOrigin);
			if (intensiteBloomOrigin < CSG.profil.intensiteBloom) {
				effetBloom = false;
				intensiteBloomOrigin = CSG.profil.intensiteBloom;
				bloom.setBloomIntesity(intensiteBloomOrigin);
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
			
//			Thread t = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						sendInfos();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});
//			t.start();
//			fps = 0;
//			frameCounter = 0;
//			minFPS = 60;
//			maxFPS = 0;
		} 
		
		if (score < 3000) {
			if (conseil == 0) conseil = (int) (1 + Math.random() * 6);
			switch (conseil) {
			case 1:		afficherConseil(Strings.ADVICE1);								break;
			case 2:		afficherConseil(Strings.ADVICE2);								break;
			case 3:		afficherConseil(Strings.ADVICE3);								break;
			case 4:		afficherConseil(Strings.ADVICE4, AssetMan.bombe, batch);		break;
			case 5:		afficherConseil(Strings.ADVICE5, AssetMan.bonusetoile, batch);	break;
			case 6:		afficherConseil(Strings.ADVICE6, AssetMan.temps, batch);		break;
			}
		}
		
		if (VaisseauJoueur.addDroite || VaisseauJoueur.addDroite2 || VaisseauJoueur.addGauche || VaisseauJoueur.bouclier || VaisseauJoueur.addGauche2) 
			CSG.menuFontPetite.draw(batch, Strings.TRY_AGAIN, ((cam.position.x-CSG.DEMI_LARGEUR_ECRAN)) + ((CSG.DEMI_LARGEUR_ECRAN - (CSG.menuFontPetite.getBounds( Strings.TRY_AGAIN).width)/2)),
					(CSG.DEMI_HAUTEUR_ECRAN/2) - CSG.menuFontPetite.getBounds(Strings.TRY_AGAIN).height);
		
		CSG.menuFont.draw(batch, strScore, ((cam.position.x-CSG.DEMI_LARGEUR_ECRAN)) + ((CSG.DEMI_LARGEUR_ECRAN - (CSG.menuFont.getBounds(strScore).width)/2)),
				CSG.DEMI_HAUTEUR_ECRAN + CSG.menuFontPetite.getBounds(strScore).height);
		
	}

	private void sendInfos() throws Exception {
//		final StringBuilder sb = new StringBuilder(1550);
//		sb.append("_score:").append(score).append("_").
//		append("time:").append(maintenant).append("_").
//		append("bloomIntensity:").append(CSG.profil.intensiteBloom).append("_").
//		append("niveau:").append(modeDifficulte).append("_").
//		append("version:").append(CSG.profil.VERSION).append("_").
//		append("averageFPS:").append(fps/frameCounter).append("_").
//		append("minFPS:").append(minFPS).append("_").
//		append("maxFPS:").append(maxFPS).append("_").
//		append("date:").append(Calendar.getInstance().getTime().toString()).append("_").
//		append("control:").append(CSG.profil.getNomControle()).append("_").
//		
//		append("androidVersion:").append(Gdx.app.getVersion()).append("_").
//		append("modele:").append(CSG.getGlyph().getDeviceName()).append("_").
//		append("cpu:").append(CSG.getGlyph().readCPUinfo()).append("_").
//		append("gpu renderer:").append(gl.glGetString(GL20.GL_RENDERER)).append("_").
//		append("gpu vendor:").append(gl.glGetString(GL20.GL_VENDOR)).append("_").
//		append("largeur:").append(CSG.LARGEUR_ECRAN).append("_").
//		append("hauteur:").append(CSG.HAUTEUR_ECRAN).append("_").
//		append("ratio:").append((float)CSG.HAUTEUR_ECRAN / (float)CSG.LARGEUR_ECRAN).append("_").
//		append("ppi:").append(Gdx.graphics.getDensity() * 160).append("_").
//		append("arme:").append(CSG.profil.getArmeSelectionnee().getLabel()).append("_").
//		append("arme niveau-balayage:").append(CSG.profil.NvArmeBalayage).append("_").
//		append("arme niveau-de-base:").append(CSG.profil.NvArmeDeBase).append("_").
//		append("arme niveau-hantee:").append(CSG.profil.NvArmeHantee).append("_").
//		append("arme niveau-trois:").append(CSG.profil.NvArmeTrois).append("_").
//		append("musique:").append("Outside Norm").append("_").
//		
//		append("volume musique:").append(CSG.profil.volumeMusique).append("_").
//		append("volume bruitage:").append(CSG.profil.volumeBruitages).append("_").
//		append("volume arme:").append(CSG.profil.volumeArme).append("_").
//		append("jeu:").append("ESG_");
//		Iterator it = Ennemis.ennemisTues.entrySet().iterator();
//		while (it.hasNext()) {
//			Map.Entry pair = (Map.Entry) it.next();
//			sb.append(pair.getKey()).append(":").append(pair.getValue()).append("_");
//		}
//		c.send(sb.toString());
	}

	private float prevDelta;
	private void affichagePerdu() {
		prevDelta = delta;
		delta = 0;
		ship.draw(batch);
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
		case 3:	batch.draw(AssetMan.bonusetoile, cam.position.x + X_CHRONO + Bonus.WIDTH *2 + Bonus.HALF_WIDTH *2, HAUTEUR_POLICE*2, Bonus.WIDTH, Bonus.WIDTH);
		case 2:	batch.draw(AssetMan.bonusetoile, cam.position.x + X_CHRONO + Bonus.WIDTH + Bonus.HALF_WIDTH, HAUTEUR_POLICE*2, Bonus.WIDTH, Bonus.WIDTH);
		case 1:	batch.draw(AssetMan.bonusetoile, cam.position.x + X_CHRONO, HAUTEUR_POLICE*2, Bonus.WIDTH, Bonus.WIDTH);
		case 0:
		}
		switch(nbBombes) {
		default :
		case 3:	batch.draw(AssetMan.bombe, CSG.DEMI_LARGEUR_ECRAN + cam.position.x + X_CHRONO + Bonus.WIDTH *3 + Bonus.HALF_WIDTH *3, Bonus.HALF_WIDTH, Bonus.WIDTH, Bonus.WIDTH);
		case 2:	batch.draw(AssetMan.bombe, CSG.DEMI_LARGEUR_ECRAN + cam.position.x + X_CHRONO + Bonus.WIDTH *2 + Bonus.HALF_WIDTH *2, Bonus.HALF_WIDTH, Bonus.WIDTH, Bonus.WIDTH);
		case 1:	batch.draw(AssetMan.bombe, CSG.DEMI_LARGEUR_ECRAN + cam.position.x + X_CHRONO + Bonus.WIDTH *1 + Bonus.HALF_WIDTH *1, Bonus.HALF_WIDTH, Bonus.WIDTH, Bonus.WIDTH);
		case 0:
		}
	}

	private void affichageNonPerdu() {
		Bonus.drawAndMove(batch);
		ship.draw(batch);
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
		if (!lost) {
			// **************************************  M O U V E M E N T   E T   M E N U  **************************************  
			mouvement();

			if (alternate) {	
				Ennemis.possibleApparitionEtUpdateScore();
				if (!triggerStop) 			Physique.testCollisions();
				if (!afficherMenuRadial)	ship.tir();
				if (alternateUpdateScore) {
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
				alternateUpdateScore = !alternateUpdateScore;
			}
			alternate = !alternate;
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
		if (CSG.profil.typeControle == CSG.CONTROLE_ACCELEROMETRE & !afficherMenuRadial) ship.accelerometre();
	}

	private static void pasTouche() {
		if (afficherMenuRadial)	{
			if (nbBonusStop > 0) batch.draw(AssetMan.bonusetoile,(menuX - Bonus.DISPLAY_WIDTH) + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauJoueur.DEMI_LARGEUR, menuY, Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH);
			else batch.draw(AssetMan.bonusetoileGris,(menuX - Bonus.DISPLAY_WIDTH) + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauJoueur.DEMI_LARGEUR, menuY, Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH);
			
			if (chronoRalentir > .01f) batch.draw(AssetMan.temps, menuX + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauJoueur.DEMI_LARGEUR, menuY + Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH,Bonus.DISPLAY_WIDTH);
			else batch.draw(AssetMan.tempsGris, menuX + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauJoueur.DEMI_LARGEUR, menuY + Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH,Bonus.DISPLAY_WIDTH);
			
			if (nbBombes > 0) batch.draw(AssetMan.bombe, (menuX + Bonus.DISPLAY_WIDTH) + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauJoueur.DEMI_LARGEUR, menuY, Bonus.DISPLAY_WIDTH,Bonus.DISPLAY_WIDTH);
			else batch.draw(AssetMan.bombeGris, (menuX + Bonus.DISPLAY_WIDTH) + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauJoueur.DEMI_LARGEUR, menuY, Bonus.DISPLAY_WIDTH,Bonus.DISPLAY_WIDTH);
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
			ship.mouvements();
			if (onVaRalentir) {
				triggerSlowMotion = !triggerSlowMotion;
				onVaRalentir = false;
				}
				if (onVaStopper) {
					triggerStop = true;
					onVaStopper = false;
				}
		}
	}

	private static void justeTouche() {
		if (CSG.profil.typeControle == CSG.CONTROLE_DPAD) {
			VaisseauJoueur.prevX = Gdx.input.getX();
			VaisseauJoueur.prevY = Gdx.input.getY();
		}
		if (afficherMenuRadial) { 		// ---- SELECTION
			if (nbBonusStop > 0 && Physique.pointDansRectangle(Gdx.input.getX(), CSG.HAUTEUR_ECRAN - Gdx.input.getY(), (menuX - Bonus.DISPLAY_WIDTH) - VaisseauJoueur.DEMI_LARGEUR, menuY, Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH)) {
				onVaStopper = true;
				onAchoisis = true;
			} else if (chronoRalentir > 0.01 && Physique.pointDansRectangle(Gdx.input.getX(), CSG.HAUTEUR_ECRAN - Gdx.input.getY(), (menuX) - VaisseauJoueur.DEMI_LARGEUR, menuY + Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH)) {
				onVaRalentir = true;
				onAchoisis = true;
			} else if (nbBombes > 0 && Physique.pointDansRectangle(Gdx.input.getX(), CSG.HAUTEUR_ECRAN - Gdx.input.getY(), (menuX + Bonus.DISPLAY_WIDTH) - VaisseauJoueur.DEMI_LARGEUR, menuY, Bonus.DISPLAY_WIDTH,Bonus.DISPLAY_WIDTH)) {
				Ennemis.bombe();
				CSG.google.unlockAchievementGPGS(Strings.ACH_BOMB);
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
			batch.draw(fleche,(cam.position.x-CSG.DEMI_LARGEUR_ECRAN) + VaisseauJoueur.prevX + DEMI_LARGEUR_FLECHE, CSG.HAUTEUR_ECRAN - (VaisseauJoueur.prevY + DEMI_HAUTEUR_FLECHE), DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 0);
			//			F L E C H E   G A U C H E
			batch.draw(fleche,(cam.position.x-CSG.DEMI_LARGEUR_ECRAN) + VaisseauJoueur.prevX - (LARGEUR_FLECHE+DEMI_LARGEUR_FLECHE), CSG.HAUTEUR_ECRAN - (VaisseauJoueur.prevY + DEMI_HAUTEUR_FLECHE), DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 180);
			//			F L E C H E   H A U T
			batch.draw(fleche,(cam.position.x-CSG.DEMI_LARGEUR_ECRAN) + VaisseauJoueur.prevX - DEMI_LARGEUR_FLECHE, CSG.HAUTEUR_ECRAN - (VaisseauJoueur.prevY - DEMI_HAUTEUR_FLECHE), DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 90);
			//			F L E C H E   B A S
			batch.draw(fleche,(cam.position.x-CSG.DEMI_LARGEUR_ECRAN) + VaisseauJoueur.prevX - DEMI_LARGEUR_FLECHE, CSG.HAUTEUR_ECRAN - (VaisseauJoueur.prevY + DEMI_HAUTEUR_FLECHE + HAUTEUR_FLECHE), DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 270);
		}
	}

	private static void clavier() {
		if (!afficherMenuRadial & !onAchoisis) 	ship.mouvements();
		if (Gdx.input.isKeyPressed(Keys.LEFT)) 	ship.mvtLimiteVitesse(-500, 0);
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) ship.mvtLimiteVitesse(500, 0);
		if (Gdx.input.isKeyPressed(Keys.UP)) 	ship.mvtLimiteVitesse(0, 500);
		if (Gdx.input.isKeyPressed(Keys.DOWN)) 	ship.mvtLimiteVitesse(0, -500);
		if (Gdx.input.isKeyPressed(Keys.S) && chronoRalentir > 0.1f) triggerSlowMotion = true;
		if (Gdx.input.isKeyPressed(Keys.D) && tempsBonusStop > 0) triggerStop = true;
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
	public void resume() { 		CSG.assetMan.reload(true);	}

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
		batch.draw(tr, ((cam.position.x-CSG.DEMI_LARGEUR_ECRAN) + CSG.DEMI_LARGEUR_ECRAN) - Bonus.DISPLAY_WIDTH/2 , CSG.DEMI_HAUTEUR_ECRAN - CSG.menuFontPetite.getBounds(s).height * 12, Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH);
	}

	private void afficherConseil(String s) {
		CSG.menuFontPetite.draw(batch, s, ((cam.position.x-CSG.DEMI_LARGEUR_ECRAN)) + ((CSG.DEMI_LARGEUR_ECRAN - CSG.menuFontPetite.getBounds(s).width/2)),	CSG.DEMI_HAUTEUR_ECRAN - CSG.menuFontPetite.getBounds(s).height * 4);
	}

	public static void perdu() {
		 lost = true;
	}

	public static boolean aPerdu() {
		return lost;
	}
	
	public static void reset() {
		afficherMenuRadial = false;
		onAchoisis = false;
		ship = new VaisseauJoueur();
		onVaRalentir = false;
		onVaStopper = false;
		menuX = (int) VaisseauJoueur.position.x;
		triggerStop = false;
		triggerSlowMotion = false;
		pause = false;
		menuY = (int) VaisseauJoueur.position.y;
	}
}
