package jeu;
import java.text.DecimalFormat;
import java.util.Random;

import menu.screens.Menu;
import menu.tuto.OnClick;
import menu.ui.Bouton;
import assets.AssetMan;
import assets.SoundMan;
import bloom.Bloom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import elements.generic.Player;
import elements.generic.enemies.Enemy;
import elements.generic.enemies.Progression;
import elements.generic.enemies.individual.bosses.BossMine;
import elements.generic.enemies.individual.bosses.BossQuad;
import elements.generic.enemies.individual.bosses.BossSat;
import elements.generic.enemies.individual.lvl1.Boule;
import elements.generic.enemies.individual.lvl1.Cylon;
import elements.generic.enemies.individual.lvl1.DeBase;
import elements.generic.enemies.individual.lvl1.Insecte;
import elements.generic.enemies.individual.lvl1.Kinder;
import elements.generic.enemies.individual.lvl1.Laser;
import elements.generic.enemies.individual.lvl1.Plane;
import elements.generic.enemies.individual.lvl1.PorteRaisin;
import elements.generic.enemies.individual.lvl1.QuiTir;
import elements.generic.enemies.individual.lvl1.QuiTirTriangle;
import elements.generic.enemies.individual.lvl1.QuiTourne;
import elements.generic.enemies.individual.lvl1.Toupie;
import elements.generic.enemies.individual.lvl1.Vicious;
import elements.generic.enemies.individual.lvl1.ZigZag;
import elements.generic.enemies.individual.lvl2.BouleTirCote;
import elements.generic.enemies.individual.lvl2.BouleTirCoteRotation;
import elements.generic.enemies.individual.lvl3.BouleNv3;
import elements.generic.enemies.individual.lvl3.CylonNv3;
import elements.generic.enemies.individual.lvl3.DeBaseNv3;
import elements.generic.enemies.individual.lvl3.Group3;
import elements.generic.enemies.individual.lvl3.InsecteNv3;
import elements.generic.enemies.individual.lvl3.KinderNv3;
import elements.generic.enemies.individual.lvl3.LaserNv3;
import elements.generic.enemies.individual.lvl3.Plane3;
import elements.generic.enemies.individual.lvl3.PorteRaisinNv3;
import elements.generic.enemies.individual.lvl3.QuiTirNv3;
import elements.generic.enemies.individual.lvl3.QuiTirTriangle3;
import elements.generic.enemies.individual.lvl3.QuiTourneNv3;
import elements.generic.enemies.individual.lvl3.ToupieNv3;
import elements.generic.enemies.individual.lvl3.ZigZagNv3;
import elements.generic.enemies.individual.lvl4.BouleNv4;
import elements.generic.enemies.individual.lvl4.CylonNv4;
import elements.generic.enemies.individual.lvl4.DeBaseNv4;
import elements.generic.enemies.individual.lvl4.InsecteNv4;
import elements.generic.enemies.individual.lvl4.KinderNv4;
import elements.generic.enemies.individual.lvl4.LaserNv4;
import elements.generic.enemies.individual.lvl4.Plane4;
import elements.generic.enemies.individual.lvl4.PorteRaisinNv4;
import elements.generic.enemies.individual.lvl4.QuiTirNv4;
import elements.generic.enemies.individual.lvl4.QuiTirTriangle4;
import elements.generic.enemies.individual.lvl4.QuiTourneNv4;
import elements.generic.enemies.individual.lvl4.ToupieNv4;
import elements.generic.enemies.individual.lvl4.ZigZagNv4;
import elements.generic.weapons.Weapons;
import elements.particular.bonuses.Bonus;
import elements.particular.bonuses.BonusStop;
import elements.particular.bonuses.XP;
import elements.particular.particles.Particles;

/**
 * Classe principale gerant le mode infini du jeu
 * @author Julien
 */
public class EndlessMode implements Screen {
	
	private Game game;
	private static SpriteBatch batch = CSG.batch;
	private Bloom bloom = CSG.bloom;
	private GL20 gl;
	public static boolean alternate = true;
	// states
	public static boolean pause = false, scoreSent = false, triggerStop = false, lost = false;
	
	private static Player ship;
	public static String strScore = "0";
	public static float now = 0, score = 0;
	private float vientDEtreTouche = 0;
	public static final int X_CHRONO = CSG.DIXIEME_LARGEUR/2 - CSG.screenHalfWidth;
	public static final int HAUTEUR_POLICE = CSG.HEIGHT_DIV10/3;
	// *************************  J  A  U  G  E  *************************
	private final TextureRegion red;
	private static final int MAX_LARGEUR_JAUGE = CSG.screenWidth/6, TIER_LARGEUR_JAUGE = MAX_LARGEUR_JAUGE/3, HAUTEUR_JAUGE = CSG.SCREEN_HEIGHT/75;
	// *************************  D  P  A  D  ****************************
	private static final TextureRegion ARROW = AssetMan.arrow;
	private static final float HAUTEUR_FLECHE = CSG.SCREEN_HEIGHT/30, DEMI_HAUTEUR_FLECHE = HAUTEUR_FLECHE/2, LARGEUR_FLECHE = HAUTEUR_FLECHE, DEMI_LARGEUR_FLECHE = LARGEUR_FLECHE/2;

	static final DecimalFormat DF = new DecimalFormat();
	private static final OrthographicCamera cam = new OrthographicCamera(CSG.screenWidth, CSG.SCREEN_HEIGHT);
	public static int modeDifficulte, nbBonusStop = 0, nbBombes = 0;
	public static float color = 0, colorRapide, intensiteBloomOrigin = 1, camXmoinsDemiEcran, delta = 0, tempsBonusStop = 0, delta15 = 0, deltaDiv3, delta2, deltaU, deltaMicroU, UnPlusDelta, unPlusDelta3, deltaPlusExplosion;
	public static boolean effetBloom = false, xpAjout = false, afficherMenuRadial = false, konamiCode = false, transitionVersMouvement = false;
	private static boolean onAchoisis = false, onVaStopper = false;
	private static int menuX = 0, menuY = 0;
	private int conseil = 0;
	private static final Vector2 TMP_POS = new Vector2(), TMP_DIR = new Vector2();
	private Bouton boutonUpgradeOrRestart = null;
	private Bouton boutonTwitter = null;
	private static Bouton boutonBack = null;
	// screenshake
	private static boolean shake = false;
	private static float chronoShake = 0;
	public static final Random R = new Random();
	private static float mvtTotalX, mvtTotalY;
	private static float force = 0;
//	private static ShaderProgram shaderMort = ShaderMort.init(), originalShader;
//	Client c = new Client("beyondpixels.no-ip.biz");
//	Client c = new Client("127.0.0.1");
//	private float fps = 0, minFPS = 200, maxFPS = 0, currentFPS = 0;
//	private int frameCounter = 0;
	private static Matrix4 tmpCombined; 
	public static float originalDelta = 0;
	public static int fps;
	public static int perf = 3;
	public static int explosions = 0;

	public EndlessMode(Game game, SpriteBatch batch, int level) {
		super();
		Gdx.input.setCatchBackKey(true);
		EndlessMode.batch = batch;
//		originalShader = batch.createDefaultShader();
		this.game = game;
		ship = new Player();
		modeDifficulte = level;
		init();
		ship.initialiser();
		DF.setMaximumFractionDigits(1);
		DF.setMinimumFractionDigits(1);	
		DF.setDecimalSeparatorAlwaysShown(true);
		gl = Gdx.graphics.getGL20();
		gl.glViewport(0, 0, CSG.screenWidth, CSG.SCREEN_HEIGHT);
		red = CSG.getAssetMan().getAtlas().findRegion("rougefonce");
		bloom = CSG.bloom;
	}

	private void init() {
//		batch.setShader(originalShader);
		if (CSG.bloom != null && CSG.profile.bloom) {
			bloom.setBloomIntesity(CSG.profile.intensiteBloom);
		} else {
			CSG.profile.bloom = false;
		}
		ship.reInit(); // Pour remettre les positions mais garder shield et adds
		if (Gdx.app.getVersion() != 0)
			CSG.myRequestHandler.showAds(false); // desactiver adds. A VIRER POUR LA RELEASE
		// ** DEPLACEMENT ZONE DE JEU
		cam.position.set(CSG.gameZoneWidth/2, CSG.SCREEN_HEIGHT / 2, 0);
		
		CSG.reset();
        scoreSent = false;
        xpAjout = false;
        pause = false;
        lost = false;
        score = 0;
        now = 0;
        Enemy.clear();
        Gdx.graphics.setVSync(true);
        SoundMan.playMusic();
		strScore = String.valueOf(score);
		tempsBonusStop = 0;
		nbBonusStop = 0;
		triggerStop = false;
		nbBombes = 0;
		boutonUpgradeOrRestart = null;
		boutonTwitter = null;
		boutonBack = null;
		
		shake = false;
		chronoShake = 0;
		Weapons.clear();
		Progression.reset();
		cam.position.z = 1;
		if (modeDifficulte == 1)
			Player.activateShield();
		Progression.reset();
	}

	@Override
	public void render(float delta) {
		if ((Gdx.input.isTouched() || Gdx.input.justTouched()) && !lost)
			ship.mouvements();
		originalDelta = delta;
		if (Gdx.app.getVersion() == 0)
			tests();
		cam();
		
		if (CSG.profile.bloom)
			bloomActive();
		else
			gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  //+10% de perfs !!. Si pas de bloom il faut le mettre
		
		explosions = 0;
		batch.begin();
		
		Particles.background(batch);
//		projet.act(batch);
		if (!pause) {
			if (delta < 1) { 
				EndlessMode.delta = delta;
				if ( (afficherMenuRadial || onAchoisis) && CSG.profile.typeControle != CSG.CONTROLE_ACCELEROMETRE)
					EndlessMode.delta = delta / 7;
				score += (EndlessMode.delta + EndlessMode.delta + EndlessMode.delta);
				majDeltas();
			}
			// S I   O N   A   P A S   E N C O R E   P E R D U
			if (!lost && !triggerStop) {
				if (transitionVersMouvement)
					transitionVersMouvement();
				affichageNonPerdu();
			} else {
				if (triggerStop) {
					affichageEtUpdateStop();
				} else {
//					batch.setShader(shaderMort);
					if (lost && !scoreSent && !konamiCode){
						if (CSG.profile.bloom)
							bloom.setBloomIntesity(-1f);
						final int monScore = (int) score;
						switch (modeDifficulte) {
						case 1:		CSG.google.submitScore("CgkIrsqv7rIVEAIQAw", monScore );	break;
						case 2:		CSG.google.submitScore("CgkIrsqv7rIVEAIQGA", monScore );	break;
						case 3:		CSG.google.submitScore("CgkIrsqv7rIVEAIQGQ", monScore );	break;
						case 4:		CSG.google.submitScore(Strings.LVL4LB, monScore );			break;
						}
						scoreSent = true;
					}
				}
				affichagePerdu();
				if (!triggerStop && lost) scoreEtConseils();
			}					 
			update();
		} else { // D O N C   E N   P A U S E
			if (Gdx.input.isKeyPressed(Keys.BACK) && (now > vientDEtreTouche + .1)) {
				goToMenu();
			}
			affichagePerdu();
			afficherResume();
			if (Gdx.input.justTouched()) {
				afficherMenuRadial = false;
				pause = false;
//				if (now - vientDEtreTouche < .4) {
//					pause = false;
//					SoundMan.playMusic();
//				}
				vientDEtreTouche = now;
			}
		}
		batch.end();
		if (CSG.profile.bloom)
			bloom.render();
		now += EndlessMode.delta;
		if (shake)
			screenShake();
		Enemy.deadBodiesEverywhere();
		fps = Gdx.graphics.getFramesPerSecond();
		perf = fps / 10;
		deltaPlusExplosion = EndlessMode.delta + explosions;
	}

	private void cam() {
		cam.update();
		tmpCombined = cam.combined;
		if (tmpCombined != null)
			batch.setProjectionMatrix(tmpCombined);
	}

	public static void majDeltas() {
		deltaPlusExplosion = EndlessMode.delta + explosions;
		delta15 = delta * 15;
		deltaDiv3 = delta / 3;
		delta2 = delta * 2;
		deltaU = delta * Stats.U;
		deltaMicroU =  Stats.microU * delta;
		UnPlusDelta = 1 + delta;
		unPlusDelta3 = UnPlusDelta + delta2;
	}

	private static final float SHAKE_MIN = 1.1f, SHAKE_MAX = 5;//, SHAKE_MUL = 2;
	private void screenShake() {
		if (chronoShake <= SHAKE_MIN) {
			shake = false;
			cam.position.y = CSG.halfHeight;
			
//			if (cam.position.x < CSG.screenHalfWidth) 	
			cam.position.x = CSG.screenHalfWidth;
//			else if (cam.position.x > CSG.DEMI_CAMERA)
//				cam.position.x = CSG.DEMI_CAMERA;
		} else {
			force = (float) ((R.nextFloat()/2) * (chronoShake*Stats.u));//SHAKE_MUL)) * Stats.u;
			if (mvtTotalX < 0) {
				cam.position.x += force;
				mvtTotalX += force;
			} else {
				cam.position.x -= force;
				mvtTotalX -= force;
			}
			force = (float) ((R.nextFloat()/2) * (chronoShake*Stats.u));//SHAKE_MUL)) * Stats.u;
			if (mvtTotalY < 0) {
				cam.position.y += force;
				mvtTotalY += force;
			} else {
				cam.position.y -= force;
				mvtTotalY -= force;
			}
			
			// avant game zone
//			if (cam.position.x < CSG.screenHalfWidth) 	
//				cam.position.x = CSG.screenHalfWidth;
//			else if (cam.position.x > CSG.screenHalfWidth)
//				cam.position.x = CSG.screenHalfWidth;
			chronoShake /= 1.01f + delta2;
		}
	}
	
	@SuppressWarnings("unused")
	private void tests() {
		//		if (Gdx.input.isKeyPressed(Keys.A)) {
//			Ennemis.LISTE.add(DeBase.pool.obtain());
//		}
//		CSG.profil.NvArmeBalayage = 1;
//		CSG.profil.NvArmeDeBase = 1;
//		CSG.profil.NvArmeHantee = 1;
//		CSG.profil.NvArmeTrois = 1;
		if (Gdx.input.isKeyPressed(Keys.PAGE_DOWN))	{
			cam.translate(0, 0, 1);
			cam();
			SoundMan.playBruitage(SoundMan.bonusTaken);
		}
		if (Gdx.input.isKeyPressed(Keys.END))	Group3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.A)) 	BossSat.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Z)) 	DeBase.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Z)) 	DeBaseNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Z)) 	DeBaseNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.E))		ZigZag.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.E))		ZigZagNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.E))		ZigZagNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.T))		QuiTir.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.T))		QuiTirNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.T))		QuiTirNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Y))		QuiTirTriangle.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Y))		QuiTirTriangle3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Y))		QuiTirTriangle4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.U))		Boule.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.U))		BouleNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.U))		BouleNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.O))		BouleTirCote.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.P))		BouleTirCoteRotation.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Q))		Toupie.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Q))		ToupieNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.Q))		ToupieNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.D))		XP.POOL.obtain().init(400, 400, 300);
		if (Gdx.input.isKeyPressed(Keys.F))		QuiTourne.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F))		QuiTourneNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F))		QuiTourneNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.J))		Kinder.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.J))		KinderNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.J))		KinderNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.M))		Cylon.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.M))		CylonNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.M))		CylonNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.X))		Plane.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.X))		Plane3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.X))		Plane4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.V))		Laser.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.V))		LaserNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.V))		LaserNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.N))		PorteRaisin.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.N))	PorteRaisinNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.N))	PorteRaisinNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F6))	Insecte.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F6))	InsecteNv3.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F6))	InsecteNv4.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F9))	BossMine.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F10))	BossQuad.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F11))	Vicious.ref.invoquer();
		if (Gdx.input.isKeyPressed(Keys.F12))	DeBaseNv4.ref.invoquer();
//		if (Gdx.input.isKeyPressed(Keys.F1))	CSG.assetMan.reload(false);
		if (Gdx.input.isKeyPressed(Keys.F3))	Bonus.LIST.add(BonusStop.POOL.obtain());
		if (Gdx.input.isKeyPressed(Keys.F4)) {
			Bonus.LIST.add(XP.POOL.obtain());
			CSG.profile.bfg = true;
		}
//		if (Gdx.input.isKeyPressed(Keys.H))
//			try {
//				sendInfos();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		if (Gdx.input.isKeyPressed(Keys.F5)) score++;
	}
	
	private float facteurTransition = 12;
	private final static float stepTransition = .1f;
	private void transitionVersMouvement() {
		// On ne d�cremente le chrono de ralentissement que quand on est au facteur de ralentissement max
		if (facteurTransition > 1) {
			facteurTransition -= stepTransition;
		} else {
			transitionVersMouvement = false;
			facteurTransition = 20;
		}
		EndlessMode.delta /= facteurTransition;
		majDeltas();
	}

	private void affichageEtUpdateStop() {
		tempsBonusStop -= delta;
		batch.draw(red, (Player.POS.x + Player.DEMI_LARGEUR) - (TIER_LARGEUR_JAUGE * tempsBonusStop)/2, Player.POS.y - HAUTEUR_JAUGE * 3, TIER_LARGEUR_JAUGE * tempsBonusStop, HAUTEUR_JAUGE);
		if (tempsBonusStop < 0) {
			triggerStop = false;
			transitionVersMouvement = true;
			if (--nbBonusStop > 0)
				tempsBonusStop += 3;
		}
	}

	private void bloomActive() {
		if (triggerStop) {
			bloom.setBloomIntesity(CSG.profile.intensiteBloom + (tempsBonusStop*2));
		} else {
			if (!shake) {
				bloom.setBloomIntesity(CSG.profile.intensiteBloom);
			} else {
				bloom.setBloomIntesity(CSG.profile.intensiteBloom + (chronoShake * 1.9f) );
			}
		}
		bloom.capture();
	}

	private void afficherResume() {
		if (boutonBack == null) {
			Particles.ajoutPanneau(TMP_POS, TMP_DIR);
			boutonBack = new Bouton(Strings.BACK, CSG.menuFont, 
					(int) CSG.menuFont.getBounds(Strings.BACK).width, 
					(int) CSG.menuFont.getBounds(Strings.BACK).height, 
					(int) ((cam.position.x) - CSG.menuFont.getBounds(Strings.BACK).width / 2),
					Menu.HAUTEUR_BOUTON * 3,
					new OnClick() {
					public void onClick() {
					}
			});
		}
		if (Gdx.input.justTouched()) {
			if (boutonBack != null && 
					Physic.isPointInRect(Gdx.input.getX() + cam.position.x / 2, CSG.SCREEN_HEIGHT - Gdx.input.getY(),
							0, Menu.HAUTEUR_BOUTON * 1.1f, CSG.gameZoneWidth, Menu.HAUTEUR_BOUTON * 3)) {
				goToMenu();
				boutonBack = null;
			}
		}
		if (boutonBack != null)
			boutonBack.draw(batch);
	}

	private void goToMenu() {
		CSG.profilManager.persist();
		game.setScreen(new Menu(game));
		Enemy.clear();
		if (CSG.profile.xpDispo >= 30000) {
			CSG.google.unlockAchievementGPGS(Strings.ACH_30k_XP);
		}
	}

	private static void mettrePause() {
		pause = true;
		CSG.profilManager.persist();
	}
	
	private void scoreEtConseils() {
		if (Gdx.app.getVersion() != 0)
			CSG.myRequestHandler.showAds(true);
		
		if (!xpAjout && !konamiCode) {
			CSG.profile.addXp((int) score);
			xpAjout = true;
		}
		
//		batch.setShader(originalShader);
		if (CSG.profile.xpDispo > CSG.profile.getCoutUpArme() && Player.weapon.nv() < Profil.NV_ARME_MAX) {
			if (boutonUpgradeOrRestart == null) {
				Particles.ajoutPanneau(TMP_POS, TMP_DIR);
				boutonUpgradeOrRestart = new Bouton(Strings.UPGRADE_BUTTON, CSG.menuFont, 
						(int) CSG.menuFont.getBounds(Strings.UPGRADE_BUTTON).width, 
						(int) CSG.menuFont.getBounds(Strings.UPGRADE_BUTTON).height, 
						(int) ((cam.position.x) - CSG.menuFont.getBounds(Strings.UPGRADE_BUTTON).width / 2),
						Menu.HAUTEUR_BOUTON,
						new OnClick() {
						public void onClick() {
							CSG.profile.upArme();
							CSG.profilManager.persist();
							boutonUpgradeOrRestart = null;
						}
				});
			}
		} else if (boutonUpgradeOrRestart == null) {
			Particles.ajoutPanneau(TMP_POS, TMP_DIR);
			boutonUpgradeOrRestart = new Bouton(Strings.RESTART_BUTTON, CSG.menuFont, 
					(int) CSG.menuFont.getBounds(Strings.RESTART_BUTTON).width, 
					(int) CSG.menuFont.getBounds(Strings.RESTART_BUTTON).height, 
					(int) ((cam.position.x) - CSG.menuFont.getBounds(Strings.RESTART_BUTTON).width / 2),
					Menu.HAUTEUR_BOUTON,
					new OnClick() {
					public void onClick() {
						init();
						boutonUpgradeOrRestart = null;
					}
			});
		}
		if (boutonTwitter == null) {
			boutonTwitter = new Bouton(Strings.BRAG_TWITTER, CSG.menuFont, 
					(int) CSG.menuFont.getBounds(Strings.BRAG_TWITTER).width, 
					(int) CSG.menuFont.getBounds(Strings.BRAG_TWITTER).height, 
					(int) ((cam.position.x) - CSG.menuFont.getBounds(Strings.BRAG_TWITTER).width / 2),
					Menu.HAUTEUR_BOUTON * 6,
					new OnClick() {
					public void onClick() {
						CSG.google.bragTwitter("I made " + (int)score + " on #MESS" + modeDifficulte);
					}
			});
		}
		
		if (score < 1000) {
			if (conseil == 0)
				conseil = (int) (1 + Math.random() * 5);
			switch (4) {
				case 3:		afficherConseil(Strings.ADVICE3);								break;
				case 4:		afficherConseil(Strings.ADVICE4, AssetMan.bomb, batch);			break;
				case 5:		afficherConseil(Strings.ADVICE5, AssetMan.stopBonus, batch);	break;
				
			}
		}
		
		CSG.menuFont.draw(batch, Strings.DEAD, ((cam.position.x-CSG.screenHalfWidth)) + ((CSG.screenHalfWidth - (CSG.menuFont.getBounds(Strings.DEAD).width)/2)),
				CSG.halfHeight + CSG.menuFontPetite.getBounds(Strings.DEAD).height * 3);
		CSG.menuFont.draw(batch, strScore, ((cam.position.x-CSG.screenHalfWidth)) + ((CSG.screenHalfWidth - (CSG.menuFont.getBounds(strScore).width)/2)),
				CSG.halfHeight + CSG.menuFontPetite.getBounds(strScore).height);
		
		if (boutonUpgradeOrRestart != null) {
			boutonUpgradeOrRestart.draw(batch);
			if (boutonTwitter != null)
				boutonTwitter.draw(batch);
			Particles.drawUi(batch);
		}
	}

	private float prevDelta;
	private void affichagePerdu() {
		prevDelta = delta;
		delta = 0;
		ship.draw(batch);
		Enemy.draw(batch);
		delta = prevDelta;
		Particles.draw(batch);
		Particles.impacts(batch);
		Weapons.affichage(batch);
		ui();
		
//		camZoom();
	}

	protected void camZoom() {
//		if (cam.position.x < Player.xCenter) {
//			moveCamX(CSG.screenWidth / 0.98f);
//		} else if (cam.position.x > Player.xCenter)	{
//			moveCamX(-CSG.screenWidth / 0.98f);
//		}
//		
//		if (cam.position.y < Player.xCenter) {
//			moveCamY(CSG.SCREEN_HEIGHT / 0.98f);
//		} else if (cam.position.y > Player.xCenter)	{
//			moveCamY(-CSG.SCREEN_HEIGHT / 0.98f);
//		}
		
		final float f = (cam.position.z) - (cam.position.z / .98f);
		cam.position.z -= f;
		if (boutonBack != null)				boutonBack.camZoom(f);
		if (boutonTwitter != null)			boutonTwitter.camZoom(f);
		if (boutonUpgradeOrRestart != null)	boutonUpgradeOrRestart.camZoom(f);
	}

	protected void moveCamY(float y) {
		if (boutonBack != null)				boutonBack.camMoveY(y);
		if (boutonTwitter != null)			boutonTwitter.camMoveY(y);
		if (boutonUpgradeOrRestart != null)	boutonUpgradeOrRestart.camMoveY(y);
		cam.position.y += y;
	}
	
	protected void moveCamX(float x) {
		if (boutonBack != null)				boutonBack.camMoveX(x);
		if (boutonTwitter != null)			boutonTwitter.camMoveX(x);
		if (boutonUpgradeOrRestart != null)	boutonUpgradeOrRestart.camMoveX(x);
		cam.position.x += x;
	}
	
	public static void effetBloom() {
		effetBloom = true;
		intensiteBloomOrigin = 50;
	}

	private void ui() {
		// ***************                   P   O   L   I   C   E                  ***************
		CSG.menuFontPetite.draw(batch, strScore, cam.position.x + X_CHRONO, HAUTEUR_POLICE);
		if (CSG.profile.manualBonus) {
			// ****  A F F I C H E R   S T O P  ****
			switch(nbBonusStop) {
			default :
//			case 3:	batch.draw(AssetMan.stopBonus, cam.position.x + X_CHRONO + Bonus.WIDTH * 2 + Bonus.HALF_WIDTH * 2, HAUTEUR_POLICE * 2, Bonus.WIDTH, Bonus.WIDTH);
			case 2:	batch.draw(AssetMan.stopBonus, cam.position.x + X_CHRONO + Bonus.WIDTH + Bonus.HALF_WIDTH, HAUTEUR_POLICE * 2, Bonus.WIDTH, Bonus.WIDTH);
			case 1:	batch.draw(AssetMan.stopBonus, cam.position.x + X_CHRONO, HAUTEUR_POLICE * 2, Bonus.WIDTH, Bonus.WIDTH);
			case 0:
			}
			switch(nbBombes) {
			default :
			case 3:	batch.draw(AssetMan.bomb, CSG.screenHalfWidth + cam.position.x + X_CHRONO + Bonus.WIDTH * 3 + Bonus.HALF_WIDTH * 3, Bonus.HALF_WIDTH, Bonus.WIDTH, Bonus.WIDTH);
			case 2:	batch.draw(AssetMan.bomb, CSG.screenHalfWidth + cam.position.x + X_CHRONO + Bonus.WIDTH * 2 + Bonus.HALF_WIDTH * 2, Bonus.HALF_WIDTH, Bonus.WIDTH, Bonus.WIDTH);
			case 1:	batch.draw(AssetMan.bomb, CSG.screenHalfWidth + cam.position.x + X_CHRONO + Bonus.WIDTH * 1 + Bonus.HALF_WIDTH * 1, Bonus.HALF_WIDTH, Bonus.WIDTH, Bonus.WIDTH);
			case 0:
			}
		}
	}

	private void affichageNonPerdu() {
		Bonus.drawAndMove(batch);
		ship.draw(batch);
		Enemy.affichageEtMouvement(batch);
		Particles.impacts(batch);
		Particles.draw(batch);
		Weapons.drawAndMove(batch);
		ui();
	}

	private void update() {
		if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.POWER) || Gdx.input.isKeyPressed(Keys.HOME) || Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			vientDEtreTouche = now;
			mettrePause();
		}
		if (!lost) {
			mouvement();
			if (alternate) {
				strScore = String.valueOf((int)score);
				Progression.invoqueBaseOnScore();
				if (!triggerStop) 			Physic.collisionsTest();
 			}
			if (!afficherMenuRadial)	ship.tir();
			alternate = !alternate;
		} else { // Donc si on a perdu
			if (Gdx.input.justTouched()) {
				if (boutonUpgradeOrRestart != null &&
						Physic.isPointInRect(Gdx.input.getX() + cam.position.x / 2, CSG.SCREEN_HEIGHT - Gdx.input.getY(), 
								0, boutonUpgradeOrRestart.sprite.getY() - Stats.U, CSG.gameZoneWidth, boutonUpgradeOrRestart.sprite.getHeight() + Stats.U * 2)) {
					boutonUpgradeOrRestart.click.onClick();
				}
				if (boutonTwitter != null &&
						Physic.isPointInRect(Gdx.input.getX() + cam.position.x / 2, CSG.SCREEN_HEIGHT - Gdx.input.getY(), 
								0, boutonTwitter.sprite.getY() - Stats.U, CSG.gameZoneWidth, boutonTwitter.sprite.getHeight() + Stats.U * 2)) {
					boutonTwitter.click.onClick();
				} else {
					init();
				}
			}
		}
	}

	public static void mouvement() {
		if (Gdx.input.justTouched())
			justeTouche();
		else {
			if (Gdx.input.isTouched())
				touche();
			else
				pasTouche();
		}	
		if (CSG.profile.typeControle == CSG.CONTROLE_ACCELEROMETRE & !afficherMenuRadial)
			ship.accelerometre();
	}

	private static void pasTouche() {
		boutonBack = null;
		if (afficherMenuRadial && CSG.profile.manualBonus)	{
			if (nbBonusStop > 0 && !triggerStop) batch.draw(AssetMan.stopBonus,(menuX - Bonus.DISPLAY_WIDTH) + (cam.position.x-CSG.screenHalfWidth) - Player.DEMI_LARGEUR, menuY, Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH);
			else batch.draw(AssetMan.stopBonusGrey,(menuX - Bonus.DISPLAY_WIDTH) + (cam.position.x-CSG.screenHalfWidth) - Player.DEMI_LARGEUR, menuY, Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH);
			
//			if (chronoRalentir > .01f) batch.draw(AssetMan.temps, menuX + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauJoueur.DEMI_LARGEUR, menuY + Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH,Bonus.DISPLAY_WIDTH);
//			else batch.draw(AssetMan.tempsGris, menuX + (cam.position.x-CSG.DEMI_LARGEUR_ECRAN) - VaisseauJoueur.DEMI_LARGEUR, menuY + Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH,Bonus.DISPLAY_WIDTH);
			
			if (nbBombes > 0) batch.draw(AssetMan.bomb, (menuX + Bonus.DISPLAY_WIDTH) + (cam.position.x-CSG.screenHalfWidth) - Player.DEMI_LARGEUR, menuY, Bonus.DISPLAY_WIDTH,Bonus.DISPLAY_WIDTH);
			else batch.draw(AssetMan.bombGrey, (menuX + Bonus.DISPLAY_WIDTH) + (cam.position.x-CSG.screenHalfWidth) - Player.DEMI_LARGEUR, menuY, Bonus.DISPLAY_WIDTH,Bonus.DISPLAY_WIDTH);
		} else if (!onAchoisis) {
			afficherMenuRadial = true;
			menuX = Gdx.input.getX();
			menuY = CSG.SCREEN_HEIGHT - Gdx.input.getY();
		}
	}

	private static void touche() {
		if (!onAchoisis) {
			if (Gdx.app.getVersion() == 0) clavier();
			if (CSG.profile.typeControle == CSG.CONTROLE_DPAD) afficherDPAD();  
			if (onVaStopper) {
				triggerStop = true;
				onVaStopper = false;
			}
		}
	}

	private static void justeTouche() {
		if (CSG.profile.typeControle == CSG.CONTROLE_DPAD) {
			Player.prevX = Gdx.input.getX();
			Player.prevY = Gdx.input.getY();
		}
		if (afficherMenuRadial) { 		// ---- SELECTION
			if (nbBonusStop > 0 && Physic.isPointInRect(Gdx.input.getX(), CSG.SCREEN_HEIGHT - Gdx.input.getY(), (menuX - Bonus.DISPLAY_WIDTH) - Player.DEMI_LARGEUR, menuY, Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH)) {
				onVaStopper = true;
				onAchoisis = true;
			} else if (nbBombes > 0 && Physic.isPointInRect(Gdx.input.getX(), CSG.SCREEN_HEIGHT - Gdx.input.getY(), (menuX + Bonus.DISPLAY_WIDTH) - Player.DEMI_LARGEUR, menuY, Bonus.DISPLAY_WIDTH,Bonus.DISPLAY_WIDTH)) {
				Enemy.bombe();
				nbBombes--;
				onAchoisis = true;
			}
			afficherMenuRadial = false;
		} else { 						// ---- REPRISE JEU
			onAchoisis = false;
		}
	}

	private static void afficherDPAD() {
		if (CSG.profile.typeControle == CSG.CONTROLE_DPAD && Gdx.input.isTouched()){
			//			F L E C H E   D R O I T E
			batch.draw(ARROW,(cam.position.x-CSG.screenHalfWidth) + Player.prevX + DEMI_LARGEUR_FLECHE, CSG.SCREEN_HEIGHT - (Player.prevY + DEMI_HAUTEUR_FLECHE),
					DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 0);
			//			F L E C H E   G A U C H E
			batch.draw(ARROW,(cam.position.x-CSG.screenHalfWidth) + Player.prevX - (LARGEUR_FLECHE+DEMI_LARGEUR_FLECHE), CSG.SCREEN_HEIGHT - (Player.prevY + DEMI_HAUTEUR_FLECHE),
					DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 180);
			//			F L E C H E   H A U T
			batch.draw(ARROW,(cam.position.x-CSG.screenHalfWidth) + Player.prevX - DEMI_LARGEUR_FLECHE, CSG.SCREEN_HEIGHT - (Player.prevY - DEMI_HAUTEUR_FLECHE), DEMI_LARGEUR_FLECHE,
					DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 90);
			//			F L E C H E   B A S
			batch.draw(ARROW,(cam.position.x-CSG.screenHalfWidth) + Player.prevX - DEMI_LARGEUR_FLECHE, CSG.SCREEN_HEIGHT - (Player.prevY + DEMI_HAUTEUR_FLECHE + HAUTEUR_FLECHE),
					DEMI_LARGEUR_FLECHE, DEMI_HAUTEUR_FLECHE, LARGEUR_FLECHE, HAUTEUR_FLECHE, 1, 1, 270);
		}
	}

	private static void clavier() {
		if (!afficherMenuRadial & !onAchoisis) 	ship.mouvements();
		if (Gdx.input.isKeyPressed(Keys.LEFT)) 	ship.mvtLimiteVitesse(-500, 0);
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) ship.mvtLimiteVitesse(500, 0);
		if (Gdx.input.isKeyPressed(Keys.UP)) 	ship.mvtLimiteVitesse(0, 500);
		if (Gdx.input.isKeyPressed(Keys.DOWN)) 	ship.mvtLimiteVitesse(0, -500);
//		if (Gdx.input.isKeyPressed(Keys.D) && tempsBonusStop > 0) triggerStop = true;
//		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) mettrePause();
	}

	@Override
	public void resize(int width, int height) {		CSG.profilManager.persist();	}

	@Override
	public void show() {	}

	@Override
	public void hide() { 		CSG.profilManager.persist(); }

	@Override
	public void pause() {	}

	@Override
	public void resume() { 		CSG.assetMan.reload(true);	}

	@Override
	public void dispose() {	}
	public static void addBonusStop() {
		if (CSG.profile.manualBonus) {
			nbBonusStop++;
			if (nbBonusStop > 2) {
				triggerStop = true;
			}
		} else {
			triggerStop = true;
		}
		tempsBonusStop = 3;
	}

	public static void ajoutBombe() {
		if (CSG.profile.manualBonus && nbBombes < 3)	nbBombes++;
		else 											Enemy.bombe();
	}
	
	private void afficherConseil(String s, TextureRegion tr, SpriteBatch batch) {
		CSG.menuFontPetite.draw(batch, s, ((cam.position.x-CSG.screenHalfWidth)) + ((CSG.screenHalfWidth - CSG.menuFontPetite.getBounds(s).width/2)),	
				CSG.halfHeight * 1.5f - CSG.menuFontPetite.getBounds(s).height * 4);
		batch.draw(tr, ((cam.position.x-CSG.screenHalfWidth) + CSG.screenHalfWidth) - Bonus.DISPLAY_WIDTH/2 ,
				CSG.halfHeight * 1.5f,
				Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH);
	}

	private void afficherConseil(String s) {
		CSG.menuFontPetite.draw(batch, s, ((cam.position.x-CSG.screenHalfWidth)) + ((CSG.screenHalfWidth - CSG.menuFontPetite.getBounds(s).width/2)),	CSG.halfHeight - CSG.menuFontPetite.getBounds(s).height * 4);
	}

	public static void lost() {
		 lost = true;
		 strScore = "Score : " + strScore;
	}

	public static boolean aPerdu() {
		return lost;
	}
	
	public static void reset() {
		Particles.clear();
		afficherMenuRadial = false;
		onAchoisis = false;
		ship = new Player();
		onVaStopper = false;
		menuX = (int) Player.POS.x;
		menuY = (int) Player.POS.y;
		triggerStop = false;
		pause = false;
	}

	public static void screenShake(int xp) {
		if (shake == false) {
			mvtTotalX = 0;
			mvtTotalY = 0;
		}
		shake = true;
		chronoShake += (float)xp / 50;
		if (chronoShake < SHAKE_MIN)
			chronoShake += SHAKE_MIN;
		else if (chronoShake > SHAKE_MAX)
			chronoShake = SHAKE_MAX;
	}

	public static Camera getCam() {
		return cam;
	}

	public static void rotate(Vector2 dir, float angle) {
		if (alternate)
			dir.rotate(angle);
	}
}
//	public static void mvtCamPositive(float x) {
//		if (EndlessMode.cam.position.x > CSG.screenHalfWidth) {
//			EndlessMode.cam.position.x += x;
//			x *= 0.15f;
//			Player.camXmoinsDemiEcran = EndlessMode.cam.position.x - CSG.screenHalfWidth;
//			Player.POS.x -= x; // On doit d'office decrementer de ce que la camera s'est déplacée pour ne pas additionner les deux vitesses !
//		}
//	}
//
//	public static void mvtCamNegative(float x) {
//		if (EndlessMode.cam.position.x < CSG.DEMI_CAMERA) {
//			EndlessMode.cam.position.x += x;
//			x *= 0.15f;
//			Player.camXmoinsDemiEcran = EndlessMode.cam.position.x - CSG.screenHalfWidth;
//			Player.POS.x -= x; // On doit d'office decrementer de ce que la camera s'est déplacée pour ne pas additionner les deux vitesses !
//		}
//	}
//	
//	public static void mvtCamSuivantDeplacement() {
//		EndlessMode.cam.position.x += EndlessMode.delta * Player.destX * 0.18f;
//		Player.camXmoinsDemiEcran = EndlessMode.cam.position.x - CSG.screenHalfWidth;
//		Player.destX -= EndlessMode.delta * Player.destX * 0.125f; // On doit d'office decrementer de ce que la camera s'est déplacée pour ne pas additionner les deux vitesses !
//	}


