package menu;

import objets.armes.joueur.ArmeAdd;
import jeu.EndlessMode;
import assets.AssetMan;
import assets.SoundMan;
import assets.particules.Particules;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.moribitotech.mtx.AbstractScreen;

public class Menu extends AbstractScreen {

	private float temps = 0;
	private float triggerClickLogin = 0;
	private int etapeCode = 0;
	private Bouton swarm;
	private static final float LIGNE_PLAY = 4, LIGNE_SHIP = 6, LIGNE_OPTION = 8, LIGNE_TUTO = 10, LIGNE_HIGHSCORE = 12, LIGNE_ACHIEVEMENT = 14, LIGNE_EXIT = 17;

	public Menu(Game game) {
		super(game);
		Particules.initBackground();
		setUpScreenElements();
		
		Gdx.graphics.setVSync(true);
	}

	public void setUpScreenElements() {
//		if (!CSG.google.getSignedIn())
//			CSG.google.Login();
		ArmeAdd.determinerCadenceTir();
		temps = 0;
		Gdx.input.setCatchBackKey(false);

		ajout(new Bouton(PLAY, false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, (int) (CSG.HAUTEUR_ECRAN
						- (HAUTEUR_BOUTON * LIGNE_PLAY)), this, new OnClick() {
					public void onClick() {
						ChoixDifficulte choix = new ChoixDifficulte(getGame());
						getGame().setScreen(choix);
					}
				}, true));
		ajout(new Bouton(SHIP, false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, (int) (CSG.HAUTEUR_ECRAN
						- (HAUTEUR_BOUTON * LIGNE_SHIP)), this, new OnClick() {
					public void onClick() {
						MenuXP choix = new MenuXP(getGame());
						getGame().setScreen(choix);
					}
				}, true));
		ajout(new Bouton(OPTION, false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, (int) (CSG.HAUTEUR_ECRAN
						- (HAUTEUR_BOUTON * LIGNE_OPTION)), this, new OnClick() {
					public void onClick() {
						MenuOptions choix = new MenuOptions(getGame());
						getGame().setScreen(choix);
					}
				}, true));
		ajout(new Bouton(TUTO, false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, (int) (CSG.HAUTEUR_ECRAN
						- (HAUTEUR_BOUTON * LIGNE_TUTO)), this, new OnClick() {
					public void onClick() {
						// Tutorial choix = new Tutorial(getGame());
						Tuto choix = new Tuto(getGame());
						getGame().setScreen(choix);
					}
				}, true));
		swarm = new Bouton(HIGHSCORE, false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, (int) (CSG.HAUTEUR_ECRAN
						- (HAUTEUR_BOUTON * LIGNE_HIGHSCORE)), this);
		ajout(swarm);
		
		ajout(new Bouton(ACHIEVEMENT, false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, (int) (CSG.HAUTEUR_ECRAN
						- (HAUTEUR_BOUTON * LIGNE_ACHIEVEMENT)), this));

		ajout(new Bouton(EXIT, false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.LARGEUR_ECRAN / PADDING, (int) (CSG.HAUTEUR_ECRAN
						- (HAUTEUR_BOUTON * LIGNE_EXIT)), this, new OnClick() {
					public void onClick() {
						Gdx.app.exit();
					}
				}, true));

		if (Gdx.app.getVersion() != 0)
			CSG.myRequestHandler.showAds(true);
	}

	@Override
	public void keyBackPressed() {
		super.keyBackPressed();
//		getGame().setScreen(new EndlessMode(getGame(), batch, 1));
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isTouched()
				&& CSG.HAUTEUR_ECRAN - Gdx.input.getY() > CSG.HAUTEUR_ECRAN
						- (HAUTEUR_BOUTON * LIGNE_HIGHSCORE)
				&& CSG.HAUTEUR_ECRAN - Gdx.input.getY() < ((CSG.HAUTEUR_ECRAN - (HAUTEUR_BOUTON * LIGNE_HIGHSCORE)) + HAUTEUR_BOUTON)) {
			if (triggerClickLogin < temps) {
				if (CSG.google.getSignedIn())
					CSG.google.getScores();
				else
					CSG.google.Login();
				triggerClickLogin = temps + 1;
			}
		}
		if (Gdx.input.isTouched()
				&& CSG.HAUTEUR_ECRAN - Gdx.input.getY() > CSG.HAUTEUR_ECRAN
				- (HAUTEUR_BOUTON * LIGNE_ACHIEVEMENT)
				&& CSG.HAUTEUR_ECRAN - Gdx.input.getY() < ((CSG.HAUTEUR_ECRAN - (HAUTEUR_BOUTON * LIGNE_ACHIEVEMENT)) + HAUTEUR_BOUTON)) {
			if (triggerClickLogin < temps) {
				if (CSG.google.getSignedIn())
					CSG.google.getAchievements();
				else
					CSG.google.Login();
				triggerClickLogin = temps + 1;
			}
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
			if (Gdx.input.justTouched()
					&& Gdx.input.getY() < CSG.DIXIEME_HAUTEUR * 2)
				etapeCode++;
			break;
		case 2:
		case 3:
			if (Gdx.input.justTouched()
					&& Gdx.input.getY() > CSG.HAUTEUR_ECRAN
							- CSG.DIXIEME_HAUTEUR)
				etapeCode++;
			break;
		case 4:
		case 6:
			if (Gdx.input.justTouched()
					&& Gdx.input.getX() < CSG.DEMI_LARGEUR_ECRAN)
				etapeCode++;
			break;
		case 5:
		case 7:
			if (Gdx.input.justTouched()
					&& Gdx.input.getX() > CSG.DEMI_LARGEUR_ECRAN)
				etapeCode++;
			break;
		case 8:
			EndlessMode.konamiCode = true;
			SoundMan.playBruitage(SoundMan.explosionGrosse);
			CSG.assetMan.reload(true);
			break;
		}
	}

	@Override
	public void reset() {
		super.reset();
		triggerClickLogin = 0;
	}

}
