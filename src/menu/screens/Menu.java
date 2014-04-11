package menu.screens;

import jeu.CSG;
import jeu.Strings;
import menu.tuto.OnClick;
import menu.ui.Bouton;
import assets.SoundMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import elements.generic.weapons.player.ArmeAdd;
import elements.particular.particles.Particles;

public class Menu extends AbstractScreen {

	private float temps = 0;
	private int etapeCode = 0;
	private Bouton highscores, achievements;
	private static final float ECART = 0.9f;
	private static final float 	LIGNE_PLAY = 1.9f, 
								LIGNE_SHIP = LIGNE_PLAY + ECART, 
								LIGNE_OPTION = LIGNE_SHIP + ECART, 
//								LIGNE_TUTO = LIGNE_OPTION + ECART, 
								LIGNE_HIGHSCORE = LIGNE_OPTION + ECART, 
								LIGNE_ACHIEVEMENT = LIGNE_HIGHSCORE + ECART, 
								LIGNE_SUPPORT = LIGNE_ACHIEVEMENT + ECART, 
								LIGNE_EXIT = LIGNE_SUPPORT + ECART * 2.5f;
//	private final Skin skin;
//    private final Stage stage;

	public Menu(Game game) {
		super(game);
		Particles.initBackground();
		setUpScreenElements();
		Gdx.graphics.setVSync(true);
	}

	public void setUpScreenElements() {
		ArmeAdd.determinerCadenceTir();
		temps = 0;
		Gdx.input.setCatchBackKey(false);

		ajout(new Bouton(PLAY, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / PADDING, (int) (CSG.SCREEN_HEIGHT - (CSG.HEIGHT_DIV10 * LIGNE_PLAY)), this, new OnClick() {
			public void onClick() {
				changeMenu(new ChoixDifficulte(game));
			}
		}, true));
		ajout(new Bouton(SHIP, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / PADDING, (int) (CSG.SCREEN_HEIGHT - (CSG.HEIGHT_DIV10 * LIGNE_SHIP)), this, new OnClick() {
			public void onClick() {
				changeMenu(new MenuXP(game));
			}
		}, true));
		ajout(new Bouton(OPTION, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / PADDING, (int) (CSG.SCREEN_HEIGHT - (CSG.HEIGHT_DIV10 * LIGNE_OPTION)), this, new OnClick() {
			public void onClick() {
				changeMenu(new MenuOptions(game));
			}
		}, true));
//		ajout(new Bouton(TUTO, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / PADDING, (int) (CSG.SCREEN_HEIGHT - (CSG.HEIGHT_DIV10 * LIGNE_TUTO)), this, new OnClick() {
//			public void onClick() {
//				changeMenu(new Tuto(game));
//			}
//		}, true));
		highscores = new Bouton(HIGHSCORE, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / PADDING, (int) (CSG.SCREEN_HEIGHT - (CSG.HEIGHT_DIV10 * LIGNE_HIGHSCORE)), this);
		ajout(highscores);

		achievements = new Bouton(ACHIEVEMENT, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / PADDING, (int) (CSG.SCREEN_HEIGHT - (CSG.HEIGHT_DIV10 * LIGNE_ACHIEVEMENT)), this);
		ajout(achievements);
		
		ajout(new Bouton(SUPPORT_US, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / PADDING, (int) (CSG.SCREEN_HEIGHT - (CSG.HEIGHT_DIV10 * LIGNE_SUPPORT)), this, new OnClick() {
			public void onClick() {
				CSG.google.buyUsABeer();
			}
		}, false));

		ajout(new Bouton(EXIT, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / PADDING, (int) (CSG.SCREEN_HEIGHT - (CSG.HEIGHT_DIV10 * LIGNE_EXIT)), this, new OnClick() {
			public void onClick() {
				Gdx.app.exit();
			}
		}, true));
		
		ajout(new Bouton(Strings.TWITTER, false, CSG.menuFontPetite, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON / 2, (int) (CSG.screenWidth - ((CSG.menuFontPetite.getBounds(Strings.TWITTER).width * 2)) - PADDING * 3), (int) (4 + CSG.menuFont.getBounds("T").height*2), this, new OnClick() {
			public void onClick() {
				CSG.google.followTwitter();
			}
		}, false));
		
		if (Gdx.app.getVersion() != 0)
			CSG.myRequestHandler.showAds(true);
	}

	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
		Gdx.app.exit();
		// getGame().setScreen(new EndlessMode(getGame(), batch, 1));
	}

	@Override
	public void render(float delta) {
		cam.update();
		CSG.batch.setProjectionMatrix(cam.combined);
		if (Gdx.input.isTouched()
				&& CSG.SCREEN_HEIGHT - Gdx.input.getY() > highscores.sprite.getY() 
				&& CSG.SCREEN_HEIGHT - Gdx.input.getY() < highscores.sprite.getY() + highscores.sprite.getHeight()) {
			if (CSG.google.getSignedIn())
				CSG.google.getScores();
			else
				CSG.google.Login();
		}
		if (Gdx.input.isTouched() 
				&& CSG.SCREEN_HEIGHT - Gdx.input.getY() > achievements.sprite.getY() 
				&& CSG.SCREEN_HEIGHT - Gdx.input.getY() < achievements.sprite.getY() + achievements.sprite.getHeight()) {
			if (CSG.google.getSignedIn())
				CSG.google.getAchievements();
			else
				CSG.google.Login();
		}
		temps += delta;
		detectiopnKonamiCode();
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.BACK) && temps > 2)
			Gdx.app.exit();
	}

	/**
	 * Il a besoin du batch pour afficher le jeu background en cas de code
	 * valide
	 * 
	 * @param batch
	 */
	private void detectiopnKonamiCode() {
		switch (etapeCode) {
		case 0:
		case 1:
			if (Gdx.input.justTouched() && Gdx.input.getY() < CSG.halfHeight)
				etapeCode++;
			break;
		case 2:
		case 3:
			if (Gdx.input.justTouched() && Gdx.input.getY() > CSG.halfHeight)
				etapeCode++;
			break;
		case 4:
		case 6:
			if (Gdx.input.justTouched() && Gdx.input.getX() < CSG.screenHalfWidth)
				etapeCode++;
			break;
		case 5:
		case 7:
			if (Gdx.input.justTouched() && Gdx.input.getX() > CSG.screenHalfWidth)
				etapeCode++;
			break;
		case 8:
//			EndlessMode.konamiCode = true;
			SoundMan.playBruitage(SoundMan.bigExplosion);
			if (CSG.profile.xpDispo < 55000)
				CSG.profile.xpDispo = 55000;
			CSG.profilManager.persist();
			etapeCode++;
//			CSG.assetMan.reload(true);
			CSG.alternateGraphics = true;
			break;
		}
	}

	@Override
	public void reset() {
		super.reset();
	}

}
