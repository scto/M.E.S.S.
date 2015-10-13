package menu.screens;

import jeu.CSG;
import jeu.Strings;
import menu.tuto.OnClick;
import menu.ui.Button;
import assets.SoundMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import elements.generic.weapons.player.ArmeAdd;
import elements.particular.particles.Particles;

public class Menu extends AbstractScreen {

	private float temps = 0;
	private int etapeCode = 0;
	private Button highscores, achievements;
	private static final float ECART = 0.9f;
	private static final float 	LIGNE_PLAY = 1.9f, 
								LIGNE_SHIP = LIGNE_PLAY + ECART, 
								LIGNE_OPTION = LIGNE_SHIP + ECART, 
//								LIGNE_TUTO = LIGNE_OPTION + ECART, 
								LIGNE_HIGHSCORE = LIGNE_OPTION + ECART, 
								LIGNE_ACHIEVEMENT = LIGNE_HIGHSCORE + ECART, 
								LIGNE_SUPPORT = LIGNE_ACHIEVEMENT + ECART,
								LIGNE_OTHER_GAMES = LIGNE_SUPPORT + ECART,
								LIGNE_EXIT = LIGNE_SUPPORT + ECART * 3f;
//	private final Skin skin;
//    private final Stage stage;

	public Menu(Game game) {
		super(game);
		Particles.initBackground();
		setUpScreenElements();
	}

	public void setUpScreenElements() {
		ArmeAdd.determinerCadenceTir();
		temps = 0;
		Gdx.input.setCatchBackKey(false);

		ajout(new Button(PLAY, CSG.menuFont, BUTTON_WIDTH, BUTTON_HEIGHT, CSG.screenWidth / PADDING, (int) (CSG.height - (CSG.heightDiv10 * LIGNE_PLAY)), new OnClick() {
			public void onClick() {
				changeMenu(new ChoixDifficulte(game));
			}
		}));
		ajout(new Button(SHIP, CSG.menuFont, BUTTON_WIDTH, BUTTON_HEIGHT, CSG.screenWidth / PADDING, (int) (CSG.height - (CSG.heightDiv10 * LIGNE_SHIP)), new OnClick() {
			public void onClick() {
				changeMenu(new MenuXP(game));
			}
		}));
		ajout(new Button(OPTION, CSG.menuFont, BUTTON_WIDTH, BUTTON_HEIGHT, CSG.screenWidth / PADDING, (int) (CSG.height - (CSG.heightDiv10 * LIGNE_OPTION)), new OnClick() {
			public void onClick() {
				changeMenu(new MenuOptions(game));
			}
		}));

		highscores = new Button(HIGHSCORE, false, CSG.menuFont, BUTTON_WIDTH, BUTTON_HEIGHT, CSG.screenWidth / PADDING, (int) (CSG.height - (CSG.heightDiv10 * LIGNE_HIGHSCORE)));
		ajout(highscores);

		achievements = new Button(ACHIEVEMENT, false, CSG.menuFont, BUTTON_WIDTH, BUTTON_HEIGHT, CSG.screenWidth / PADDING, (int) (CSG.height - (CSG.heightDiv10 * LIGNE_ACHIEVEMENT)));
		ajout(achievements);
		
		ajout(new Button(SUPPORT_US, CSG.menuFont, BUTTON_WIDTH, BUTTON_HEIGHT, CSG.screenWidth / PADDING, (int) (CSG.height - (CSG.heightDiv10 * LIGNE_SUPPORT)), new OnClick() {
			public void onClick() {
				CSG.talkToTheWorld.buyUsABeer();
			}
		}));
		
		ajout(new Button("Other games", CSG.menuFont, BUTTON_WIDTH, BUTTON_HEIGHT, CSG.screenWidth / PADDING, (int) (CSG.height - (CSG.heightDiv10 * LIGNE_OTHER_GAMES)), new OnClick() {
			public void onClick() {
				CSG.talkToTheWorld.otherGames();
			}
		}));

		ajout(new Button(EXIT, CSG.menuFont, BUTTON_WIDTH, BUTTON_HEIGHT, CSG.screenWidth / PADDING, (int) (CSG.height - (CSG.heightDiv10 * LIGNE_EXIT)), new OnClick() {
			public void onClick() {
				Gdx.app.exit();
			}
		}));
		
		ajout(new Button(Strings.TWITTER, CSG.menuFontSmall, MINI_BOUTON_WIDTH, MINI_BOUTON_HEIGHT / 2, (int) (CSG.screenWidth - ((CSG.menuFontSmall.getBounds(Strings.TWITTER).width * 2)) - PADDING * 3), (int) (4 + CSG.menuFont.getBounds("T").height*2), new OnClick() {
			public void onClick() {
				CSG.talkToTheWorld.followTwitter();
			}
		}));
		
		if (Gdx.app.getVersion() != 0)
			CSG.talkToTheWorld.showAds(true);
	}

	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		Gdx.app.exit();
	}

	@Override
	public void render(float delta) {
		cam.update();
		CSG.batch.setProjectionMatrix(cam.combined);
		if (Gdx.input.isTouched() && CSG.height - Gdx.input.getY() > highscores.sprite.getY() && CSG.height - Gdx.input.getY() < highscores.sprite.getY() + highscores.sprite.getHeight())
			CSG.talkToTheWorld.getScores();
		
		if (Gdx.input.isTouched() && CSG.height - Gdx.input.getY() > achievements.sprite.getY() && CSG.height - Gdx.input.getY() < achievements.sprite.getY() + achievements.sprite.getHeight()) {
			if (CSG.talkToTheWorld.getSignedIn())		CSG.talkToTheWorld.getAchievements();
			else										CSG.talkToTheWorld.login();
		}
		temps += delta;
		etapeCode = detectiopnKonamiCode(etapeCode);
		if (etapeCode == 8) {
			SoundMan.playBruitage(SoundMan.bigExplosion);
			if (CSG.profile.xp < 55000)
				CSG.profile.xp = 55000;
			CSG.profilManager.persist();
			etapeCode++;
			CSG.alternateGraphics = true;
		}
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.BACK) && temps > 2)
			Gdx.app.exit();
	}

}
