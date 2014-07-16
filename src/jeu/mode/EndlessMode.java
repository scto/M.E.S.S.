package jeu.mode;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import jeu.Strings;
import jeu.mode.extensions.Buttons;
import jeu.mode.extensions.DesktopTests;
import jeu.mode.extensions.Score;
import jeu.mode.extensions.ScreenShake;
import jeu.mode.extensions.Transition;
import jeu.mode.extensions.Tutorial;
import shaders.Bloom;
import assets.AssetMan;
import assets.SoundMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import elements.generic.enemies.Enemy;
import elements.generic.enemies.Progression;
import elements.generic.weapons.Weapon;
import elements.particular.Player;
import elements.particular.bonuses.Bonus;
import elements.particular.bonuses.XP;
import elements.particular.other.WaveEffect;
import elements.particular.particles.Particles;

/**
 * Classe principale gerant le mode infini du jeu
 * @author Julien
 */
public class EndlessMode implements Screen {
	
	private Game game;
	private GL20 gl;
	private static SpriteBatch batch = CSG.batch;
	public static Bloom bloom = CSG.bloom;
	public static boolean alternate = true, pause = false, scoreSent = false, triggerStop = false, lost = false;
	private static Player ship;
	public static float now = 0;
	
	public static final int X_CHRONO = CSG.widthDiv10/2 - CSG.halfWidth;
	public static final int FONT_HEIGHT = CSG.heightDiv10/3;
	// *************************  J  A  U  G  E  *************************
	private final TextureRegion red;
	private static final int TIER_WIDTH_JAUGE =  CSG.width/18, HEIGHT_JAUGE = CSG.height/75;
	// *************************  D  P  A  D  ****************************

	public static final OrthographicCamera cam = new OrthographicCamera(CSG.width, CSG.height);
	public static int difficulty = 1, freezeBonus = 0, bombs = 0;
	public static float bloomOriginalIntensity = 1, delta = 0, timeStopBonus = 0, delta15 = 0, deltaDiv3, deltaDiv2, delta2, deltaU, deltaMicroU, UnPlusDelta, unPlusDelta2, unPlusDelta3, deltaPlusExplosion, delta25, delta4;
	public static boolean effetBloom = false, xpAjout = false, konamiCode = false;
	private static int menuX = 0, menuY = 0;
	private int advice = 0;
//	private static ShaderProgram shaderMort = ShaderMort.init(), originalShader;
	public static ShaderProgram originalShader;
	private static Matrix4 tmpCombined; 
	public static float originalDelta = 0, timeSinceLost = 0;
	public static int fps, perf = 3, explosions = 0;
	private boolean toastSent = false;
	private float justTouched = 0;
	public static final float STOP = 3;
	public static boolean invicibility = false, freeze = false, frameByFrame = false, invoque = true;
	private Tutorial tuto = new Tutorial();
	private static int cheat = CSG.NO_CHEAT;
	public static Transition transition = new Transition();
	public static int oneToFour = 1;
	private static boolean started = false;

	public EndlessMode(Game game, SpriteBatch batch, int level, int cheat) {
		super();
		Gdx.input.setCatchBackKey(true);
		EndlessMode.batch = batch;
		originalShader = SpriteBatch.createDefaultShader();
		this.game = game;
		ship = new Player();
		difficulty = level;
		init();
		ship.initialiser();
		gl = Gdx.graphics.getGL20();
		gl.glViewport(0, 0, CSG.width, CSG.height);
		red = AssetMan.getTextureRegion("rougefonce");
		bloom = CSG.bloom;
		EndlessMode.cheat = cheat;
	}

	public static void init() {
//		batch.setShader(originalShader);
		if (CSG.bloom != null && CSG.profile.bloom) {
			bloom.setBloomIntesity(CSG.profile.bloomIntensity);
		} else {
			CSG.profile.bloom = false;
		}
		ship.reInit(); // Pour remettre les positions mais garder shield et adds
		if (Gdx.app.getVersion() != 0)
			CSG.talkToTheWorld.showAds(false); // desactiver adds. A VIRER POUR LA RELEASE
		// ** DEPLACEMENT ZONE DE JEU
		cam.position.set(CSG.halfWidth, CSG.height / 2, 0);
		
		CSG.reset();
        scoreSent = false;
        xpAjout = false;
        pause = false;
        lost = false;
        Score.init();
		if (cheat == CSG.BEGIN_70K)
			Score.score = 70000;
        now = 0;
        timeSinceLost = 0;
        Enemy.clear();
        SoundMan.playMusic();
		timeStopBonus = 0;
		freezeBonus = 0;
		triggerStop = false;
		bombs = 0;
		Buttons.init();
		Weapon.clear();
		Progression.reset();
		cam.position.z = 1;
		if (difficulty == 1)
			Player.activateShield();
		Progression.reset();
		CSG.profilManager.persist();
//		getReady.reset();
//		started = false;
		transition.reset();
		Weapon.resetAll();
		Player.POS.set(CSG.halfWidth - Player.HALF_WIDTH, Player.HEIGHT * 7);
		started = false;
	}

	@Override
	public void render(float delta) {
		delta = Math.min(delta, 0.1f);
		if (!started) {
			if (Gdx.input.justTouched())
				started = true;
			delta = 0;
		}
		int cpt = 0;
		while (frameByFrame && cpt < 500000000) {
			cpt++;
			delta = 1f / 60f;
		}
		
		alternate();
		if (freeze) {
			delta = 0;
		}
		if ((Gdx.input.isTouched() || Gdx.input.justTouched()) && !lost)
			ship.mouvements();
		originalDelta = delta;
		if (Gdx.app.getVersion() == 0)
			DesktopTests.debug();
		cam();
		
		if (CSG.profile.bloom)
			bloomActive();
		else
			gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  //+10% de perfs !!. Si pas de bloom il faut le mettre
		
		explosions = 0;
		
		batch.begin();
		
		Particles.background(batch);
//		projet.act(batch);
		if (!pause && !freeze) {
			if (delta < 1) { 
				EndlessMode.delta = delta;
				if (!Gdx.input.isTouched())
					EndlessMode.delta = delta / 7;
				if (CSG.profile.isFirstTime()) 
					tuto.act(batch);
				majDeltas(Gdx.input.isTouched());
			}
//			CSG.world.step(EndlessMode.delta, 0, 0);
			// S I   O N   A   P A S   E N C O R E   P E R D U
			if (!lost && !triggerStop) {
				transition.act();
				affichageNonPerdu();
			} else {
				if (triggerStop) {
					affichageEtUpdateStop();
				} else {
//					batch.setShader(shaderMort);
					if (lost && !scoreSent && !konamiCode){
						if (CSG.profile.bloom)
							bloom.setBloomIntesity(-1f);
						final int monScore = Score.getRoundScore();
						switch (difficulty) {
						case 1:		CSG.talkToTheWorld.submitScore("CgkIrsqv7rIVEAIQKQ", monScore );	break;
						case 2:		CSG.talkToTheWorld.submitScore("CgkIrsqv7rIVEAIQKg", monScore );	break;
						case 3:		CSG.talkToTheWorld.submitScore("CgkIrsqv7rIVEAIQKw", monScore );	break;
						case 4:		CSG.talkToTheWorld.submitScore(Strings.LVL4LB, monScore );			break;
						}
						scoreSent = true;
					}
				}
				affichagePerdu();
				if (!triggerStop && lost) drawFinalScoreAndAdvices(batch);
			}					 
			update();
		} else { // D O N C   E N   P A U S E
			if (Gdx.input.isKeyPressed(Keys.BACK) && (now > justTouched + .1)) {
				Buttons.goToMenu(game);
			}
			affichagePerdu();
			Buttons.backButton(batch, game);
			if (Gdx.input.justTouched()) {
				pause = false;
				justTouched = now;
			}
		}
		WaveEffect.draw(batch);
		if (triggerStop)
			stopActivated();
		if (CSG.profile.isFirstTime()) 
			tuto.act(batch);
//		if (!started) {
//			getReady.act(batch, true);
//			if (Gdx.input.isTouched())
//				started = true;
//		}
		Particles.foreground(batch);
		batch.end();
		if (CSG.profile.bloom)
			bloom.render();
		
		now += EndlessMode.delta;
		ScreenShake.act();
		Enemy.deadBodiesEverywhere();
		fps = Gdx.graphics.getFramesPerSecond();
		perf = fps / 10;
		deltaPlusExplosion = EndlessMode.delta + explosions;
	}

	private void alternate() {
		alternate = !alternate;
		if (++oneToFour > 4)
			oneToFour = 1;
	}

	private void stopActivated() {
		ship.draw(batch);
	}

	public static void cam() {
		cam.update();
		tmpCombined = cam.combined;
		if (tmpCombined != null) {
			batch.setProjectionMatrix(tmpCombined);
//			CSG.rayHandler.setCombinedMatrix(cam.combined);
		}
	}

	public static void majDeltas(boolean touched) {
		deltaPlusExplosion = EndlessMode.delta + explosions;
		if (!touched)
			delta /= 5;
		delta15 = delta * 15;
		deltaDiv3 = delta / 3;
		deltaDiv2 = delta / 2;
		delta2 = delta * 2;
		deltaU = delta * Stats.U;
		deltaMicroU =  Stats.uDiv75 * delta;
		UnPlusDelta = 1 + delta;
		unPlusDelta2 = 1 + delta2;
		unPlusDelta3 = UnPlusDelta + delta2;
		delta25 = delta * 25;
		delta4 = delta * 4;
	}

	private void affichageEtUpdateStop() {
		timeStopBonus -= delta;
		batch.draw(red, (Player.POS.x + Player.HALF_WIDTH) - (TIER_WIDTH_JAUGE * timeStopBonus)/2, Player.POS.y - HEIGHT_JAUGE * 3, TIER_WIDTH_JAUGE * timeStopBonus, HEIGHT_JAUGE);
		if (timeStopBonus < 0) {
			triggerStop = false;
			transition.activate(20);
			if (freezeBonus > 0)
				timeStopBonus += STOP;
		}
	}

	private void bloomActive() {
		if (triggerStop) {
			bloom.setBloomIntesity(CSG.profile.bloomIntensity + (timeStopBonus*2));
		} else {
			ScreenShake.bloomEffect();
		}
		bloom.capture();
	}

	private static void mettrePause() {
		pause = true;
		CSG.profilManager.persist();
	}
	
	private void drawFinalScoreAndAdvices(SpriteBatch batch) {
		timeSinceLost += originalDelta;
		
		if (timeSinceLost < 0.7f)
			return;
		if (Gdx.app.getVersion() != 0)
			CSG.talkToTheWorld.showAds(true);
		
		if (!xpAjout && !konamiCode) {
			CSG.profile.addXp(Score.getRoundScore());
			xpAjout = true;
		}
//		batch.setShader(originalShader);
		Buttons.initAndDrawButtons(batch);
		
		if (Score.score < 1000) {
			if (advice == 0)
				advice = (int) (1 + Math.random() * 5);
			switch (4) {
				case 3:		afficherConseil(Strings.ADVICE3);								break;
				case 4:		afficherConseil(Strings.ADVICE4, AssetMan.bomb, batch);			break;
				case 5:		afficherConseil(Strings.ADVICE5, AssetMan.stopBonus, batch);	break;
			}
		}
		float width = CSG.menuFont.getBounds(Score.strScore).width;
		if (width < CSG.menuFont.getBounds(Score.strScore).width) {
			width = CSG.menuFont.getBounds(Score.strScore).width;
		}
		batch.setColor(AssetMan.BLACK);
		batch.draw(AssetMan.dust, 0, CSG.halfHeight - CSG.menuFont.getBounds(Score.strScore).height*2, CSG.width, CSG.menuFont.getBounds(Score.strScore).height * 6);
		batch.setColor(AssetMan.WHITE);
		CSG.menuFont.draw(batch, Strings.DEAD, ((cam.position.x-CSG.halfWidth)) + ((CSG.halfWidth - (CSG.menuFont.getBounds(Strings.DEAD).width)/2)),
				CSG.halfHeight + CSG.menuFontSmall.getBounds(Strings.DEAD).height * 3);
		
		CSG.menuFont.draw(batch, Score.strScore, ((cam.position.x-CSG.halfWidth)) + ((CSG.halfWidth - (CSG.menuFont.getBounds(Score.strScore).width)/2)),
				CSG.halfHeight);
		
		
		Buttons.drawUpgradeAndTwitter(batch);
	}

	private float prevDelta;
	private void affichagePerdu() {
		prevDelta = delta;
		delta = 0;
		stopActivated();
		Enemy.draw(batch);
		delta = prevDelta;
		Particles.draw(batch);
		if (!lost)
			XP.act(Bonus.XP_LIST, batch);
		Particles.drawImpacts(batch);
		Weapon.affichage(batch);
		ui();
	}

	public static void effetBloom() {
		effetBloom = true;
		bloomOriginalIntensity = 50;
	}

	private void ui() {
		Score.draw(batch, lost);
		if (CSG.profile.manualBonus) {
			System.out.println(" nb stop : " + freezeBonus);
			System.out.println(" nb bomb : " + bombs);
			switch(freezeBonus) {
			case 2:	batch.draw(AssetMan.stopBonus, cam.position.x + X_CHRONO + Bonus.WIDTH + Bonus.HALF_WIDTH, FONT_HEIGHT * 2, Bonus.WIDTH, Bonus.WIDTH);
			case 1:	batch.draw(AssetMan.stopBonus, cam.position.x + X_CHRONO, FONT_HEIGHT * 2, Bonus.WIDTH, Bonus.WIDTH);
			case 0:
			}
			switch(bombs) {
			case 3:	batch.draw(AssetMan.bomb, CSG.halfWidth + cam.position.x + X_CHRONO + Bonus.WIDTH * 3 + Bonus.HALF_WIDTH * 3, Bonus.HALF_WIDTH, Bonus.WIDTH, Bonus.WIDTH);
			case 2:	batch.draw(AssetMan.bomb, CSG.halfWidth + cam.position.x + X_CHRONO + Bonus.WIDTH * 2 + Bonus.HALF_WIDTH * 2, Bonus.HALF_WIDTH, Bonus.WIDTH, Bonus.WIDTH);
			case 1:	batch.draw(AssetMan.bomb, CSG.halfWidth + cam.position.x + X_CHRONO + Bonus.WIDTH * 1 + Bonus.HALF_WIDTH * 1, Bonus.HALF_WIDTH, Bonus.WIDTH, Bonus.WIDTH);
			case 0:
			}
		}
	}

	private void affichageNonPerdu() {
		Bonus.drawAndMove(batch);
		stopActivated();
		Enemy.affichageEtMouvement(batch);
		Particles.drawImpacts(batch);
		Particles.draw(batch);
		Weapon.drawAndMove(batch);
		ui();
	}

	private void update() {
		if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.POWER) || Gdx.input.isKeyPressed(Keys.HOME) || Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			justTouched = now;
			mettrePause();
		}
		if (!lost) {
			mouvement();
			if (alternate) {
				if (invoque)
					Progression.invoqueBaseOnScore();
				if (!triggerStop)
					Physic.collisionsTest();
				Score.act(now, lost, triggerStop);
 			}
			if (!freeze)	
				ship.shot();
		} else { // Donc si on a perdu
			if (toastSent) {
				CSG.talkToTheWorld.toast("You might want to check the highscore menu !");
				toastSent = false;
			}
			Buttons.testClick();
		}
	}

	public static void mouvement() {
		if (Gdx.input.justTouched())
			justeTouche();
		else if (Gdx.input.isTouched()) {
			menuX = Gdx.input.getX();
			menuY = CSG.height - Gdx.input.getY();
		} else 
			pasTouche();
	}

	private static void pasTouche() {
		Buttons.removeBack();
		if (CSG.profile.manualBonus)	{
			if (freezeBonus > 0 && !triggerStop) batch.draw(AssetMan.stopBonus,(menuX - Bonus.DISPLAY_WIDTH) + (cam.position.x-CSG.halfWidth) - Player.HALF_WIDTH, menuY, Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH);
			else batch.draw(AssetMan.stopBonusGrey,(menuX - Bonus.DISPLAY_WIDTH) + (cam.position.x-CSG.halfWidth) - Player.HALF_WIDTH, menuY, Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH);
			
			if (bombs > 0) batch.draw(AssetMan.bomb, (menuX + Bonus.DISPLAY_WIDTH) + (cam.position.x-CSG.halfWidth) - Player.HALF_WIDTH, menuY, Bonus.DISPLAY_WIDTH,Bonus.DISPLAY_WIDTH);
			else batch.draw(AssetMan.bombGrey, (menuX + Bonus.DISPLAY_WIDTH) + (cam.position.x-CSG.halfWidth) - Player.HALF_WIDTH, menuY, Bonus.DISPLAY_WIDTH,Bonus.DISPLAY_WIDTH);
		}
	}

	private static void activateStop() {
		SoundMan.halfVolume();
		triggerStop = true;
		Particles.addChronoGenerator();
		WaveEffect.add(Player.xCenter, Player.yCenter, AssetMan.GREEN);
	}

	private static void justeTouche() {
		if (freezeBonus > 0 && Physic.isPointInRect(Gdx.input.getX(), CSG.height - Gdx.input.getY(), (menuX - Bonus.DISPLAY_WIDTH) - Player.HALF_WIDTH, menuY, Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH)) {
			activateStop();
			freezeBonus--;
		} else if (bombs > 0 && Physic.isPointInRect(Gdx.input.getX(), CSG.height - Gdx.input.getY(), (menuX + Bonus.DISPLAY_WIDTH) - Player.HALF_WIDTH, menuY, Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH)) {
			Enemy.bombe();
			bombs--;
		}
	}

	@Override
	public void resize(int width, int height) {
		CSG.profilManager.persist();
	}

	@Override	public void show() {	}
	@Override	public void hide() {		CSG.profilManager.persist();	}
	@Override	public void pause() {	}
	@Override	public void resume() {		CSG.assetMan.reload();	}
	@Override	public void dispose() {	}

	public static void addBonusStop() {
		if (CSG.profile.manualBonus) {
			freezeBonus++;
			if (freezeBonus > 2) {
				freezeBonus = 2;
				activateStop();
			}
		} else {
			activateStop();
		}
		timeStopBonus = STOP;
	}

	public static void ajoutBombe() {
		if (CSG.profile.manualBonus && bombs < 3)	bombs++;
		else 											Enemy.bombe();
	}
	
	private void afficherConseil(String s, TextureRegion tr, SpriteBatch batch) {
		CSG.menuFontSmall.draw(batch, s, ((cam.position.x-CSG.halfWidth)) + ((CSG.halfWidth - CSG.menuFontSmall.getBounds(s).width/2)),	
				CSG.halfHeight * 1.5f - CSG.menuFontSmall.getBounds(s).height * 4);
		batch.draw(tr, ((cam.position.x-CSG.halfWidth) + CSG.halfWidth) - Bonus.DISPLAY_WIDTH/2 ,
				CSG.halfHeight * 1.5f,
				Bonus.DISPLAY_WIDTH, Bonus.DISPLAY_WIDTH);
	}

	private void afficherConseil(String s) {
		CSG.menuFontSmall.draw(batch, s, ((cam.position.x-CSG.halfWidth)) + ((CSG.halfWidth - CSG.menuFontSmall.getBounds(s).width/2)),	CSG.halfHeight - CSG.menuFontSmall.getBounds(s).height * 4);
	}

	public static void lost() {
		if (!invicibility) {
			lost = true;
			Score.lost(!konamiCode);
			timeSinceLost = 0;
		}
	}

	public static boolean aPerdu() {
		return lost;
	}
	
	public static void reset() {
		Particles.clear();
		ship = new Player();
		menuX = (int) Player.POS.x;
		menuY = (int) Player.POS.y;
		triggerStop = false;
		pause = false;
	}

	public static Camera getCam() {
		return cam;
	}

	public static void rotate(Vector2 dir, float angle) {
		if (alternate)
			dir.rotate(angle);
	}

}


