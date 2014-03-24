package menu.screens;

import menu.tuto.OnClick;
import menu.ui.Bouton;
import jeu.CSG;
import jeu.EndlessMode;
import jeu.Strings;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class ChoixDifficulte extends AbstractScreen{
	
	private final Game game;
	
	 public ChoixDifficulte(Game game) {
		 super(game);
		 this.game = game;
		 setUpScreenElements();
		 Gdx.input.setCatchBackKey(true);
	 }
	  
	 public void setUpScreenElements() {
		 ajout(boutonBack);
		// ** ** ** PIECE OF CAKE ** ** **
		final Bouton lvl1 = new Bouton(Strings.LVL1, false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.screenWidth / PADDING, CSG.SCREEN_HEIGHT - HAUTEUR_BOUTON * 5, this, new OnClick() {
					public void onClick() {
						changeMenu(new EndlessMode(game, CSG.batch, 1));
					}
				},true);
		ajout(lvl1);
		// ** ** **  LET'S ROCK  ** ** **
		final Bouton lvl2 = new Bouton(Strings.LVL2, false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.screenWidth / PADDING, CSG.SCREEN_HEIGHT - HAUTEUR_BOUTON * 8, this, new OnClick() {
					public void onClick() {
						changeMenu(new EndlessMode(game, CSG.batch, 2));
					}
				},true);
		ajout(lvl2);
		// ** ** **  COME GET SOME  ** ** **
		final Bouton lvl3 = new Bouton(Strings.LVL3, false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.screenWidth / PADDING, CSG.SCREEN_HEIGHT - HAUTEUR_BOUTON * 11, this, new OnClick() {
					public void onClick() {
						changeMenu(new EndlessMode(game, CSG.batch, 3));
					}
				},true);
		ajout(lvl3);
		if (Gdx.app.getVersion() != 0) {
			CSG.myRequestHandler.showAds(true);
		} 
		final Bouton lvl4 = new Bouton(Strings.LVL4, false, CSG.menuFont, LARGEUR_BOUTON,
				HAUTEUR_BOUTON, CSG.screenWidth / PADDING, CSG.SCREEN_HEIGHT - HAUTEUR_BOUTON * 14, this, new OnClick() {
			public void onClick() {
				changeMenu(new EndlessMode(game, CSG.batch, 4));
			}
		},true);
		ajout(lvl4);
	 }
	 
	 @Override
	public void render(float delta) {
		super.render(delta);
		if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			keyBackPressed();
		}
	}

	@Override
	public void keyBackPressed() {
		changeMenu(new Menu(game));
	}
}